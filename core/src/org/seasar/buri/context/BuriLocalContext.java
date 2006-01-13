/*
 * çÏê¨ì˙: 2005/10/08
 *
 */
package org.seasar.buri.context;


import java.util.Map;

import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.framework.container.S2Container;

public interface BuriLocalContext extends Map{
    public S2Container getContainer();
    public void setContainer(S2Container container);
    
    public void put(String name,Object data);
    public Object get(String name,Object defVal);
    
    public void putBool(String name,boolean bol);
    public boolean getBool(String name,Boolean defVal);
    
    public void setUserContext(BuriInnerContext context);
    public BuriInnerContext getUserContext();
    
    public Object getAction();
    public void setAction(Object action);

    public void setIsNotUpdateMode(boolean mode);
    public boolean getIsNotUpdateMode();
    
    public FlowPickout getFlowPickout();
    public void setFlowPickout(FlowPickout flowPickout);
    
    public BuriParticipant getBuriParticipant();
    public void setBuriParticipant(BuriParticipant buriParticipant);

    public Long getDataID();
    public void setDataID(Long dataID);
    public void setDataID(long dataID);
    
    public Object getData();
    public void setData(Object data);
    
    public String getDataClassName();
    
    public BuriPath getCallBuriPath();
    public void setCallBuriPath(BuriPath path);
    
    public Long getBranchIDContextKey();
    public void setBranchIDContextKey(Long key);

    public Object getUserData();
    public void setUserData(Object data);

}
