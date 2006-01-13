/*
 * çÏê¨ì˙: 2005/06/08
 *
 */
package org.seasar.buri.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


import org.seasar.buri.common.util.StackUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.engine.util.BuriTraceElement;
import org.seasar.buri.engine.util.BuriTraceElement.BuriTraceElementItem;
import org.seasar.buri.exception.BuriNotSupportOperationException;
import org.seasar.buri.rule.ConditionProcessingRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.framework.container.S2Container;


/**
 * @author makotan
 *
 */
public class WakanagoFlowPickoutImpl implements FlowPickout {
    
    private List history = new ArrayList();
    private Stack waiting = new Stack();
    private Set status = new HashSet();
    
    private WakanagoWorkFlows workFlows;
    private ConditionProcessingRule conditionRule;
    
    private S2Container container;
    
    /* (îÒ Javadoc)
     * @see org.seasar.buri.engine.FlowPickout#addProcessedActivity(org.seasar.buri.xpdl.util.ActivityTagSelect)
     */
    public void addProcessedActivity(BuriPath path,ActivityTagSelect tagSelect,Map context) {
        BuriTraceElement traceEle = createBuriTraceElement(path,tagSelect);
        history.add(traceEle);
        doSetupPickoutInfoFromBuriTraceElement(path,traceEle,context);
    }
    
    protected BuriTraceElement createBuriTraceElement(BuriPath path,ActivityTagSelect tagSelect) {
        BuriTraceElement traceEle = (BuriTraceElement)container.getComponent(BuriTraceElement.class);
        traceEle.setPropertyFromActivityTagSelect(tagSelect,path);
        return traceEle;
    }
    
    protected void doSetupPickoutInfoFromBuriTraceElement(BuriPath basePath,BuriTraceElement traceEle,Map context) {
        Stack waitStack = new Stack();
        setupWaitStack(basePath,waitStack,traceEle,context);
        doSelectActivity(traceEle,waitStack);
    }
    
    public Stack setupWaitStack(BuriPath basePath,Stack waitStack,BuriTraceElement traceEle,Map context) {
        Iterator ite = traceEle.getNextElementItems().iterator();
        while(ite.hasNext()) {
            BuriTraceElementItem item = (BuriTraceElementItem)ite.next();
            item.setJudgesCondition(context);
            if(item.isJudges()) {
                addWaitingFromBuriTraceElementItem(basePath,item,waitStack);
            }
        }
        return waitStack;
    }
    
    public void doSelectActivity(BuriTraceElement traceEle,Stack waitStack) {
        if(waitStack.size()==0) {
            doZeroWaitStack(traceEle,waitStack);
        } else if(waitStack.size()==1) {
            doOneWaitStack(traceEle,waitStack);
        } else {
            doManyWaitStack(traceEle,waitStack);
        }
    }
    
    protected void doZeroWaitStack(BuriTraceElement traceEle,Stack waitStack) {
        addState(traceEle);
    }
    
    protected void doOneWaitStack(BuriTraceElement traceEle,Stack waitStack) {
        addWaiting(traceEle,waitStack);
    }
    
    protected void doManyWaitStack(BuriTraceElement traceEle,Stack waitStack) {
        throw new BuriNotSupportOperationException("EBRI0006",new Object[]{"ï™äÚèàóù",traceEle});
    }
    
    
    protected void addWaitingFromBuriTraceElementItem(BuriPath basePath,BuriTraceElementItem item,Stack waitStack)  {
        BuriPath waitingPath;
        waitingPath = (BuriPath)basePath.clone();
        waitingPath.setBuriPathID(0);
        String newId = item.getNextActivityId();
        waitingPath.changePath("",newId);
        WorkFlowsUtil flowsUtil = (WorkFlowsUtil)container.getComponent(WorkFlowsUtil.class);
        waitingPath = flowsUtil.getActivity(waitingPath).getBuriPath();
        waitStack.push(waitingPath);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.engine.FlowPickout#getNextActivity()
     */
    public BuriPath getNextActivity() {
        if(waiting.size()==0) {
            return null;
        }
        BuriPath nextActPath = (BuriPath)waiting.pop();
        return nextActPath;
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.engine.FlowPickout#addWaiting(org.seasar.buri.engine.BuriTraceElement)
     */
    public void addWaiting(BuriTraceElement traceEle,Stack waitStack) {
        StackUtil.putAllStack(waiting,waitStack);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.engine.FlowPickout#addState(org.seasar.buri.engine.BuriTraceElement)
     */
    public void addState(BuriTraceElement traceEle) {
        status.add(traceEle.getTracePath());
    }

    public void setConditionRule(ConditionProcessingRule conditionRule) {
        this.conditionRule = conditionRule;
    }
    public void setWorkFlows(WakanagoWorkFlows workFlows) {
        this.workFlows = workFlows;
    }
    protected List getHistory() {
        return history;
    }
    protected void setHistory(List history) {
        this.history = history;
    }
    public WakanagoWorkFlows getWorkFlows() {
        return workFlows;
    }

    public ConditionProcessingRule getConditionRule() {
        return conditionRule;
    }

    public Set getStatus() {
        return status;
    }

    public void setStatus(Set status) {
        this.status = status;
    }

    public Stack getWaiting() {
        return waiting;
    }

    public void setWaiting(Stack waiting) {
        this.waiting = waiting;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }
}
