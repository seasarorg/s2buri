/*
 * çÏê¨ì˙: 2005/11/28
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.Map;

import org.seasar.framework.container.S2Container;

public class InnerSimpleBuriInvokerImpl extends SimpleBuriInvokerImpl {
    
    protected Object invoke(String path, S2Container s2con, Object data, Object userData,Object action, String context, boolean notUpdateMode,Map appendContext) {
        return innerInvoke(path, s2con, data, userData,action, context, notUpdateMode,appendContext,null);
    }
    

}
