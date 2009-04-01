package example.org.escafe.buri.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

@SuppressWarnings("serial")
public class DebugDaoInterceptor extends AbstractInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return invocation.proceed();
	}
}
