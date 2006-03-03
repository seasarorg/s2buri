/*
 * çÏê¨ì˙: 2006/01/10
 *
 */
package org.seasar.buri.common.util;

import java.lang.reflect.Field;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;

public class ClassDefUtilImpl implements ClassDefUtil{
    public String getClassName(Class clazz) {
        return clazz.getName();
    }
    public String getClassName(Object data) {
        if(data==null) {
            return "";
        }
        Class clazz = getClazz(data);
        String className = data.getClass().getName();
        String clazzName = clazz.getName();
        return className;
    }
    public Class getClazz(Object data) {
        if(data==null) {
            return null;
        }
        Class clazz = data.getClass();
        return clazz;
    }

    public Object getMethodSignatureValue(MethodInvocation invoke,String sig,String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(getClazz(invoke.getThis()));
        String argsName = methodName+sig;
        if(desc.hasField(argsName)) {
            Field field = desc.getField(argsName);
            return FieldUtil.get(field, null);
        }
        return null;
    }
}
