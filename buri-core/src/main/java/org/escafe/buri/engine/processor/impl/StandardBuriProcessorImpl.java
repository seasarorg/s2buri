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
package org.escafe.buri.engine.processor.impl;

import java.util.List;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

/**
 * ぶりの標準的な実行とデータの取得に関係するAPIのセットの実装です。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 */
public class StandardBuriProcessorImpl implements StandardBuriProcessor {
	/**
	 * ぶりのエンジン
	 */
	private BuriEngine engine;

	/**
	 * S2コンテナ
	 */
	private S2Container container;

	/**
	 * データ操作ユーティリティ
	 */
	private BuriDataUtil dataUtil;

	/**
	 * {@inheritDoc}
	 */
	public void toNextStatus(String path, Object data, Object userData) {
		toNextStatusAction(path, getRootContainer(), data, userData, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object toNextStatus(String path, Object data, Object userData,
	        String resultExp) {
		return toNextStatusAction(
		    path,
		    getRootContainer(),
		    data,
		    userData,
		    null,
		    resultExp);
	}

	/**
	 * {@inheritDoc}
	 */
	public void toNextStatusAction(String path, Object data, Object userData,
	        Object action) {
		toNextStatusAction(
		    path,
		    getRootContainer(),
		    data,
		    userData,
		    action,
		    null);
	}

	/**
	 * 状態を次に進めます。
	 * 
	 * @param path パッケージ名.プロセス名[.アクティビティ名]
	 * @param container 使用する{@link S2Container}
	 * @param data ぶりに管理させたい(させてる)entity
	 * @param userData 権限管理時に使用するユーザ情報のDto
	 * @param action Actionとして指定する文字列
	 * @param resultExp 戻り値を作るONGL式
	 * @return 戻り値を作るOGNL式の実行結果
	 */
	protected Object toNextStatusAction(String path, S2Container container,
	        Object data, Object userData, Object action, String resultExp) {
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.setAction(action);
		info.setContainer(container);
		info.setResultExp(resultExp);
		return toNextStatus(path, data, userData, info);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object toNextStatus(String path, Object data, Object userData,
	        BuriProcessorInfo info) {
		if (data instanceof List) {
			return toNextStatusList(path, (List<Object>) data, userData, info);
		} else {
			return toNextStatusOne(path, data, userData, info);
		}
	}

	/**
	 * 1件のデータに対して、状態を先に進める処理を行います。
	 * 
	 * @param path パッケージ名.プロセス名[.アクティビティ名]
	 * @param data ぶりに管理させたい(させてる)entity
	 * @param userData 権限管理時に使用するユーザ情報のDto
	 * @param info フロー実行時に使用するデータをセットした{@link BuriProcessorInfo}
	 * @return {@code info}に設定されたONGL式の実行結果
	 */
	public Object toNextStatusOne(String path, Object data, Object userData,
	        BuriProcessorInfo info) {
		// BuriUserContextの作成
		BuriUserContext userContext =
		    engine.createUserContext(data, userData, info.getAction(), info
		        .getContext());
		// BuriSystemContextの作成
		BuriSystemContext systemContext =
		    engine.createSystemContext(path, userContext);
		S2Container cont =
		    info.getContainer() == null ? getRootContainer() : info
		        .getContainer();
		systemContext.setContainer(cont);
		systemContext.setActivityNames(info.getActNames());
		// エンジンの実行
		Object result = engine.execute(systemContext, info.getResultExp());
		return result;
	}

	/**
	 * 複数件のデータに対して、状態を先に進める処理を行います。
	 * 
	 * @param path パッケージ名.プロセス名[.アクティビティ名]
	 * @param datas ぶりに管理させたい(させてる)Dtoのリスト
	 * @param userData 権限管理時に使用するユーザ情報のDto
	 * @param info フロー実行時に使用するデータをセットした{@link BuriProcessorInfo}
	 * @return {@code info}に設定されたONGL式の実行結果
	 */
	public Object toNextStatusList(String path, List<Object> datas,
	        Object userData, BuriProcessorInfo info) {
		Object result = null;
		for (Object data : datas) {
			result = toNextStatusOne(path, data, userData, info);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Object> getDataListFromPath(String path, Object userData,
	        Class<?> tgtClass) {
		return getDataListFromPath(path, userData, tgtClass, getRootContainer());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getDataListFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container) {
		BuriUserContext userContext =
		    engine.createUserContext(null, userData, null, null);
		BuriSystemContext systemContext =
		    engine.createSystemContext(path, userContext);
		systemContext.setTargetDtoClass(tgtClass);
		systemContext.setContainer(container);
		engine.setupUserID(systemContext);
		BuriExecProcess execProcess =
		    engine.selectPackage(systemContext).getProcess(
		        systemContext.getCallPath());
		List<Object> dataList =
		    dataUtil.getDtoListByPathName(
		        path,
		        (DataAccessFactory) execProcess,
		        systemContext);
		return dataList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass) {
		return getDataIDFromPath(path, userData, tgtClass, getRootContainer());
	}

	/**
	 * 指定された状態に所属するデータのIDのリストを検索して返します。
	 * 
	 * @param path パッケージ名.プロセス名[.アクティビティ名]
	 * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass リストにしたいDtoのクラス
	 * @param container 使用する{@link S2Container}
	 * @return データのIDのリスト
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container) {
		// BuriUserContext
		BuriUserContext userContext =
		    engine.createUserContext(null, userData, null, null);
		// BuriSystemContext
		BuriSystemContext systemContext =
		    engine.createSystemContext(path, userContext);
		systemContext.setTargetDtoClass(tgtClass);
		systemContext.setContainer(container);
		// ユーザ情報の変換
		engine.setupUserID(systemContext);
		BuriExecProcess execProcess =
		    engine.selectPackage(systemContext).getProcess(
		        systemContext.getCallPath());
		// 検索処理
		List<Object> dataList =
		    dataUtil.getDtoListByPathName(
		        path,
		        (DataAccessFactory) execProcess,
		        systemContext);
		return dataList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BuriPath> getPathFromData(String path, Object data,
	        Object userData, Class<?> tgtClass) {
		return getPathFromData(
		    path,
		    data,
		    userData,
		    tgtClass,
		    getRootContainer());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BuriPath> getPathFromData(String path, Object data,
	        Object userData, Class<?> tgtClass, S2Container container) {
		// BuriUserContextの作成
		BuriUserContext userContext =
		    engine.createUserContext(data, userData, null, null);
		// BuriSystemContextの作成
		BuriSystemContext systemContext =
		    engine.createSystemContext(path, userContext);
		systemContext.setContainer(container);
		// ユーザ情報の変換
		engine.setupUserID(systemContext);
		BuriExecProcess execProcess =
		    engine.selectPackage(systemContext).getProcess(
		        systemContext.getCallPath());
		// 検索処理
		List<BuriPath> pathList =
		    dataUtil.getBuriPathByDto(
		        data,
		        (DataAccessFactory) execProcess,
		        systemContext);
		return pathList;
	}

	/**
	 * {@inheritDoc}
	 */
	public long countByPathAndDatas(String path, List<Object> datas,
	        Object userData) {
		return countByPathAndDatas(path, datas, userData, getRootContainer());
	}

	/**
	 * {@inheritDoc}
	 */
	public long countByPathAndDatas(String path, List<Object> datas,
	        Object userData, S2Container container) {
		// BuriUserContextの作成
		BuriUserContext userContext =
		    engine.createUserContext(null, userData, null, null);
		// BuriSystemContextの作成
		BuriSystemContext systemContext =
		    engine.createSystemContext(path, userContext);
		systemContext.setContainer(container);
		// ユーザ情報の変換
		engine.setupUserID(systemContext);
		BuriExecProcess execProcess =
		    engine.selectPackage(systemContext).getProcess(
		        systemContext.getCallPath());
		// 検索処理
		long count =
		    dataUtil.countByPathAndDatas(
		        path,
		        datas,
		        (DataAccessFactory) execProcess,
		        systemContext);
		return count;
	}

	/**
	 * S2コンテナからルートのコンテナを取得してを返します。
	 * 
	 * @return ルートのS2コンテナ
	 */
	protected S2Container getRootContainer() {
		return container.getRoot();
	}

	/**
	 * S2コンテナを返します。
	 * 
	 * @return S2コンテナ
	 */
	public S2Container getContainer() {
		return container;
	}

	/**
	 * S2コンテナを登録します。
	 * <p>
	 * コンテナから自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param container S2コンテナ
	 */
	public void setContainer(S2Container container) {
		this.container = container;
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
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param dataUtil データ操作ユーティリティ
	 */
	public void setDataUtil(BuriDataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	/**
	 * ぶりのエンジンを返します。
	 * <p>
	 * 基本設定を変えていなければ、{@link BuriStandardEngineImpl}が返されます。
	 * </p>
	 * 
	 * @return ぶりのエンジン
	 */
	public BuriEngine getEngine() {
		return engine;
	}

	/**
	 * ぶりのエンジンを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param engine ぶりのエンジン
	 */
	public void setEngine(BuriEngine engine) {
		this.engine = engine;
	}
}
