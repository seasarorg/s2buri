/*
 * 作成日: 2006/06/14
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.StandardBuriProcessor;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class StandardBuriProcessorImpl implements StandardBuriProcessor {
    private BuriEngine engine;
    private S2Container container;
    private BuriDataUtil dataUtil;

    public void toNextStatus(String path, Object data, Object userData) {
        toNextStatusAction(path, getRootContainer(), data, userData,null, null);
    }

    public Object toNextStatus(String path, Object data, Object userData,String resultExp) {
        return toNextStatusAction(path, getRootContainer(), data, userData,null, resultExp);
    }

    public void toNextStatusAction(String path, Object data, Object userData,Object action) {
        toNextStatusAction(path, getRootContainer(), data, userData,action, null);
    }

    protected Object toNextStatusAction(String path, S2Container container, Object data, Object userData,Object action, String resultExp) {
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.setAction(action);
        info.setContainer(container);
        info.setResultExp(resultExp);
        return toNextStatus(path, data, userData, info);
    }

    public Object toNextStatus(String path, Object data, Object userData,BuriProcessorInfo info) {
        if(data instanceof List) {
            return toNextStatusList(path, (List) data, userData,info);
        } else {
            return toNextStatusOne(path, data, userData,info);
        }
    }
    
    public Object toNextStatusOne(String path, Object data, Object userData,BuriProcessorInfo info) {
        BuriUserContext userContext = engine.createUserContext(data,userData,info.getAction(),info.getContext());
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        S2Container cont = info.getContainer() == null ? getRootContainer() : info.getContainer();
        systemContext.setContainer(cont);
        systemContext.setActNames(info.getActNames());
        Object result = engine.execute(systemContext,info.getResultExp());
        return result;
    }
    
    public Object toNextStatusList(String path, List datas, Object userData,BuriProcessorInfo info) {
        Object result = null;
        Iterator ite = datas.iterator();
        while(ite.hasNext()) {
            Object data = ite.next();
            result = toNextStatusOne(path, data, userData,info);
        }
        return result;
    }
    

    public List getDataListFromPath(String path, Object userData, Class tgtClass) {
        return getDataListFromPath(path, userData,tgtClass, getRootContainer());
    }

    public List getDataListFromPath(String path, Object userData,Class tgtClass, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null,userData,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setTgtClass(tgtClass);
        systemContext.setContainer(container);
        engine.setupUserID(systemContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path,(DataAccessFactory)execProcess,systemContext);
        return dataList;
    }

    public List getDataIDFromPath(String path, Object userData, Class tgtClass) {
        return getDataIDFromPath(path, userData, tgtClass,getRootContainer());
    }

    public List getDataIDFromPath(String path, Object userData, Class tgtClass,S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null,userData,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setTgtClass(tgtClass);
        systemContext.setContainer(container);
        engine.setupUserID(systemContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path,(DataAccessFactory)execProcess,systemContext);
        return dataList;
    }

    public List getPathFromData(String path, Object data, Object userData,Class tgtClass) {
        return getPathFromData(path, data, userData,tgtClass, getRootContainer());
    }

    public List getPathFromData(String path, Object data, Object userData,Class tgtClass, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(data,userData,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setContainer(container);
        engine.setupUserID(systemContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List pathList = dataUtil.getBuriPathByDto(data,(DataAccessFactory)execProcess,systemContext);
        return pathList;
    }

    public long countByPathAndDatas(String path, List datas, Object userData) {
        return countByPathAndDatas(path, datas, userData,getRootContainer());
    }

    public long countByPathAndDatas(String path, List datas, Object userData,S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null,userData,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setContainer(container);
        engine.setupUserID(systemContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        long count = dataUtil.countByPathAndDatas(path,datas,(DataAccessFactory)execProcess,systemContext);
        return count;
    }
    
    protected S2Container getRootContainer() {
        return container.getRoot();
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriDataUtil getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(BuriDataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public BuriEngine getEngine() {
        return engine;
    }

    public void setEngine(BuriEngine engine) {
        this.engine = engine;
    }

}
