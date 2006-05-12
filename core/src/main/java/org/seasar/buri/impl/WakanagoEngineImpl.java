/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.impl;

import java.util.HashMap;
import java.util.Map;


import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.ToolUtil;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;


/**
 * @author makotan
 *
 */
public class WakanagoEngineImpl implements WakanagoEngine {
//    private WakanagoWorkFlows workflows;
    private S2Container container;
    private static Logger logger = Logger.getLogger(WakanagoEngineImpl.class);
    private WorkFlowsUtil flowsUtil;
    private ExtendedAttributeUtil attributeUtil;
    private ToolUtil toolUtil;
    private ContextUtil contextUtil;

    public Object execute(String strBuriPath, Object root, String context) {
        return execute(container,strBuriPath,root,context);
    }

    public Object execute(S2Container container,String strBuriPath,Object root,String context) {
        FlowPickout flowPickout = (FlowPickout)this.container.getComponent(FlowPickout.class);
        BuriPath buriPath = createBuriPath(strBuriPath);
        Map wkngContext = setupOgnlContext(container,root,flowPickout);
        ActivityTagSelect targetTagSelect = execute(container,buriPath,root,flowPickout,wkngContext);
        Object result = createResult(container,targetTagSelect,wkngContext,context);
        return result;
    }
    
    protected ActivityTagSelect execute(S2Container container,BuriPath buriPath,Object root,FlowPickout flowPickout,Map context) {
        ActivityTagSelect targetTagSelect = null;
        do {
            if(logger.isDebugEnabled()) {
                logger.debug("wakanago Processed = "+buriPath);
            }
            context.put("WakanagoEngineImpl.isDebugEnabled()",new Boolean(logger.isDebugEnabled()));
            targetTagSelect = flowsUtil.getActivity(buriPath);
            if(targetTagSelect.isFinishManual()==false) {
                toolUtil.processActivity(targetTagSelect,container,root,context);
                flowPickout.addProcessedActivity(buriPath,targetTagSelect,context);
                buriPath = flowPickout.getNextActivity();
            } else {
                buriPath = null;
            }
        } while(buriPath != null);
        return targetTagSelect;
    }
    
    protected Object createResult(S2Container container,ActivityTagSelect targetTagSelect,Map context,String contextStr) {
        Object result = null;
        if( ! StringUtil.isEmpty(contextStr)) {
            result = getContextData(container,contextStr,context,targetTagSelect);
        }
        return result;
    }
    
    public void execute(S2Container container,String strBuriPath,Object root) {
        execute(container,strBuriPath,root,null);
    }

    
    /**
     * @param ognlContext
     * @param context
     * @param targetTagSelect
     * @return
     */
    private Object getContextData(S2Container container,String contextStr,Map context, ActivityTagSelect targetTagSelect) {
        Map extendedAttributes = attributeUtil.get(targetTagSelect);
        context.put("extendedAttributes",extendedAttributes);
        ScriptProcessor processor = new ScriptProcessor();
        processor.putAllContext(extendedAttributes);
        processor.putAllContext(contextUtil.getContext());
        Object result = processor.getValue(contextStr,container,context);
        return result;
    }

    /**
     * @param ognlContext
     */
    private void clearContext(Map context) {
        context.clear();
    }

    /**
     * @param root
     * @param context
     * @return
     */
    protected Map setupOgnlContext(S2Container container,Object root,FlowPickout flowPickout) {
        BuriLocalContext context = (BuriLocalContext)this.container.getComponent(BuriLocalContext.class);
        Map wkngContext = new HashMap();
        wkngContext.putAll(context);
        wkngContext.put("args",root);
        wkngContext.put("BuriContext",wkngContext);
//        context.getUserContext().setData(root);
        wkngContext.put("container",container);
        wkngContext.put("flowPickout",flowPickout);
        return wkngContext;
    }
    
    protected BuriPath createBuriPath(String strBuriPath) {
        BuriPath buriPath = new BuriPath(strBuriPath);
        return buriPath;
    }

    public void execute(String buriPath, Object root) {
        execute(buriPath, root, null);
    }

    public S2Container getContainer() {
        return container;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public WakanagoWorkFlows getWorkflows(S2Container container) {
        WakanagoWorkFlows workFlows = (WakanagoWorkFlows)this.container.getComponent(WakanagoWorkFlows.class);
        return workFlows;
    }
    public WakanagoWorkFlows getWorkflows() {
        return getWorkflows(container);
    }
//    public void setWorkflows(WakanagoWorkFlows workflows) {
//        this.workflows = workflows;
//    }

    public ExtendedAttributeUtil getAttributeUtil() {
        return attributeUtil;
    }

    public void setAttributeUtil(ExtendedAttributeUtil attributeUtil) {
        this.attributeUtil = attributeUtil;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

    public ToolUtil getToolUtil() {
        return toolUtil;
    }

    public void setToolUtil(ToolUtil toolUtil) {
        this.toolUtil = toolUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }
}
