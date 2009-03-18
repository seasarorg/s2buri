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
 * 指定された1つのアクティビティを選択します。
 * <p>
 * 既に選択済みのアクティビティ群は無視され、ここで決定した1つのみが結果に含まれます。
 * </p>
 * <p>
 * アクティビティIDが1つだけ明示指定された場合のみ適用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/23
 */
public class DirectSingleBuriActivitySelector extends AbstractBuriActivitySelector {

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#applyRule(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        List<String> activityIds = systemContext.getCallPath().getActivityId();
        String activityId = activityIds.get(0).toString();
        BuriActivityType actType = execProcess.getBuriWorkflowProcessType().getActivityById(activityId);
        activities.clear();
        activities.add(actType);
    }

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#isTarget(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
//        List<String> activityIds = systemContext.getCallPath().getRealPath().getActivity();
        if (activities.isEmpty()) {
            return false;
        }
        if (activities.size() != 1) {
        	return false;
        }
        return true;
    }

}
