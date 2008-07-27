package org.escafe.buri.engine.selector.activityImpl;

import java.util.List;
import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

public class DirectNamedBuriActivitySelector extends AbstractBuriActivitySelector {

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List activityNames = systemContext.getCallPath().getActivityName();
        String actName = activityNames.get(0).toString();
        List acts = execProcess.getBuriWorkflowProcessType().getActivityByName(actName);
        activities.clear();
        activities.addAll(acts);
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if (!activities.isEmpty()) {
            return false;
        }
        if (systemContext.getCallPath().getActivityName().isEmpty()) {
            return false;
        }
        return true;
    }

}
