/*
 * 作成日: 2006/01/10
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
        return removeEnhanPos(clazz.getName());
    }
    public String getClassName(Object data) {
        if(data==null) {
            return "";
        }
        Class clazz = getClazz(data);
        String className = data.getClass().getName();
        String clazzName = clazz.getName();
        className = removeEnhanPos(className);
        return className;
    }
    protected String removeEnhanPos(String clazzName) {
        if(clazzName.indexOf("$$") > -1) {
        	int enhansPos = clazzName.indexOf("$$");
        	clazzName = clazzName.substring(0,enhansPos);
        }
        return clazzName;
    }
    public Class getClazz(Object data) {
        if(data==null) {
            return null;
        }
        Class clazz = data.getClass();
        return clazz;
    }

    public Object getMethodSignatureValue(MethodInvocation invoke,String sig,String methodName) {
        return getMethodSignatureValue(getClazz(invoke.getThis()),sig,methodName);
    }

    public Object getMethodSignatureValue(Class tgtClass,String sig,String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(tgtClass);
        String argsName = methodName+sig;
        if(desc.hasField(argsName)) {
            Field field = desc.getField(argsName);
            return FieldUtil.get(field, null);
        }
        return null;
    }
    
    public static boolean isClassName(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
    
    public static boolean hasPropertyName(Class tgtClass,String propertyName) {
        String methodName = "get" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
        try {
            tgtClass.getMethod(methodName,new Class[]{});
        } catch (SecurityException e) {
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }
}
