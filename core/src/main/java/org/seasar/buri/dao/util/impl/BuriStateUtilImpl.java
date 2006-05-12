/*
 * çÏê¨ì˙: 2005/07/07
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.context.BuriInnerContext;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dto.BuriBranchEntityDto;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;


public class BuriStateUtilImpl implements BuriStateUtil {
    private BuriDataDaoUtil dataDaoUtil;
    private BuriPathDaoUtil pathDaoUtil;
    private BuriPathDao pathDao;
    private BuriStateDao stateDao;
    private BuriBranchDao branchDao;
    private ContextUtil contextUtil;
    private WorkFlowsUtil flowsUtil;
    
    
    public BuriStateEntityDto readOnlyStateDto(BuriPath path,long branchID) {
        BuriLocalContext context = contextUtil.getLocalContext();
        pathDaoUtil.setup(path);
        dataDaoUtil.readBuriDataID(path);
        if(context.getDataID().longValue()==0) {
            dataDaoUtil.createOnlyBuriDataID(path);
        }
        BuriStateEntityDto startEntityDto = getStartStateEntity(path);
        return startEntityDto;
    }
    
    public BuriStateEntityDto readStateDto(BuriPath path,long branchID) {
        BuriStateEntityDto startEntityDto = readOnlyStateDto(path,branchID);
        if(startEntityDto==null) {
            startEntityDto = createNewStateDto(path,branchID,null);
        }
        return startEntityDto;
    }
    
    public BuriStateEntityDto createNewStateDto(BuriPath path,long branchID,Date limitDate) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriStateEntityDto startEntityDto = new BuriStateEntityDto();
        startEntityDto.setPathID(new Long(path.getBuriPathID()));
        startEntityDto.setDataID(context.getDataID());
        BuriParticipant participant = context.getBuriParticipant();
        if(participant != null) {
            startEntityDto.setUserIDVal(participant.getUserIDVal());
            startEntityDto.setUserIDNum(participant.getUserIDNum());
        }
        if(branchID!=0) {
            startEntityDto.setBranchID(new Long(branchID));
        }
        startEntityDto.setInsertDate(new Date());
        if(limitDate!=null) {
            startEntityDto.setAutoRunTime(limitDate);
        }
        createNewBuriStateID(startEntityDto);
        return startEntityDto;
    }
    
    protected void createNewBuriStateID(BuriStateEntityDto startEntityDto) {
        startEntityDto.setStateID(stateDao.getNewBuriStateID());
    }
    
    public void updateStateDto(BuriStateEntityDto startEntityDto,Collection savePaths,long branchID) {
        if(savePaths == null) {
            savePaths = new ArrayList();
        }
//        stopBuriState(startEntityDto);
        Iterator ite = savePaths.iterator();
        while(ite.hasNext()) {
            BuriPath path = (BuriPath)ite.next();
            pathDaoUtil.setup(path);
            Date limitDate = getLimitDate(path);
            BuriStateEntityDto entityDto = createNewStateDto(path,branchID,limitDate);
            insertStateDto(entityDto);
        }
    }
    
    protected Date getLimitDate(BuriPath path) {
        BuriInnerContext context = contextUtil.getContext();
        ActivityTagSelect tagSelect = flowsUtil.getActivity(path);
        String limitOgnl = tagSelect.getActivity().getLimit();
        if(org.seasar.framework.util.StringUtil.isEmpty(limitOgnl)) {
            return null;
        }
        ScriptProcessor processor = new ScriptProcessor();
        Object result = processor.getValue(limitOgnl,contextUtil.getLocalContext().getContainer(),context);
        Date resultDate = null;
        if(result instanceof Date) {
            resultDate = (Date)result;
        } else if(result instanceof Calendar) {
            resultDate = ((Calendar)result).getTime();
        }
        return resultDate;
    }

    protected void insertStateDto(BuriStateEntityDto entityDto) {
        stateDao.insert(entityDto);
    }
    
    protected BuriStateEntityDto getStartStateEntity(BuriPath startPath) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriStateEntityDto stateEntityDto = null;
        if(startPath != null) {
            stateEntityDto = stateDao.getBuriStateByPathAndData(startPath.getBuriPathID(),context.getDataID().longValue());
        }
        return stateEntityDto;
    }

    public List getStatusPath(BuriPath tgtPath) {
        BuriLocalContext context = contextUtil.getLocalContext();
        dataDaoUtil.readBuriDataID(tgtPath);
        List result = pathDao.getBuriPathByDataID(context.getDataID().longValue());
        return result;
    }
    
    public void processedJoinXor(BuriPath path) {
        pathDaoUtil.setup(path);
        long dataID = dataDaoUtil.readBuriDataID(path);
        BuriStateEntityDto stateEntityDto =  stateDao.getAllBuriStateByPathAndData(path.getBuriPathID(),dataID);

        Long parentBranchID = getXorJoinBuriStatesID(path);
        List branchDtos = branchDao.getBuriParentBranch(parentBranchID.longValue());
        Iterator ite = branchDtos.iterator();
        while(ite.hasNext()) {
            BuriBranchEntityDto entityDto = (BuriBranchEntityDto)ite.next();
            abortBuriState(entityDto.getBranchID(),stateEntityDto.getStateID());
        }
        stopBuriState(stateEntityDto);
    }
    
    protected Long getXorJoinBuriStatesID(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        Long currentBranchID = new Long(0);
        if( ! hasBranchIDContextKey()) {
            currentBranchID = getCurrentBranchID(path);
        } else {
            currentBranchID = context.getBranchIDContextKey();
        }
        if(currentBranchID.longValue()==0) {
            return currentBranchID;
        }
        BuriBranchEntityDto branchEntityDto = branchDao.getBuriBranch(currentBranchID.longValue());
        Long parentBranchID = branchEntityDto.getParentBranchID();
        if(parentBranchID == null) {
            parentBranchID = currentBranchID;
        }
        context.setBranchIDContextKey(parentBranchID);
        return parentBranchID;
    }
    
    protected boolean hasBranchIDContextKey() {
        BuriLocalContext context = contextUtil.getLocalContext();
        if(context.getBranchIDContextKey().longValue() != 0) {
            return true;
        }
        return false;
    }
    
    protected Long getCurrentBranchID(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        pathDaoUtil.setup(path);
        dataDaoUtil.readBuriDataID(path);
        BuriStateEntityDto stateEntityDto = stateDao.getAllBuriStateByPathAndData(path.getBuriPathID(),context.getDataID().longValue());
        long branchID = 0;
        if(stateEntityDto != null && stateEntityDto.getBranchID() != null) {
            branchID = stateEntityDto.getBranchID().longValue();
        }
        return new Long(branchID);
    }
    
    protected Long getAndJoinBuriStatesID(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        Long currentBranchID = new Long(0);
        if( ! hasBranchIDContextKey()) {
            currentBranchID = getCurrentBranchID(path);
        } else {
            currentBranchID = context.getBranchIDContextKey();
        }
        if(currentBranchID.longValue()==0) {
            return new Long(0);
        }
        BuriBranchEntityDto branchEntityDto = branchDao.getBuriBranch(currentBranchID.longValue());
        Long branchID = branchEntityDto.getParentBranchID();
        context.setBranchIDContextKey(branchID);
        return branchID;
    }
    
    public boolean canAndJoin(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        Long prevBranchID = new Long(0);
        if(hasBranchIDContextKey()) {
            prevBranchID = context.getBranchIDContextKey();
        }
        Long parentBranchID = getAndJoinBuriStatesID(path);
        if(parentBranchID == null) {
            return true;
        }
        long count = countNotBuriState(parentBranchID.longValue(),0);

        if(count==0) {
            return true;
        } else {
            context.setBranchIDContextKey(prevBranchID);
            return false;
        }
    }
    
    protected long countNotBuriState(long parentBranchID,long count) {
        List branchDtos = branchDao.getBuriParentBranch(parentBranchID);
        Iterator ite = branchDtos.iterator();
        while(ite.hasNext()) {
            BuriBranchEntityDto entityDto = (BuriBranchEntityDto)ite.next();
            long stateCount = stateDao.countByBranchIDAndNotProcessed(entityDto.getBranchID());
            count = count + stateCount;
            count = countNotBuriState(entityDto.getBranchID(),count);
        }
        return count;
    }

    
    protected void abortBuriState(long branchID,long savingStateID) {
        List branchDtos = branchDao.getBuriParentBranch(branchID);
        stateDao.updateAbortByBranchID(branchID,savingStateID);
        Iterator ite = branchDtos.iterator();
        while(ite.hasNext()) {
            BuriBranchEntityDto entityDto = (BuriBranchEntityDto)ite.next();
            abortBuriState(entityDto.getBranchID(),savingStateID);
        }
    }
        
    public void stopBuriState(BuriStateEntityDto stateEntityDto) {
        if(stateEntityDto == null) {
            return;
        }
        stateEntityDto.setProcessDate(new Date());
        BuriStateEntityDto readEntityDto = stateDao.getBuriState(stateEntityDto.getStateID());
        if(readEntityDto == null) {
            stateDao.insert(stateEntityDto);
        } else {
//            readEntityDto.setProcessDate(new Date());
            stateDao.updateProcessedByBranchID(stateEntityDto.getStateID());
        }
    }

    public BuriBranchDao getBranchDao() {
        return branchDao;
    }
    public void setBranchDao(BuriBranchDao branchDao) {
        this.branchDao = branchDao;
    }
    public BuriDataDaoUtil getDataDaoUtil() {
        return dataDaoUtil;
    }
    public void setDataDaoUtil(BuriDataDaoUtil dataDaoUtil) {
        this.dataDaoUtil = dataDaoUtil;
    }
    public BuriPathDao getPathDao() {
        return pathDao;
    }
    public void setPathDao(BuriPathDao pathDao) {
        this.pathDao = pathDao;
    }
    public BuriPathDaoUtil getPathDaoUtil() {
        return pathDaoUtil;
    }
    public void setPathDaoUtil(BuriPathDaoUtil pathDaoUtil) {
        this.pathDaoUtil = pathDaoUtil;
    }
    public BuriStateDao getStateDao() {
        return stateDao;
    }
    public void setStateDao(BuriStateDao stateDao) {
        this.stateDao = stateDao;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }
}
