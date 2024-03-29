package org.escafe.buri.common.util;

import java.lang.reflect.Field;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;

public class ClassDefUtilImpl implements ClassDefUtil {
	public String getClassName(Class<?> clazz) {
		return removeEnhanPos(clazz.getName());
	}

	public String getClassName(Object data) {
		if (data == null) {
			return "";
		}
		String className = data.getClass().getName();
		className = removeEnhanPos(className);
		return className;
	}

	protected String removeEnhanPos(String clazzName) {
		if (clazzName.indexOf("$$") > -1) {
			int enhansPos = clazzName.indexOf("$$");
			clazzName = clazzName.substring(0, enhansPos);
		}
		return clazzName;
	}

	public Class<?> getClazz(Object data) {
		if (data == null) {
			return null;
		}
		Class<?> clazz = data.getClass();
		return clazz;
	}

	public Object getMethodSignatureValue(MethodInvocation invoke, String sig,
	        String methodName) {
		return getMethodSignatureValue(
		    getClazz(invoke.getThis()),
		    sig,
		    methodName);
	}

	public Object getMethodSignatureValue(Class<?> tgtClass, String sig,
	        String methodName) {
		BeanDesc desc = BeanDescFactory.getBeanDesc(tgtClass);
		String argsName = methodName + sig;
		if (desc.hasField(argsName)) {
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

	public static boolean hasPropertyName(Class<?> tgtClass, String propertyName) {
		BeanDesc desc = BeanDescFactory.getBeanDesc(tgtClass);
		return desc.hasPropertyDesc(propertyName);
	}
}
