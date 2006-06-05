/*
 * ì¬“ú: 2006/05/24
 *
 */
package org.seasar.buri.engine.impl;

import org.seasar.buri.dao.BuriPathDataDao;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.dto.BuriPathDataEntityDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public class BuriSimpleEngineImpl extends WakanagoEngineImpl implements BuriEngine{
    private BuriPathDataDao pathDataDao;
    
    protected void updateSystemContext(BuriSystemContext sysContext,BuriExecProcess wp) {
        BuriDataAccessFactory factory = (BuriDataAccessFactory)wp;
        preprocessData(factory,sysContext.getUserContext());
        Object data = sysContext.getUserContext().getData();
        DataAccessUtil accessUtil = factory.getDataAccessUtil(data.getClass());
        String manyKey = getManyKey(accessUtil,data);
        Long longKey = getLongKey(accessUtil,data);
        if(manyKey == null && longKey == null) {
            return;
        }
        String pathName = sysContext.getCallPath().getPlainName() + ".%";
        if(sysContext.getCallPath().getActivityName().size() > 0) {
            pathName = sysContext.getCallPath().getPlainName();
        }
        BuriPathDataEntityDto dto = pathDataDao.getDtoByPathKey(data.getClass().getName(),longKey,manyKey,pathName,sysContext.getCallPath().getPathType());
        finalSetup(dto,sysContext);
    }
    
    protected void finalSetup(BuriPathDataEntityDto dto,BuriSystemContext sysContext) {
        if(dto != null) {
            sysContext.setDataID(new Long(dto.getDataID()));
            sysContext.setStatusID(new Long(dto.getStateID()));
            BuriPath callPath = new BuriPath(dto.getPathName(),dto.getRealPathName(),dto.getPathID(),dto.getPathType());
            sysContext.setCallPath(callPath);
        }
    }
    
    protected String getManyKey(DataAccessUtil accessUtil,Object data) {
        if(accessUtil instanceof DataAccessUtilManyKey) {
            return ((DataAccessUtilManyKey)accessUtil).getKey(data);
        }
        return null;
    }
    
    protected Long getLongKey(DataAccessUtil accessUtil,Object data) {
        if(accessUtil instanceof DataAccessUtilLongKey) {
            return ((DataAccessUtilLongKey)accessUtil).getKey(data);
        }
        return null;
        
    }
    
    protected void preprocessData(BuriDataAccessFactory factory,BuriUserContext userContext) {
        PreprocessAccessUtil pre = factory.getPreprocessAccessUtil(userContext.getData().getClass());
        if(pre != null) {
            Object trueData = pre.getTrueData(userContext.getData());
            userContext.setData(trueData);
        }
    }

    public BuriPathDataDao getPathDataDao() {
        return pathDataDao;
    }

    public void setPathDataDao(BuriPathDataDao pathDataDao) {
        this.pathDataDao = pathDataDao;
    }

}
