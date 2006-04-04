/*
 * çÏê¨ì˙: 2006/04/01
 *
 */
package org.seasar.buri.bao.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActionConverter;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriActivityValidate;
import org.seasar.buri.annotation.BuriArgs;
import org.seasar.buri.annotation.BuriConvertRule;
import org.seasar.buri.annotation.BuriConverter;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.annotation.BuriResult;
import org.seasar.buri.annotation.BuriTargetDto;
import org.seasar.buri.annotation.BuriUserInfo;
import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoInvokeMetadata;
import org.seasar.buri.bao.BaoMetadata;
import org.seasar.buri.bao.BaoMetadataFactory;
import org.seasar.buri.bao.BaoStatusMetadata;
import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.IsNumberUtil;
import org.seasar.buri.exception.BuriBaoException;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class TigerBaoMetadataFactoryImpl implements BaoMetadataFactory {

    private static final Log LOG = LogFactory
        .getLog(TigerBaoMetadataFactoryImpl.class);

    private HashMap<Class, BaoMetadata> metadatas = new HashMap<Class, BaoMetadata>();
    private ClassDefUtil classDefUtil;

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

    protected void updateProcess(BaoMetadata metadata, MethodInvocation invoke) {
        BuriProcess anot = getAnnotationOfClass(invoke, BuriProcess.class);
        if (anot != null) {
            metadata.setProcess(anot.value());
        } else {
            throw createBaoExcep("EBRI0021", invoke);
        }
    }

    protected void updateUserInfo(BaoMetadata metadata, MethodInvocation invoke) {
        BuriUserInfo anot = getAnnotationOfClass(invoke, BuriUserInfo.class);
        if (anot != null) {
            metadata.setUserInfo(anot.value());
        }
    }

    @SuppressWarnings("unchecked")
    protected void updateGlobalConverter(BaoMetadata metadata,
            MethodInvocation invoke) {
        Annotation anot = getAnnotationOfClass(invoke, BuriConverter.class);
        if (anot != null) {
            LOG.debug("Annotation: " + anot);
            BuriConvertRule[] rules = ((BuriConverter) anot).value();
            for (BuriConvertRule rule : rules) {
                LOG.debug("ConvertRule: " + rule.clazz() + ", " + rule.ognl());
                String className = classDefUtil.getClassName(rule.clazz());
                BuriConvert convert = new BuriConvert(rule.clazz(), rule.ognl());
                metadata.getConverter().put(className, convert);
            }
        }
    }

    protected void updateTargetDto(BaoMetadata metadata, MethodInvocation invoke) {
        Annotation anot = getAnnotationOfClass(invoke, BuriTargetDto.class);
        if (anot != null) {
            metadata.setTargetDto((Class) ((BuriTargetDto) anot).value());
        }
    }

    public boolean isStatusMetadata(BaoMetadata metadata,
            MethodInvocation invoke) {
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

    public BaoInvokeMetadata getBaoInvokeMetadata(BaoMetadata metadata,
            MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = metadata.getMetadata(invoke);
        if (funcMetadata == null) {
            funcMetadata = createBaoInvokeMetadata(metadata, invoke);
            metadata.addMetadata(invoke, funcMetadata);
        }
        return (BaoInvokeMetadata) funcMetadata;
    }

    protected BaoInvokeMetadata createBaoInvokeMetadata(BaoMetadata metadata,
            MethodInvocation invoke) {
        BaoInvokeMetadata invokeMetadata = new BaoInvokeMetadataImpl();
        setupArgs(invokeMetadata, invoke);
        setupActivityName(invokeMetadata, invoke);
        setupValidateActivityName(invokeMetadata, invoke);
        updateAction(invokeMetadata, invoke);
        updateActionConverter(invokeMetadata, invoke);
        updateResult(invokeMetadata, invoke);
        invokeMetadata.setBaoMetadata(metadata);
        validationBaoInvoke(invokeMetadata, invoke);
        return invokeMetadata;
    }

    protected void updateAction(BaoInvokeMetadata invokeMetadata,
            MethodInvocation invoke) {
        BuriAction anot = getAnnotationOfMethod(invoke, BuriAction.class);
        if (anot != null) {
            invokeMetadata.setAction(anot.value());
        }
    }

    protected void updateActionConverter(BaoInvokeMetadata invokeMetadata,
            MethodInvocation invoke) {
        BuriActionConverter anot = getAnnotationOfMethod(invoke,
            BuriActionConverter.class);
        if (anot != null) {
            BuriConvertRule rule = anot.value();
            LOG.debug("ConvertRule: " + rule.clazz() + ", " + rule.ognl());
            BuriConvert convert = new BuriConvert(rule.clazz(), rule.ognl());
            invokeMetadata.setBuriConvert(convert);
        }
    }

    protected void updateResult(BaoInvokeMetadata invokeMetadata,
            MethodInvocation invoke) {
        BuriResult anot = getAnnotationOfMethod(invoke, BuriResult.class);
        if (anot != null) {
            invokeMetadata.setResult(anot.value());
        }
    }

    protected void validationBaoInvoke(BaoInvokeMetadata invokeMetadata,
            MethodInvocation invoke) {
        if (!StringUtil.isEmpty(invokeMetadata.getResult())) {
            if (invoke.getMethod().getReturnType().equals(Void.class)) {
                throw createBaoExcep("EBRI0025", invoke);
            }
        }
        if (invoke.getArguments().length != invokeMetadata.getArgName().size()) {
            throw createBaoExcep("EBRI0024", invoke);
        }
    }

    public BaoStatusMetadata getBaoStatusMetadata(BaoMetadata metadata,
            MethodInvocation invoke) {
        BaoFunctionMetadata statusMetadata = metadata.getMetadata(invoke);
        if (statusMetadata == null) {
            statusMetadata = createBaoStatusMetadata(metadata, invoke);
            metadata.addMetadata(invoke, statusMetadata);
        }
        return (BaoStatusMetadata) statusMetadata;
    }

    protected BaoFunctionMetadata createBaoStatusMetadata(BaoMetadata metadata,
            MethodInvocation invoke) {
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

    protected void validationBaoStatus(BaoStatusMetadata smd,
            MethodInvocation invoke) {
        Class retType = invoke.getMethod().getReturnType();
        if (!ClassUtil.isAssignableFrom(retType, List.class)
                && !IsNumberUtil.isNumberType(retType)) {
            throw createBaoExcep("EBRI0022", invoke);
        }
        if (invoke.getArguments().length != smd.getArgName().size()) {
            throw createBaoExcep("EBRI0024", invoke);
        }
    }

    @SuppressWarnings("unchecked")
    protected void setupValidateActivityName(BaoFunctionMetadata funcMetadata,
            MethodInvocation invoke) {
        BuriActivityValidate anot = getAnnotationOfMethod(invoke,
            BuriActivityValidate.class);
        if (anot != null) {
            String validate[] = anot.value();
            funcMetadata.getValidateAction().addAll(Arrays.asList(validate));
        }
    }

    protected void setupActivityName(BaoFunctionMetadata funcMetadata,
            MethodInvocation invoke) {
        BuriActivity anot = getAnnotationOfMethod(invoke, BuriActivity.class);
        if (anot != null) {
            funcMetadata.setActivityName(anot.value());
        }
    }

    protected void setupArgs(BaoFunctionMetadata fm, MethodInvocation invoke) {
        BuriArgs anot = getAnnotationOfMethod(invoke, BuriArgs.class);
        if (anot != null) {
            String[] args = anot.value();
            fm.setArgName(Arrays.asList(args));
        } else {
            if (invoke.getArguments().length == 0) {
                fm.setArgName(Arrays.asList(new String[0]));
            }
        }
    }

    protected BuriBaoException createBaoExcep(String messageCode,
            MethodInvocation invoke) {
        return new BuriBaoException(messageCode, new Object[] {
            classDefUtil.getClassName(invoke.getThis()),
            invoke.getMethod().getName() });
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> T getAnnotationOfClass(
            MethodInvocation invoke, Class<T> annotationClass) {
        Class<Object> enhancedClass = (Class<Object>) invoke.getThis()
            .getClass();
        for (Class<Object> interfc : enhancedClass.getInterfaces()) {
            T anot = interfc.getAnnotation(annotationClass);
            if (anot != null) {
                return anot;
            }
        }
        T anot = enhancedClass.getSuperclass().getAnnotation(annotationClass);
        if (anot != null) {
            return anot;
        }
        return null;
    }

    private <T extends Annotation> T getAnnotationOfMethod(
            MethodInvocation invoke, Class<T> annotationClass) {
        return (T) invoke.getMethod().getAnnotation(annotationClass);
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }
}
