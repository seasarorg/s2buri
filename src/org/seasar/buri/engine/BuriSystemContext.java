/*
 * çÏê¨ì˙: 2006/03/21
 *
 */
package org.seasar.buri.engine;

import java.util.HashMap;
import java.util.Stack;

public class BuriSystemContext extends HashMap {
    
    private static final long serialVersionUID = 1L;
    
    private BuriPath callPath;
    private Stack pathStack;

    public BuriUserContext getUserContext() {
        return (BuriUserContext)super.get("userContext");
    }
    public void setUserContext(BuriUserContext context) {
        super.put("userContext",context);
    }
    public BuriPath getCallPath() {
        return callPath;
    }
    public void setCallPath(BuriPath callPath) {
        this.callPath = callPath;
    }
    public Stack getPathStack() {
        return pathStack;
    }
    public void setPathStack(Stack pathStack) {
        this.pathStack = pathStack;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append(super.toString());
        buff.append("/callPath=").append(callPath);
        buff.append("/pathStack=").append(pathStack);
        buff.append("]");
        return buff.toString();
    }
    
}
