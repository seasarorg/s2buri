/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.xpdl.rule.ActivitySelectRule;
import org.seasar.buri.xpdl.rule.WorkflowProcessSelectRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ActivityTagSelectUtil;
import org.seasar.buri.xpdl.util.ParticipantUtil;
import org.seasar.buri.xpdl.util.TransitionUtil;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;
import org.wfmc.x2002.xpdl10.PackageDocument.Package;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;

public class WorkFlowsUtilImpl implements WorkFlowsUtil {
    private WakanagoWorkFlows workFlows;
    private WorkflowProcessSelectRule workflowProcessSelectRule;
    private XPathUtil pathUtil;
    private S2Container container;
    private ActivitySelectRule activitySelectRule;
    private TransitionUtil transitionUtil;
    private ParticipantUtil participantUtil;
    private ActivityTagSelectUtil tagSelectUtil;
    private Map cache = new HashMap();

    public List getActivityTagSelectByPathList(List pathList) {
        List result = new ArrayList();
        Iterator ite = pathList.iterator();
        while(ite.hasNext()) {
            BuriPath path = (BuriPath)ite.next();
            ActivityTagSelect tagSelect = getActivityTagSelectFromBuriPath(path);
            result.add(tagSelect);
        }
        return result;
    }
    
    public List getActivityListByParticipant(List activityList,BuriParticipant participant) {
        List result = new ArrayList();
        if(activityList == null || activityList.size() == 0) {
            return result;
        }
        ActivityTagSelect tagSelect = (ActivityTagSelect)activityList.get(0);
        BuriPath fstPath = tagSelect.getBuriPath();
        return participantUtil.findParticipant(fstPath,activityList,participant);
    }
    
    
    public WorkFlowPackageTagSelect getWorkFlowPackage(BuriPath path) {
        return workFlows.getPackageTagSelect(path);
    }

    public WorkflowProcessTagSelect getWorkflowProcess(BuriPath path) {
        if(cache.containsKey(path)) {
            WorkflowProcessTagSelect processTagSelect = (WorkflowProcessTagSelect)cache.get(path);
            path.setWorkflowProcess(processTagSelect);
            return processTagSelect;
        }
        WorkFlowPackageTagSelect packageTagSelect = getWorkFlowPackage(path);
        WorkflowProcess process = getWorkflowProcess(packageTagSelect,path);
        WorkflowProcessTagSelect processTagSelect = (WorkflowProcessTagSelect)container.getComponent(WorkflowProcessTagSelect.class);
        processTagSelect.setBuriPath(path);
        processTagSelect.setWorkflowProcess(process);
        path.setWorkflowProcess(processTagSelect);
        cache.put(path,processTagSelect);
        return processTagSelect;
    }

    protected String getWorkflowProcessXPath() {
        String xpath = "./xpdl:WorkflowProcesses/xpdl:WorkflowProcess";
        return xpath;
    }
    
    protected WorkflowProcess[] getWorkflowProcessArray(Package packageDoc,BuriPath path) {
        String xpath = getWorkflowProcessXPath();
        XmlObject[] srcObj = pathUtil.getXmlObjArrayFromXPath(packageDoc,xpath,path.getWorkflowProcess(),null);
        if(srcObj.length==0) {
            return null;
        } else {
            WorkflowProcess wkfp[] = (WorkflowProcess[])srcObj;
            return wkfp;
        }
    }

    protected WorkflowProcess getWorkflowProcess(WorkFlowPackageTagSelect packageTagSelect,BuriPath path) {
        Package packageDoc = packageTagSelect.getPackageDocument();
        WorkflowProcess wkfp[] = getWorkflowProcessArray(packageDoc,path);
        WorkflowProcess process = workflowProcessSelectRule.getWorkflowProcess(packageDoc,wkfp,path);
        return process;
    }
    
    protected String getActivityXPath() {
        String xpath = "./xpdl:Activities/xpdl:Activity";
        return xpath;
    }

    protected Activity[] getActivityArray(XmlObject xmlObj,String name,String id) {
        XmlObject[] xmlObjs = pathUtil.getXmlObjArrayFromXPath(xmlObj,getActivityXPath(),name,id);
        if(xmlObjs.length == 0) {
            return null;
        } else {
            Activity[] activitys = (Activity[])xmlObjs;
            return activitys;
        }
    }

    protected Activity[] getActivityArrayFromBuriPath(XmlObject xmlObj,BuriPath path,int defaultLevel) {
        String activityName = null;
        String activityId = null;
        if(path.getActivity().size() > 0) {
            activityName = (String)path.getActivity().get(defaultLevel);
            activityId = (String)path.getRealPath().getActivity().get(defaultLevel);
        }
        Activity[] activitys = getActivityArray(xmlObj,activityName,activityId);
        return activitys;
    }
    
    protected ActivityTagSelect getActivityFromPathAndParticipant(BuriPath path, BuriParticipant participant,ActivitySelectRule activitySelectRule) {
        WorkflowProcessTagSelect processTagSelect = getWorkflowProcess(path);
        Activity[] activitys = getActivityArrayFromBuriPath(processTagSelect.getXmlObject(),path,0);
        ActivityTagSelect activity = activitySelectRule.selectActivity(activitys,path, participant);
        return activity;
    }
    
    public ActivityTagSelect getActivity(BuriPath path) {
        return getActivityTagSelectFromBuriPath(path);
    }
    
    public ActivityTagSelect getActivityTagSelectFromPathAndParticipant(BuriPath path, BuriParticipant participant,ActivitySelectRule activitySelectRule) {
        ActivityTagSelect tagSelect = getActivityFromPathAndParticipant(path, participant,activitySelectRule);
        path.changePath(tagSelect);
        return tagSelect;
    }
    
    public ActivityTagSelect getActivityTagSelectFromPathAndParticipant(BuriPath path, BuriParticipant participant) {
        return getActivityTagSelectFromPathAndParticipant(path,participant,activitySelectRule);
    }
    
    public ActivityTagSelect getActivityTagSelectFromBuriPath(BuriPath path) {
        return getActivityTagSelectFromPathAndParticipant(path, null);
    }

    public List getActivityTagSelectList(BuriPath path) {
        WorkflowProcessTagSelect processTagSelect = getWorkflowProcess(path);
        Activity[] activitys = getActivityArrayFromBuriPath(processTagSelect.getXmlObject(),path,0);
        List result = new ArrayList();
        for(int i=0 ; i < activitys.length ; i++ ) {
            ActivityTagSelect tagSelect = createActivityTagSelectFromActivity(activitys[i],path);
            result.add(tagSelect);
        }
        return result;
    }

    protected ActivityTagSelect createActivityTagSelectFromActivity(Activity activity,BuriPath path) {
        BuriPath tgtPath = (BuriPath)path.clone();
        tgtPath.setBuriPathID(0);
        if(tgtPath.getActivity().size()==0) {
            tgtPath.moveChildPath(activity);
        } else {
            tgtPath.changePath("",activity.getId());
        }
        ActivityTagSelect tagSelect = tagSelectUtil.convTagSelect(activity,tgtPath);
        return tagSelect;
    }
    
    
    public List getStartActivityTagSelectList(BuriPath path) {
        return getStartActivityTagSelectList((BuriParticipant)null,path);
    }

    public List getStartActivityTagSelectList(BuriParticipant participant,BuriPath path) {
        WorkflowProcessTagSelect processTagSelect = getWorkflowProcess(path);
        Activity[] activitys = getActivityArray(processTagSelect.getXmlObject(),null,null);
        List result = new ArrayList();
        if(activitys==null) {
            return result;
        }
        result = getStartActivityList(activitys,participant,path);
        return result;
    }
    
    protected List getStartActivityList(Activity[] activitys,BuriParticipant participant,BuriPath path) {
        WorkflowProcessTagSelect processTagSelect = getWorkflowProcess(path);
        List result = new ArrayList();
        for(int i=0 ; i < activitys.length ; i++ ) {
            List transition = transitionUtil.getTransitionToActivityId(processTagSelect,activitys[i].getId());
            if(transition.size() == 0) {
                ActivityTagSelect tagSelect = createActivityTagSelectFromActivity(activitys[i],path);
                result.add(tagSelect);
            }
        }
        return result;
    }

    
    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

    public WorkflowProcessSelectRule getWorkflowProcessSelectRule() {
        return workflowProcessSelectRule;
    }

    public void setWorkflowProcessSelectRule(
            WorkflowProcessSelectRule workflowProcessSelectRule) {
        this.workflowProcessSelectRule = workflowProcessSelectRule;
    }

    public WakanagoWorkFlows getWorkFlows() {
        return workFlows;
    }

    public void setWorkFlows(WakanagoWorkFlows workFlows) {
        this.workFlows = workFlows;
    }

    public ActivitySelectRule getActivitySelectRule() {
        return activitySelectRule;
    }

    public void setActivitySelectRule(ActivitySelectRule activitySelectRule) {
        this.activitySelectRule = activitySelectRule;
    }

    public TransitionUtil getTransitionUtil() {
        return transitionUtil;
    }

    public void setTransitionUtil(TransitionUtil transitionUtil) {
        this.transitionUtil = transitionUtil;
    }

    public ParticipantUtil getParticipantUtil() {
        return participantUtil;
    }

    public void setParticipantUtil(ParticipantUtil participantUtil) {
        this.participantUtil = participantUtil;
    }

    public ActivityTagSelectUtil getTagSelectUtil() {
        return tagSelectUtil;
    }

    public void setTagSelectUtil(ActivityTagSelectUtil tagSelectUtil) {
        this.tagSelectUtil = tagSelectUtil;
    }
    
    
}
