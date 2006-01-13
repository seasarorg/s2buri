/*
 * çÏê¨ì˙: 2006/01/10
 *
 */
package org.seasar.buri.common.util;

public class ClassDefUtilImpl implements ClassDefUtil{
    public String getClassName(Class clazz) {
        return clazz.getName();
    }
    public String getClassName(Object data) {
        Class clazz = getClazz(data);
        String className = data.getClass().getName();
        String clazzName = clazz.getName();
        return className;
    }
    public Class getClazz(Object data) {
        Class clazz = data.getClass();
        return clazz;
    }
}
