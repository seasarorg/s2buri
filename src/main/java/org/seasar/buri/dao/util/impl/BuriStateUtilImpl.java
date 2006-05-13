/*
 * 作成日: 2006/05/10
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.Date;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.BuriBranchEntityDto;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStateUtilImpl implements BuriStateUtil {
    private BuriDataUtil dataUtil;
    private BuriPathUtil pathUtil;
    private BuriStateDao stateDao;
    private BuriBranchDao branchDao;
    
    public BranchWalker getNowPathBranchWalker(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BuriPath callPath) {
        long dataID = dataUtil.getBuriDataId(argDto,factory,sysContext);
        BuriPath path = pathUtil.getBuriPathFromRealPath(callPath);
        BranchWalker walker = new BranchWalker();
        BuriStateEntityDto stateDto = stateDao.getBuriStateByPathAndData(path.getBuriPathID(),dataID);
        if(stateDto ==null) {
            return null;
        }
        walker.setBranchID(stateDto.getBranchID().longValue());
        walker.setNowPath(path);
        walker.setParentPath(sysContext.getCallPath());
        BuriBranchEntityDto branchDto = branchDao.getBuriBranch(stateDto.getBranchID().longValue());
        if(branchDto != null) { // TODO 本当は状態と一緒に保存しているはずなので不要なハズ
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
    
    public long saveStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        BuriStateEntityDto stateDto = createStateDto(argDto,factory,sysContext,walker);
        stateDao.insert(stateDto);
        return stateDto.getStateID();
    }
    
    protected BuriStateEntityDto createStateDto(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        long dataID = dataUtil.getBuriDataId(argDto,factory,sysContext);
        BuriPath path = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
        BuriStateEntityDto stateDto = new BuriStateEntityDto();
        stateDto.setDataID(new Long(dataID));
        stateDto.setPathID(new Long(path.getBuriPathID()));
        stateDto.setBranchID(new Long(walker.getBranchID()));
        stateDto.setInsertDate(new Date());

        stateDto.setAbortDate(DateUtil.getSQLMaxDate());
        stateDto.setProcessDate(DateUtil.getSQLMaxDate());
        stateDto.setAutoRunTime(DateUtil.getSQLMaxDate());
        return stateDto;
    }
    
    public void processed(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert sysContext.getStatusID() != null;
        long stateID = sysContext.getStatusID().longValue();
        stateDao.updateProcessed(stateID);
    }
    
    public void abortStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert sysContext.getStatusID() != null;
        long stateID = sysContext.getStatusID().longValue();
        assert walker.getBranchID() != 0;
        stateDao.updateAbort(stateID);
    }
    
    public void abortBranch(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        assert walker.getBranchID() != 0;
        stateDao.updateAbortByBranchID(walker.getBranchID());
    }
    
    public long countNoProcessedSiblingStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
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
    
}
