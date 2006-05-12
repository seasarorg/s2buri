/*
 * çÏê¨ì˙: 2005/06/04
 *
 */
package org.seasar.buri.engine.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.rule.ConditionProcessingRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.TransitionUtil;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.wfmc.x2002.xpdl10.TransitionDocument.Transition;

/**
 * @author makotan
 *
 */
public class BuriTraceElement {
    private List nextElementItems = new ArrayList();
    private BuriPath tracePath;
    private WakanagoWorkFlows workFlows;
    private ConditionProcessingRule conditionRule;
    private ActivityTagSelect tagSelect;
    
    private WorkFlowsUtil flowsUtil;
    private TransitionUtil transitionUtil;
    
    public BuriPath getTracePath() {
        return tracePath;
    }
    public void setTracePath(BuriPath tracePath) {
        this.tracePath = tracePath;
    }
    public List getNextElementItems() {
        return nextElementItems;
    }
    public void setNextElementItems(List transitions) {
        this.nextElementItems = transitions;
    }
    public WakanagoWorkFlows getWorkFlows() {
        return workFlows;
    }
    public void setWorkFlows(WakanagoWorkFlows workFlows) {
        this.workFlows = workFlows;
    }
    
    public void setPropertyFromActivityTagSelect(ActivityTagSelect tagSelect,BuriPath tracePath) {
        this.tracePath = (BuriPath)tracePath.clone();
        this.tagSelect = tagSelect;
        setNextElementItemsFromActivityTagSelect(tagSelect);
    }
    
    protected void setNextElementItemsFromActivityTagSelect(ActivityTagSelect tagSelect) {
        List transitions;
        WorkflowProcessTagSelect processTagSelect = flowsUtil.getWorkflowProcess(tagSelect.getBuriPath());
        String activityId = tagSelect.getActivity().getId();
        transitions = transitionUtil.getTransitionFromActivityId(processTagSelect,activityId);
        addBuriTraceElementItemFromTransitions(transitions,nextElementItems);
    }
    
    protected void addBuriTraceElementItemFromTransitions(List transitions,List elementItems) {
        Iterator ite = transitions.iterator();
        while(ite.hasNext()) {
            Transition tran = (Transition)ite.next();
            BuriTraceElementItem elementItem;
            elementItem = convTransitionToBuriTraceElementItem(tran);
            elementItems.add(elementItem);
        }
    }
    protected BuriTraceElementItem convTransitionToBuriTraceElementItem(Transition tran) {
        BuriTraceElementItem elementItem = new BuriTraceElementItem();
        String condition = "true";
        if(tran.getCondition() != null) { 
            condition = tran.getCondition().newCursor().getTextValue();
        }
        elementItem.setConditionRule(conditionRule);
        elementItem.setCondition(condition);
        elementItem.setNextActivityId(tran.getTo());
        return elementItem;
    }

    public void setConditionRule(ConditionProcessingRule conditionRule) {
        this.conditionRule = conditionRule;
    }

    public ActivityTagSelect getTagSelect() {
        return tagSelect;
    }
    public void setTagSelect(ActivityTagSelect tagSelect) {
        this.tagSelect = tagSelect;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("nextElementItems=").append(nextElementItems);
        buff.append("\n/tracePath=").append(tracePath);
        buff.append("\n/workFlows=").append(workFlows);
        buff.append("]");
        return buff.toString();
    }
    public class BuriTraceElementItem {
        private String condition = null;
        private boolean judges = false;
        private String nextActivityId = "";
        private BuriTraceElement childElement;
        private ConditionProcessingRule conditionRule;
        
        public BuriTraceElement getChildElement() {
            return childElement;
        }
        public void setChildElement(BuriTraceElement childElement) {
            this.childElement = childElement;
        }
        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            this.condition = condition;
        }
        public boolean isJudges() {
            return judges;
        }
        public void setJudges(boolean decision) {
            this.judges = decision;
        }
        public String getNextActivityId() {
            return nextActivityId;
        }
        public void setNextActivityId(String nextActivityId) {
            this.nextActivityId = nextActivityId;
        }
        public void setConditionRule(ConditionProcessingRule conditionRule) {
            this.conditionRule = conditionRule;
        }
        public void setJudgesCondition(Map context) {
            boolean judges = conditionRule.judgesCondition(getCondition(),context);
            setJudges(judges);
        }
        public String toString() {
            StringBuffer buff = new StringBuffer("[");
            buff.append("condition=").append(condition);
            buff.append("/judges=").append(judges);
            buff.append("/nextActivityId=").append(nextActivityId);
            buff.append("/childElement=").append(childElement);
            buff.append("]");
            return buff.toString();
        }
    }
    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }
    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }
    public TransitionUtil getTransitionUtil() {
        return transitionUtil;
    }
    public void setTransitionUtil(TransitionUtil transitionUtil) {
        this.transitionUtil = transitionUtil;
    }
    public ConditionProcessingRule getConditionRule() {
        return conditionRule;
    }
}

