/*
 * ì¬“ú: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.util.packages.BuriExecProcess;

public class StartActivitySelector extends AbstractBuriActivitySelector {

    protected Set getActivityList(Set activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List acts = execProcess.getBuriWorkflowProcessType().getStartActivitys();
        return new HashSet(acts);
    }

    protected boolean checkCanActivitySelect(Set activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if(activitys.size() != 0) {
            return false;
        }
        if(systemContext.getCallPath().getActivityId().size()  > 0) {
            return false;
        }
        return true;
    }

}
