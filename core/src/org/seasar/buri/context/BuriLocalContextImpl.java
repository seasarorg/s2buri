/*
 * çÏê¨ì˙: 2005/10/08
 *
 */
package org.seasar.buri.context;


import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.framework.container.S2Container;

public class BuriLocalContextImpl extends HashMap implements BuriLocalContext {
    private static final long serialVersionUID = 3822424614720128371L;
    private S2Container container;
    private ClassDefUtil classDefUtil;
    

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }
    
//    protected Map getBaseContext() {
//        return ;
////        BuriInnerContextImpl localContext = (BuriInnerContextImpl)container.getComponent(BuriInnerContext.class);
////        return localContext;
//    }
    
    public String toString() {
        return super.toString();
    }
    

    public void put(String name, Object data) {
        super.put(name, data);
    }
//    public void put(String name,Object data) {
//        getBaseContext().put(name,data);
//    }
    public Object get(String name,Object defVal) {
        Map localContext = this;
        if(localContext.containsKey(name)) {
            return localContext.get(name);
        } else {
            put(name,defVal);
            return defVal;
        }
    }
    
    public void putBool(String name,boolean bol) {
        put(name,new Boolean(bol));
    }
    public boolean getBool(String name,Boolean defVal) {
        return ((Boolean)get(name,defVal)).booleanValue();
    }
    
    public void setUserContext(BuriInnerContext context) {
//        put("UserContext",context);
    }
    public BuriInnerContext getUserContext() {
        BuriInnerContextImpl localContext = (BuriInnerContextImpl)container.getComponent(BuriInnerContext.class);
        return localContext;
//        return (BuriInnerContext)get("UserContext",new BuriInnerContextImpl());
    }
    

    public Object getAction() {
        return get("Action","");
    }

    public void setAction(Object action) {
        put("Action",action);
    }

    public void setIsNotUpdateMode(boolean mode) {
        putBool("IsNotUpdateMode",mode);
    }

    public boolean getIsNotUpdateMode() {
        return getBool("IsNotUpdateMode",Boolean.FALSE);
    }
    
    public FlowPickout getFlowPickout() {
        return (FlowPickout)get("FlowPickout",container.getComponent(FlowPickout.class));
    }

    public void setFlowPickout(FlowPickout flowPickout) {
        put("FlowPickout",flowPickout);
    }
    
    public BuriParticipant getBuriParticipant() {
        BuriParticipant participant = null;
        if(container.hasComponentDef(BuriParticipant.class)){
            participant = (BuriParticipant)container.getComponent(BuriParticipant.class);
        }
        participant = (BuriParticipant)get("BuriParticipant",participant);
        return participant;
    }

    public void setBuriParticipant(BuriParticipant buriParticipant) {
        put("BuriParticipant",buriParticipant);
    }
    
    public Long getDataID() {
        return (Long)get("DataID",new Long(0));
    }
    
    public void setDataID(long dataID) {
        setDataID(new Long(dataID));
    }
    
    public void setDataID(Long dataID) {
        put("DataID",dataID);
    }

    public Object getData() {
        return get("Data",null);
    }
    public void setData(Object data) {
        put("Data",data);
    }
    public String getDataClassName() {
        Object data = get("Data",null);
        if(data != null) {
            return classDefUtil.getClassName(data);
        }
        return null;
    }
    public BuriPath getCallBuriPath() {
        return (BuriPath)get("CallBuriPath",null);
    }
    public void setCallBuriPath(BuriPath path) {
        put("CallBuriPath",path);
    }
    public Long getBranchIDContextKey() {
        return ((Long)get("BranchIDContextKey",new Long(0)));
    }
    public void setBranchIDContextKey(Long key) {
        put("BranchIDContextKey",key);
    }
    public Object getUserData() {
        return get("UserData",null);
    }
    public void setUserData(Object data){
        put("UserData",data);
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

//    public int size() {
//        return getBaseContext().size();
//    }
//
//    public boolean isEmpty() {
//        return getBaseContext().isEmpty();
//    }
//
//    public boolean containsKey(Object key) {
//        return getBaseContext().containsKey(key);
//    }
//
//    public boolean containsValue(Object value) {
//        return getBaseContext().containsValue(value);
//    }
//
//    public Object get(Object key) {
//        return getBaseContext().get(key);
//    }
//
//    public Object put(Object arg0, Object arg1) {
//        return getBaseContext().put(arg0, arg1);
//    }
//
//    public Object remove(Object key) {
//        return getBaseContext().remove(key);
//    }
//
//    public void putAll(Map arg0) {
//        getBaseContext().putAll(arg0);
//    }
//
//    public void clear() {
//        getBaseContext().clear();
//    }
//
//    public Set keySet() {
//        return getBaseContext().keySet();
//    }
//
//    public Collection values() {
//        return getBaseContext().values();
//    }
//
//    public Set entrySet() {
//        return getBaseContext().entrySet();
//    }
    
}
