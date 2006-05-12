/*
 * çÏê¨ì˙: 2005/06/10
 *
 */
package org.seasar.buri.rule.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.component.AfterProcessBuriComponent;
import org.seasar.buri.component.BuriComponent3;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.rule.ToolExecuteRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ApplicationUutil;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.ApplicationDocument.Application;


/**
 * @author makotan
 *
 */
public class BuriToolExecuteRuleImpl implements ToolExecuteRule {
    private ApplicationUutil applicationUutil;
    private ContextUtil contextUtil;
    
    /* (îÒ Javadoc)
     * @see org.seasar.buri.rule.ToolExecuteRule#executeTool(org.wfmc.x2002.xpdl10.ToolDocument.Tool, java.lang.Object, java.util.Map)
     */
    public void executeTool(S2Container container,ActivityTagSelect targetTagSelect,ToolTagSelect tool, Object root,Map contextData) {
        Application app = applicationUutil.getApplication(targetTagSelect.getBuriPath(),tool.getTool().getId());
        String appName = app.getName();
        Object component = container.getComponent(appName);
        ScriptProcessor processor = new ScriptProcessor();
        if(component instanceof BuriComponent3) {
            BuriComponent3 buri = (BuriComponent3)component;
            buri.buriExecute(processor,tool,contextData);
        } else if(component instanceof AfterProcessBuriComponent) {
            BuriExecuteDelayInfo delayInfo = new BuriExecuteDelayInfo(component,tool,contextData);
            addAfterProcess(delayInfo);
        }
    }

    public void afterExecuteTool() {
        BuriLocalContext localContext = contextUtil.getLocalContext();
        List afterProcess = (List)localContext.get("AfterProcess");
        if(afterProcess == null) {
            return;
        }
        Iterator ite = afterProcess.iterator();
        ScriptProcessor processor = new ScriptProcessor();
        while(ite.hasNext()) {
            BuriExecuteDelayInfo delayInfo = (BuriExecuteDelayInfo)ite.next();
            AfterProcessBuriComponent component = (AfterProcessBuriComponent)delayInfo.component;
            component.buriExecute(processor,delayInfo.tagSelect,delayInfo.contextData);
        }
    }

    
    protected void addAfterProcess(BuriExecuteDelayInfo delayInfo) {
        BuriLocalContext localContext = contextUtil.getLocalContext();
        List afterProcess = (List)localContext.get("AfterProcess");
        if(afterProcess==null) {
            afterProcess = new ArrayList();
            localContext.put("AfterProcess",afterProcess);
        }
        afterProcess.add(delayInfo);
    }
    
    private  class BuriExecuteDelayInfo {
        public Object component;
        public ToolTagSelect tagSelect;
        public Map contextData;
        public BuriExecuteDelayInfo(Object component,ToolTagSelect tagSelect,Map contextData) {
            this.component = component;
            this.tagSelect = tagSelect;
            this.contextData = contextData;
        }
    }

    public ApplicationUutil getApplicationUutil() {
        return applicationUutil;
    }

    public void setApplicationUutil(ApplicationUutil applicationUutil) {
        this.applicationUutil = applicationUutil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

}
