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
package org.escafe.buri.engine.impl;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.compiler.BuriCompiler;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.service.BuriPathDataEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

/**
 * ぶりのエンジンの実装です。
 * <p>
 * 権限管理を行わないフローを実行する際に使用するエンジンです。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/24
 */
public class BuriSimpleEngineImpl extends WakanagoEngineImpl implements
        BuriEngine {
	/**
	 * ぶり固有のビュー{@code BuriPathDataEntity}のService
	 */
	private BuriPathDataEntityService buriPathDataEntityService;

	/**
	 * クラス定義ユーティリティ
	 */
	private ClassDefUtil classDefUtil;

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のService
	 */
	private BuriStateEntityService buriStateEntityService;

	/*
	 * @see
	 * org.escafe.buri.engine.BuriEngine#setupUserID(org.escafe.buri.engine.
	 * BuriSystemContext)
	 */
	public void setupUserID(BuriSystemContext sysContext) {
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.WakanagoEngineImpl#readWorkFlowFromResource
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName) {
		readFromResource(workFlowName, resourceName, null);
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.WakanagoEngineImpl#readWorkFlowFromResource
	 * (java.lang.String, java.lang.String,
	 * org.escafe.buri.engine.ParticipantProvider)
	 */
	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName, ParticipantProvider provider) {
	}

	/**
	 * {@link BuriSystemContext}で指定されたデータと状態の関係を無効にします。
	 * <p>
	 * この操作で、前後のデータと状態の整合性の調整などは行われません。
	 * </p>
	 * 
	 * @param sysContext
	 *            フローの実行に必要なコンテキスト
	 */
	public void abortData(BuriSystemContext sysContext) {
		BuriExePackages wPackageObj = selectPackage(sysContext);
		BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
		Object data = sysContext.getUserContext().getData();
		BuriDataAccessFactory factory = (BuriDataAccessFactory) wp;
		DataAccessUtil accessUtil = factory.getDataAccessUtil(data.getClass());
		String manyKey = getManyKey(accessUtil, data);
		Long longKey = getLongKey(accessUtil, data);
		if ((manyKey == null) && (longKey == null)) {
			return;
		}
		buriStateEntityService.updateAbortByData(longKey, manyKey, accessUtil
		    .getClassName(data));
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.WakanagoEngineImpl#updateSystemContext(org
	 * .escafe.buri.engine.BuriSystemContext,
	 * org.escafe.buri.util.packages.BuriExecProcess,
	 * org.escafe.buri.util.packages.BuriExePackages)
	 */
	@Override
	protected void updateSystemContext(BuriSystemContext sysContext,
	        BuriExecProcess wp, BuriExePackages wPackageObj) {
		super.updateSystemContext(sysContext, wp, wPackageObj);
		// フローのプロセスをデータ操作ファクトリにキャスト
		BuriDataAccessFactory factory = (BuriDataAccessFactory) wp;
		// セットアップ前の前処理
		preprocessData(factory, sysContext.getUserContext());
		Object data = sysContext.getUserContext().getData();
		DataAccessUtil accessUtil = factory.getDataAccessUtil(data.getClass());
		String manyKey = getManyKey(accessUtil, data);
		Long longKey = getLongKey(accessUtil, data);
		if ((manyKey == null) && (longKey == null)) {
			return;
		}
		// 実行対象のパスを取得
		String pathName = sysContext.getCallPath().getPlainName() + ".%";
		if (sysContext.getCallPath().getActivityName().size() > 0) {
			pathName = sysContext.getCallPath().getPlainName();
		}
		// ぶり固有のビューBuriPathDataを検索
		BuriPathDataEntity dto =
		    buriPathDataEntityService.getDtoByPathKey(accessUtil
		        .getClassName(data), longKey, manyKey, pathName, sysContext
		        .getCallPath()
		        .getPathType());
		// 最終的なセットアップを実施
		finalSetup(dto, sysContext);
	}

	/**
	 * 管理対象のデータが{@code null}ではない場合に{@link BuriSystemContext}の内容を更新します。
	 * 
	 * @param dto
	 *            更新対象の{@link BuriPathDataEntityDto}
	 * @param sysContext
	 *            更新時に参照する{@link BuriSystemContext}
	 */
	protected void finalSetup(BuriPathDataEntity dto,
	        BuriSystemContext sysContext) {
		if (dto != null) {
			sysContext.setDataId(dto.dataId);
			sysContext.setStatusId(dto.stateId);
			BuriPath callPath =
			    new BuriPath(
			        dto.pathName,
			        dto.realPathName,
			        dto.pathId,
			        dto.pathType);
			sysContext.setCallPath(callPath);
		}
	}

	/**
	 * 対象のデータの主キーを返します。
	 * <p>
	 * 対象のデータの主キーが複合項目の場合に結果を返します。
	 * </p>
	 * 
	 * @param accessUtil
	 *            対象データに対応した{@link DataAccessUtil}の実装
	 * @param data
	 *            対象データ
	 * @return 主キー
	 */
	protected String getManyKey(DataAccessUtil accessUtil, Object data) {
		if (accessUtil instanceof DataAccessUtilManyKey) {
			return ((DataAccessUtilManyKey) accessUtil).getKey(data);
		}
		return null;
	}

	/**
	 * 対象のデータの主キーを返します。
	 * <p>
	 * 対象のデータの主キーが{@code Long}型の単一項目の場合に結果を返します。
	 * </p>
	 * 
	 * @param accessUtil
	 *            対象データに対応した{@link DataAccessUtil}の実装
	 * @param data
	 *            対象データ
	 * @return 主キー
	 */
	protected Long getLongKey(DataAccessUtil accessUtil, Object data) {
		if (accessUtil instanceof DataAccessUtilLongKey) {
			return ((DataAccessUtilLongKey) accessUtil).getKey(data);
		}
		return null;
	}

	/**
	 * セットアップの実行前処理を行います。
	 * 
	 * @param factory
	 *            指定された{@link BuriExecProcess}
	 * @param userContext
	 *            設定済みの{@link BuriUserContext}
	 */
	protected void preprocessData(BuriDataAccessFactory factory,
	        BuriUserContext userContext) {
		PreprocessAccessUtil pre =
		    factory.getPreprocessAccessUtil(userContext.getData().getClass());
		if (pre != null) {
			Object trueData = pre.getTrueData(userContext.getData());
			userContext.setData(trueData);
		}
	}

	/**
	 * ぶり固有のビュー{@code BuriPathDataEntity}のServiceを返します。
	 * 
	 * @return ぶり固有のビュー{@code BuriPathDataEntity}のService
	 */
	public BuriPathDataEntityService getBuriPathDataEntityService() {
		return buriPathDataEntityService;
	}

	/**
	 * ぶり固有のビュー{@code BuriPathDataEntity}のServiceを登録します。
	 * <p>
	 * DIコンテナによって自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param pathDataDao
	 *            ぶり固有のビュー{@code BuriPathDataEntity}のService
	 */
	public void setBuriPathDataEntityService(
	        BuriPathDataEntityService buriPathDataEntityService) {
		this.buriPathDataEntityService = buriPathDataEntityService;
	}

	/*
	 * @see org.escafe.buri.engine.impl.WakanagoEngineImpl#getBuriCompiler()
	 */
	@Override
	public BuriCompiler getBuriCompiler() {
		return buriCompiler;
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.WakanagoEngineImpl#setBuriCompiler(org.escafe
	 * .buri.compiler.BuriCompiler)
	 */
	@Override
	public void setBuriCompiler(BuriCompiler buriCompiler) {
		this.buriCompiler = buriCompiler;
	}

	/**
	 * クラス定義ユーティリティを返します。
	 * 
	 * @return クラス定義ユーティリティ
	 */
	public ClassDefUtil getClassDefUtil() {
		return classDefUtil;
	}

	/**
	 * クラス定義ユーティリティを登録します。
	 * <p>
	 * DIコンテナによって自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param classDefUtil
	 *            クラス定義ユーティリティ
	 */
	public void setClassDefUtil(ClassDefUtil classDefUtil) {
		this.classDefUtil = classDefUtil;
	}

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のServiceを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriStateEntity}のService
	 */
	public BuriStateEntityService getBuriStateEntityService() {
		return buriStateEntityService;
	}

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のServiceを登録します。
	 * <p>
	 * DIコンテナで自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param stateDao
	 *            ぶり固有のテーブル{@code BuriState}のService
	 */
	public void setBuriStateEntityService(
	        BuriStateEntityService buriStateEntityService) {
		this.buriStateEntityService = buriStateEntityService;
	}
}
