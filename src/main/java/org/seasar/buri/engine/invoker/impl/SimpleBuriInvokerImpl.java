/*
 * çÏê¨ì˙: 2006/05/30
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.List;

import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class SimpleBuriInvokerImpl implements SimpleBuriInvoker {
    private BuriEngine engine;
    private S2Container container;
    private BuriDataUtil dataUtil;

    public void invoke(String path, Object data) {
        invoke(path, container, data, null, null);
    }

    public Object invoke(String path, Object data, String resultExp) {
        return invoke(path, container, data, null, resultExp);
    }

    public Object invoke(String path, Object data, Object action, String resultExp) {
        return invoke(path, container, data, action, resultExp);
    }

    public void invoke(String path, S2Container container, Object data) {
        invoke(path, container, data, null, null);
    }

    public Object invoke(String path, S2Container container, Object data, String resultExp) {
        return invoke(path, container, data, null, resultExp);
    }

    public Object invoke(String path, S2Container container, Object data, Object action, String resultExp) {
        BuriUserContext userContext = engine.createUserContext(data,null,action);
        BuriSystemContext systemContext = engine.createSystemContext(path,userContext);
        systemContext.setContainer(container);
        Object result = engine.execute(systemContext,resultExp);
        return result;
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
