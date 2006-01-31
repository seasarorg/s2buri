/*
 * 作成日: 2005/11/16
 *
 */
package org.seasar.buri.xpdl.rule.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.exception.select.BuriManySelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectProcessException;
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
        List actList = (List)buriExecuteEngine.execute(container, "buri2.BuriWorkFlowsUtil.getFirstActivity",args,"#activityList");
        ActivityTagSelect tagSelect = getActivityTagSelect(path,actList);
        return tagSelect;
    }
    
    protected ActivityTagSelect getActivityTagSelect(BuriPath path,List actList) {
        if(actList == null || actList.size() == 0 ) {
            throw new BuriNotSelectProcessException(path,"同名のActivityがあるのにユーザ情報");
         } else if(actList.size() > 1) {
             throw new BuriManySelectProcessException(path,"同名のActivityがあるのにユーザ情報");
         }
         ActivityTagSelect tagSelect = (ActivityTagSelect)actList.get(0);
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
