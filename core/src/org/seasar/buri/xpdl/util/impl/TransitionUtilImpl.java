/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.common.util.ArrayUtil;
import org.seasar.buri.exception.IllegalArgumentRuntimeException;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.TransitionUtil;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;
import org.wfmc.x2002.xpdl10.TransitionDocument.Transition;
import org.wfmc.x2002.xpdl10.TransitionRefDocument.TransitionRef;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;


public class TransitionUtilImpl implements TransitionUtil {
    
    private XPathUtil pathUtil;
    private WorkFlowsUtil flowsUtil;

    protected final String split = "Split";
    protected final String join = "Join";
    protected final String and = "AND";
    protected final String xor = "XOR";
    
    
    protected String getTransitionPath(String splitOrJoin,String andOrXor) {
        String xpath = getTransitionRestrictionPath(splitOrJoin,andOrXor) + "/xpdl:TransitionRefs/xpdl:TransitionRef";
        return xpath;
    }
    
    protected String getTransitionRestrictionPath(String splitOrJoin,String andOrXor) {
        String xpath = "./xpdl:TransitionRestrictions/xpdl:TransitionRestriction/xpdl:"+splitOrJoin+"[@Type='"+andOrXor+"']";
        return xpath;
    }
    
    protected String getJoinTransitionPath(String andOrXor) {
        String xpath = getTransitionRestrictionPath(join,andOrXor);
        return xpath;
    }
    
    public List getTransitions(ActivityTagSelect tagSelect, String splitOrJoin , String andOrXor) {
        ArrayList transitions = new ArrayList();
        String xpath = getTransitionPath(splitOrJoin,andOrXor);
        XmlObject[] objs = pathUtil.getXmlObjArrayFromXPath(tagSelect.getXmlObject(),xpath);
        if(objs.length == 0) {
            return transitions;
        }
        TransitionRef[] trefs = (TransitionRef[])objs;
        for(int i=0 ; i < trefs.length ; i++ ) {
            String transitionId = trefs[i].getId();
            WorkflowProcessTagSelect workflowProcessTagSelect = flowsUtil.getWorkflowProcess(tagSelect.getBuriPath());
            Transition transition = getTransitionFromId(workflowProcessTagSelect,transitionId);
            transitions.add(transition);
        }
        return transitions;
    }
    
    protected XmlObject[] getTransitionRestriction(XmlObject xmlObj,String splitOrJoin,String andOrXor) {
        String xpath = getTransitionRestrictionPath(splitOrJoin,andOrXor);
        XmlObject[] objs = pathUtil.getXmlObjArrayFromXPath(xmlObj,xpath);
        return objs;
    }

    public boolean hasSplitTransitionRestriction(ActivityTagSelect tagSelect,String andOrXor) {
        XmlObject[] objs = getTransitionRestriction(tagSelect.getXmlObject(),split,andOrXor);
        boolean result;
        if(objs.length == 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    
    protected boolean hasJoinTransitionRestriction(ActivityTagSelect tagSelect,String andOrXor) {
        XmlObject[] objs = getTransitionRestriction(tagSelect.getXmlObject(),join,andOrXor);
        if(objs.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List getJoinTransitions(ActivityTagSelect tagSelect, String andOrXor) {
        String xpath = getJoinTransitionPath(andOrXor);
        XmlObject[] objs = pathUtil.getXmlObjArrayFromXPath(tagSelect.getXmlObject(),xpath);
        if(objs.length==0) {
            return new ArrayList();
        }
        Activity activity = (Activity)tagSelect.getXmlObject();
        String activityId = activity.getId();
        WorkflowProcessTagSelect workflowProcessTagSelect = flowsUtil.getWorkflowProcess(tagSelect.getBuriPath());
        List transitions = getTransitionToActivityId(workflowProcessTagSelect,activityId);
        return transitions;
    }

    public boolean isSplitAnd(ActivityTagSelect tagSelect) {
        return hasSplitTransitionRestriction(tagSelect,and);
    }

    public boolean isSplitXor(ActivityTagSelect tagSelect) {
        if(isSplitAnd(tagSelect) == true) {
            return false;
        }
        boolean ret = hasSplitTransitionRestriction(tagSelect,xor);
        if(ret == true) {
            return ret;
        }
        WorkflowProcessTagSelect workflowProcessTagSelect = flowsUtil.getWorkflowProcess(tagSelect.getBuriPath());
        List transitions = getTransitionFromActivityId(workflowProcessTagSelect,tagSelect.getActivity().getId());
        if(transitions.size() > 0) {
            ret = true;
        }
        return ret;
    }

    public boolean isJoinAnd(ActivityTagSelect tagSelect) {
        return hasJoinTransitionRestriction(tagSelect,and);
    }
    
    public boolean isJoinXor(ActivityTagSelect tagSelect) {
        if(isJoinAnd(tagSelect) == true) {
            return false;
        }
        boolean ret = hasJoinTransitionRestriction(tagSelect,xor);
        if(ret == true) {
            return ret;
        }
        WorkflowProcessTagSelect workflowProcessTagSelect = flowsUtil.getWorkflowProcess(tagSelect.getBuriPath());
        List transitions = getTransitionToActivityId(workflowProcessTagSelect,tagSelect.getActivity().getId());
        if(transitions.size() > 0) {
            ret = true;
        }
        return ret;
    }

    
    protected String getTransitionXPath() {
        String xpath = "./xpdl:Transitions/xpdl:Transition";
        return xpath;
    }
    
    protected Transition[] getTransitionArray(WorkflowProcessTagSelect tagSelect,String name) {
        Transition[] transitions = (Transition[])pathUtil.getXmlObjArrayFromXPath(tagSelect.getXmlObject(),getTransitionXPath(),null,name);
        return transitions;
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.WorkflowProcessTagSelect#getTransitionFromActivityId(java.lang.String)
     */
    public List getTransitionFromActivityId(WorkflowProcessTagSelect tagSelect,String activityId) {
        String xpath = getTransitionXPath() + "[@From='" + activityId + "']";
        List xmlObjs = pathUtil.getXmlObjListFromXPath(tagSelect.getXmlObject(),xpath);
        return xmlObjs;
    }

    public List getTransitionToActivityId(WorkflowProcessTagSelect tagSelect,String activityId) {
        String xpath = getTransitionXPath() + "[@To='" + activityId + "']";
        List xmlObjs = pathUtil.getXmlObjListFromXPath(tagSelect.getXmlObject(),xpath);
        return xmlObjs;
    }

    public Transition getTransitionFromId(WorkflowProcessTagSelect tagSelect,String transitionId) {
        Transition[] transitions = (Transition[])getTransitionArray(tagSelect,transitionId);
        if(ArrayUtil.isEmpty(transitions)) {
            WorkflowProcess process = tagSelect.getWorkflowProcess();
            throw new IllegalArgumentRuntimeException("EBRI0012",new Object[]{"Process",process.getName()+"/"+process.getId(),"transitionId",transitionId});
        }
        return transitions[0];
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

}
