/*
 * çÏê¨ì˙: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExecProcess;

public class DirectActivitySelector extends AbstractBuriActivitySelector {

    protected List getActivityList(List activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List activityIds = systemContext.getCallPath().getActivityId();
        String activityId = activityIds.get(0).toString();
        List result = new ArrayList();
        BuriActivityType actType = execProcess.getBuriWorkflowProcessType().getActivityById(activityId);
        result.add(actType);
        return result;
    }

    protected boolean checkCanActivitySelect(List activitys, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if(activitys.size() != 0) {
            return false;
        }
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
