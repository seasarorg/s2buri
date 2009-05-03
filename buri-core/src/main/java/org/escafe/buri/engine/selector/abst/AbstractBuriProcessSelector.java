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

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 実行対象のプロセスを選択するための抽象クラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2006/05/29
 */
public abstract class AbstractBuriProcessSelector implements
        BuriProcessSelector {
	/**
	 * {@inheritDoc}
	 */
	public int select(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages) {
		if (!isTarget(processes, systemContext, execPackages)) {
			return SELECT_NEXT;
		}
		applyRule(processes, systemContext, execPackages);
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
	 * ルールの適用を行う対象かどうかを判定して返します。
	 * 
	 * @param processes 返されるフローのプロセスのリスト
	 * @param systemContext 実行用コンテキスト
	 * @param execPackages 実行対象のフローのパッケージ
	 * @return 適用処理の結果ステータス（成功時に{@code true}、失敗時に{@code false}）
	 */
	protected abstract boolean isTarget(
	        List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages);

	/**
	 * プロセス選択ルールを適用します。
	 * <p>
	 * 実行の結果、第1引数のListに変更が反映されます。
	 * </p>
	 * 
	 * @param processes 返されるフローのプロセスのリスト
	 * @param systemContext 実行用コンテキスト
	 * @param execPackages 実行対象のフローのパッケージ
	 */
	protected abstract void applyRule(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages);
}
