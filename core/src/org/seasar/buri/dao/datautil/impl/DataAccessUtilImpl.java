/*
 * çÏê¨ì˙: 2005/11/09
 *
 */
package org.seasar.buri.dao.datautil.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.component.OgnlInvoker;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.BuriDataMetaData;
import org.seasar.buri.xpdl.util.BuriDataMetaDataUtil;
import org.seasar.framework.util.StringUtil;

public class DataAccessUtilImpl implements DataAccessUtil {

    private OgnlInvoker ognlInvoker;
    private BuriDataDao buriDataDao;
    private ContextUtil contextUtil;
    private BuriDataMetaDataUtil metaDataUtil;
    
    public void sotreData(Object data) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriPath callPath = context.getCallBuriPath();
        if(data instanceof List) {
            storeListData((List)data,callPath);
        } else {
            BuriDataMetaData metaData = metaDataUtil.getMetaData(callPath,data);
            processDataAccess(metaData,data);
        }
    }
    
    protected void storeListData(List datas,BuriPath callPath) {
        Iterator ite = datas.iterator();
        BuriDataMetaData metaData = null;
        while(ite.hasNext()) {
            Object oneData = ite.next();
            if(metaData == null) {
                metaData = metaDataUtil.getMetaData(callPath,oneData);
            }
            processDataAccess(metaData,oneData);
        }
    }
    
    public void sotreDataNoPkey(Object data) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriPath callPath = context.getCallBuriPath();
        BuriDataMetaData metaData = metaDataUtil.getMetaData(callPath,data);
        if(metaData.getPkeyFromObject(data) == null ){
            processDataAccess(metaData,data);
        }
    }
    
    public Object getObjectFromDataID(BuriPath path,long dataID) {
        BuriLocalContext context = contextUtil.getLocalContext();
        BuriDataMetaData metaData = metaDataUtil.getMetaDataFromTypeName(path,context.getDataClassName());
        Object result = getObjectFromDataID(metaData,dataID);
        return result;
    }

    public Object getObjectFromDataID(BuriDataMetaData metaData,long dataID) {
        BuriDataEntityDto buriDataStoreDto = buriDataDao.getBuriData(dataID);
        Object keyData = metaData.cnvStorePkeyToObj(buriDataStoreDto.getPkeyNum(),buriDataStoreDto.getPkeyVal());
        String processOgnl = metaData.getSelectOgnl();
        Object result = keyData;
        if( ! StringUtil.isEmpty(processOgnl)) {
            result = callOgnls(metaData,processOgnl,keyData);
        }
        return result;
    }
    
    protected String getProcessOgnl(BuriDataMetaData metaData,Object data) {
        BuriLocalContext context = contextUtil.getLocalContext();
        String processOgnl = null;
        if(metaData.isCorrectPkey(context.getData())) {
            processOgnl = metaData.getSelectOgnl();
            Object result = callOgnls(metaData,processOgnl);
            if(result==null) {
                processOgnl = metaData.getInsertOgnl();
            } else {
                processOgnl = metaData.getUpdateOgnl();
            }
        } else {
            processOgnl = metaData.getInsertOgnl();
        }
        return processOgnl;
    }
    protected void processDataAccess(BuriDataMetaData metaData) {
        BuriLocalContext context = contextUtil.getLocalContext();
        processDataAccess(metaData,context.getData());
    }
    
    protected void processDataAccess(BuriDataMetaData metaData,Object data) {
        String processOgnl = getProcessOgnl(metaData,data);
        if(processOgnl == null) {
            return;
        }
        callOgnls(metaData,processOgnl,data);
    }
    
    protected Object callOgnls(BuriDataMetaData metaData,String processOgnl) {
        BuriLocalContext context = contextUtil.getLocalContext();
        return callOgnls(metaData,processOgnl,context.getData());
    }
    
    public Object callFindOgnls(BuriDataMetaData metaData,String processOgnl,Object ids,Object findDto) {
        Object result = null;
        BuriLocalContext context = contextUtil.getLocalContext();
        ScriptProcessor processor = new ScriptProcessor();
        Map ognlContext = new HashMap();
        ognlContext.put("container",context.getContainer());
        ognlContext.put("id",ids);
        ognlContext.put("findDto",findDto);
        result = ognlInvoker.processOgnls(processOgnl,processor,ognlContext);
        return result;
    }
    
    public Object callOgnls(BuriDataMetaData metaData,String processOgnl,Object data) {
        Object result = null;
        BuriLocalContext context = contextUtil.getLocalContext();
        ScriptProcessor processor = new ScriptProcessor();
        Map ognlContext = new HashMap();
        ognlContext.put("container",context.getContainer());
        ognlContext.put("data",data);
        result = ognlInvoker.processOgnls(processOgnl,processor,ognlContext);
        return result;
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

    public OgnlInvoker getOgnlInvoker() {
        return ognlInvoker;
    }

    public void setOgnlInvoker(OgnlInvoker ognlInvoker) {
        this.ognlInvoker = ognlInvoker;
    }
    
}
