/*
 * 作成日: 2006/01/10
 *
 */
package org.seasar.buri.common.util;

import org.aopalliance.intercept.MethodInvocation;

public interface ClassDefUtil {
    String getClassName(Class clazz);
    Class getClazz(Object data);
    String getClassName(Object data);
    Object getMethodSignatureValue(Class tgtClass,String sig,String methodName);
    Object getMethodSignatureValue(MethodInvocation invoke,String sig,String methodName);
}
