/*
 * çÏê¨ì˙: 2005/06/29
 *
 */
package org.seasar.buri.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.util.BuriTraceElement;
import org.seasar.framework.container.S2Container;


/**
 * @author makotan
 *
 */
public class BuriFlowPickout extends WakanagoFlowPickoutImpl implements
        FlowPickout {
    private WakanagoEngine buriExecuteEngine;
    private Stack waiting = new Stack();
    private Set status = new HashSet();
    private ContextUtil contextUtil;
//    private S2Container container;

    public void setBuriExecuteEngine(WakanagoEngine wakanagoEngine) {
        this.buriExecuteEngine = wakanagoEngine;
    }
    
    public void doSelectActivity(BuriTraceElement traceEle) {
        BuriLocalContext context = contextUtil.getLocalContext();
        traceEle.setConditionRule(getConditionRule());
        Map args = setupArgs(traceEle);
        buriExecuteEngine.execute(getContainer(),"buri2.BuriFlowPickout.doSelectActivity",args);
        updateArgOriginalParam(args);
    }

    
    public void doFirstSelectActivity(BuriTraceElement traceEle) {
        BuriLocalContext context = contextUtil.getLocalContext();
        traceEle.setConditionRule(getConditionRule());
        Map args = setupArgs(traceEle);
        buriExecuteEngine.execute(getContainer(),"buri2.BuriFlowPickout.doFirstSelectActivity",args);
        updateArgOriginalParam(args);
    }
    
    protected void updateArgOriginalParam(Map args) {
        Stack waiting = (Stack)args.get("waitStack");
        Set status = (HashSet)args.get("status");
        getWaiting().addAll(waiting);
        getStatus().addAll(status);
        
    }
    
    protected Map setupArgs(BuriTraceElement traceEle) {
        Map args = new HashMap();
        waiting = new Stack();
        status = new HashSet();
        args.put("traceEle",traceEle);
        args.put("waitStack",waiting);
        args.put("status",status);
        return args;
    }
    
    public Stack getPrevWaiting() {
        return waiting;
    }
    
    public Set getPrevStatus() {
        return status;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

//    public S2Container getContainer() {
//        return container;
//    }
//
//    public void setContainer(S2Container container) {
//        this.container = container;
//    }
    
}
