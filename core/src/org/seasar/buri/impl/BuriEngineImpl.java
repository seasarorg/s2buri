/*
 * ì¬“ú: 2005/06/20
 *
 */
package org.seasar.buri.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.engine.util.BuriTraceElement;
import org.seasar.buri.exception.BuriValidateException;
import org.seasar.buri.rule.ConditionProcessingRule;
import org.seasar.buri.rule.ToolExecuteRule;
import org.seasar.buri.xpdl.rule.ActivitySelectRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.BuriDataMetaData;
import org.seasar.buri.xpdl.util.BuriDataMetaDataUtil;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;

/**
 * @author makotan
 *
 */
public class BuriEngineImpl implements BuriEngine {
    private static Logger logger = Logger.getLogger(BuriEngineImpl.class);

    private WakanagoEngine wakanagoEngine;
    private WakanagoEngine buriExecuteEngine;
    private S2Container container;
    private BuriDataDaoUtil dataDaoUtil;
    private ContextUtil contextUtil;
    private BuriPathDaoUtil pathDaoUtil;
    private WorkFlowsUtil flowsUtil;
    private ActivitySelectRule activityFirstSelectRule;
    private BuriDataMetaDataUtil metaDataUtil;
    private DataAccessUtil accessUtil;
    private ToolExecuteRule toolExecuteRule;

    public void setWakanagoEngine(WakanagoEngine wakanagoEngine) {
        this.wakanagoEngine = wakanagoEngine;
    }
    
    public void execute(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        preProcess(path,context);
        setupContext(path);
        Map args = new HashMap();
        args.put("context",context);
        args.put("path",context.getCallBuriPath());
        buriExecuteEngine.execute(context.getContainer(), "buri2.BuriEngine.execute",args);
        toolExecuteRule.afterExecuteTool();
//        context.setContext(null);
    }
    
    protected void preProcess(BuriPath path,BuriLocalContext context) {
        BuriDataMetaData metaData = metaDataUtil.getMetaData(path,context.getData());
        String preProcess = metaData.getPreprocessOgnl();
        if( preProcess != null ) {
            Object result = accessUtil.callOgnls(metaData,preProcess,context.getData());
            context.setData(result);
            context.getUserContext().setData(result);
        }
    }
    
    public void traceBuriPath(BuriPath path) {
        if(logger.isDebugEnabled()) {
            Object data = contextUtil.getContext().getData();
            Object userData = contextUtil.getContext().getUserData();
            logger.debug("BuriEngine processed = "+path + "/data="+data + "/userData="+userData);
        }
        
    }
    
    protected void setupContext(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        if(context.getFlowPickout() == null) {
            FlowPickout flowPickout = (FlowPickout)container.getComponent(FlowPickout.class);
            context.setFlowPickout(flowPickout);
        }
        if(context.getContainer() == null) {
            context.setContainer(container);
        }
        dataDaoUtil.setupBuriParticipant(path);
        flowsUtil.getActivityTagSelectFromPathAndParticipant(path, context.getBuriParticipant(),activityFirstSelectRule);
//        getFirstActivityFromPathAndParticipant(path,context.getBuriParticipant());
//        BuriPath buriPath = pathDaoUtil.getBuriPath(path);
//        context.setContext(context);
//        context.setBuriEngine(this);
        context.setCallBuriPath(path);
        pathDaoUtil.getBuriPath(path);
        validateActionList(path);
    }

    protected void validateActionList(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        List validateList = (List)context.get("ValidateAction");
        if(validateList != null && validateList.size() >= 1) {
            Collections.sort(validateList);
            String actName = path.getActivity().get(0).toString();
            int pos = Collections.binarySearch(validateList,actName);
            if(pos==-1) {
                throw new BuriValidateException("",validateList,actName);
            }
        }
    }
    
    public BuriLocalContext createContext(Object data) {
        return createContext(data,container.getRoot());
    }
    
    public BuriLocalContext createContext(Object data,S2Container container) {
        BuriLocalContext context = contextUtil.getLocalContext();
        context.clear();
        contextUtil.getContext().clear();
//        context.setBuriEngine(this);
        context.setData(data);
        context.setContainer(container);
        context.setBuriParticipant(new BuriParticipant());
        FlowPickout flowPickout = (FlowPickout)container.getComponent(BuriFlowPickout.class);
        context.setFlowPickout(flowPickout);
        return context;
    }
    
    
    public BuriTraceElement createBuriTraceElement(BuriPath path) {
        BuriLocalContext context = contextUtil.getLocalContext();
        WakanagoWorkFlows workflows = wakanagoEngine.getWorkflows();
        BuriTraceElement traceEle = (BuriTraceElement)context.getContainer().getComponent(BuriTraceElement.class);
        ActivityTagSelect tagSelect = flowsUtil.getActivity(path);
        traceEle.setTracePath(path);
        traceEle.setWorkFlows(workflows);
        traceEle.setTagSelect(tagSelect);
        traceEle.setConditionRule((ConditionProcessingRule)context.getContainer().getComponent(ConditionProcessingRule.class));
        traceEle.setPropertyFromActivityTagSelect(tagSelect,path);
        return traceEle;
    }
    
    public WakanagoWorkFlows getWorkflows() {
        return wakanagoEngine.getWorkflows();
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriDataDaoUtil getDataDaoUtil() {
        return dataDaoUtil;
    }

    public void setDataDaoUtil(BuriDataDaoUtil dataDaoUtil) {
        this.dataDaoUtil = dataDaoUtil;
    }

    public void setBuriExecuteEngine(WakanagoEngine buriExecuteEngine) {
        this.buriExecuteEngine = buriExecuteEngine;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

    public BuriPathDaoUtil getPathDaoUtil() {
        return pathDaoUtil;
    }

    public void setPathDaoUtil(BuriPathDaoUtil pathDaoUtil) {
        this.pathDaoUtil = pathDaoUtil;
    }

    public ActivitySelectRule getActivityFirstSelectRule() {
        return activityFirstSelectRule;
    }

    public void setActivityFirstSelectRule(
            ActivitySelectRule activityFirstSelectRule) {
        this.activityFirstSelectRule = activityFirstSelectRule;
    }

    public DataAccessUtil getAccessUtil() {
        return accessUtil;
    }

    public void setAccessUtil(DataAccessUtil accessUtil) {
        this.accessUtil = accessUtil;
    }

    public BuriDataMetaDataUtil getMetaDataUtil() {
        return metaDataUtil;
    }

    public void setMetaDataUtil(BuriDataMetaDataUtil metaDataUtil) {
        this.metaDataUtil = metaDataUtil;
    }

    public ToolExecuteRule getToolExecuteRule() {
        return toolExecuteRule;
    }

    public void setToolExecuteRule(ToolExecuteRule toolExecuteRule) {
        this.toolExecuteRule = toolExecuteRule;
    }
    
}
