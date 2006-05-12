/*
 * çÏê¨ì˙: 2005/07/07
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.Date;

import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriDataPathHistoryDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.util.BuriHistoryUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dto.BuriDataPathHistoryEntityDto;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;


public class BuriHistoryUtilImpl implements BuriHistoryUtil {
    private BuriPathDaoUtil buriPathDaoUtil;
    private BuriDataDaoUtil buriDataDaoUtil;
    private BuriDataPathHistoryDao buriDataPathHistoryDao;
    private ContextUtil contextUtil;
    
    public void addHistory(ActivityTagSelect actTagSelect, BuriPath path) {
        buriPathDaoUtil.setup(path);
        long dataID = buriDataDaoUtil.readBuriDataID(path);
        insertBuriDataPathHistoryEntityDto(dataID,path.getBuriPathID());
    }
    
    protected void insertBuriDataPathHistoryEntityDto(long dataID,long pathID) {
        BuriParticipant participant = contextUtil.getLocalContext().getBuriParticipant();
        BuriDataPathHistoryEntityDto entityDto = new BuriDataPathHistoryEntityDto();
        if(participant!=null) {
            entityDto.setUserIDVal(participant.getUserIDVal());
            entityDto.setUserIDNum(participant.getUserIDNum());
        }
        entityDto.setDataID(new Long(dataID));
        entityDto.setPathID(new Long(pathID));
        entityDto.setInsertDate(new Date());
        buriDataPathHistoryDao.insert(entityDto);
    }

    public BuriDataDaoUtil getBuriDataDaoUtil() {
        return buriDataDaoUtil;
    }

    public void setBuriDataDaoUtil(BuriDataDaoUtil buriDataDaoUtil) {
        this.buriDataDaoUtil = buriDataDaoUtil;
    }

    public BuriDataPathHistoryDao getBuriDataPathHistoryDao() {
        return buriDataPathHistoryDao;
    }

    public void setBuriDataPathHistoryDao(
            BuriDataPathHistoryDao buriDataPathHistoryDao) {
        this.buriDataPathHistoryDao = buriDataPathHistoryDao;
    }

    public BuriPathDaoUtil getBuriPathDaoUtil() {
        return buriPathDaoUtil;
    }

    public void setBuriPathDaoUtil(BuriPathDaoUtil buriPathDaoUtil) {
        this.buriPathDaoUtil = buriPathDaoUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

}
