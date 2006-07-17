/*
 * ì¬“ú: 2006/03/26
 *
 */
package org.seasar.buri.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.engine.BuriConfigDto;
import org.seasar.buri.engine.BuriEngineConfig;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.selector.BuriActivitySelector;
import org.seasar.buri.engine.selector.BuriProcessSelector;
import org.seasar.buri.exception.select.BuriActivitySelectException;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriManySelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.exception.select.BuriProcessSelectException;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class WakanagoEngineImpl implements WakanagoEngine {
    protected Map packageObjs = new HashMap();
    protected Map roleMap = new HashMap();
    protected BuriCompiler buriCompiler;
    protected List activitySelector = new ArrayList();
    protected List processSelector = new ArrayList();
    protected S2Container container;
    protected ScriptFactory scriptFactory;
    
    public void readWorkFlowFromResource(String workFlowName,String resourceName) {
        readFromResource(workFlowName,resourceName,null);
    }
    public void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider) {
        readFromResource(workFlowName,resourceName,provider);
    }
    
    public void readFromResource(String workFlowName,String resourceName,ParticipantProvider provider) {
        BuriExePackages exePackages = buriCompiler.CompileResource(workFlowName,provider);
        String packageId = exePackages.getBuriPackageType().getId();
        packageObjs.put(packageId,exePackages);
        packageObjs.put(resourceName,exePackages);
        roleMap.put(packageId,provider);
    }
    
    public void setupBuriEngineConfig(BuriEngineConfig engineConfig) {
        Iterator ite = engineConfig.getResourceConfigs().iterator();
        while(ite.hasNext()) {
            BuriConfigDto dto = (BuriConfigDto)ite.next();
            readWorkFlowFromResource(dto.getFileName(),dto.getPackageName(),dto.getProvider());
        }
    }
    
    public void setupDelayLoad(String resourceName) {
        packageObjs.put(resourceName,null);
    }
    
    public boolean hasPackage(String packageName) {
        if(packageObjs.containsKey(packageName)) {
            return true;
        }
        return false;
    }
    
    public void addProcessSelector(BuriProcessSelector selector) {
        processSelector.add(selector);
    }
    
    public void addActivitySelector(BuriActivitySelector selector) {
        activitySelector.add(selector);
    }

    public BuriUserContext createUserContext(Object data,Object userData,Object action,Map addContext) {
        BuriUserContext context = new BuriUserContext();
        if(addContext != null) {
            context.putAll(addContext);
        }
        context.setData(data);
        context.setUserData(userData);
        context.setAction(action);
        return context;
    }
    
    public BuriSystemContext createSystemContext(String buriPath,BuriUserContext userContext) {
        BuriSystemContext context = new BuriSystemContext();
        context.setCallPath(new BuriPath(buriPath));
        userContext.setCallPath(context.getCallPath());
        context.setUserContext(userContext);
        return context;
    }
    
    private void setupSystemContext(BuriSystemContext sysContext) {
        if(sysContext.getContainer()==null) {
            sysContext.setContainer(container.getRoot());
        }
    }

    
    public Object execute(BuriSystemContext sysContext,String resultScript) {
        setupSystemContext(sysContext);
        BuriExePackages wPackageObj = (BuriExePackages)selectPackage(sysContext);
        BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj,sysContext);
        updateSystemContext(sysContext,wp,wPackageObj);
        updateUserInfo(sysContext,wp,wPackageObj);
        wp = selectProcess(wPackageObj,sysContext);
        execActivity(wp,sysContext);
        return getResultObj(sysContext,resultScript);
    }
    
    protected Object getResultObj(BuriSystemContext sysContext,String resultScript) {
        if(StringUtil.isEmpty(resultScript)) {
            return null;
        }
        Object result = scriptFactory.getDefaultScript().eval(null,resultScript,sysContext.getUserContext());
        return result;
    }
    
    protected void updateSystemContext(BuriSystemContext sysContext,BuriExecProcess wp,BuriExePackages wPackageObj) {
    }
    
    protected void updateUserInfo(BuriSystemContext sysContext,BuriExecProcess wp,BuriExePackages wPackageObj) {
        ParticipantProvider provider = wPackageObj.getParticipantProvider();
        Object userData = sysContext.getUserContext().getUserData();
        if(userData == null && provider == null) {
            return;
        }
        if(sysContext.getCallPath().getActivityName().size() == 1) {
            List acts = wp.getBuriWorkflowProcessType().getActivityByName(sysContext.getCallPath().getActivityName().get(0).toString());
            updateUserKeysFromActivitys(sysContext,userData,acts,provider);
        }
    }
    
    protected void updateUserKeysFromActivitys(BuriSystemContext sysContext,Object userData,List acts,ParticipantProvider provider) {
        String roleType = null;
        if(acts.size() > 0) {
            roleType = roleTypeSelector(acts);
        }
        if(roleType != null) {
            Long userIDNum = provider.getUserIDNum(userData,roleType);
            String userIDVal = provider.getUserIDString(userData,roleType);
            sysContext.setUserPkeyNum(userIDNum);
            sysContext.setUserPkeyVal(userIDVal);
        }
    }
    
    protected String roleTypeSelector(List acts) {
        Iterator ite = acts.iterator();
        String roleType = ((BuriActivityType)ite.next()).getRoleType();
        while(ite.hasNext()) {
            BuriActivityType actType = (BuriActivityType)ite.next();
            if( ! roleType.equals(actType.getRoleType())) {
                roleType = null;
                break;
            }
            
        }
        return roleType;
    }
    
    protected void execActivity(BuriExecProcess wp,BuriSystemContext sysContext) {
        BranchWalker walker = wp.readBranchWalker(sysContext);
        String actId = selectActivityId(wp,sysContext);
        wp.entryActivity(actId,sysContext,walker);
    }
    
    protected String selectActivityId(BuriExecProcess wp,BuriSystemContext sysContext) {
        Set acts = new HashSet();
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
    
    protected String selectActivityFinal(Set acts,BuriSystemContext systemContext,BuriExecProcess wp) {
        if(acts.size() != 1) {
            errorActivitySelect(acts,systemContext,wp);
        }
        BuriActivityType actType = (BuriActivityType)(acts.toArray()[0]);
        return actType.getId();
    }
    
    protected void errorActivitySelect(Set acts,BuriSystemContext systemContext,BuriExecProcess wp) {
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
    
    public BuriExecProcess selectProcessNoDataAccess(BuriExePackages wPackageObj,BuriSystemContext sysContext) {
        BuriExecProcess wp = (BuriExecProcess)wPackageObj.getProcess(sysContext.getCallPath());
        BuriPath callPath = sysContext.getCallPath();
        String processId = ClassUtil.getShortClassName(wp.getClass());
        callPath = callPath.setWorkflowProcess(callPath.getWorkflowProcess(),processId);
        sysContext.setCallPath(callPath);
        return wp;
    }
    
    public BuriExecProcess selectDirectProcess(BuriPath path) {
        BuriExePackages wPackageObj = (BuriExePackages)packageObjs.get(path.getWorkflowPackage());
        return wPackageObj.getProcess(path); //TODO Direct‚ª•K—v
    }
    
    public BuriExecProcess selectProcess(BuriExePackages wPackageObj,BuriSystemContext sysContext) {
        List pros = new ArrayList();
        Iterator ite = processSelector.iterator();
        while(ite.hasNext()) {
            BuriProcessSelector selector = (BuriProcessSelector)ite.next();
            int nextAct = selector.select(pros,sysContext,wPackageObj);
            if(nextAct == BuriProcessSelector.SELECT_FINAL) {
                break;
            }
            if(nextAct == BuriProcessSelector.SELECT_ERROR) {
                errorProcessSelect(pros,sysContext,wPackageObj);
            }
        }
        return selectProcessFinal(pros,sysContext,wPackageObj);
    }
    
    protected BuriExecProcess selectProcessFinal(List pros,BuriSystemContext systemContext,BuriExePackages wPackageObj) {
        if(pros.size() != 1) {
            errorProcessSelect(pros,systemContext,wPackageObj);
        }
        BuriWorkflowProcessType procType = (BuriWorkflowProcessType)pros.get(0);
        BuriPath callPath = systemContext.getCallPath().setWorkflowProcess(procType);
        return wPackageObj.getProcess(callPath);
    }
    
    protected void errorProcessSelect(List proces,BuriSystemContext systemContext,BuriExePackages wPackageObj) {
        BuriPath callPath = systemContext.getCallPath();
        String pakageID = callPath.getWorkflowPackage();
        ParticipantProvider provider = (ParticipantProvider)roleMap.get(pakageID);
        if(proces.size()==0) {
            throw new BuriNotSelectProcessException(callPath);
        }
        if(proces.size() > 1) {
            throw new BuriManySelectProcessException(callPath);
        }
        throw new BuriProcessSelectException(callPath,provider);
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
    public S2Container getContainer() {
        return container;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }
    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

}
