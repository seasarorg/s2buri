/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoInvokeMetadata;
import org.seasar.buri.bao.BaoMetadata;
import org.seasar.buri.bao.BaoMetadataFactory;
import org.seasar.buri.bao.BaoStatusMetadata;
import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.IsNumberUtil;
import org.seasar.buri.exception.BuriBaoException;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;

public class BaoMetadataFactoryImpl implements BaoMetadataFactory {
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

    public BaoMetadata getBaoMetadata(MethodInvocation invoke) {
        BaoMetadata metadata = null;
        Class clazz = classDefUtil.getClazz(invoke.getThis());
        synchronized (metadatas) {
            if(metadatas.containsKey(clazz)) {
                metadata = (BaoMetadata)metadatas.get(clazz);
            } else {
                metadata = createBaoMetadata(invoke);
                metadatas.put(clazz,metadata);
            }
        }
        return metadata;
    }
    
    protected BaoMetadata createBaoMetadata(MethodInvocation invoke) {
        BaoMetadata metadata = new BaoMetadataImpl();
        updateProcess(metadata,invoke);
        updateUserInfo(metadata,invoke);
        updateGlobalConverter(metadata,invoke);
        updateTargetDto(metadata,invoke);
        return metadata;
    }
    
    protected void updateProcess(BaoMetadata metadata,MethodInvocation invoke) {
        Object val = getSignatureValue(invoke,PROCESS);
        if(val != null) {
            metadata.setProcess(val.toString());
        } else {
            throw createBaoExcep("EBRI0021",invoke);
        }
    }
    
    protected void updateUserInfo(BaoMetadata metadata,MethodInvocation invoke) {
        Object val = getSignatureValue(invoke,USERINFO);
        if(val != null) {
            if(val instanceof String) {
                metadata.setUserInfo(val.toString());
            } else {
                metadata.setUserInfo((Class)val);
            }
        }
    }
    
    protected void updateGlobalConverter(BaoMetadata metadata,MethodInvocation invoke) {
        Object val = getSignatureValue(invoke,GLBLCONVERTER);
        if(val != null) {
            BuriConvert[] converters = (BuriConvert[])val;
            for(int i=0;i < converters.length ; i++) {
                String className = classDefUtil.getClassName(converters[i].getClazz());
                metadata.getConverter().put(className,converters[i]);
            }
        }
    }
    
    protected void updateTargetDto(BaoMetadata metadata,MethodInvocation invoke) {
        Object val = getSignatureValue(invoke,TARGETDTO);
        if(val != null) {
            metadata.setTargetDto((Class)val);
        } else {
            throw createBaoExcep("EBRI0020",invoke);
        }
    }

    
    
    public boolean isStatusMetadata(BaoMetadata metadata,MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = metadata.getMetadata(invoke);
        if(funcMetadata==null) {
            String methodName = invoke.getMethod().getName();
            if(isStatusMethodName(methodName)) {
                return true;
            }
        } else if(funcMetadata instanceof BaoStatusMetadata) {
            return true;
        }
        return false;
    }
    
    protected boolean isStatusMethodName(String methodName) {
        if(methodName.startsWith("get") ) {
            return true;
        }
        if(methodName.startsWith("find") ) {
            return true;
        }
        if(methodName.startsWith("select") ) {
            return true;
        }
        if(methodName.startsWith("count")) {
            return true;
        }
        return false;
        
    }
    
    
    
    public BaoInvokeMetadata getBaoInvokeMetadata(BaoMetadata metadata,MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = metadata.getMetadata(invoke);
        if(funcMetadata==null) {
            funcMetadata = createBaoInvokeMetadata(metadata,invoke);
            metadata.addMetadata(invoke,funcMetadata);
        }
        return (BaoInvokeMetadata)funcMetadata;
    }
    
    protected BaoInvokeMetadata createBaoInvokeMetadata(BaoMetadata metadata,MethodInvocation invoke) {
        BaoInvokeMetadata invokeMetadata = new BaoInvokeMetadataImpl();
        setupArgs(invokeMetadata,invoke);
        setupActivityName(invokeMetadata,invoke);
        updateAction(invokeMetadata,invoke);
        updateActionConverter(invokeMetadata,invoke);
        updateResult(invokeMetadata,invoke);
        invokeMetadata.setBaoMetadata(metadata);
        validationBaoInvoke(invokeMetadata,invoke);
        return invokeMetadata;
    }
    
    protected void updateAction(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        Object val = getMethodSignatureValue(invoke,ACTION);
        if(val != null) {
            invokeMetadata.setAction(val.toString());
        }
    }
    protected void updateActionConverter(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        Object val = getMethodSignatureValue(invoke,ACTCONVERTER);
        if(val != null) {
            invokeMetadata.setBuriConvert((BuriConvert)val);
        }
    }
    protected void updateResult(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        Object val = getMethodSignatureValue(invoke,RESULT);
        if(val != null) {
            invokeMetadata.setResult(val.toString());
        }
    }
    
    protected void validationBaoInvoke(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        if( ! StringUtil.isEmpty(invokeMetadata.getResult())) {
            boolean errorRetType = false;
            Class retType = invoke.getMethod().getReturnType();
            if(retType.equals(Void.class)) {
                errorRetType = true;
            }
            if(errorRetType) {
                throw createBaoExcep("EBRI0025",invoke);
            }
        }
        if(invoke.getArguments().length != invokeMetadata.getArgName().size()) {
            throw createBaoExcep("EBRI0024",invoke);
        }
    }

    

    
    
    
    public BaoStatusMetadata getBaoStatusMetadata(BaoMetadata metadata,MethodInvocation invoke) {
        BaoFunctionMetadata statusMetadata = metadata.getMetadata(invoke);
        if(statusMetadata==null) {
            statusMetadata = createBaoStatusMetadata(metadata,invoke);
            metadata.addMetadata(invoke,statusMetadata);
        }
        return (BaoStatusMetadata)statusMetadata;
    }

    protected BaoFunctionMetadata createBaoStatusMetadata(BaoMetadata metadata,MethodInvocation invoke) {
        BaoStatusMetadata smd = new BaoStatusMetadataImpl();
        smd.setBaoMetadata(metadata);
        setupArgs(smd,invoke);
        setupActivityName(smd,invoke);
        if(smd.getActivityName().length() == 0) {
            throw createBaoExcep("EBRI0023",invoke);
        }
        validationBaoStatus(smd,invoke);
        return smd;
    }
    
    protected void validationBaoStatus(BaoStatusMetadata smd,MethodInvocation invoke) {
        Class retType = invoke.getMethod().getReturnType();
        if( ! ClassUtil.isAssignableFrom(retType,List.class) && ! IsNumberUtil.isNumberType(retType)) {
            throw createBaoExcep("EBRI0022",invoke);
        }
        if(invoke.getArguments().length != smd.getArgName().size()) {
            throw createBaoExcep("EBRI0024",invoke);
        }
    }

    
    
    
    protected void setupActivityName(BaoFunctionMetadata funcMetadata,MethodInvocation invoke) {
        Object val = getMethodSignatureValue(invoke,ACTIVITY);
        if(val != null) {
            String acts[] = val.toString().split(",");
            for(int i=0; i < acts.length ;i++) {
                funcMetadata.addActivityNames(acts[i]);
            }
        }
    }
    
    protected void setupArgs(BaoFunctionMetadata fm,MethodInvocation invoke) {
        Object val = getMethodSignatureValue(invoke,ARGS);
        if(val!=null) {
            String[] args = convArgName(val.toString(),invoke);
            fm.setArgName(Arrays.asList(args));
        } else {
            if(invoke.getArguments().length == 0) {
                fm.setArgName(Arrays.asList(new String[0]));
            }
        }
    }
    
    protected String[] convArgName(String arg,MethodInvocation invoke) {
        String argData[] = arg.split(",");
        for(int i=0; i < argData.length ; i++) {
            String oneArgName = argData[i].trim();
            argData[i] = oneArgName;
        }
        return argData;
    }
    
    
    protected Object getMethodSignatureValue(MethodInvocation invoke,String sig) {
        String methodName = invoke.getMethod().getName();
        return getMethodSignatureValue(invoke,sig,methodName);
    }
    
    protected Object getSignatureValue(MethodInvocation invoke,String sig) {
        String methodName = "";
        return getMethodSignatureValue(invoke,sig,methodName);
    }
    
    protected Object getMethodSignatureValue(MethodInvocation invoke,String sig,String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(classDefUtil.getClazz(invoke.getThis()));
        String argsName = methodName+sig;
        if(desc.hasField(argsName)) {
            Field field = desc.getField(argsName);
            return FieldUtil.get(field, null);
        }
        return null;
    }
    
    protected boolean hasMethodSignature(MethodInvocation invoke,String sig) {
        String methodName = invoke.getMethod().getName();
        return hasMethodSignature(invoke,sig,methodName);
    }
    
    protected boolean hasSignature(MethodInvocation invoke,String sig) {
        return hasMethodSignature(invoke,sig,"");
    }
    
    protected boolean hasMethodSignature(MethodInvocation invoke,String sig,String methodName) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(classDefUtil.getClazz(invoke.getThis()));
        String argsName = methodName+sig;
        if(desc.hasField(argsName)) {
            return true;
        }
        return false;
    }
    
    protected BuriBaoException createBaoExcep(String messageCode,MethodInvocation invoke) {
        return new BuriBaoException(messageCode,new Object[]{classDefUtil.getClassName(invoke.getThis()),invoke.getMethod().getName()});
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }
    
}
