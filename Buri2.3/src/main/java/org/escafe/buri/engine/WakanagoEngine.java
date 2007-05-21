/*
 * 作成日: 2006/04/01
 *
 */
package org.escafe.buri.engine;

import java.io.InputStream;
import java.util.Map;

import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface WakanagoEngine {
    BuriUserContext createUserContext(Object data,Object userData,Object action,Map context);
    BuriSystemContext createSystemContext(String buriPath,BuriUserContext userContext);
    Object execute(BuriSystemContext sysContext,String resultScript);
    
    BuriExecProcess selectDirectProcess(BuriPath path);
    BuriExePackages selectPackage(BuriSystemContext sysContext);
    BuriExecProcess selectProcess(BuriExePackages wPackageObj,BuriSystemContext sysContext);
    
    void readWorkFlowFromResource(String workFlowName,String resourceName);
    void readWorkFlowFromInputStream(InputStream workFlowIs,String resourceName);
    void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,String resourceName);
    void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider);
    void readWorkFlowFromInputStream(InputStream workFlowIs,String resourceName,ParticipantProvider provider);
    void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,String resourceName,ParticipantProvider provider);
    boolean hasPackage(String packageName);
}
