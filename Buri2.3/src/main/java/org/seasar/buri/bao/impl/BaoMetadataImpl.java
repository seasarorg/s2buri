/*
 * 作成日: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoMetadata;

public class BaoMetadataImpl implements BaoMetadata {
    private String process;
    private Class targetDto;
    private Class baoClass;
    private String userInfo;
    private Class userInfoClass;
    
    private HashMap converter = new HashMap();
    
    private HashMap metadatas = new HashMap();
    
    public void dispose() {
    	metadatas.clear();
    	converter.clear();
    }
    
    public BaoFunctionMetadata getMetadata(MethodInvocation invoke) {
        BaoFunctionMetadata funcMetadata = null;
        synchronized (metadatas) {
            String methodName = invoke.getMethod().getName();
            if(metadatas.containsKey(methodName)) {
                funcMetadata = (BaoFunctionMetadata)metadatas.get(methodName);
            }
        }
        return funcMetadata;
    }
    
    public void addMetadata(MethodInvocation invoke,BaoFunctionMetadata funcMetadata) {
        metadatas.put(invoke.getMethod().getName(),funcMetadata);
    }

    public Class getBaoClass() {
        return baoClass;
    }

    public void setBaoClass(Class baoClass) {
        this.baoClass = baoClass;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Class getTargetDto() {
        return targetDto;
    }

    public void setTargetDto(Class targetDto) {
        this.targetDto = targetDto;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public Class getUserInfoClass() {
        return userInfoClass;
    }

    public void setUserInfo(Class userInfoClass) {
        this.userInfoClass = userInfoClass;
    }

    public Map getConverter() {
        return converter;
    }

    public void setConverter(Map converter) {
        this.converter.putAll(converter);
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("process=").append(process);
        buff.append("/targetDto=").append(targetDto);
        buff.append("/baoClass=").append(baoClass);
        buff.append("/userInfo=").append(userInfo);
        buff.append("/userInfoClass=").append(userInfoClass);
        buff.append("/converter=").append(converter);
        buff.append("/metadatas=").append(metadatas);
        buff.append("]");
        return buff.toString();
    }
    
}
