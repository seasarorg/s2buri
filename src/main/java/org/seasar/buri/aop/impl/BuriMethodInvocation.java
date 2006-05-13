/*
 * çÏê¨ì˙: 2006/04/05
 *
 */
package org.seasar.buri.aop.impl;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.seasar.framework.aop.S2MethodInvocation;
import org.seasar.framework.util.MethodUtil;

public class BuriMethodInvocation implements S2MethodInvocation {
    private Class targetClass;
    private Object[] arguments;
    private Method method;
    private Object thisObject;
    
    private Method callMethod;
    private Object[] callArguments;

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getParameter(String arg0) {
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object proceed() throws Throwable {
        Object val = MethodUtil.invoke(callMethod,thisObject,callArguments);
        return val;
    }

    public Object getThis() {
        return thisObject;
    }

    public AccessibleObject getStaticPart() {
        return method;
    }

    public Object getThisObject() {
        return thisObject;
    }

    public void setThisObject(Object thisObject) {
        this.thisObject = thisObject;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object[] getCallArguments() {
        return callArguments;
    }

    public void setCallArguments(Object[] callArguments) {
        this.callArguments = callArguments;
    }

    public Method getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(Method callMethod) {
        this.callMethod = callMethod;
    }

}
