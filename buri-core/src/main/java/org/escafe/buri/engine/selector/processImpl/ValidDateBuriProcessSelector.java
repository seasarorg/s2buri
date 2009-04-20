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
package org.escafe.buri.engine.selector.processImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.starlogic.util.datetime.DateUtil;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriProcessSelector;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.seasar.framework.util.StringUtil;

/**
 * 有効期間に基づいてプロセスを選択します。
 * <p>
 * 実行対象として指定された同名のプロセスが2件以上検出された場合のみ適用されます。 適用された場合、ここで決定した1つのみが結果に含まれます。
 * </p>
 * <p>
 * ぶりでは特定の期間だけ有効となるプロセスを定義することが出来ます。 例えば、来年4月1日から業務の流れを変える必要があるときに、
 * それ以降に追加されるデータの流れをぶりが自動的に変えてくれるようになります。 この機能を使うためには、 XPDLファイルで {@code Process}
 * のプロパティーの{@code Process header}の {@code valid from}と{@code valid to}
 * に有効期間の開始日付と終了日付を指定します。 時間指定はオプションで、無指定の場合は{@code 0:0:0}を指定したとみなします。
 * 時間が重複した同名のプロセスが存在しているとエラーになります。
 * </p>
 * <p>
 * なお、この機能によって適用されるフローが変更になった場合でも、 以前のフロー上を流れているデータは新しいフローに移行されません。
 * 新しく投入されたデータだけが新しいフローを流れるようになります。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/07/15
 */
public class ValidDateBuriProcessSelector extends AbstractBuriProcessSelector {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isTarget(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages) {
		String realProcsName = systemContext.getCallPath().getWorkflowProcess();
		if (StringUtil.isEmpty(realProcsName)) {
			return false;
		}
		if (execPackages
		    .getBuriPackageType()
		    .getProcessByName(realProcsName)
		    .size() > 1) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRule(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages) {
		String realProcsName = systemContext.getCallPath().getWorkflowProcess();
		List<BuriWorkflowProcessType> tgts =
		    execPackages.getBuriPackageType().getProcessByName(realProcsName);
		List<BuriWorkflowProcessType> result =
		    new ArrayList<BuriWorkflowProcessType>();
		for (BuriWorkflowProcessType process : tgts) {
			if (isInValidDate(process)) {
				result.add(process);
			}
		}
		processes.clear();
		processes.addAll(result);
	}

	/**
	 * フローの有効期間を検査して、現在有効なフローかどうかを判定します。
	 * 
	 * @param process
	 *            対象となるフローの{@link BuriWorkflowProcessType}
	 * @return 有効なフローであれば{@code true}、そうでない場合は{@code false}
	 */
	private static boolean isInValidDate(BuriWorkflowProcessType process) {
		Date validFrom = process.getValidFrom();
		Date validTo = process.getValidTo();
		if ((validFrom != null) && (validTo != null)) {
			Date now = new Date();
			if ((DateUtil.compare(validFrom, now) <= 0)
			    && (DateUtil.compare(now, validTo) < 0)) {
				return true;
			}
		}
		return false;
	}
}
