/*
 * çÏê¨ì˙: 2006/05/08
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.BuriIDListDao;
import org.seasar.buri.dao.BuriPathDataDao;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.dto.BuriPathDataEntityDto;
import org.seasar.buri.dto.BuriPathDataUserEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.framework.util.ClassUtil;

public class BuriDataUtilImpl implements BuriDataUtil{
    private BuriDataDao dataDao;
    private BuriIDListDao idListDao;
    private BuriPathDataDao pathDataDao;
    
    public long countByPathAndDatas(String pathName,List dataList,DataAccessFactory factory,BuriSystemContext sysContext) {
        if(dataList != null && dataList.size() == 0) {
            return 0;
        }
        Class tgtClass = dataList.get(0).getClass();
        DataAccessUtil util = factory.getDataAccessUtil(tgtClass);
        if(util instanceof DataAccessUtilLongKey) {
            return countByPathAndDatasPkeyNum(pathName,dataList,(DataAccessUtilLongKey)util,sysContext);
        } else {
            return countByPathAndDatasPkeyVal(pathName,dataList,(DataAccessUtilManyKey)util,sysContext);
        }
    }
    
    protected long countByPathAndDatasPkeyNum(String pathName,List dataList,DataAccessUtilLongKey util,BuriSystemContext sysContext) {
        List longList = new ArrayList();
        String className = dataList.get(0).getClass().getName();
        Iterator ite = dataList.iterator();
        while(ite.hasNext()) {
            Object data = ite.next();
            Long key = util.getKey(data);
            longList.add(key);
        }
        long count = pathDataDao.getCountByPathKeys(className,longList,null,pathName,sysContext.getCallPath().getPathType());
        return count;
    }
    
    protected long countByPathAndDatasPkeyVal(String pathName,List dataList,DataAccessUtilManyKey util,BuriSystemContext sysContext) {
        List strList = new ArrayList();
        String className = dataList.get(0).getClass().getName();
        Iterator ite = dataList.iterator();
        while(ite.hasNext()) {
            Object data = ite.next();
            String key = util.getKey(data);
            strList.add(key);
        }
        long count = pathDataDao.getCountByPathKeys(className,null,strList,pathName,sysContext.getCallPath().getPathType());
        return count;
    }
    
    public List getBuriPathByDto(Object dto,DataAccessFactory factory,BuriSystemContext sysContext) {
        String className = dto.getClass().getName();
        Long pathType = sysContext.getCallPath().getPathType();
        DataAccessUtil util = factory.getDataAccessUtil(dto.getClass());
        List infoList;
        if(util instanceof DataAccessUtilLongKey) {
            Long key = ((DataAccessUtilLongKey)util).getKey(dto);
            infoList = pathDataDao.getListByPkeyNum(className,key,pathType);
        } else {
            String key = ((DataAccessUtilManyKey)util).getKey(dto);
            infoList = pathDataDao.getListByPkeyVal(className,key,pathType);
        }
        List pathList = convPathDataToBuriPath(infoList);
        return pathList;
    }
    
    protected List convPathDataToBuriPath(List infoList) {
        List result = new ArrayList();
        Iterator ite = infoList.iterator();
        while(ite.hasNext()) {
            BuriPathDataEntityDto dto = (BuriPathDataEntityDto)ite.next();
            BuriPath path = new BuriPath(dto.getPathName(),dto.getRealPathName(),dto.getPathID());
            result.add(path);
        }
        return result;
    }
    
    public List getIDListByPathName(String pathName,DataAccessFactory factory,BuriSystemContext sysContext) {
        List result;
        Class tgtCalss = sysContext.getTgtClass();
        DataAccessUtil util = factory.getDataAccessUtil(tgtCalss);
        if(util instanceof DataAccessUtilLongKey) {
            result = getDataDtoList(pathName,(DataAccessUtilLongKey)util,sysContext);
        } else {
            result = getManyDataDtoList(pathName,(DataAccessUtilManyKey)util,sysContext);
        }
        return result;
    }
    
    protected List getDataDtoList(String pathName,DataAccessUtilLongKey dataUtil,BuriSystemContext sysContext) {
        String className = sysContext.getTgtClass().getName();
        Long pathType = sysContext.getCallPath().getPathType();
        List infoList = pathDataDao.getListByPathName(className,pathName,pathType);
        Iterator ite = infoList.iterator();
        List result = new ArrayList();
        while(ite.hasNext()) {
            BuriPathDataEntityDto dto = (BuriPathDataEntityDto)ite.next();
            result.add(dto.getPkeyNum());
        }
        return result;
    }
    
    protected List getManyDataDtoList(String pathName,DataAccessUtilManyKey dataUtil,BuriSystemContext sysContext) {
        String className = sysContext.getTgtClass().getName();
        Long pathType = sysContext.getCallPath().getPathType();
        List infoList = pathDataDao.getListByPathName(className,pathName,pathType);
        Iterator ite = infoList.iterator();
        List result = new ArrayList();
        while(ite.hasNext()) {
            BuriPathDataUserEntityDto dto = (BuriPathDataUserEntityDto)ite.next();
            Object data = dataUtil.getObjectFromKey(dto.getPkeyVal());
            result.add(data);
        }
        return result;
    }
    
    public List getDtoListByPathName(String pathName,DataAccessFactory factory,BuriSystemContext sysContext) {
        List result = new ArrayList();
        DataAccessUtil util = factory.getDataAccessUtil(sysContext.getTgtClass());
        List keys = getIDListByPathName(pathName,factory,sysContext);
        if(keys.size()==0) {
            return result;
        }
        if(util instanceof DataAccessUtilLongKey) {
            result = ((DataAccessUtilLongKey)util).get(keys);
        } else {
            result = ((DataAccessUtilManyKey)util).get(keys);
        }
        return result;
        
    }
    
    protected BuriDataEntityDto getBuriDataDto(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext) {
        BuriDataEntityDto findDto = new BuriDataEntityDto();
        setupPkey(findDto,argDto,factory);
        findDto.setDataType(argDto.getClass().getName());
        List datas = dataDao.getBuridataFromDto(findDto);
        BuriDataEntityDto dto = null;
        if(datas.size()==0) {
            findDto.setInsertUserID(sysContext.getUserID());
            dataDao.insert(findDto);
            dto = findDto;
        } else {
            dto = (BuriDataEntityDto)datas.get(0);
        }
        return dto;
    }
    
    protected void setupPkey(BuriDataEntityDto dto,Object argDto,DataAccessFactory factory) {
        DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
        if(util==null) {
            throw new NullPointerException("DataAccessUtil Not Found TgtClass=" + argDto.getClass().toString() + " / XPDL Miss!(Workflow variables)");
        }
        if(util instanceof DataAccessUtilLongKey) {
            Long dataID = ((DataAccessUtilLongKey)util).getKey(argDto);
            dto.setPkeyNum(dataID);
        } else {
            String dataID = ((DataAccessUtilManyKey)util).getKey(argDto);
            dto.setPkeyVal(dataID);
        }
    }
    
    public long getBuriDataId(DataAccessFactory factory,BuriSystemContext sysContext) {
        Object argDto = sysContext.getUserContext().getData();
        if(sysContext.getDataID() == null) {
            BuriDataEntityDto dto = getBuriDataDto(argDto,factory,sysContext);
            assert dto != null;
            sysContext.setDataID(new Long( dto.getDataID() ));
        }
        return sysContext.getDataID().longValue();
    }
    
    public void storeData(DataAccessFactory factory,BuriSystemContext sysContext) {
        Object argDto = sysContext.getUserContext().getData();
        DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
        util.Store(argDto);
    }
    
    public void updateBuriData(DataAccessFactory factory,BuriSystemContext sysContext) {
        Object argDto = sysContext.getUserContext().getData();
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

    public BuriIDListDao getIdListDao() {
        return idListDao;
    }

    public void setIdListDao(BuriIDListDao idListDao) {
        this.idListDao = idListDao;
    }

    public BuriPathDataDao getPathDataDao() {
        return pathDataDao;
    }

    public void setPathDataDao(BuriPathDataDao pathDataDao) {
        this.pathDataDao = pathDataDao;
    }

}
