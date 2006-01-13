/*
 * çÏê¨ì˙: 2005/11/16
 *
 */
package org.seasar.buri.xpdl.rule.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.xpdl.rule.ActivitySelectRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ActivityTagSelectUtil;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

public class BuriActivitySelectRule implements ActivitySelectRule {
    private WakanagoEngine buriExecuteEngine;
    private ContextUtil contextUtil;
    private ActivityTagSelectUtil selectUtil;
    private S2Container container;

    public ActivityTagSelect selectActivity(Activity[] activitys, BuriPath path,BuriParticipant participant) {
        if(activitys != null && activitys.length == 1) {
            return selectUtil.convTagSelect(activitys[0],path);
        }
        BuriLocalContext context = contextUtil.getLocalContext();
        Map args = new HashMap();
        args.put("path",path);
        args.put("participant",context.getBuriParticipant());
        args.put("context",context);
        Object act = buriExecuteEngine.execute(container, "buri2.BuriWorkFlowsUtil.getFirstActivity",args,"#result");
        ActivityTagSelect tagSelect = (ActivityTagSelect)act;
        if(path.getActivity().size()==0) {
            path.moveChildPath(tagSelect);
        } else {
            path.changePath(tagSelect);
        }
        return tagSelect;
    }

    public WakanagoEngine getBuriExecuteEngine() {
        return buriExecuteEngine;
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

    public ActivityTagSelectUtil getSelectUtil() {
        return selectUtil;
    }

    public void setSelectUtil(ActivityTagSelectUtil selectUtil) {
        this.selectUtil = selectUtil;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

}
