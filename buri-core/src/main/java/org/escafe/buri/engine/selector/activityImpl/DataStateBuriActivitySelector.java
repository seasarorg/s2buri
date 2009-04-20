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
import java.util.List;
import java.util.Set;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * 登録済みのデータが現在所属しているアクティビティ群を選択します。
 * <p>
 * 分岐フローによっては1つのデータが複数アクティビティにデータが所属する事が可能です。 その場合には、複数のアクティビティが選択されます。
 * </p>
 * <p>
 * 呼び出されれば常に適用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/23
 */
public class DataStateBuriActivitySelector extends AbstractBuriActivitySelector {
	/**
	 * データ操作ユーティリティ
	 */
	private BuriDataUtil dataUtil;

	/**
	 * ぶり固有のテーブル{@code BuriPath}のユーティリティ
	 */
	private BuriPathUtil pathUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRule(Set<BuriActivityType> activities,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		Set<BuriActivityType> result = new HashSet<BuriActivityType>();
		long dataID =
		    dataUtil.getBuriDataId(
		        (DataAccessFactory) execProcess,
		        systemContext);
		List<BuriPath> pathList = pathUtil.getPathListByDataId(dataID);
		for (BuriPath path : pathList) {
			String actID = path.getActivityId().get(0).toString();
			BuriActivityType act =
			    execProcess.getBuriWorkflowProcessType().getActivityById(actID);
			result.add(act);
		}
		activities.clear();
		activities.addAll(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isTarget(Set<BuriActivityType> activitys,
	        BuriSystemContext systemContext, BuriExecProcess execProcess) {
		return true;
	}

	/**
	 * データ操作ユーティリティを返します。
	 * 
	 * @return データ操作ユーティリティ
	 */
	public BuriDataUtil getDataUtil() {
		return dataUtil;
	}

	/**
	 * データ操作ユーティリティを登録します。
	 * 
	 * @param dataUtil
	 *            データ操作ユーティリティ
	 */
	public void setDataUtil(BuriDataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	/**
	 * ぶり固有のテーブル{@code BuriPath}のユーティリティを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriPath}のユーティリティ
	 */
	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	/**
	 * ぶり固有のテーブル{@code BuriPath}のユーティリティを登録します。
	 * 
	 * @param pathUtil
	 *            ぶり固有のテーブル{@code BuriPath}のユーティリティ
	 */
	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}
}
