/*
 * 作成日: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExecProcess;

public class DirectActivitySelector extends AbstractBuriActivitySelector {

    protected Set getActivityList(Set activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List activityIds = systemContext.getCallPath().getActivityId();
        String activityId = activityIds.get(0).toString();
        Set result = new HashSet();
        BuriActivityType actType = execProcess.getBuriWorkflowProcessType().getActivityById(activityId);
        result.add(actType);
        return result;
    }

    protected boolean checkCanActivitySelect(Set activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List activityIds = systemContext.getCallPath().getRealPath().getActivity();
        if(activityIds.size() == 0) {
            return false;
        }
        if(activityIds.size() != 1 ) {
            throw new UnsupportedOperationException();
        }
        return true;
    }

}
