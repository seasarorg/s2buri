/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao;

import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

public interface BaoMetadata {
    String getProcess();
    void setProcess(String process);
    Class getTargetDto();
    void setTargetDto(Class clazz);
    Class getBaoClass();
    void setBaoClass(Class clazz);
    String getUserInfo();
    void setUserInfo(String userInfo);
    Class getUserInfoClass();
    void setUserInfo(Class clazz);
    
    Map getConverter();
    void setConverter(Map converter);
    
    BaoFunctionMetadata getMetadata(MethodInvocation invoke);
    void addMetadata(MethodInvocation invoke,BaoFunctionMetadata funcMetadata);
    
}
