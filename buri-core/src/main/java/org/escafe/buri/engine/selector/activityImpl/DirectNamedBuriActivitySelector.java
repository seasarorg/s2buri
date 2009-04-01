/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine.selector.activityImpl;

import java.util.List;
import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * アクティビティ名からアクティビティ群を設定します。
 * <p>
 * 同一のアクティビティ名が複数設定されているフローの場合は、
 * 複数のアクティビティが選択されます。
 * </p>
 * <p>
 * 呼び出されれば常に適用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 *
 */
public class DirectNamedBuriActivitySelector extends AbstractBuriActivitySelector {

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#applyRule(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        // アクティビティ名のリストを取得
        List<String> activityNames = systemContext.getCallPath().getActivityName();
        // 先頭の要素を参照
        String actName = activityNames.get(0).toString();
        List<?> acts = execProcess.getBuriWorkflowProcessType().getActivityByName(actName);
        activities.clear();
        activities.addAll((List<? extends BuriActivityType>) acts);
    }

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#isTarget(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
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
