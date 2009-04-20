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

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

/**
 * ぶりのプロセッサとして最もレイヤの高いAPIセットの実装です。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/20
 */
public class BuriAutoSelectProcessorImpl implements BuriAutoSelectProcessor {
	/**
	 * 権限管理をしない場合に使うぶりプロセッサ
	 */
	private SimpleBuriProcessor simpleProcessor;

	/**
	 * 権限管理をする場合に使うぶりプロセッサ
	 */
	private StandardBuriProcessor standardProcessor;

	/**
	 * 権限管理をしない場合に使うぶりのエンジン
	 */
	private BuriEngine simpleEngine;

	/**
	 * 権限管理をする場合に使うぶりのエンジン
	 */
	private BuriEngine standardEngine;

	/**
	 * S2コンテナ
	 */
	private S2Container container;

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
	 * <p>
	 * その際に、{@code action}を指定してトランジションの経路を指定する事が可能です。
	 * </p>
	 * <p>
	 * 権限管理を行わないフローを実行する場合は、{@code userData}に{@code null}を指定します。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティー名]
	 * @param container
	 *            S2コンテナ。
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限管理時に使用するユーザ情報のDto
	 * @param action
	 *            Actionの文字列、ぶりのなかでは{@code #action}として参照可能
	 * @param resultExp
	 *            戻り値を作るためのOGNL式
	 * @return ONGL式の実行結果
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
	public Object toNextStatus(String path, Object data, Object userData,
	        BuriProcessorInfo info) {
		Object result = null;
		if (isStdProcessor(path)) {
			result = standardProcessor.toNextStatus(path, data, userData, info);
		}
		if (isSimpleProcessor(path)) {
			result = simpleProcessor.toNextStatus(path, data, info);
		}
		return result;
	}

	/**
	 * 指定したパスに対応したぶりのエンジンを返します。
	 * <p>
	 * {@link BuriSimpleEngineImpl}か{@link BuriStandardEngineImpl}の
	 * いずれかを返します。<br/. 万一両方に存在した場合は、{@link BuriSimpleEngineImpl}が優先して返されます。
	 * </p>
	 * 
	 * @param buriPath
	 *            パッケージ名.プロセス名[.アクティビティー名]
	 * @return 対応するぶりのエンジン
	 */
	protected BuriEngine getEngine(String buriPath) {
		if (isStdProcessor(buriPath)) {
			return standardEngine;
		}
		if (isSimpleProcessor(buriPath)) {
			return simpleEngine;
		}
		return null;
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
	public List<Object> getDataListFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container) {
		List<Object> result = null;
		if (isStdProcessor(path)) {
			result =
			    standardProcessor.getDataListFromPath(
			        path,
			        userData,
			        tgtClass,
			        container);
		}
		if (isSimpleProcessor(path)) {
			result =
			    simpleProcessor.getDataListFromPath(path, tgtClass, container);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass) {
		return getDataIDFromPath(path, userData, tgtClass, getRootContainer());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container) {
		List<Object> result = null;
		if (isStdProcessor(path)) {
			result =
			    standardProcessor.getDataIDFromPath(
			        path,
			        userData,
			        tgtClass,
			        container);
		}
		if (isSimpleProcessor(path)) {
			result =
			    simpleProcessor.getDataIDFromPath(path, tgtClass, container);
		}
		return result;
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
		List<BuriPath> result = null;
		if (isStdProcessor(path)) {
			result =
			    standardProcessor.getPathFromData(
			        path,
			        data,
			        userData,
			        tgtClass,
			        container);
		}
		if (isSimpleProcessor(path)) {
			result = simpleProcessor.getPathFromData(path, data, container);
		}
		return result;
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
		long result = 0;
		if (isStdProcessor(path)) {
			result =
			    standardProcessor.countByPathAndDatas(
			        path,
			        datas,
			        userData,
			        container);
		}
		if (isSimpleProcessor(path)) {
			result =
			    simpleProcessor.countByPathAndDatas(path, datas, container);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isStdProcessor(String buriPath) {
		BuriPath path = new BuriPath(buriPath);
		if (standardEngine.hasPackage(path.getWorkflowPackage())) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public DataAccessFactory getDataAccessFactory(String buriPath) {
		DataAccessFactory accessFactory = null;
		BuriPath path = new BuriPath(buriPath);
		if (isStdProcessor(buriPath)) {
			accessFactory =
			    (DataAccessFactory) standardEngine.selectDirectProcess(path);
		}
		if (isSimpleProcessor(buriPath)) {
			accessFactory =
			    (DataAccessFactory) simpleEngine.selectDirectProcess(path);
		}
		return accessFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSimpleProcessor(String buriPath) {
		BuriPath path = new BuriPath(buriPath);
		if (simpleEngine.hasPackage(path.getWorkflowPackage())) {
			return true;
		}
		return false;
	}

	/**
	 * S2コンテナからルートのコンテナを返します。
	 * 
	 * @return ルートのS2コンテナ
	 */
	protected S2Container getRootContainer() {
		return container.getRoot();
	}

	/**
	 * 権限管理を行わないぶりのエンジンとして{@link BuriEngine}を返します。
	 * 
	 * @return 権限管理を行わないぶりのエンジン
	 */
	public BuriEngine getSimpleEngine() {
		return simpleEngine;
	}

	/**
	 * 権限管理を行わないぶりのエンジンとして{@link BuriEngine}を登録します。
	 * <p>
	 * デフォルトの設定では{@link BuriSimpleEngineImpl}が設定されます。
	 * </p>
	 * 
	 * @param simpleEngine
	 *            権限管理を行わないぶりのエンジン
	 */
	public void setSimpleEngine(BuriEngine simpleEngine) {
		this.simpleEngine = simpleEngine;
	}

	/**
	 * 権限管理を行わないぶりプロセッサとして{@link SimpleBuriProcessor}を返します。
	 * 
	 * @return 権限管理を行わないぶりプロセッサ
	 */
	public SimpleBuriProcessor getSimpleProcessor() {
		return simpleProcessor;
	}

	/**
	 * 権限管理を行わないぶりプロセッサとして{@link SimpleBuriProcessor}を登録します。
	 * <p>
	 * デフォルトの設定では{@link SimpleBuriProcessorImpl}が設定されます。
	 * </p>
	 * 
	 * @param simpleProcessor
	 *            権限管理を行わないぶりプロセッサ
	 */
	public void setSimpleProcessor(SimpleBuriProcessor simpleProcessor) {
		this.simpleProcessor = simpleProcessor;
	}

	/**
	 * 権限管理を行うぶりのエンジンとして{@link BuriEngine}を返します。
	 * 
	 * @return 権限管理を行うぶりのエンジン
	 */
	public BuriEngine getStandardEngine() {
		return standardEngine;
	}

	/**
	 * 権限管理を行うぶりのエンジンとして{@link BuriEngine}を登録します。
	 * <p>
	 * デフォルトの設定では{@link BuriStandardEngineImpl}が設定されます。
	 * </p>
	 * 
	 * @param standardEngine
	 */
	public void setStandardEngine(BuriEngine standardEngine) {
		this.standardEngine = standardEngine;
	}

	/**
	 * 権限管理を行うぶりプロセッサとして{@link StandardBuriProcessor}を返します。
	 * 
	 * @return 権限管理を行うぶりプロセッサ
	 */
	public StandardBuriProcessor getStandardProcessor() {
		return standardProcessor;
	}

	/**
	 * 権限管理を行うぶりプロセッサとして{@link StandardBuriProcessor}を登録します。
	 * <p>
	 * デフォルトの設定では{@link StandardBuriProcessorImpl}が設定されます。
	 * </p>
	 * 
	 * @param standardProcessor
	 *            権限管理を行うぶりプロセッサ
	 */
	public void setStandardProcessor(StandardBuriProcessor standardProcessor) {
		this.standardProcessor = standardProcessor;
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
	 * 
	 * @param container
	 *            S2コンテナ
	 */
	public void setContainer(S2Container container) {
		this.container = container;
	}
}
