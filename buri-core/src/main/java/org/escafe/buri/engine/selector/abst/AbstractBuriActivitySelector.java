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
package org.escafe.buri.engine.selector.abst;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 実行対象のアクティビティを選択するための抽象クラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/23
 */
public abstract class AbstractBuriActivitySelector implements
        BuriActivitySelector {
	/**
	 * {@inheritDoc}
	 */
	public int select(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		if (!isTarget(activities, systemContext, execProcess)) {
			return SELECT_NEXT;
		}
		applyRule(activities, systemContext, execProcess);
		return getDefaultResultType();
	}

	/**
	 * デフォルトの結果種別を返します。
	 * 
	 * @return [選択に成功し、続けて他のセレクタを実行する]旨の結果種別
	 */
	protected int getDefaultResultType() {
		return SELECT_NEXT;
	}

	/**
	 * アクティビティ選択ルールを適用します。
	 * <p>
	 * 実行の結果、第1引数のSetに変更が反映されます。
	 * </p>
	 * 
	 * @param activities
	 *            フローのアクティビティのリスト
	 * @param systemContext
	 *            実行情報のコンテキスト
	 * @param execProcess
	 *            対象となるフローのプロセス
	 */
	protected abstract void applyRule(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess);

	/**
	 * ルールの適用を行う対象かどうかを判定します。
	 * 
	 * @param activities
	 *            フローのアクティビティのリスト
	 * @param systemContext
	 *            実行情報のコンテキスト
	 * @param execProcess
	 *            対象となるフローのプロセス
	 * @return 実行対象である場合は{@code true}、実行対象ではないは{@code false}。
	 */
	protected abstract boolean isTarget(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess);
}
