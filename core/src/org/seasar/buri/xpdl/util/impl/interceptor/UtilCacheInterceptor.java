/*
 * çÏê¨ì˙: 2005/11/22
 *
 */
package org.seasar.buri.xpdl.util.impl.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class UtilCacheInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = -637591514028051118L;
    private Map utilCaches = new HashMap();

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String cachesKey = invocation.getThis().getClass().getName();
        Map thisCache = getThisCache(cachesKey);
        String key = createKey(invocation);
        if(thisCache.containsKey(key)) {
            return thisCache.get(key);
        }
        Object ret = invocation.proceed();
        thisCache.put(key,ret);
        return ret;
    }
    
    public String createKey(MethodInvocation invocation) {
        String key = invocation.getMethod().getName();
        String argKey = "";
        for(int i=0; i < invocation.getArguments().length ; i++) {
            String addKeyStr = "";
            addKeyStr = invocation.getArguments()[i].toString();
            argKey = argKey + "/" + addKeyStr;
        }
        return key+argKey;
        
    }
    
    private Map getThisCache(String cachesKey) {
        if(utilCaches.containsKey(cachesKey)) {
            return (Map)utilCaches.get(cachesKey);
        }
        Map thisCache = new HashMap();
        utilCaches.put(cachesKey,thisCache);
        return thisCache;
    }

}
