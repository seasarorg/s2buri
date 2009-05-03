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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.escafe.buri.compiler.BuriCompiler;
import org.escafe.buri.engine.BuriConfigDto;
import org.escafe.buri.engine.BuriEngineConfig;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.event.engine.caller.BuriActivitySelectEventCaller;
import org.escafe.buri.event.engine.caller.BuriEngineEventCaller;
import org.escafe.buri.event.engine.caller.BuriProcessSelectEventCaller;
import org.escafe.buri.exception.select.BuriActivitySelectException;
import org.escafe.buri.exception.select.BuriManySelectActivityException;
import org.escafe.buri.exception.select.BuriManySelectProcessException;
import org.escafe.buri.exception.select.BuriNotSelectProcessException;
import org.escafe.buri.exception.select.BuriNotSelectedActivityException;
import org.escafe.buri.exception.select.BuriProcessSelectException;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

/**
 * ぶりの中核を担うエンジンの実装です。
 * <p>
 * {@link WakanagoEngine}の実装として、 {@link BuriSimpleEngineImpl}と
 * {@link BuriStandardEngineImpl}に基底の実装を提供しています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2006/03/26
 */
public class WakanagoEngineImpl implements WakanagoEngine {
	/**
	 * {@link BuriExePackages}を貯めておく{@link Map}
	 */
	protected Map<String, BuriExePackages> packageObjs =
	    new HashMap<String, BuriExePackages>();

	/**
	 * {@link ParticipantProvider}を貯めておく{@link Map}
	 */
	protected Map<String, ParticipantProvider> appUserIdMap =
	    new HashMap<String, ParticipantProvider>();

	/**
	 * ぶりのコンパイラ
	 */
	protected BuriCompiler buriCompiler;

	/**
	 * アクティビティ・セレクタのリスト
	 */
	protected List<BuriActivitySelector> activitySelector =
	    new ArrayList<BuriActivitySelector>();

	/**
	 * プロセス・セレクタのリスト
	 */
	protected List<BuriProcessSelector> processSelector =
	    new ArrayList<BuriProcessSelector>();

	/**
	 * S2コンテナ
	 */
	protected S2Container container;

	/**
	 * スクリプトの実行モジュール
	 */
	protected ScriptFactory scriptFactory;

	/**
	 * エンジンのセットアップが済んでいるかどうかを示すフラグ
	 * <p>
	 * セットアップ済み={@code true}、セットアップ未だ={@code false}
	 * </p>
	 */
	protected boolean finSetup = false;

	/**
	 * エンジン実行時の追加処理を提供する拡張ポイント
	 */
	protected BuriEngineEventCaller buriEngineEventCaller;

	/**
	 * フローのアクティビティを検索する際の追加処理を提供する拡張ポイント
	 */
	protected BuriActivitySelectEventCaller buriActivitySelectEventCaller;

	/**
	 * フローのプロセスを検索する際の追加処理を提供する拡張ポイント
	 */
	protected BuriProcessSelectEventCaller buriProcessSelectEventCaller;

	/**
	 * オブジェクトの初期化をします。
	 */
	public void destory() {
		/*
		 * 登録済のフローのパッケージをすべて破棄する
		 */
		for (BuriExePackages packages : packageObjs.values()) {
			if (packages != null) {
				packages.destroy();
			}
		}
		appUserIdMap.clear();
		packageObjs.clear();
		System.gc();
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName) {
		readFromResource(workFlowName, resourceName, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromInputStream(InputStream workFlowIs,
	        String resourceName) {
		readFromInputStream(workFlowIs, resourceName, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,
	        String resourceName) {
		readFromBuriPackageType(buriPackage, resourceName, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName, ParticipantProvider provider) {
		readFromResource(workFlowName, resourceName, provider);
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromInputStream(InputStream workFlowIs,
	        String resourceName, ParticipantProvider provider) {
		readFromInputStream(workFlowIs, resourceName, provider);
	}

	/**
	 * {@inheritDoc}
	 */
	public void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,
	        String resourceName, ParticipantProvider provider) {
		readFromBuriPackageType(buriPackage, resourceName, provider);
	}

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローをリソース名で指定します。
	 * </p>
	 * 
	 * @param workFlowName フローを示すリソース名
	 * @param resourceName エンジンに登録する名称
	 * @param provider 権限管理に使用する{@link ParticipantProvider}
	 */
	public void readFromResource(String workFlowName, String resourceName,
	        ParticipantProvider provider) {
		BuriExePackages exePackages =
		    buriCompiler.CompileResource(workFlowName, provider);
		readFromBuriExePackages(exePackages, resourceName, provider);
	}

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link InputStream}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param workFlowIs フローを示す{@link InputStream}
	 * @param resourceName エンジンに登録する名称
	 * @param provider 権限管理に使用する{@link ParticipantProvider}
	 */
	public void readFromInputStream(InputStream workFlowIs,
	        String resourceName, ParticipantProvider provider) {
		BuriExePackages exePackages =
		    buriCompiler.CompileInputStream(workFlowIs, provider);
		readFromBuriExePackages(exePackages, resourceName, provider);
	}

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link BuriPackageType}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param buriPackage フローから取得した{@link BuriPackageType}
	 * @param resourceName エンジンに登録する名称
	 * @param provider 権限管理に使用する{@link ParticipantProvider}
	 */
	public void readFromBuriPackageType(BuriPackageType buriPackage,
	        String resourceName, ParticipantProvider provider) {
		BuriExePackages exePackages =
		    buriCompiler.CompileObject(buriPackage, provider);
		readFromBuriExePackages(exePackages, resourceName, provider);
	}

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link BuriExePackages}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param exePackages 読み込み済みの{@link BuriExePackages}
	 * @param resourceName エンジンに登録する名称
	 * @param provider 権限管理に使用する{@link ParticipantProvider}
	 */
	protected void readFromBuriExePackages(BuriExePackages exePackages,
	        String resourceName, ParticipantProvider provider) {
		String packageId = exePackages.getBuriPackageType().getId();
		packageObjs.put(packageId, exePackages);
		BuriExePackages oldPkg = packageObjs.put(resourceName, exePackages);
		appUserIdMap.put(packageId, provider);
		if (oldPkg != null && (!oldPkg.equals(exePackages))) {
			oldPkg.destroy();
			System.gc();
		}
	}

	/**
	 * エンジンのセットアップを行います。
	 * 
	 * @param engineConfig エンジンの設定
	 */
	public void setupBuriEngineConfig(BuriEngineConfig engineConfig) {
		if (finSetup) {
			/*
			 * 設定済みならそのまま終了
			 */
			return;
		}
		for (BuriConfigDto dto : engineConfig.getResourceConfigs()) {
			if (dto.getProvider() == null) {
				readWorkFlowFromResource(dto.getFileName(), dto
				    .getPackageName());
			} else {
				readWorkFlowFromResource(dto.getFileName(), dto
				    .getPackageName(), dto.getProvider());
			}
		}
		finSetup = true;
	}

	/**
	 * 遅延ローダー用の設定を行います。
	 * <p>
	 * 遅延ローダーに登録したリソースをエンジンから削除します。
	 * </p>
	 * 
	 * @param resourceName リソース名
	 */
	public void setupDelayLoad(String resourceName) {
		packageObjs.put(resourceName, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasPackage(String packageName) {
		if (packageObjs.containsKey(packageName)) {
			return true;
		}
		return false;
	}

	/**
	 * フローのプロセス・セレクタを追加します。
	 * 
	 * @param selector {@link BuriProcessSelector}
	 */
	public void addProcessSelector(BuriProcessSelector selector) {
		processSelector.add(selector);
	}

	/**
	 * フローのアクティビティ・セレクタを追加します。
	 * 
	 * @param selector {@link BuriActivitySelector}
	 */
	public void addActivitySelector(BuriActivitySelector selector) {
		activitySelector.add(selector);
	}

	/**
	 * {@inheritDoc}
	 */
	public BuriUserContext createUserContext(Object data, Object userData,
	        Object action, Map<String, Object> addContext) {
		BuriUserContext context = new BuriUserContext();
		if (addContext != null) {
			context.putAll(addContext);
		}
		context.setData(data);
		context.setUserData(userData);
		context.setAction(action);
		return context;
	}

	/**
	 * {@inheritDoc}
	 */
	public BuriSystemContext createSystemContext(String buriPath,
	        BuriUserContext userContext) {
		BuriSystemContext context = new BuriSystemContext();
		context.setCallPath(new BuriPath(buriPath));
		userContext.setCallPath(context.getCallPath());
		context.setUserContext(userContext);
		return context;
	}

	/**
	 * 指定された{@link BuriSystemContext}にS2コンテナをセットします。
	 * 
	 * @param sysContext {@link BuriSystemContext}
	 */
	private void setupSystemContext(BuriSystemContext sysContext) {
		if (sysContext.getContainer() == null) {
			sysContext.setContainer(container.getRoot());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object execute(BuriSystemContext sysContext, String resultScript) {
		// 実行処理開始直後の追加処理を実行
		buriEngineEventCaller.startExecute(sysContext, resultScript);
		setupSystemContext(sysContext);
		BuriExePackages wPackageObj = selectPackage(sysContext);
		BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
		updateSystemContext(sysContext, wp, wPackageObj);
		updateUserInfo(sysContext, wp, wPackageObj);
		wp = selectProcess(wPackageObj, sysContext);
		// 実行前の追加処理を実行
		buriEngineEventCaller.startFlow(sysContext, resultScript);
		execActivity(wp, sysContext);
		// 実行前の追加処理を実行
		buriEngineEventCaller.endFlow(sysContext, resultScript);
		Object ret = getResultObj(sysContext, resultScript);
		// 実行処理終了直前の追加処理を実行
		buriEngineEventCaller.endExecute(sysContext, resultScript, ret);
		return ret;
	}

	/**
	 * 戻り値を作るためのOGNL式を実行して戻り値を作成します。
	 * 
	 * @param sysContext コンテキスト情報
	 * @param resultScript 戻り値のOGNL式
	 * @return OGNL式の実行結果
	 */
	protected Object getResultObj(BuriSystemContext sysContext,
	        String resultScript) {
		if (StringUtil.isEmpty(resultScript)) {
			return null;
		}
		Object result =
		    scriptFactory.getDefaultScript().eval(
		        null,
		        resultScript,
		        sysContext.getUserContext());
		return result;
	}

	/**
	 * 指定された{@link BuriSystemContext}に実行前のセットアップを行います。
	 * 
	 * @param sysContext {@link BuriSystemContext}
	 * @param wp {@link BuriExecProcess}
	 * @param wPackageObj {@link BuriExePackages}
	 */
	protected void updateSystemContext(BuriSystemContext sysContext,
	        BuriExecProcess wp, BuriExePackages wPackageObj) {
		// None.
	}

	/**
	 * 権限管理に必要なユーザ情報のセットアップをしています。
	 * <p>
	 * サブクラスである{@link BuriSimpleEngineImpl}では権限管理が不要である為この実装を使わないようにします。
	 * </p>
	 * 
	 * @param sysContext {@link BuriSystemContext}
	 * @param wp {@link BuriExecProcess}
	 * @param wPackageObj {@link BuriExePackages}
	 */
	protected void updateUserInfo(BuriSystemContext sysContext,
	        BuriExecProcess wp, BuriExePackages wPackageObj) {
		ParticipantProvider provider = wPackageObj.getParticipantProvider();
		Object userData = sysContext.getUserContext().getUserData();
		if ((userData == null) && (provider == null)) {
			return;
		}
		IdentityInfo appUserId = provider.getUserId(userData);
		sysContext.setAppUserId(appUserId);
	}

	/**
	 * アクティビティを実行します。
	 * <p>
	 * アクティビティに設定されている{@code Tool}等の実行を行います。
	 * </p>
	 * 
	 * @param wp 実行対象の{@link BuriExecProcess}
	 * @param sysContext {@link BuriSystemContext}
	 */
	protected void execActivity(BuriExecProcess wp, BuriSystemContext sysContext) {
		BranchWalker walker = wp.readBranchWalker(sysContext);
		String actId = selectActivityId(wp, sysContext);
		wp.entryActivity(actId, sysContext, walker);
	}

	/**
	 * アクティビティのIDを検索して返します。
	 * 
	 * @param wp 対象のアクティビティが所属する{@link BuriExecProcess}
	 * @param sysContext {@link BuriSystemContext}
	 * @return アクティビティのID
	 */
	protected String selectActivityId(BuriExecProcess wp,
	        BuriSystemContext sysContext) {
		// アクティビティ検索処理の実施前の追加処理を実行
		buriActivitySelectEventCaller.startSelectActivityId(wp, sysContext);
		Set<BuriActivityType> acts = new HashSet<BuriActivityType>();
		for (BuriActivitySelector selector : activitySelector) {
			// アクティビティ・セレクタの実施前の追加処理を実行
			buriActivitySelectEventCaller.startActivitySelector(
			    wp,
			    sysContext,
			    selector,
			    acts);
			int nextAct = selector.select(acts, sysContext, wp);
			if (nextAct == BuriActivitySelector.SELECT_FINAL) {
				break;
			}
			if (nextAct == BuriActivitySelector.SELECT_ERROR) {
				errorActivitySelect(acts, sysContext, wp);
			}
			// アクティビティ・セレクタの実施後の追加処理を実行
			buriActivitySelectEventCaller.endActivitySelector(
			    wp,
			    sysContext,
			    selector,
			    acts);
		}
		String actId = selectActivityFinal(acts, sysContext, wp);
		// アクティビティ検索処理の実施後の追加処理を実行
		buriActivitySelectEventCaller.endSelectActivityId(
		    wp,
		    sysContext,
		    actId,
		    acts);
		return actId;
	}

	/**
	 * 実施後のアクティビティのIDを取得します。
	 * 
	 * @param acts 対象の{@link BuriActivityType}の一覧
	 * @param systemContext フローを実行する上で必要なシステムのコンテキスト情報
	 * @param wp 対象の{@link BuriExecProcess}
	 * @return アクティビティのID
	 */
	protected String selectActivityFinal(Set<BuriActivityType> acts,
	        BuriSystemContext systemContext, BuriExecProcess wp) {
		if (acts.size() != 1) {
			errorActivitySelect(acts, systemContext, wp);
		}
		BuriActivityType actType = (BuriActivityType) (acts.toArray()[0]);
		return actType.getId();
	}

	/**
	 * 遷移先の状態取得で異常が発生した場合に例外を判定して投げます。
	 * 
	 * @param acts 遷移先になる{@link BuriActivityType}
	 * @param systemContext {@link BuriSystemContext}
	 * @param wp {@link BuriExecProcess}
	 */
	protected void errorActivitySelect(Set<BuriActivityType> acts,
	        BuriSystemContext systemContext, BuriExecProcess wp) {
		BuriPath callPath = systemContext.getCallPath();
		String pakageID = callPath.getWorkflowPackage();
		ParticipantProvider provider = appUserIdMap.get(pakageID);
		// エラー発生時の追加処理を実施
		buriActivitySelectEventCaller.errorActivitySelect(
		    acts,
		    systemContext,
		    wp);
		if (acts.size() == 0) {
			/*
			 * 遷移先がない場合の例外
			 */
			throw new BuriNotSelectedActivityException(callPath, provider);
		}
		if (acts.size() > 1) {
			/*
			 * 遷移先がありすぎる場合の例外
			 */
			throw new BuriManySelectActivityException(callPath, provider);
		}
		// その他のエラー
		throw new BuriActivitySelectException(callPath, provider);
	}

	/**
	 * 指定された{@link BuriExecProcess}をデータ操作を行わないように更新します。
	 * 
	 * @param wPackageObj 実行対象の{@link BuriExePackages}
	 * @param sysContext {@link BuriSystemContext}
	 * @return 実行対象の{@link BuriExecProcess}
	 */
	public BuriExecProcess selectProcessNoDataAccess(
	        BuriExePackages wPackageObj, BuriSystemContext sysContext) {
		BuriExecProcess wp = wPackageObj.getProcess(sysContext.getCallPath());
		BuriPath callPath = sysContext.getCallPath();
		String processId = ClassUtil.getShortClassName(wp.getClass());
		callPath =
		    callPath.setWorkflowProcess(
		        callPath.getWorkflowProcess(),
		        processId);
		sysContext.setCallPath(callPath);
		return wp;
	}

	/**
	 * {@inheritDoc}
	 */
	public BuriExecProcess selectDirectProcess(BuriPath path) {
		BuriExePackages wPackageObj =
		    packageObjs.get(path.getWorkflowPackage());
		return wPackageObj.getProcess(path); // TODO Directが必要
	}

	/**
	 * {@inheritDoc}
	 */
	public BuriExecProcess selectProcess(BuriExePackages wPackageObj,
	        BuriSystemContext sysContext) {
		// フローのプロセス検索処理の前処理の追加処理を実行
		buriProcessSelectEventCaller
		    .startSelectProcess(wPackageObj, sysContext);
		List<BuriWorkflowProcessType> pros =
		    new ArrayList<BuriWorkflowProcessType>();
		for (BuriProcessSelector selector : processSelector) {
			// フローのプロセス検索処理の前処理の追加処理を実行
			buriProcessSelectEventCaller.startSelector(
			    wPackageObj,
			    sysContext,
			    selector,
			    pros);
			int nextAct = selector.select(pros, sysContext, wPackageObj);
			if (nextAct == BuriProcessSelector.SELECT_FINAL) {
				break;
			}
			if (nextAct == BuriProcessSelector.SELECT_ERROR) {
				errorProcessSelect(pros, sysContext, wPackageObj);
			}
			// フローのプロセス検索処理の後処理の追加処理を実行
			buriProcessSelectEventCaller.endSelector(
			    wPackageObj,
			    sysContext,
			    selector,
			    pros);
		}
		BuriExecProcess process =
		    selectProcessFinal(pros, sysContext, wPackageObj);
		// フローのプロセス検索処理の後処理の追加処理を実行
		buriProcessSelectEventCaller.endSelectProcess(
		    wPackageObj,
		    sysContext,
		    process,
		    pros);
		return process;
	}

	/**
	 * 実施後のプロセスを取得します。
	 * 
	 * @param pros {@link BuriWorkflowProcessType}のリスト
	 * @param systemContext {@link BuriSystemContext}
	 * @param wPackageObj {@link BuriExePackages}
	 * @return 実行後の{@link BuriExecProcess}
	 */
	protected BuriExecProcess selectProcessFinal(
	        List<BuriWorkflowProcessType> pros,
	        BuriSystemContext systemContext, BuriExePackages wPackageObj) {
		if (pros.size() != 1) {
			errorProcessSelect(pros, systemContext, wPackageObj);
		}
		BuriWorkflowProcessType procType = pros.get(0);
		BuriPath callPath =
		    systemContext.getCallPath().setWorkflowProcess(procType);
		return wPackageObj.getProcess(callPath);
	}

	/**
	 * 遷移後のプロセスに異常があった場合に例外を判定して投げます。
	 * 
	 * @param proces {@link BuriWorkflowProcessType}のリスト
	 * @param systemContext {@link BuriSystemContext}
	 * @param wPackageObj {@link BuriExePackages}
	 */
	protected void errorProcessSelect(List<BuriWorkflowProcessType> proces,
	        BuriSystemContext systemContext, BuriExePackages wPackageObj) {
		BuriPath callPath = systemContext.getCallPath();
		String pakageID = callPath.getWorkflowPackage();
		// エラー発生時の追加処理を実施
		buriProcessSelectEventCaller.errorSelectProcess(
		    wPackageObj,
		    systemContext,
		    callPath,
		    proces);
		ParticipantProvider provider = appUserIdMap.get(pakageID);
		if (proces.size() == 0) {
			/*
			 * 遷移後のプロセスが存在しない場合の例外
			 */
			throw new BuriNotSelectProcessException(callPath);
		}
		if (proces.size() > 1) {
			/*
			 * 遷移後のプロセスがありすぎる場合の例外
			 */
			throw new BuriManySelectProcessException(callPath);
		}
		// その他の例外
		throw new BuriProcessSelectException(callPath, provider);
	}

	/**
	 * {@inheritDoc}
	 */
	public BuriExePackages selectPackage(BuriSystemContext sysContext) {
		String packageName = sysContext.getCallPath().getWorkflowPackage();
		assert !StringUtil.isEmpty(packageName);
		assert packageObjs.containsKey(packageName);
		BuriPath callPath = sysContext.getCallPath();
		BuriExePackages wPackageObj = packageObjs.get(packageName);
		String packageId = wPackageObj.getBuriPackageType().getId();
		callPath = callPath.setWorkflowPackage(packageName, packageId);
		sysContext.setCallPath(callPath);
		return wPackageObj;
	}

	/**
	 * 実行対象の{@link BuriPath}に合致する{@link BuriWorkflowProcessType}の リストを検索して返します。
	 * 
	 * @param path 実行対象の{@link BuriPath}
	 * @return {@link BuriWorkflowProcessType}のリスト
	 */
	protected List<BuriWorkflowProcessType> getProcessList(BuriPath path) {
		BuriExePackages wPackageObj = getBuriExePackages(path);
		List<BuriWorkflowProcessType> processList =
		    wPackageObj.getBuriPackageType().getProcessByName(
		        path.getWorkflowProcess());
		return processList;
	}

	/**
	 * 実行対象の{@link BuriExePackages}を返します。
	 * 
	 * @param path 実行対象を示す{@link BuriPath}
	 * @return 実行対象の{@link BuriExePackages}
	 */
	protected BuriExePackages getBuriExePackages(BuriPath path) {
		BuriExePackages wPackageObj =
		    packageObjs.get(path.getWorkflowPackage());
		assert wPackageObj != null;
		return wPackageObj;
	}

	/**
	 * ぶりのコンパイラを返します。
	 * 
	 * @return ぶりのコンパイラ
	 */
	public BuriCompiler getBuriCompiler() {
		return buriCompiler;
	}

	/**
	 * ぶりのコンパイラを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriCompiler ぶりのコンパイラ
	 */
	public void setBuriCompiler(BuriCompiler buriCompiler) {
		this.buriCompiler = buriCompiler;
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
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param container S2コンテナ
	 */
	public void setContainer(S2Container container) {
		this.container = container;
	}

	/**
	 * スクリプト実行モジュールを返します。
	 * 
	 * @return スクリプト実行モジュール
	 */
	public ScriptFactory getScriptFactory() {
		return scriptFactory;
	}

	/**
	 * スクリプト実行モジュールを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param scriptFactory スクリプト実行モジュール
	 */
	public void setScriptFactory(ScriptFactory scriptFactory) {
		this.scriptFactory = scriptFactory;
	}

	/**
	 * エンジン実行時の追加処理を提供する拡張ポイントを返します。
	 * 
	 * @return エンジン実行時の追加処理を提供する拡張ポイント
	 */
	public BuriEngineEventCaller getBuriEngineEventCaller() {
		return buriEngineEventCaller;
	}

	/**
	 * エンジン実行時の追加処理を提供する拡張ポイントを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriEngineEventCaller エンジン実行時の追加処理を提供する拡張ポイント
	 */
	public void setBuriEngineEventCaller(
	        BuriEngineEventCaller buriEngineEventCaller) {
		this.buriEngineEventCaller = buriEngineEventCaller;
	}

	/**
	 * フローのプロセスを検索する際の追加処理を提供する拡張ポイントを返します。
	 * 
	 * @return フローのプロセスを検索する際の追加処理を提供する拡張ポイント
	 */
	public BuriActivitySelectEventCaller getBuriActivitySelectEventCaller() {
		return buriActivitySelectEventCaller;
	}

	/**
	 * フローのプロセスを検索する際の追加処理を提供する拡張ポイントを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriActivitySelectEventCaller フローのプロセスを検索する際の追加処理を提供する拡張ポイント
	 */
	public void setBuriActivitySelectEventCaller(
	        BuriActivitySelectEventCaller buriActivitySelectEventCaller) {
		this.buriActivitySelectEventCaller = buriActivitySelectEventCaller;
	}

	/**
	 * フローのプロセスを検索する際の追加処理を提供する拡張ポイントを返します。
	 * 
	 * @return フローのプロセスを検索する際の追加処理を提供する拡張ポイント
	 */
	public BuriProcessSelectEventCaller getBuriProcessSelectEventCaller() {
		return buriProcessSelectEventCaller;
	}

	/**
	 * フローのプロセスを検索する際の追加処理を提供する拡張ポイントを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriProcessSelectEventCaller フローのプロセスを検索する際の追加処理を提供する拡張ポイント
	 */
	public void setBuriProcessSelectEventCaller(
	        BuriProcessSelectEventCaller buriProcessSelectEventCaller) {
		this.buriProcessSelectEventCaller = buriProcessSelectEventCaller;
	}
}
