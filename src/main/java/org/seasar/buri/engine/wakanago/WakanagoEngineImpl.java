/*
 * çÏê¨ì˙: 2006/03/26
 *
 */
package org.seasar.buri.engine.wakanago;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.impl.DefaultParticipantProvider;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class WakanagoEngineImpl implements WakanagoEngine {
    protected Map packageObjs = new HashMap();
    protected Map roleMap = new HashMap();
    protected BuriCompiler buriCompiler;
    protected BuriStateUtil stateUtil;
    
    public void readWorkFlowFromResource(String workFlowName,String resourceName) {
        readWorkFlowFromResource(workFlowName,resourceName,new DefaultParticipantProvider());
    }
    public void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider) {
        BuriExePackages exePackages = buriCompiler.CompileResource(workFlowName,provider);
        String packageId = exePackages.getBuriPackageType().getId();
        packageObjs.put(packageId,exePackages);
        roleMap.put(packageId,provider);
    }

    public BuriUserContext createUserContext(Object data,Object userData,Object action) {
        BuriUserContext context = new BuriUserContext();
        context.setData(data);
        context.setUserData(userData);
        context.setAction(action);
        return context;
    }
    
    public BuriSystemContext createSystemContext(String buriPath,BuriUserContext userContext) {
        BuriSystemContext context = new BuriSystemContext();
        context.setCallPath(new BuriPath(buriPath));
        context.setUserContext(userContext);
        return context;
    }
    
    public Object execute(BuriSystemContext sysContext) {
        BuriExePackages wPackageObj = (BuriExePackages)selectPackage(sysContext);
        BuriExecProcess wp = (BuriExecProcess)selectProcess(wPackageObj,sysContext);
        execActivity(wp,sysContext);
        return null;
    }
    
    protected void execActivity(BuriExecProcess wp,BuriSystemContext sysContext) {
        String actId = wp.selectActivityId(sysContext);
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        wp.entryActivity(actId,sysContext,walker);
    }
    
    public BuriExecProcess selectProcess(BuriExePackages wPackageObj,BuriSystemContext sysContext) {
        BuriExecProcess wp = (BuriExecProcess)wPackageObj.getProcess(sysContext.getCallPath());
        BuriPath callPath = sysContext.getCallPath();
        String processId = ClassUtil.getShortClassName(wp.getClass());
        callPath = callPath.setWorkflowProcess(callPath.getWorkflowProcess(),processId);
        sysContext.setCallPath(callPath);
        return wp;
    }
    
    public BuriExePackages selectPackage(BuriSystemContext sysContext) {
        String packageName = sysContext.getCallPath().getWorkflowPackage();
        assert ! StringUtil.isEmpty(packageName);
        assert packageObjs.containsKey(packageName);

        BuriPath callPath = sysContext.getCallPath();
        BuriExePackages wPackageObj = (BuriExePackages)packageObjs.get(packageName);
        
        String packageId = wPackageObj.getBuriPackageType().getId();
        callPath = callPath.setWorkflowPackage(packageName,packageId);
        sysContext.setCallPath(callPath);
        return wPackageObj;
    }
    
    
    protected List getProcessList(BuriPath path) {
        BuriExePackages wPackageObj = getBuriExePackages(path);
        List processList = wPackageObj.getBuriPackageType().getProcessByName(path.getWorkflowProcess());
        return processList;
    }
    
    protected BuriExePackages getBuriExePackages(BuriPath path) {
        BuriExePackages wPackageObj = (BuriExePackages)packageObjs.get(path.getWorkflowPackage());
        assert wPackageObj != null;
        return wPackageObj;
    }

    public BuriCompiler getBuriCompiler() {
        return buriCompiler;
    }
    public void setBuriCompiler(BuriCompiler buriCompiler) {
        this.buriCompiler = buriCompiler;
    }
    public BuriStateUtil getStateUtil() {
        return stateUtil;
    }
    public void setStateUtil(BuriStateUtil stateUtil) {
        this.stateUtil = stateUtil;
    }

}
