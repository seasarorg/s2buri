/*
 * 作成日: 2006/07/10
 *
 */
package org.escafe.buri.common.delayloader.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.escafe.buri.common.delayloader.DelayLoader;
import org.escafe.buri.common.util.ScriptProcessor;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;

public class ExecuteInterceptor extends AbstractInterceptor {
    private String keyOgnl;
    private DelayLoader loader;
    //    private ScriptFactory factory;
    private S2Container container;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ScriptProcessor processor = new ScriptProcessor();
        //        Script script = factory.getScript("ognl");
        Map context = new HashMap();
        context.put("arg", invocation.getArguments());
        String key = processor.getValue(keyOgnl, container.getRoot(), context).toString();
        //        String key = script.eval(container.getRoot(),keyOgnl,context).toString();
        loader.read(invocation.getThis(), key);
        return invocation.proceed();
    }

    public DelayLoader getLoader() {
        return loader;
    }

    public void setLoader(DelayLoader loader) {
        this.loader = loader;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getKeyOgnl() {
        return keyOgnl;
    }

    public void setKeyOgnl(String keyOgnl) {
        this.keyOgnl = keyOgnl;
    }

}
