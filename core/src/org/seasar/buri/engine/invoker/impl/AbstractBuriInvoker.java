/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.engine.invoker.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.datautil.DataIDListUtil;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;


public abstract class AbstractBuriInvoker {
    protected BuriEngine buriEngine;
    protected S2Container container;
    private ContextUtil contextUtil;
    protected DataIDListUtil listUtil;
    
    protected Object getObjectFromContext(String context) {
        if(StringUtil.isEmpty(context)) {
            return null;
        }
        ScriptProcessor processor = new ScriptProcessor();
        Object data = processor.getValue(context,null,getContextUtil().getLocalContext().getUserContext());
        return data;
    }
    
    protected S2Container getTargetContainer() {
        return container.getRoot();
    }
    
    protected Object invokeImpl(String path, S2Container container, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        BuriPath buriPath = new BuriPath(path);
        Object result = invokeImpl(buriPath, container, data, userData,action, context, notUpdateMode,appendContext,locContext);
        return result;
    }
    
    protected Object invokeImpl(BuriPath buriPath, S2Container container, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        BuriLocalContext localContext = getContextUtil().getLocalContext();
        buriEngine.createContext(data,container);
        setupBuriLocalContext(container, data, userData,action);
        localContext.getUserContext().put("callPath",buriPath);
        localContext.getUserContext().put("UserContext",localContext.getUserContext());
        if(appendContext!=null) {
            localContext.getUserContext().putAll(appendContext);
        }
        localContext.getUserContext().setData(data);
        localContext.setIsNotUpdateMode(notUpdateMode);
        if(locContext !=null) {
            localContext.putAll(locContext);
        }
        buriEngine.execute(buriPath);
        Object result = getObjectFromContext(context);
        return result;
    }
    
    protected Object invoke(String path, S2Container container, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map localContext) {
//        return innerInvoke(path, container, data, userData,action, context, notUpdateMode,appendContext,localContext);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        Object result = null;
        if(data instanceof List) {
            Iterator ite = ((List)data).iterator();
            while(ite.hasNext()) {
                Object tgt= ite.next();
                result = invoke(path, container, tgt, userData,action, context, notUpdateMode,appendContext,localContext);
            }
        } else {
            result = invokeImpl(path, container, data, userData,action, context, notUpdateMode,appendContext,localContext);
        }
        backup.restore();
        return result;
    }
    protected Object innerInvoke(String path, S2Container s2con, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        BuriPath buriPath = new BuriPath(path);
        Object result = innerInvoke(buriPath, s2con, data, userData,action, context, notUpdateMode,appendContext,locContext);
        return result;
    }
    protected Object innerInvoke(BuriPath buriPath, S2Container s2con, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        S2Container container = getContextUtil().getLocalContext().getContainer();
        ContextBackup backup = new ContextBackup();
        backup.backup();
        Object result = null;
        if(data instanceof List) {
            result = invokeList(buriPath, container, (List)data, userData,action, context, notUpdateMode,appendContext,locContext);
        } else {
            result = invokeOne(buriPath, container, data, userData,action, context, notUpdateMode,appendContext,locContext);
        }
        backup.restore();
        return result;
    }
    protected Object invokeOne(BuriPath buriPath, S2Container container, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        getContextUtil().getLocalContext().setContainer(container);
//        getContextUtil().getLocalContext().clear();
//        getContextUtil().getContext().clear();
        Object result = invokeImpl(buriPath, container, data, userData,action, context, notUpdateMode,appendContext,locContext);
        return result;
    }
    
    protected Object invokeList(BuriPath buriPath, S2Container container, List data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        Iterator ite = ((List)data).iterator();
        Object result = null;
        while(ite.hasNext()) {
            Object tgt= ite.next();
            result = invokeOne(buriPath, container, tgt, userData,action, context, notUpdateMode,appendContext,locContext);
        }
        return result;
    }
    
    protected Object invokeOne(String path, S2Container container, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext,Map locContext) {
        getContextUtil().getLocalContext().setContainer(container);
//        getContextUtil().getLocalContext().clear();
//        getContextUtil().getContext().clear();
        Object result = invokeImpl(path, container, data, userData,action, context, notUpdateMode,appendContext,locContext);
        return result;
    }
    
    protected void setupBuriLocalContext(S2Container container, Object userData) {
        setupBuriLocalContext(container, null, userData,null);
    }
    
    protected void setupBuriLocalContext(S2Container container, Object userData,Map appendContext) {
        setupBuriLocalContext(container, null, userData,null,appendContext);
    }
    protected void setupBuriLocalContext(S2Container container, Object data, Object userData,Object action) {
        setupBuriLocalContext(container, data, userData,action,new HashMap());
    }
    protected void setupBuriLocalContext(S2Container container, Object data, Object userData,Object action,Map appendContext) {
        getContextUtil().getLocalContext().setContainer(container);
        BuriLocalContext localContext = getContextUtil().getLocalContext();
        localContext.setContainer(container);
        localContext.setData(data);
        localContext.getUserContext().setData(data);
        localContext.setUserData(userData);
        localContext.getUserContext().setUserData(userData);
        localContext.getUserContext().put("action",action);
        localContext.getUserContext().putAll(appendContext);
        localContext.setAction(action);
    }

    public BuriEngine getBuriEngine() {
        return buriEngine;
    }

    public void setBuriEngine(BuriEngine buriEngine) {
        this.buriEngine = buriEngine;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public S2Container getContainer() {
        return container;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public DataIDListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(DataIDListUtil listUtil) {
        this.listUtil = listUtil;
    }
    
    public class ContextBackup {
        public HashMap innerContext = new HashMap();
        public HashMap localContext = new HashMap();
        public BuriParticipant participant;
        public void backup() {
            participant = contextUtil.getLocalContext().getBuriParticipant();
            localContext.putAll(contextUtil.getLocalContext());
            innerContext.putAll(contextUtil.getContext());
            contextUtil.getLocalContext().clear();
            contextUtil.getContext().clear();
            contextUtil.getLocalContext().setBuriParticipant(new BuriParticipant());
        }
        public void restore() {
            contextUtil.getLocalContext().putAll(localContext);
            contextUtil.getContext().putAll(innerContext);
            contextUtil.getLocalContext().setBuriParticipant(participant);
        }
    }
}
