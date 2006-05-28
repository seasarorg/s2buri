/*
 * çÏê¨ì˙: 2006/03/26
 *
 */
package org.seasar.buri.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.selector.BuriActivitySelector;
import org.seasar.buri.exception.select.BuriActivitySelectException;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class WakanagoEngineImpl implements WakanagoEngine {
    protected Map packageObjs = new HashMap();
    protected Map roleMap = new HashMap();
    protected BuriCompiler buriCompiler;
    protected List activitySelector = new ArrayList();
    protected List processSelector = new ArrayList();
    
    public void readWorkFlowFromResource(String workFlowName,String resourceName) {
        readWorkFlowFromResource(workFlowName,resourceName,new DefaultParticipantProvider());
    }
    public void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider) {
        BuriExePackages exePackages = buriCompiler.CompileResource(workFlowName,provider);
        String packageId = exePackages.getBuriPackageType().getId();
        packageObjs.put(packageId,exePackages);
        roleMap.put(packageId,provider);
    }
    
    public void addActivitySelector(BuriActivitySelector selector) {
        activitySelector.add(selector);
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
        updateSystemContext(sysContext,wp);
        execActivity(wp,sysContext);
        return null;
    }
    
    protected void updateSystemContext(BuriSystemContext sysContext,BuriExecProcess wp) {
        
    }
    
    protected void execActivity(BuriExecProcess wp,BuriSystemContext sysContext) {
        BranchWalker walker = wp.readBranchWalker(sysContext);
        String actId = selectActivityId(wp,sysContext);
        wp.entryActivity(actId,sysContext,walker);
    }
    
    protected String selectActivityId(BuriExecProcess wp,BuriSystemContext sysContext) {
        List acts = new ArrayList();
        Iterator ite = activitySelector.iterator();
        while(ite.hasNext()) {
            BuriActivitySelector selector = (BuriActivitySelector)ite.next();
            int nextAct = selector.select(acts,sysContext,wp);
            if(nextAct == BuriActivitySelector.SELECT_FINAL) {
                break;
            }
            if(nextAct == BuriActivitySelector.SELECT_ERROR) {
                errorActivitySelect(acts,sysContext,wp);
            }
        }
        return selectActivityFinal(acts,sysContext,wp);
    }
    
    protected String selectActivityFinal(List acts,BuriSystemContext systemContext,BuriExecProcess wp) {
        if(acts.size() != 1) {
            errorActivitySelect(acts,systemContext,wp);
        }
        BuriActivityType actType = (BuriActivityType)acts.get(0);
        return actType.getId();
    }
    
    protected void errorActivitySelect(List acts,BuriSystemContext systemContext,BuriExecProcess wp) {
        BuriPath callPath = systemContext.getCallPath();
        String pakageID = callPath.getWorkflowPackage();
        ParticipantProvider provider = (ParticipantProvider)roleMap.get(pakageID);
        if(acts.size()==0) {
            throw new BuriNotSelectedActivityException(callPath,provider);
        }
        if(acts.size() > 1) {
            throw new BuriManySelectActivityException(callPath,provider);
        }
        throw new BuriActivitySelectException(callPath,provider);
    }
    
    public BuriExecProcess selectProcess(BuriExePackages wPackageObj,BuriSystemContext sysContext) {
        //TODO SelectorÇégÇ§ÇÊÇ§Ç…èëÇ´ä∑Ç¶ÇÈÅBÇªÇÃéûÇ…ëÂïùèCê≥ÇÃó\ä¥
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

}
