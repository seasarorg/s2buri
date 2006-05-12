/*
 * çÏê¨ì˙: 2005/07/01
 *
 */
package org.seasar.buri.dao.datautil.impl;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.xpdl.util.BuriDataMetaData;
import org.seasar.buri.xpdl.util.BuriDataMetaDataUtil;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;

public class BuriDataDaoUtilImpl implements BuriDataDaoUtil {

    private BuriDataDao buriDataDao;
    private ContextUtil contextUtil;
    private BuriDataMetaDataUtil metaDataUtil;
    private DataAccessUtil accessUtil;
    private WorkFlowsUtil flowsUtil;
    private ClassDefUtil classDefUtil;
    
    
    public void setupBuriParticipant(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        Object userData = context.getUserData();
        if(userData == null) {
            return;
        }
        BuriParticipant participant = context.getBuriParticipant();
        participant.setUserData(userData);
        WorkFlowPackageTagSelect packageTagSelect = flowsUtil.getWorkFlowPackage(path);
        ParticipantProvider provider = packageTagSelect.getParticipantProvider();
        if(provider == null) {
            return;
        }
        participant.setUserIDVal(provider.getUserIDString(userData));
        participant.setUserIDNum(provider.getUserIDNum(userData));
        context.setBuriParticipant(participant);
    }
    
    public long readOnlyBuriDataID(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        long dataID = context.getDataID().longValue();
        if(dataID == 0 && context.getData() != null) {
            dataID = readOnlyBuriDataID(path,context.getData());
        }
        context.setDataID(dataID);
        return dataID;
    }
    
    public long readOnlyBuriDataID(BuriPath path,Object data) {
        long dataID = 0;
        BuriDataEntityDto dataEntityDto = createBuriDataEntityDto((BuriPath)path.clone(),data);
        dataEntityDto = buriDataDao.getBuridataFromDto(dataEntityDto);
        if(dataEntityDto != null) {
            dataID = dataEntityDto.getDataID();
        }
        return dataID;
    }
    
   public long readBuriDataID(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        long dataID = context.getDataID().longValue();
        if(dataID == 0 && context.getData() != null) {
            BuriDataEntityDto dataEntityDto = createBuriDataEntityDto(path);
            dataID = readBuriDataID(dataEntityDto);
        }
        return dataID;
    }
    
    protected long readBuriDataID(BuriDataEntityDto dataEntityDto) {
        BuriLocalContext context = contextUtil.getLocalContext();
        long dataID = 0;
        dataEntityDto = buriDataDao.getBuridataFromDto(dataEntityDto);
        if(dataEntityDto != null) {
            context.setDataID(dataEntityDto.getDataID());
            dataID = dataEntityDto.getDataID();
        }
        return dataID;
    }
    
    public long createOnlyBuriDataID(BuriPath path) {
        BuriDataEntityDto dataEntityDto = createBuriDataEntityDto(path);
        long dataID = readBuriDataID(dataEntityDto);
        if(dataID != 0) {
            return dataID;
        }
        dataID = getNewBuriDataID(dataEntityDto);
        BuriLocalContext context = contextUtil.getLocalContext();
        context.setDataID(dataID);
        return dataID;
    }
    
    protected long getNewBuriDataID(BuriDataEntityDto dataEntityDto) {
        return buriDataDao.getNewBuriDataID();
    }
    
    public void storeBuriData(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        accessUtil.sotreData(context.getData());
        BuriDataMetaData dataMetaData = metaDataUtil.getMetaDataFromTypeName(path,context.getDataClassName());
        BuriDataEntityDto entityDto = createBuriDataEntityDtoFromObject(context.getData(),dataMetaData);
        entityDto.setDataID(context.getDataID().longValue());
        storeBuriData(entityDto);
    }
    
    protected void storeBuriData(BuriDataEntityDto entityDto) {
        BuriDataEntityDto buriDataStoreDto = buriDataDao.getBuridataFromDto(entityDto);
        if(buriDataStoreDto==null) {
            buriDataDao.insert(entityDto);
        } else {
            buriDataDao.update(entityDto);
        }
    }

    public Object getDtoFromDataKey(BuriPath path) {
        long dataID =  readBuriDataID(path);
        Object dto = accessUtil.getObjectFromDataID(path,dataID);
        return dto;
    }

    protected BuriDataEntityDto createBuriDataEntityDto(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        return createBuriDataEntityDto(path,context.getData());
    }

    protected BuriDataEntityDto createBuriDataEntityDto(BuriPath path,Object data) {
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(path,classDefUtil.getClassName(data));
        BuriDataEntityDto dataEntityDto = createBuriDataEntityDtoFromObject(data,metaData);
        return dataEntityDto;
    }

    protected BuriDataEntityDto createBuriDataEntityDtoFromObject(Object data,BuriDataMetaData metaData) {
        Object pkeyVal = metaData.getPkeyFromObject(data);
        BuriDataEntityDto buriDataEntityDto = new BuriDataEntityDto();
        buriDataEntityDto.setDataType(classDefUtil.getClassName(data));
        if(metaData.isPkeyNumberType()) {
            Long longVal = new Long(((Number)pkeyVal).longValue());
            buriDataEntityDto.setPkeyNum(longVal);
        } else {
            buriDataEntityDto.setPkeyVal(pkeyVal.toString());
        }
        return buriDataEntityDto;
    }
    
    
    public BuriDataEntityDto getBuriDataEntityDto() {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(context.getCallBuriPath(),context.getDataClassName());
        BuriDataEntityDto dataEntityDto = storeBuriDataEntityDto(metaData);
        return dataEntityDto;
    }
    public BuriDataEntityDto getBuriDataEntityDto(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(path,context.getDataClassName());
        BuriDataEntityDto dataEntityDto = storeBuriDataEntityDto(metaData);
        return dataEntityDto;
    }
    
    
    protected BuriDataEntityDto storeBuriDataEntityDto(BuriDataMetaData metaData) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriDataEntityDto buriDataEntityDto = createBuriDataEntityDtoFromObject(context.getData(),metaData);
        BuriDataEntityDto buriDataStoreDto = buriDataDao.getBuridataFromDto(buriDataEntityDto);
        if(buriDataStoreDto!=null) {
            context.setDataID(buriDataEntityDto.getDataID());
        }
        return buriDataStoreDto;
    }

    public DataAccessUtil getAccessUtil() {
        return accessUtil;
    }

    public void setAccessUtil(DataAccessUtil accessUtil) {
        this.accessUtil = accessUtil;
    }

    public BuriDataDao getBuriDataDao() {
        return buriDataDao;
    }

    public void setBuriDataDao(BuriDataDao buriDataDao) {
        this.buriDataDao = buriDataDao;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public BuriDataMetaDataUtil getMetaDataUtil() {
        return metaDataUtil;
    }

    public void setMetaDataUtil(BuriDataMetaDataUtil metaDataUtil) {
        this.metaDataUtil = metaDataUtil;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }
    

    

}
