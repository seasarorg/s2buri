/*
 * ì¬“ú: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.util.packages.BuriExecProcess;

public class StartActivitySelector extends AbstractBuriActivitySelector {

    protected List getActivityList(List activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        return execProcess.getBuriWorkflowProcessType().getStartActivitys();
    }

    protected boolean checkCanActivitySelect(List activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if(activitys.size() != 0) {
            return false;
        }
        if(systemContext.getCallPath().getActivityId().size()  > 0) {
            return false;
        }
        return true;
    }

}
