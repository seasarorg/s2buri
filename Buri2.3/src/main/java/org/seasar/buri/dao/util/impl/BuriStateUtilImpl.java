/*
 * 作成日: 2006/05/10
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.util.BTIDUtil;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dao.util.BuriUndoLogUtil;
import org.seasar.buri.dto.BuriBranchEntityDto;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriRealPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.exception.IllegalArgumentRuntimeException;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.util.StringUtil;

public class BuriStateUtilImpl implements BuriStateUtil {
    private BuriDataUtil dataUtil;
    private BuriPathUtil pathUtil;
    private BuriStateDao stateDao;
    private BuriBranchDao branchDao;
    private BuriUndoLogUtil undoLogUtil;
    private BTIDUtil btidUtil;
    private ScriptFactory scriptFactory;
    
    public BranchWalker createBranchWalker(BuriSystemContext sysContext) {
        BranchWalker walker = new BranchWalker();
        walker.setBranchID(0);
        walker.setParentBranchID(0);
        walker.setParentPath(sysContext.getCallPath().moveUpPath());
        walker.setNowPath(null);
        return walker;
    }
    
    public BranchWalker loadBranchWalker(BuriSystemContext sysContext) {
        if(sysContext.getStatusID()==null || sysContext.getStatusID().longValue() == 0) {
            return createBranchWalker(sysContext);
        }
        BuriStateEntityDto stateDto = stateDao.getBuriState(sysContext.getStatusID().longValue());
        if(stateDto == null || stateDto.getBranchID()==null) {
            return createBranchWalker(sysContext);
        }
        return loadBranchWalker(sysContext,stateDto);
    }
    
    private BranchWalker loadBranchWalker(BuriSystemContext sysContext,BuriStateEntityDto stateDto) {
        BuriBranchEntityDto branchDto = branchDao.select(stateDto.getBranchID().longValue());
        if(branchDto==null) {
            return createBranchWalker(sysContext);
        } else {
            return loadBranchWalker(sysContext,stateDto,branchDto);
        }
    }
    
    private BranchWalker loadBranchWalker(BuriSystemContext sysContext,BuriStateEntityDto stateDto,BuriBranchEntityDto branchDto) {
        BranchWalker walker = new BranchWalker();
        walker.setBranchID(branchDto.getBranchID());
        walker.setParentBranchID(branchDto.getParentBranchID().longValue());
        walker.setParentPath(sysContext.getCallPath().moveUpPath());
        BuriPath branchPath = pathUtil.getBuriPathFromRealPath(pathUtil.getBuriPathByID(stateDto.getPathID().longValue()));
        walker.setNowPath(branchPath);
        return walker;
    }
    
    public void saveBranch(BranchWalker walker,DataAccessFactory factory,BuriSystemContext sysContext) {
        if(walker.getBranchID() != 0) {
            return;
        }
        BuriBranchEntityDto branchDto = new BuriBranchEntityDto();
        branchDto.setBTID(btidUtil.getCurrentBTID());
        long dataID = dataUtil.getBuriDataId(factory,sysContext);
        branchDto.setDataID(new Long(dataID));
        branchDto.setParentBranchID(new Long(walker.getParentBranchID()));
        branchDao.insert(branchDto);
        walker.setBranchID(branchDto.getBranchID());
    }
    
    public BranchWalker branchChild(BranchWalker parentWalker,DataAccessFactory factory,BuriSystemContext sysContext) {
        BranchWalker walker = new BranchWalker();
        walker.setParentBranchID(parentWalker.getBranchID());
        walker.setParentPath(parentWalker.getParentPath());
        return walker;
    }
    
    public BranchWalker getNowPathBranchWalker(DataAccessFactory factory,BuriSystemContext sysContext,BuriPath callPath) {
        long dataID = dataUtil.getBuriDataId(factory,sysContext);
        BuriPath path = pathUtil.getBuriPathFromRealPath(callPath);
        BuriStateEntityDto stateDto = stateDao.getBuriStateByPathAndData(path.getBuriPathID(),dataID);
        if(stateDto ==null) {
            return null;
        }
        return getNowPathBranchWalker(path,stateDto,sysContext);
    }
    
    private BranchWalker getNowPathBranchWalker(BuriPath path,BuriStateEntityDto stateDto,BuriSystemContext sysContext) {
        BranchWalker walker = new BranchWalker();
        walker.setBranchID(stateDto.getBranchID().longValue());
        walker.setNowPath(path);
        walker.setParentPath(sysContext.getCallPath());
        BuriBranchEntityDto branchDto = branchDao.select(stateDto.getBranchID().longValue());
        if(branchDto != null) { // 本当は状態と一緒に保存しているはずなので不要なハズ
            assert branchDto != null;
            assert branchDto.getParentBranchID() != null;
            walker.setParentBranchID(branchDto.getParentBranchID().longValue());
        }
        return walker;
    }
    
    public BranchWalker createBranch(BranchWalker nowWalker,BuriPath parentPath,BuriSystemContext sysContext,String pathName,String PathId) {
        BuriPath tgtPath = parentPath.moveChildPath(pathName,PathId);
        BuriPath path = pathUtil.getBuriPathFromRealPath(tgtPath);
        BranchWalker walker = new BranchWalker();
        walker.setParentPath(parentPath);
        walker.setParentBranchID(nowWalker.getBranchID());
        BuriBranchEntityDto dto = new BuriBranchEntityDto();
        dto.setDataID(sysContext.getDataID());
        dto.setParentBranchID(new Long(nowWalker.getBranchID()));
        dto.setProcessDate(new Date());
        dto.setPathID(new Long(path.getBuriPathID()));
        branchDao.insert(dto);
        walker.setBranchID(dto.getBranchID());
        walker.setNowPath(tgtPath);
        return walker;
    }
    
    public long loadStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        BuriPath path = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
        long dataID = dataUtil.getBuriDataId(factory,sysContext);
        if(dataID==0) {
            return 0;
        }
        BuriStateEntityDto dto = stateDao.getBuriStateByPathAndData(path.getBuriPathID(),dataID);
        if(dto!=null) {
            return dto.getStateID();
        }
        return 0;
    }
    
    public long saveStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        dataUtil.storeData(factory,sysContext);
        dataUtil.updateBuriData(factory,sysContext);
        BuriStateEntityDto stateDto = createStateDto(factory,sysContext,walker);
        stateDao.insert(stateDto);
        return stateDto.getStateID();
    }
    
    protected BuriStateEntityDto createStateDto(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        long dataID = dataUtil.getBuriDataId(factory,sysContext);
        BuriPath path = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
        BuriStateEntityDto stateDto = new BuriStateEntityDto();
        stateDto.setDataID(new Long(dataID));
        stateDto.setPathID(new Long(path.getBuriPathID()));
        stateDto.setBranchID(new Long(walker.getBranchID()));
        stateDto.setInsertDate(new Date());

        stateDto.setAbortDate(DateUtil.getSQLMaxDate());
        stateDto.setProcessDate(DateUtil.getSQLMaxDate());
        setupAutoRunTime(stateDto,factory,sysContext,walker);
        return stateDto;
    }
    
    protected void setupAutoRunTime(BuriStateEntityDto stateDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        Date setupDate = DateUtil.getSQLMaxDate();
        BuriExePackages exePackages = getBuriExePackages(factory);
        if(exePackages != null) {
            Script exScript = scriptFactory.getScript(exePackages.getTimeLimitExpressionType());
            String limit = getLimit(exePackages,walker);
            setupDate = getLimitDate(limit,exScript,sysContext,walker);
        }
        stateDto.setAutoRunTime(setupDate);
    }
    
    protected Date getLimitDate(String limit,Script exScript,BuriSystemContext sysContext,BranchWalker walker) {
        if( ! StringUtil.isEmpty(limit)) {
            Object obj = exScript.eval(null,limit,sysContext.getUserContext());
            if(obj instanceof Date) {
                return (Date)obj;
            } else if(obj instanceof Calendar) {
                return ((Calendar)obj).getTime();
            } else {
                throw new IllegalArgumentRuntimeException("EBRI0007",new Object[]{walker.getNowPath(),obj});
            }
        }
        return null;
    }
    
    protected BuriExePackages getBuriExePackages(DataAccessFactory factory) {
        BuriExePackages exePackages = null;
        if(factory instanceof BuriExecProcess) {
            exePackages = ((BuriExecProcess)factory).getBuriExePackages();
        } else if(factory instanceof BuriExePackages) {
            exePackages = (BuriExePackages)factory;
        }
        return exePackages;
    }
    
    protected String getLimit(BuriExePackages exePackages,BranchWalker walker) {
        BuriRealPath nowRealPath = walker.getNowPath().getRealPath();
        BuriWorkflowProcessType processType = exePackages.getBuriPackageType().getProcessById(nowRealPath.getWorkflowProcess());
        BuriActivityType actType = processType.getActivityById(nowRealPath.getActivity().get(0).toString());
        String limit = actType.getLimit();
        return limit;
    }
    
    public void processed(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert sysContext.getStatusID() != null;
        long stateID = sysContext.getStatusID().longValue();
        undoLogUtil.addUndoLog(stateID,0);
        stateDao.updateProceesByStateID(stateID);
    }
    
    public void abortStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert sysContext.getStatusID() != null;
        long stateID = sysContext.getStatusID().longValue();
        assert walker.getBranchID() != 0;
        undoLogUtil.addUndoLog(stateID,0);
        stateDao.updateAbortByStateID(stateID);
    }
    
    public void abortBranch(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        abortParentBranchID(walker.getParentBranchID(),factory,sysContext);
    }
    
    protected void abortParentBranchID(long parentBranchId,DataAccessFactory factory,BuriSystemContext sysContext) {
        if(parentBranchId==0) {
            return;
        }
        List childs = branchDao.getBranchByParentID(parentBranchId);
        Iterator ite = childs.iterator();
        while(ite.hasNext()) {
            BuriBranchEntityDto child = (BuriBranchEntityDto)ite.next();
            abortParentBranchID(child.getBranchID(),factory,sysContext);
        }
        undoLogUtil.addUndoLog(0,parentBranchId);
        stateDao.updateAbortByBranchID(parentBranchId);
    }
    
    public long countNoProcessedSiblingStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert walker.getParentBranchID() != 0;
        long count = stateDao.countByBranchIDAndNotProcessed(walker.getParentBranchID());
        return count;
    }

    public BuriBranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BuriBranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public BuriDataUtil getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(BuriDataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public BuriPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(BuriPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

    public BuriStateDao getStateDao() {
        return stateDao;
    }

    public void setStateDao(BuriStateDao stateDao) {
        this.stateDao = stateDao;
    }

    public BuriUndoLogUtil getUndoLogUtil() {
        return undoLogUtil;
    }

    public void setUndoLogUtil(BuriUndoLogUtil undoLogUtil) {
        this.undoLogUtil = undoLogUtil;
    }

    public BTIDUtil getBtidUtil() {
        return btidUtil;
    }

    public void setBtidUtil(BTIDUtil btidUtil) {
        this.btidUtil = btidUtil;
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }
    
}
