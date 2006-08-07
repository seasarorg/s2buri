/*
 * çÏê¨ì˙: 2006/06/20
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.List;

import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.processor.BuriAutoSelectProcessor;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.SimpleBuriProcessor;
import org.seasar.buri.engine.processor.StandardBuriProcessor;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class BuriAutoSelectProcessorImpl implements BuriAutoSelectProcessor {
    private SimpleBuriProcessor simpleProcessor;
    private StandardBuriProcessor standardProcessor;
    private BuriEngine simpleEngine;
    private BuriEngine standardEngine;
    private S2Container container;
    

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
        Object result = null;
        if(isStdProcessor(path)) {
            result = standardProcessor.toNextStatus(path, data, userData,info);
        }
        if(isSimpleProcessor(path)) {
            result = simpleProcessor.toNextStatus(path, data, info);
        }
        return result;
    }
    
    protected BuriEngine getEngine(String buriPath) {
        if(isStdProcessor(buriPath)) {
            return standardEngine;
        }
        if(isSimpleProcessor(buriPath)) {
            return simpleEngine;
        }
        return null;
    }

    public List getDataListFromPath(String path, Object userData, Class tgtClass) {
        return getDataListFromPath(path, userData,tgtClass, getRootContainer());
    }
    
    public List getDataListFromPath(String path, Object userData,Class tgtClass, S2Container container) {
        List result = null;
        if(isStdProcessor(path)) {
            result = standardProcessor.getDataListFromPath(path, userData,tgtClass, container);
        }
        if(isSimpleProcessor(path)) {
            result = simpleProcessor.getDataListFromPath(path, tgtClass, container);
        }
        return result;
    }

    public List getDataIDFromPath(String path, Object userData, Class tgtClass) {
        return getDataIDFromPath(path, userData, tgtClass,getRootContainer());
    }

    public List getDataIDFromPath(String path, Object userData, Class tgtClass,S2Container container) {
        List result = null;
        if(isStdProcessor(path)) {
            result = standardProcessor.getDataIDFromPath(path, userData,tgtClass, container);
        }
        if(isSimpleProcessor(path)) {
            result = simpleProcessor.getDataIDFromPath(path, tgtClass, container);
        }
        return result;
    }

    public List getPathFromData(String path, Object data, Object userData,Class tgtClass) {
        return getPathFromData(path, data, userData,tgtClass, getRootContainer());
    }

    public List getPathFromData(String path, Object data, Object userData,Class tgtClass, S2Container container) {
        List result = null;
        if(isStdProcessor(path)) {
            result = standardProcessor.getPathFromData(path, data, userData,tgtClass, container);
        }
        if(isSimpleProcessor(path)) {
            result = simpleProcessor.getPathFromData(path, tgtClass, container);
        }
        return result;
    }

    public long countByPathAndDatas(String path, List datas, Object userData) {
        return countByPathAndDatas(path, datas, userData,getRootContainer());
    }

    public long countByPathAndDatas(String path, List datas, Object userData,S2Container container) {
        long result = 0;
        if(isStdProcessor(path)) {
            result = standardProcessor.countByPathAndDatas(path, datas, userData,container);
        }
        if(isSimpleProcessor(path)) {
            result = simpleProcessor.countByPathAndDatas(path, datas,container);
        }
        return result;
    }
    
    public boolean isStdProcessor(String buriPath) {
        BuriPath path = new BuriPath(buriPath);
        if(standardEngine.hasPackage(path.getWorkflowPackage())) {
            return true;
        }
        return false;
    }
    
    public DataAccessFactory getDataAccessFactory(String buriPath) {
        DataAccessFactory accessFactory = null;
        BuriPath path = new BuriPath(buriPath);
        if(isStdProcessor(buriPath)) {
            accessFactory = (DataAccessFactory)standardEngine.selectDirectProcess(path);
        }
        if(isSimpleProcessor(buriPath)) {
            accessFactory = (DataAccessFactory)simpleEngine.selectDirectProcess(path);
        }
        return accessFactory;
    }
    
    public boolean isSimpleProcessor(String buriPath) {
        BuriPath path = new BuriPath(buriPath);
        if(simpleEngine.hasPackage(path.getWorkflowPackage())) {
            return true;
        }
        return false;
    }

    protected S2Container getRootContainer() {
        return container.getRoot();
    }

    public BuriEngine getSimpleEngine() {
        return simpleEngine;
    }

    public void setSimpleEngine(BuriEngine simpleEngine) {
        this.simpleEngine = simpleEngine;
    }

    public SimpleBuriProcessor getSimpleProcessor() {
        return simpleProcessor;
    }

    public void setSimpleProcessor(SimpleBuriProcessor simpleProcessor) {
        this.simpleProcessor = simpleProcessor;
    }

    public BuriEngine getStandardEngine() {
        return standardEngine;
    }

    public void setStandardEngine(BuriEngine standardEngine) {
        this.standardEngine = standardEngine;
    }

    public StandardBuriProcessor getStandardProcessor() {
        return standardProcessor;
    }

    public void setStandardProcessor(StandardBuriProcessor standardProcessor) {
        this.standardProcessor = standardProcessor;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }
    
    
}
