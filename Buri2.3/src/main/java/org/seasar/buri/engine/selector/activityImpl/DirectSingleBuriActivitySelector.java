/*
 * 作成日: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.List;
import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExecProcess;

/**
 * 指定された1つのアクティビティを選択します。
 * <p>
 * 既に選択済みのアクティビティ群は無視され、ここで決定した1つのみが結果に含まれます。
 * </p>
 * <p>
 * アクティビティIDが1つだけ明示指定された場合のみ適用されます。
 * </p>
 * 
 * @author $Author$
 */
public class DirectSingleBuriActivitySelector extends AbstractBuriActivitySelector {

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        List activityIds = systemContext.getCallPath().getActivityId();
        String activityId = activityIds.get(0).toString();
        BuriActivityType actType = execProcess.getBuriWorkflowProcessType().getActivityById(
            activityId);
        activities.clear();
        activities.add(actType);
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        List activityIds = systemContext.getCallPath().getRealPath().getActivity();
        if (activityIds.isEmpty()) {
            return false;
        }
        if (activityIds.size() != 1) {
            throw new UnsupportedOperationException();
        }
        return true;
    }

}
