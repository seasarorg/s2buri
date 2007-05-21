/*
 * 作成日: 2006/05/30
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.util.packages.BuriExecProcess;
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
        if (data instanceof List) {
            return toNextStatusList(path, (List) data, info);
        } else {
            return toNextStatusOne(path, data, info);
        }
    }

    public Object toNextStatusOne(String path, Object data, BuriProcessorInfo info) {
        BuriUserContext userContext = engine.createUserContext(data, null, info.getAction(), info
            .getContext());
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        S2Container cont = info.getContainer() == null ? getRootContainer() : info.getContainer();
        systemContext.setContainer(cont);
        systemContext.setActivityNames(info.getActNames());
        Object result = engine.execute(systemContext, info.getResultExp());
        return result;
    }

    public Object toNextStatusList(String path, List datas, BuriProcessorInfo info) {
        Object result = null;
        Iterator ite = datas.iterator();
        while (ite.hasNext()) {
            Object data = ite.next();
            result = toNextStatusOne(path, data, info);
        }
        return result;
    }

    protected Object toNextStatusAction(String path, S2Container container, Object data,
            Object action, String resultExp) {
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.setAction(action);
        info.setContainer(container);
        info.setResultExp(resultExp);
        return toNextStatus(path, data, info);
    }

    public List getDataListFromPath(String path, Class tgtClass) {
        return getDataListFromPath(path, tgtClass, getRootContainer());
    }

    public List getDataListFromPath(String path, Class tgtClass, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setTargetDtoClass(tgtClass);
        systemContext.setContainer(container);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(
            systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path, (DataAccessFactory) execProcess,
            systemContext);
        return dataList;
    }

    public List getDataIDFromPath(String path, Class tgtClass) {
        return getDataIDFromPath(path, tgtClass, getRootContainer());
    }

    public List getDataIDFromPath(String path, Class tgtClass, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setTargetDtoClass(tgtClass);
        systemContext.setContainer(container);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(
            systemContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName(path, (DataAccessFactory) execProcess,
            systemContext);
        return dataList;
    }

    public List getPathFromData(String path, Object data) {
        return getPathFromData(path, data, getRootContainer());
    }

    public List getPathFromData(String path, Object data, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(data, null, null, null);
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setContainer(container);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(
            systemContext.getCallPath());
        List pathList = dataUtil.getBuriPathByDto(data, (DataAccessFactory) execProcess,
            systemContext);
        return pathList;
    }

    public long countByPathAndDatas(String path, List datas) {
        return countByPathAndDatas(path, datas, getRootContainer());
    }

    public long countByPathAndDatas(String path, List datas, S2Container container) {
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setContainer(container);
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(
            systemContext.getCallPath());
        long count = dataUtil.countByPathAndDatas(path, datas, (DataAccessFactory) execProcess,
            systemContext);
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
