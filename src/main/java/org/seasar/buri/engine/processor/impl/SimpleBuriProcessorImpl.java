/*
 * çÏê¨ì˙: 2006/05/30
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.List;

import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.SimpleBuriProcessor;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class SimpleBuriProcessorImpl implements SimpleBuriProcessor {
    private BuriEngine engine;
    private S2Container container;
    private BuriDataUtil dataUtil;

    public void toNextStatus(String path, Object data) {
        toNextStatusAction(path, null, data, null, null);
    }

    public Object toNextStatus(String path, Object data, String resultExp) {
        return toNextStatusAction(path, null, data, null, resultExp);
    }

    public void toNextStatusAction(String path, Object data, String action) {
        toNextStatusAction(path, null, data, action, null);
    }

    public Object toNextStatus(String path, Object data, BuriProcessorInfo info) {
        BuriUserContext userContext = engine.createUserContext(data,null,info.getAction());
        userContext.putAll(info.getContext());
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        S2Container cont = info.getContainer() == null ? container : info.getContainer();
        systemContext.setContainer(cont);
        Object result = engine.execute(systemContext,info.getResultExp());
        return result;
    }

    protected Object toNextStatusAction(String path, S2Container container, Object data, Object action, String resultExp) {
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.setAction(action);
        info.setContainer(container);
        info.setResultExp(resultExp);
        return toNextStatus(path, data, info);
    }

    public List getDataListFromPath(String path,Class tgtClass) {
        BuriUserContext userContext = engine.createUserContext(null,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setTgtClass(tgtClass);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path,(DataAccessFactory)execProcess,systemContext);
        return dataList;
    }

    public List getDataIDFromPath(String path,Class tgtClass) {
        BuriUserContext userContext = engine.createUserContext(null,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setTgtClass(tgtClass);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path,(DataAccessFactory)execProcess,systemContext);
        return dataList;
    }

    public List getPathFromData(String path, Object data) {
        BuriUserContext userContext = engine.createUserContext(data,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        List pathList = dataUtil.getBuriPathByDto(data,(DataAccessFactory)execProcess,systemContext);
        return pathList;
    }

    public long countByPathAndDatas(String path, List datas) {
        BuriUserContext userContext = engine.createUserContext(null,null,null);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        long count = dataUtil.countByPathAndDatas(path,datas,(DataAccessFactory)execProcess,systemContext);
        return count;
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
