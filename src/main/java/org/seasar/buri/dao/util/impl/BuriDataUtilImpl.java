/*
 * çÏê¨ì˙: 2006/05/08
 *
 */
package org.seasar.buri.dao.util.impl;

import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.framework.util.ClassUtil;

public class BuriDataUtilImpl implements BuriDataUtil{
    private BuriDataDao dataDao;
    
    
    protected BuriDataEntityDto getBuriDataDto(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext) {
        BuriDataEntityDto findDto = new BuriDataEntityDto();
        setupPkey(findDto,argDto,factory);
        findDto.setDataType(argDto.getClass().getName());
        BuriDataEntityDto dto = dataDao.getBuridataFromDto(findDto);
        if(dto==null) {
            findDto.setInsertUserID(sysContext.getUserID());
            dataDao.insert(findDto);
            dto = findDto;
        }
        return dto;
    }
    
    protected void setupPkey(BuriDataEntityDto dto,Object argDto,DataAccessFactory factory) {
        DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
        if(util instanceof DataAccessUtilLongKey) {
            Long dataID = ((DataAccessUtilLongKey)util).getKey(argDto);
            dto.setPkeyNum(dataID);
        } else {
            String dataID = ((DataAccessUtilManyKey)util).getKey(argDto);
            dto.setPkeyVal(dataID);
        }
    }
    
    public long getBuriDataId(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext) {
        if(sysContext.getDataID() == null) {
            BuriDataEntityDto dto = getBuriDataDto(argDto,factory,sysContext);
            sysContext.setDataID(new Long( dto.getDataID() ));
        }
        return sysContext.getDataID().longValue();
    }
    
    public void updateBuriData(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext) {
        if(sysContext.getDataID() == null) {
            BuriDataEntityDto dto = getBuriDataDto(argDto,factory,sysContext);
            sysContext.setDataID(new Long( dto.getDataID() ));
        } else {
            BuriDataEntityDto dto = dataDao.getBuriData(sysContext.getDataID().longValue());
            setupPkey(dto,argDto,factory);
            dataDao.update(dto);
        }
    }
    
    
    public Object getBuriData(long dataId,DataAccessFactory factory) {
        Object result = null;
        BuriDataEntityDto dto = dataDao.getBuriData(dataId);
        Class tgtClass = ClassUtil.forName(dto.getDataType());
        DataAccessUtil util = factory.getDataAccessUtil(tgtClass);
        if(util instanceof DataAccessUtilLongKey) {
            result = ((DataAccessUtilLongKey)util).getObjectFromKey(dto.getPkeyNum());
        } else {
            result = ((DataAccessUtilManyKey)util).getObjectFromKey(dto.getPkeyVal());
        }
        return result;
    }

    public BuriDataDao getDataDao() {
        return dataDao;
    }

    public void setDataDao(BuriDataDao dataDao) {
        this.dataDao = dataDao;
    }

}
