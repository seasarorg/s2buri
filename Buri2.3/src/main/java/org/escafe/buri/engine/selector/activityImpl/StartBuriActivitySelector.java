/*
 * 作成日: 2006/05/23
 *
 */
package org.escafe.buri.engine.selector.activityImpl;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * プロセス中の開始アクティビティにあたるアクティビティ群を選択します。
 * <p>
 * 既に選択済みのアクティビティ群は無視され、ここで決定した1つのみが結果に含まれます。
 * </p>
 * <p>
 * 既に1つ以上のアクティビティが選択されている場合は適用されません。
 * また、対象アクティビティが明示指定されている場合にも適用されません。
 * </p>
 * 
 * @author $Author$
 */
public class StartBuriActivitySelector extends AbstractBuriActivitySelector {

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        activities.clear();
        activities.addAll(execProcess.getBuriWorkflowProcessType().getStartActivitys());
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if (!activities.isEmpty()) {
            return false;
        }
        if (!systemContext.getCallPath().getActivityName().isEmpty()) {
            return false;
        }
        return true;
    }

}
