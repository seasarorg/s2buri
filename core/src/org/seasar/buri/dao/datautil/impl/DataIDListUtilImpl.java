/*
 * ì¬“ú: 2005/11/09
 *
 */
package org.seasar.buri.dao.datautil.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dao.datautil.DataIDListUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.dto.BuriPathEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.BuriDataMetaData;
import org.seasar.buri.xpdl.util.BuriDataMetaDataUtil;
import org.seasar.framework.util.StringUtil;

public class DataIDListUtilImpl implements DataIDListUtil {

    private BuriDataDao buriDataDao;
    private BuriPathDaoUtil pathDaoUtil;
    private ContextUtil contextUtil;
    private BuriDataMetaDataUtil metaDataUtil;
    private DataAccessUtil accessUtil;
    private BuriStateUtil stateUtil;
    private BuriDataDaoUtil dataDaoUtil;
    private BuriStateDao stateDao;
    private ClassDefUtil classDefUtil;

    public long countByPathAndDatas(String path,List datas) {
        BuriPath buriPath = pathDaoUtil.getBuriPath(new BuriPath(path));
        long pathID = buriPath.getBuriPathID();
        List ids = new ArrayList();
        Iterator ite = datas.iterator();
        while(ite.hasNext()) {
            Object dto = ite.next();
            long dataID = dataDaoUtil.readOnlyBuriDataID(buriPath,dto);
            ids.add(new Long(dataID));
        }
        long count = stateDao.countByPathAndDatas(pathID,ids);
        return count;
    }
    
    
    public List getDataIDListFromPathAndUser(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        return getDataIDListFromPathAndUser(path,context.getBuriParticipant().getUserIDVal(),context.getBuriParticipant().getUserIDNum());
    }
    
    public List getDataIDListFromPath(BuriPath path) {
        dataDaoUtil.setupBuriParticipant(path);
        BuriPath buriPath = pathDaoUtil.getBuriPath(path);
        return getDataIDListFromPathAndUser(buriPath,null,null);
    }

    public List getDataIDListFromPathAndUser(BuriPath path,String userIDVal,Long userIDNum) {
        pathDaoUtil.setup(path);
        dataDaoUtil.setupBuriParticipant(path);
        BuriPath buriPath = pathDaoUtil.getBuriPath(path);
        List dataIdList = buriDataDao.getDataIDsFromBuriPathID(buriPath.getBuriPathID(),userIDVal,userIDNum);
        List keys = new ArrayList();
        Iterator ite = dataIdList.iterator();
        while(ite.hasNext()) {
            BuriDataEntityDto entityDto = (BuriDataEntityDto)ite.next();
            Long dataid = entityDto.getPkeyNum();
            keys.add(dataid);
        }
        return keys;
    }
    

    protected List getDataListFromPathAndUser(BuriPath path,String userIDVal,Long userIDNum) {
        List result = new ArrayList();
        pathDaoUtil.setup(path);
        BuriDataEntityDto countNumType = buriDataDao.getCountNumDataFromBuriPathID(path.getBuriPathID(),userIDVal,userIDNum);
        if(countNumType==null) {
            return result;
        }
        result = getDataListFromBuriDataEntityDto(path,countNumType,userIDVal,userIDNum);
        return result;
    }
    
    protected List getDataListFromBuriDataEntityDto(BuriPath path,BuriDataEntityDto countNumType,String userIDVal,Long userIDNum) {
        List result = new ArrayList();
        Long count = countNumType.getPkeyNum();
        String dataType = countNumType.getDataType();
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(path,dataType);
        
        pathDaoUtil.getBuriPath(path);

        if(canUseSelectMany(count,metaData) ) {
            List dataIdList = getDataIDListFromPath(path);
            result = getObjectListFromIDs(metaData,dataIdList);
        } else {
            List dataList = buriDataDao.getBuriDataFromBuriPathID(path.getBuriPathID(),userIDVal,userIDNum);
            result = getDataListFromBuriDataMetaData(path,metaData,dataList);
        }
        return result;
        
    }
    
    protected List getObjectListFromIDs(BuriDataMetaData metaData,List dataIDs) {
        String processOgnl = metaData.getSelectManyOgnl();
        Object result = new ArrayList();
        if( ! StringUtil.isEmpty(processOgnl)) {
            result = accessUtil.callOgnls(metaData,processOgnl,dataIDs);
        }
        return (List)result;
    }
    
    protected List getDataListFromBuriDataMetaData(BuriPath path,BuriDataMetaData metaData,List dataList) {
        List result = null;
        if(metaData.getSelectManyOgnl() != null && metaData.isPkeyNumberType()) {
            result = getObjectListFromIDs(metaData,dataList);
        } else {
            result = getObjectFromDataID(metaData,dataList);
        }
        return result;
    }
    
    protected List getObjectFromDataID(BuriDataMetaData metaData,List dataIDs) {
        List output = new ArrayList();
        Iterator ite = dataIDs.iterator();
        while(ite.hasNext()) {
            BuriDataEntityDto entityDto = (BuriDataEntityDto)ite.next();
            long dataid = entityDto.getDataID();
            Object data = accessUtil.getObjectFromDataID(metaData,dataid);
            output.add(data);
        }
        return output;
    }
    
    public List getStatePathListByData(BuriPath path) {
        List list = stateUtil.getStatusPath(path);
        List result = new ArrayList();
        Iterator ite = list.iterator();
        while(ite.hasNext()) {
            BuriPathEntityDto entityDto = (BuriPathEntityDto)ite.next();
            BuriPath resultPath = new BuriPath(entityDto.getPathName(),entityDto.getRealPathName());
            resultPath.setBuriPathID(entityDto.getPathID());
            result.add(resultPath);
        }
        return result;
    }

    public List searchDataList(BuriPath path, Object findDto) {
        List result = new ArrayList();
        List ids = getDataIDListFromPathAndUser(path,null,null);
        result = searchDataFromDto(path,findDto,ids);
        return result;
    }


    public List searchDataListFromPathAndUser(BuriPath path, Object findDto) {
        BuriLocalContext context = contextUtil.getLocalContext();
        List result = new ArrayList();
        List ids = getDataIDListFromPathAndUser(path,context.getBuriParticipant().getUserIDVal(),context.getBuriParticipant().getUserIDNum());
        result = searchDataFromDto(path,findDto,ids);
        return result;
    }
    
    protected List searchDataFromDto(BuriPath path, Object findDto,List ids) {
        Object result = new ArrayList();
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(path,classDefUtil.getClassName(findDto));
        String processOgnl = metaData.getFilterManyOgnl();
        if(! StringUtil.isEmpty(processOgnl)) {
            result = accessUtil.callFindOgnls(metaData,processOgnl,ids,findDto);
        }
        return (List)result;
    }
    
    public List filterActivityListFromPath(List activityList,List buriPathList) {
        List result = new ArrayList();
        Iterator ite = activityList.iterator();
        while(ite.hasNext()) {
            ActivityTagSelect tagSelect = (ActivityTagSelect)ite.next();
            checkAndAppendActivityTagSelect(tagSelect,buriPathList,result);
        }
        
        return result;
    }
    
    protected void checkAndAppendActivityTagSelect(ActivityTagSelect tagSelect,List buriPathList,List result) {
        String actPathName = tagSelect.getBuriPath().getRealPath().getPlainName();
        Iterator pathIte = buriPathList.iterator();
        while(pathIte.hasNext()) {
            BuriPath path = (BuriPath)pathIte.next();
            if(actPathName.compareTo(path.getRealPath().getPlainName()) == 0 ) {
                result.add(tagSelect);
            }
        }
    }

    
    protected boolean canUseSelectMany(Long pkeyCount,BuriDataMetaData metaData) {
        return pkeyCount.longValue() > 0 && (! StringUtil.isEmpty(metaData.getSelectManyOgnl()));
    }
    
    public List getDataListFromPathAndUser(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        dataDaoUtil.setupBuriParticipant(path);
        BuriPath buriPath = pathDaoUtil.getBuriPath(path);
        return getDataListFromPathAndUser(buriPath,context.getBuriParticipant().getUserIDVal(),context.getBuriParticipant().getUserIDNum());
    }
    
    public List getDataListFromPath(BuriPath path) {
        dataDaoUtil.setupBuriParticipant(path);
        pathDaoUtil.setup(path);
        return getDataListFromPathAndUser(path,null,null);
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

    public BuriPathDaoUtil getPathDaoUtil() {
        return pathDaoUtil;
    }

    public void setPathDaoUtil(BuriPathDaoUtil pathDaoUtil) {
        this.pathDaoUtil = pathDaoUtil;
    }

    public BuriDataDaoUtil getDataDaoUtil() {
        return dataDaoUtil;
    }

    public void setDataDaoUtil(BuriDataDaoUtil dataDaoUtil) {
        this.dataDaoUtil = dataDaoUtil;
    }

    public BuriStateUtil getStateUtil() {
        return stateUtil;
    }

    public void setStateUtil(BuriStateUtil stateUtil) {
        this.stateUtil = stateUtil;
    }


    public BuriStateDao getStateDao() {
        return stateDao;
    }


    public void setStateDao(BuriStateDao stateDao) {
        this.stateDao = stateDao;
    }


    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }


    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

    

}
