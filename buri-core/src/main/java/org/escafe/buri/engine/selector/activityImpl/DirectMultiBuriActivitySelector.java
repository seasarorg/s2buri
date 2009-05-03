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

import java.util.HashSet;
import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 指定された複数アクティビティを選択します。
 * <p>
 * アクティビティが1つ以上選択され、かつ、 1つ以上のアクティビティ名が明示指定されている場合のみ適用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2006/06/19
 */
public class DirectMultiBuriActivitySelector extends
        AbstractBuriActivitySelector {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRule(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		// アクティビティの取得
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

	/**
	 * 実行用コンテキストからアクティビティ名の一覧を取得して返します。
	 * <p>
	 * {@code Bao}で使用されたアノテーションも含めてアクティビティ名を取得します。
	 * </p>
	 * 
	 * @param systemContext 実行用コンテキスト
	 * @return 取得できたアクティビティ名の一覧
	 */
	private Set<String> getActivityNames(BuriSystemContext systemContext) {
		Set<String> acts = new HashSet<String>();
		// 実行対象のパスが1件以上の場合
		if (systemContext.getCallPath().getActivityName().size() > 0) {
			acts.add(systemContext.getCallPath().getActivityName().get(0));
		}
		// Baoアノテーションからの取得
		if ((systemContext.getActivityNames() != null)
		    && systemContext.getActivityNames().isEmpty() == false) {
			acts.addAll(systemContext.getActivityNames());
		}
		return acts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isTarget(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		if ((activities.size() > 0)
		    && (systemContext.getCallPath().getActivityName().size() > 0 || systemContext
		        .getActivityNames() != null)) {
			return true;
		}
		return false;
	}
}
