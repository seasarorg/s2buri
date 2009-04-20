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
 * 既に1つ以上のアクティビティが選択されている場合は適用されません。 また、対象アクティビティが明示指定されている場合にも適用されません。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/23
 */
public class StartBuriActivitySelector extends AbstractBuriActivitySelector {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRule(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		activities.clear();
		activities.addAll(execProcess
		    .getBuriWorkflowProcessType()
		    .getStartActivitys());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isTarget(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		if (!activities.isEmpty()) {
			return false;
		}
		if (!systemContext.getCallPath().getActivityName().isEmpty()) {
			return false;
		}
		return true;
	}
}
