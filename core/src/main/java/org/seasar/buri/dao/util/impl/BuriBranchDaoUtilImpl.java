/*
 * çÏê¨ì˙: 2005/08/14
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.util.BuriBranchDaoUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dto.BuriBranchEntityDto;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;


public class BuriBranchDaoUtilImpl implements BuriBranchDaoUtil {
    private BuriPathDaoUtil pathDaoUtil;
    private BuriDataDaoUtil dataDaoUtil;
    private ContextUtil contextUtil;
    private BuriBranchDao branchDao;
    private BuriStateDao stateDao;

    public BuriBranchEntityDto setupBranchID(BuriStateEntityDto stateEntityDto,BuriPath path) {
        pathDaoUtil.setup(path);
        dataDaoUtil.readBuriDataID(path);
        BuriStateEntityDto stateDto = stateDao.getAllBuriStateByPathAndData(path.getBuriPathID(),contextUtil.getLocalContext().getDataID().longValue());
        BuriBranchEntityDto entityDto = new BuriBranchEntityDto();
        if(stateDto == null || stateDto.getBranchID()==null) {
            entityDto.setDataID(contextUtil.getLocalContext().getDataID());
            entityDto.setPathID(new Long(path.getBuriPathID()));
            setupBranchID(entityDto);
        }else{
            entityDto = branchDao.getBuriBranch(stateDto.getBranchID().longValue());
        }
        return entityDto;
    }
    
    protected void setupBranchID(BuriBranchEntityDto entityDto) {
        entityDto.setBranchID(branchDao.getNewBuriBranchID());
    }

    public void updateBranchPath(BuriBranchEntityDto branchEntityDto, BuriPath path) {
        pathDaoUtil.setup(path);
        branchEntityDto.setPathID(new Long(path.getBuriPathID()));
    }

    public List setupBranchID(Collection paths, BuriBranchEntityDto parentBranchEntityDto) {
        List branchEntityList = new ArrayList();
        Iterator ite = paths.iterator();
        while(ite.hasNext()) {
            BuriPath path = (BuriPath)ite.next();
            BuriBranchEntityDto entityDto = setupBranchID(null,path);
            entityDto.setParentBranchID(new Long(parentBranchEntityDto.getBranchID()));
            branchEntityList.add(entityDto);
        }
        return branchEntityList;
    }

    public void saveBranchEntityDto(BuriBranchEntityDto branchEntityDto) {
        if(branchEntityDto.getBranchID() == 0 ) {
            branchEntityDto.setProcessDate(new Date());
            branchDao.insert(branchEntityDto);
        } else {
            branchEntityDto.setProcessDate(new Date());
            branchDao.update(branchEntityDto);
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

    public BuriPathDaoUtil getPathDaoUtil() {
        return pathDaoUtil;
    }

    public void setPathDaoUtil(BuriPathDaoUtil pathDaoUtil) {
        this.pathDaoUtil = pathDaoUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public BuriStateDao getStateDao() {
        return stateDao;
    }

    public void setStateDao(BuriStateDao stateDao) {
        this.stateDao = stateDao;
    }

}
