/*
 * 作成日: 2006/06/19
 *
 */
package org.escafe.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 指定された複数アクティビティを選択します。
 * <p>
 * アクティビティが1つ以上選択され、かつ、
 * 1つ以上のアクティビティ名が明示指定されている場合のみ適用されます。
 * </p>
 * 
 * @author $Author$
 */
public class DirectMultiBuriActivitySelector extends AbstractBuriActivitySelector {

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        Set<BuriActivityType> result = new HashSet<BuriActivityType>();
        Set<String> activityNames = getActivityNames(systemContext);
        for (BuriActivityType activityType : activities) {
            if (activityNames.contains(activityType.getName())) {
                result.add(activityType);
            }
        }
        activities.clear();
        activities.addAll(result);
    }

    private Set<String> getActivityNames(BuriSystemContext systemContext) {
        Set acts = new HashSet();
        if (systemContext.getActivityNames() == null || systemContext.getActivityNames().isEmpty()) {
            acts.add(systemContext.getCallPath().getActivityName().get(0));
        } else {
            acts.addAll(systemContext.getActivityNames());
        }
        return acts;
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        if (activities.size() > 0 && systemContext.getCallPath().getActivityName().size() > 0) {
            return true;
        }
        return false;
    }

}
