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

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriProcessSelector;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 直接指定されている1つのプロセスを選択します。
 * <p>
 * まだプロセスが選択されておらず、かつ、 実行対象として指定されたプロセスが1件のみ検出された場合に適用されます。
 * 適用された場合、ここで決定した1つのみが結果に含まれます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/29
 */
public class DirectSingleBuriProcessSelector extends
        AbstractBuriProcessSelector {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isTarget(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages) {
		if (processes.size() > 0) {
			return false;
		}
		String processName = systemContext.getCallPath().getWorkflowProcess();
		if (execPackages
		    .getBuriPackageType()
		    .getProcessByName(processName)
		    .size() > 1) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRule(List<BuriWorkflowProcessType> processes,
	        BuriSystemContext systemContext, BuriExePackages execPackages) {
		String processName = systemContext.getCallPath().getWorkflowProcess();
		processes.clear();
		processes.addAll(execPackages.getBuriPackageType().getProcessByName(
		    processName));
	}
}
