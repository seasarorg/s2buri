/*
 * 作成日: 2006/07/10
 *
 */
package org.seasar.buri.common.delayloader.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.common.delayloader.DelayLoader;
import org.seasar.buri.common.delayloader.DelayLoaderInfo;
import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;

public class FileLoadInterceptor extends AbstractInterceptor {
    private int keyNo = 0;
    private int resourceNo = 0;
    private DelayLoader loader;
    private String afterCallLoader = null;
    private S2Container container;
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String key = invocation.getArguments()[keyNo].toString();
        String resource = invocation.getArguments()[resourceNo].toString();
        DelayLoaderInfo info = new DelayLoaderInfo();
        info.setTgtKey(key);
        info.setName(resource);
        info.setInvoke(invocation);
        loader.addFileLoader(invocation.getThis(),info);
        if( ! StringUtil.isEmpty(afterCallLoader)) {
            ScriptProcessor processor = new ScriptProcessor();
            Map context = new HashMap();
            context.put("self",invocation.getThis());
            context.put("arg",invocation.getArguments());
            processor.getValue(afterCallLoader,container,context);
        }
        return null;
    }

    public int getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(int keyNo) {
        this.keyNo = keyNo;
    }

    public int getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(int resourceNo) {
        this.resourceNo = resourceNo;
    }

    public DelayLoader getLoader() {
        return loader;
    }

    public void setLoader(DelayLoader loader) {
        this.loader = loader;
    }

    public String getAfterCallLoader() {
        return afterCallLoader;
    }

    public void setAfterCallLoader(String afterCallLoader) {
        this.afterCallLoader = afterCallLoader;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

}
