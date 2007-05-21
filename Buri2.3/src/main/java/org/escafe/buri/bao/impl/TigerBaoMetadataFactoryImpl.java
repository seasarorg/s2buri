/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActionConverter;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriArgs;
import org.escafe.buri.annotation.BuriConverter;
import org.escafe.buri.annotation.BuriResult;
import org.escafe.buri.annotation.BuriUserInfo;
import org.escafe.buri.annotation.BuriUserInfoClass;
import org.escafe.buri.bao.BaoFunctionMetadata;
import org.escafe.buri.bao.BaoInvokeMetadata;
import org.escafe.buri.bao.BaoMetadata;
import org.escafe.buri.bao.BaoMetadataFactory;
import org.escafe.buri.bao.BaoStatusMetadata;
import org.escafe.buri.bao.BuriConvert;
import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.common.util.IsNumberUtil;
import org.escafe.buri.exception.BuriBaoException;
import org.seasar.framework.aop.S2MethodInvocation;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;

public class TigerBaoMetadataFactoryImpl implements BaoMetadataFactory {
    private HashMap metadatas = new HashMap();
    private ClassDefUtil classDefUtil;
    private final static String ARGS = "_ARGS";

    private final static String ACTIVITY = "_ACTIVITY";

    private static final String ACTION = "_ACTION";
    private static final String ACTCONVERTER = "_CONVERTER";
    private static final String RESULT = "_RESULT";

    private static final String PROCESS = "PROCESS";
    private static final String USERINFO = "USERINFO";
    private static final String GLBLCONVERTER = "CONVERTER";
    private static final String TARGETDTO = "TARGETDTO";

    public void dispose() {
        metadatas.clear();
    }

    public BaoMetadata getBaoMetadata(MethodInvocation invoke) {
        BaoMetadata metadata = null;
        Class clazz = classDefUtil.getClazz(invoke.getThis());
        synchronized (metadatas) {
            if (metadatas.containsKey(clazz)) {
                metadata = (BaoMetadata) metadatas.get(clazz);
            } else {
                metadata = createBaoMetadata(invoke);
                metadatas.put(clazz, metadata);
            }
        }
        return metadata;
    }

    protected BaoMetadata createBaoMetadata(MethodInvocation invoke) {
        BaoMetadata metadata = new BaoMetadataImpl();
        updateProcess(metadata, invoke);
        updateUserInfo(metadata, invoke);
        updateGlobalConverter(metadata, invoke);
        updateTargetDto(metadata, invoke);
        return metadata;
    }

    protected Class getInvokeClass(MethodInvocation invoke) {
        Class targetClass = null;
        if (invoke instanceof S2MethodInvocation) {
            S2MethodInvocation s2Invoke = (S2MethodInvocation) invoke;
            targetClass = s2Invoke.getTargetClass();
        }
        return targetClass;
    }

    protected <T> T getAnnotationByClass(Class<? extends Annotation> annotation, MethodInvocation invoke) {
        Class targetClass = getInvokeClass(invoke);
        if (targetClass != null) {
            return (T) targetClass.getAnnotation(annotation);
        } else {
            return null;
        }
    }

    protected <T> T getAnnotationByMethod(Class<? extends Annotation> annotation, MethodInvocation invoke) {
        Method targetMethod = invoke.getMethod();
        if (targetMethod != null) {
            return (T) targetMethod.getAnnotation(annotation);
        } else {
            return null;
        }
    }

    protected void updateProcess(BaoMetadata metadata, MethodInvocation invoke) {
        Buri buri = getAnnotationByClass(Buri.class, invoke);
        if (buri != null) {
            metadata.setProcess(buri.process());
            return;
        }
        Object val = getSignatureValue(invoke, PROCESS);
        if (val != null) {
            metadata.setProcess(val.toString());
            return;
        }
        throw createBaoExcep("EBRI0021", invoke);
    }

    protected void updateUserInfo(BaoMetadata metadata, MethodInvocation invoke) {
        BuriUserInfo userInfo = getAnnotationByClass(BuriUserInfo.class, invoke);
        if (userInfo != null) {
            metadata.setUserInfo(userInfo.value());
            return;
        }
        BuriUserInfoClass userInfoClass = getAnnotationByClass(BuriUserInfoClass.class, invoke);
        if (userInfoClass != null) {
            metadata.setUserInfo(userInfoClass.value());
            return;
        }
        Object val = getSignatureValue(invoke, USERINFO);
        if (val != null) {
            if (val instanceof String) {
                metadata.setUserInfo(val.toString());
                return;
            } else {
                metadata.setUserInfo((Class) val);
                return;
            }
        }
    }

    protected void updateGlobalConverter(BaoMetadata metadata, MethodInvocation invoke) {
        BuriConverter converter = getAnnotationByClass(BuriConverter.class, invoke);
        if (converter != null) {
            org.escafe.buri.annotation.BuriConversionRule[] converters = converter.value();
            for (int i = 0; i < converters.length; i++) {
                BuriConvert buriConvert = new BuriConvert(converters[i].convertClass(), converters[i].ognl());
                String className = classDefUtil.getClassName(converters[i].convertClass());
                metadata.getConverter().put(className, buriConvert);
            }
            return;
        }
        Object val = getSignatureValue(invoke, GLBLCONVERTER);
        if (val != null) {
            BuriConvert[] converters = (BuriConvert[]) val;
            for (int i = 0; i < converters.length; i++) {
                String className = classDefUtil.getClassName(converters[i].getClazz());
                metadata.getConverter().put(className, converters[i]);
            }
            return;
        }
    }

    protected void updateTargetDto(BaoMetadata metadata, MethodInvocation invoke) {
        Buri buri = getAnnotationByClass(Buri.class, invoke);
        if (buri != null) {
            metadata.setTargetDto(buri.dtoClass());
            return;
        }
        Object val = getSignatureValue(invoke, TARGETDTO);
        if (val != null) {
            metadata.setTargetDto((Class) val);
            return;
        }
        throw createBaoExcep("EBRI0020", invoke);
    }

    public boolean isStatusMetadata(BaoMetadata metadata, MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = metadata.getMetadata(invoke);
        if (funcMetadata == null) {
            String methodName = invoke.getMethod().getName();
            if (isStatusMethodName(methodName)) {
                return true;
            }
        } else if (funcMetadata instanceof BaoStatusMetadata) {
            return true;
        }
        return false;
    }

    protected boolean isStatusMethodName(String methodName) {
        if (methodName.startsWith("get")) {
            return true;
        }
        if (methodName.startsWith("find")) {
            return true;
        }
        if (methodName.startsWith("select")) {
            return true;
        }
        if (methodName.startsWith("count")) {
            return true;
        }
        return false;

    }

    public BaoInvokeMetadata getBaoInvokeMetadata(BaoMetadata metadata, MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = metadata.getMetadata(invoke);
        if (funcMetadata == null) {
            funcMetadata = createBaoInvokeMetadata(metadata, invoke);
            metadata.addMetadata(invoke, funcMetadata);
        }
        return (BaoInvokeMetadata) funcMetadata;
    }

    protected BaoInvokeMetadata createBaoInvokeMetadata(BaoMetadata metadata, MethodInvocation invoke) {
        BaoInvokeMetadata invokeMetadata = new BaoInvokeMetadataImpl();
        setupArgs(invokeMetadata, invoke);
        setupActivityName(invokeMetadata, invoke);
        updateAction(invokeMetadata, invoke);
        updateActionConverter(invokeMetadata, invoke);
        updateResult(invokeMetadata, invoke);
        invokeMetadata.setBaoMetadata(metadata);
        validationBaoInvoke(invokeMetadata, invoke);
        return invokeMetadata;
    }

    protected void updateAction(BaoInvokeMetadata invokeMetadata, MethodInvocation invoke) {
        BuriAction action = getAnnotationByMethod(BuriAction.class, invoke);
        if (action != null) {
            invokeMetadata.setAction(action.value());
            return;
        }
        Object val = getMethodSignatureValue(invoke, ACTION);
        if (val != null) {
            invokeMetadata.setAction(val.toString());
            return;
        }
    }

    protected void updateActionConverter(BaoInvokeMetadata invokeMetadata, MethodInvocation invoke) {
        BuriActionConverter actionConvert = getAnnotationByMethod(BuriActionConverter.class, invoke);
        if (actionConvert != null) {
            BuriConvert buriConvert = new BuriConvert(null, actionConvert.value());
            invokeMetadata.setBuriConvert(buriConvert);
            return;
        }
        Object val = getMethodSignatureValue(invoke, ACTCONVERTER);
        if (val != null) {
            invokeMetadata.setBuriConvert((BuriConvert) val);
            return;
        }
    }

    protected void updateResult(BaoInvokeMetadata invokeMetadata, MethodInvocation invoke) {
        BuriResult result = getAnnotationByMethod(BuriResult.class, invoke);
        if (result != null) {
            invokeMetadata.setResult(result.value());
            return;
        }
        Object val = getMethodSignatureValue(invoke, RESULT);
        if (val != null) {
            invokeMetadata.setResult(val.toString());
            return;
        }
    }

    protected void validationBaoInvoke(BaoInvokeMetadata invokeMetadata, MethodInvocation invoke) {
        if (!StringUtil.isEmpty(invokeMetadata.getResult())) {
            boolean errorRetType = false;
            Class retType = invoke.getMethod().getReturnType();
            if (retType.equals(Void.class)) {
                errorRetType = true;
            }
            if (errorRetType) {
                throw createBaoExcep("EBRI0025", invoke);
            }
        }
        if (invoke.getArguments().length != invokeMetadata.getArgName().size()) {
            throw createBaoExcep("EBRI0024", invoke);
        }
    }

    public BaoStatusMetadata getBaoStatusMetadata(BaoMetadata metadata, MethodInvocation invoke) {
        BaoFunctionMetadata statusMetadata = metadata.getMetadata(invoke);
        if (statusMetadata == null) {
            statusMetadata = createBaoStatusMetadata(metadata, invoke);
            metadata.addMetadata(invoke, statusMetadata);
        }
        return (BaoStatusMetadata) statusMetadata;
    }

    protected BaoFunctionMetadata createBaoStatusMetadata(BaoMetadata metadata, MethodInvocation invoke) {
        BaoStatusMetadata smd = new BaoStatusMetadataImpl();
        smd.setBaoMetadata(metadata);
        setupArgs(smd, invoke);
        setupActivityName(smd, invoke);
        if (smd.getActivityName().length() == 0) {
            throw createBaoExcep("EBRI0023", invoke);
        }
        validationBaoStatus(smd, invoke);
        return smd;
    }

    protected void validationBaoStatus(BaoStatusMetadata smd, MethodInvocation invoke) {
        Class retType = invoke.getMethod().getReturnType();
        if (!ClassUtil.isAssignableFrom(retType, List.class) && !IsNumberUtil.isNumberType(retType)) {
            throw createBaoExcep("EBRI0022", invoke);
        }
        if (invoke.getArguments().length != smd.getArgName().size()) {
            throw createBaoExcep("EBRI0024", invoke);
        }
    }

    protected void setupActivityName(BaoFunctionMetadata funcMetadata, MethodInvocation invoke) {
        BuriActivity activity = getAnnotationByMethod(BuriActivity.class, invoke);
        if (activity != null) {
            String acts[] = activity.value();
            for (int i = 0; i < acts.length; i++) {
                funcMetadata.addActivityNames(acts[i]);
            }
            return;
        }
        Object val = getMethodSignatureValue(invoke, ACTIVITY);
        if (val != null) {
            String acts[] = val.toString().split(",");
            for (int i = 0; i < acts.length; i++) {
                funcMetadata.addActivityNames(acts[i]);
            }
            return;
        }
    }

    protected void setupArgs(BaoFunctionMetadata fm, MethodInvocation invoke) {
        if (invoke.getArguments().length == 0) {
            fm.setArgName(Arrays.asList(new String[0]));
            return;
        }
        BuriArgs buriArgs = getAnnotationByMethod(BuriArgs.class, invoke);
        if (buriArgs != null) {
            String[] args = buriArgs.value();
            fm.setArgName(Arrays.asList(args));
            return;
        }
        Object val = getMethodSignatureValue(invoke, ARGS);
        if (val != null) {
            String[] args = convArgName(val.toString(), invoke);
            fm.setArgName(Arrays.asList(args));
            return;
        }
    }

    protected String[] convArgName(String arg, MethodInvocation invoke) {
        String argData[] = arg.split(",");
        for (int i = 0; i < argData.length; i++) {
            argData[i] = argData[i].trim();
        }
        return argData;
    }

    protected Object getMethodSignatureValue(MethodInvocation invoke, String sig) {
        String methodName = invoke.getMethod().getName();
        return getMethodSignatureValue(invoke, sig, methodName);
    }

    protected Object getSignatureValue(MethodInvocation invoke, String sig) {
        String methodName = "";
        return getMethodSignatureValue(invoke, sig, methodName);
    }

    protected Object getMethodSignatureValue(MethodInvocation invoke, String sig, String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(classDefUtil.getClazz(invoke.getThis()));
        String argsName = methodName + sig;
        if (desc.hasField(argsName)) {
            Field field = desc.getField(argsName);
            return FieldUtil.get(field, null);
        }
        return null;
    }

    protected boolean hasMethodSignature(MethodInvocation invoke, String sig) {
        String methodName = invoke.getMethod().getName();
        return hasMethodSignature(invoke, sig, methodName);
    }

    protected boolean hasSignature(MethodInvocation invoke, String sig) {
        return hasMethodSignature(invoke, sig, "");
    }

    protected boolean hasMethodSignature(MethodInvocation invoke, String sig, String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(classDefUtil.getClazz(invoke.getThis()));
        String argsName = methodName + sig;
        if (desc.hasField(argsName)) {
            return true;
        }
        return false;
    }

    protected BuriBaoException createBaoExcep(String messageCode, MethodInvocation invoke) {
        return new BuriBaoException(messageCode, new Object[] { classDefUtil.getClassName(invoke.getThis()), invoke.getMethod().getName() });
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

}
