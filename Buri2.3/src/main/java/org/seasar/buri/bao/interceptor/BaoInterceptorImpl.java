/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.bao.BaoInvokeMetadata;
import org.seasar.buri.bao.BaoInvoker;
import org.seasar.buri.bao.BaoMetadata;
import org.seasar.buri.bao.BaoMetadataFactory;
import org.seasar.buri.bao.BaoStatusMetadata;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.util.MethodUtil;

public class BaoInterceptorImpl extends AbstractInterceptor {
    private BaoMetadataFactory factory;
    private BaoInvoker invoker;
    private static final long serialVersionUID = -4368109274110757228L;

    public Object invoke(MethodInvocation invoke) throws Throwable {
        Method method = invoke.getMethod();
        if (!MethodUtil.isAbstract(method)) {
            return invoke.proceed();
        }
        BaoMetadata baoMetadata = factory.getBaoMetadata(invoke);
        if(factory.isStatusMetadata(baoMetadata,invoke)) {
            BaoStatusMetadata statusMetadata = factory.getBaoStatusMetadata(baoMetadata,invoke);
            Object result = invoker.getDataFromStatus(statusMetadata,invoke);
            return result;
        } else {
            BaoInvokeMetadata invokeMetadata= factory.getBaoInvokeMetadata(baoMetadata,invoke);
            Object result = invoker.invoke(invokeMetadata,invoke);
            return result;
        }
    }

    public BaoMetadataFactory getFactory() {
        return factory;
    }

    public void setFactory(BaoMetadataFactory factory) {
        this.factory = factory;
    }

    public BaoInvoker getInvoker() {
        return invoker;
    }

    public void setInvoker(BaoInvoker invoker) {
        this.invoker = invoker;
    }

}
