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
import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

/**
 * 基本的な機能を提供するAPIセットの実装です。
 * <p>
 * {@link BuriSimpleEngineImpl}のWrapperとして実装されています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 */
public class SimpleBuriProcessorImpl implements SimpleBuriProcessor {

    /**
     * ぶりのエンジン
     */
    private BuriEngine engine;
    /**
     * S2コンテナ
     */
    private S2Container container;
    /**
     * 検索処理用ユーティリティ
     */
    private BuriDataUtil dataUtil;

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#toNextStatus(java.lang.String, java.lang.Object)
     */
    public void toNextStatus(String path, Object data) {
        toNextStatusAction(path, null, data, null, null);
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#toNextStatus(java.lang.String, java.lang.Object, java.lang.String)
     */
    public Object toNextStatus(String path, Object data, String resultExp) {
        return toNextStatusAction(path, null, data, null, resultExp);
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#toNextStatusAction(java.lang.String, java.lang.Object, java.lang.String)
     */
    public void toNextStatusAction(String path, Object data, String action) {
        toNextStatusAction(path, null, data, action, null);
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#toNextStatus(java.lang.String, java.lang.Object, org.escafe.buri.engine.processor.BuriProcessorInfo)
     */
    @SuppressWarnings("unchecked")
    public Object toNextStatus(String path, Object data, BuriProcessorInfo info) {
        if (data instanceof List) {
            return toNextStatusList(path, (List<Object>) data, info);
        } else {
            return toNextStatusOne(path, data, info);
        }
    }

    /**
     * 状態を次に進めるメソッドの基幹となる実装です。
     * <p>
     * 1件のデータに対してエンジンの実行を行います。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)entity
     * @param info {@link BuriProcessorInfo}
     * @return {@code info}に含まれるONGL式の実行結果
     */
    public Object toNextStatusOne(String path, Object data, BuriProcessorInfo info) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(data, null, info.getAction(), info.getContext());
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        // S2Containerの設定
        S2Container cont = info.getContainer() == null ? getRootContainer() : info.getContainer();
        systemContext.setContainer(cont);
        systemContext.setActivityNames(info.getActNames());
        // BuriEngineの実行
        Object result = engine.execute(systemContext, info.getResultExp());
        return result;
    }

    /**
     * 状態を次に進めるメソッドです。
     * <p>
     * 複数件のデータをまとめて実行します。
     * </p>
     * <p>
     * データの件数分、{@link #toNextStatusOne(String, Object, BuriProcessorInfo)}
     * を実行します。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param datas ぶりに管理させたい(させてる)entityのリスト
     * @param info {@link BuriProcessorInfo}
     * @return {@code info}に含まれるONGL式の実行結果
     */
    public Object toNextStatusList(String path, List<Object> datas, BuriProcessorInfo info) {
        Object result = null;
        for (Object data : datas) {
            result = toNextStatusOne(path, data, info);
        }
        return result;
    }

    /**
     * {@code #action}を指定して状態を先に進めます。
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param container 任意の{@link S2Container}
     * @param data ぶりに管理させたい(させてる)entity
     * @param action 指定する{@code #action}
     * @param resultExp 戻り値のONGL式
     * @return {@code resultExp}の実行結果
     */
    protected Object toNextStatusAction(String path, S2Container container, Object data, Object action, String resultExp) {
        // BuriProcessorInfoの作成
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.setAction(action);
        info.setContainer(container);
        info.setResultExp(resultExp);
        return toNextStatus(path, data, info);
    }

    /**
     * 指定したデータの状態を無効にするためのメソッドです。
     * <p>
     * テスト時やデータメンテナンスを行う際に、不要となったデータと状態の関連を削除するために使用します。
     * このメソッドで使用されたデータは、以後パスで指定された状態には無いものとして扱われます。
     * </p>
     * <p>
     * この操作で、前後のデータと状態の整合性の調整などは行われません。
     * </p>
     *
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param info {@link BuriProcessorInfo}
     */
    public void abortData(String path, Object data, BuriProcessorInfo info) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(data, null, info.getAction(), info.getContext());
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        S2Container cont = info.getContainer() == null ? getRootContainer() : info.getContainer();
        systemContext.setContainer(cont);
        systemContext.setActivityNames(info.getActNames());
        // 実行
        ((BuriSimpleEngineImpl) engine).abortData(systemContext);
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getDataListFromPath(java.lang.String, java.lang.Class)
     */
    public List<Object> getDataListFromPath(String path, Class<?> tgtClass) {
        return getDataListFromPath(path, tgtClass, getRootContainer());
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getDataListFromPath(java.lang.String, java.lang.Class, org.seasar.framework.container.S2Container)
     */
    public List<Object> getDataListFromPath(String path, Class<?> tgtClass, S2Container container) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setTargetDtoClass(tgtClass);
        systemContext.setContainer(container);
        // フローのプロセスの取得
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        // 検索処理の実施
        List<Object> dataList = dataUtil.getDtoListByPathName(path, (DataAccessFactory) execProcess, systemContext);
        return dataList;
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getDataIDFromPath(java.lang.String, java.lang.Class)
     */
    public List<Object> getDataIDFromPath(String path, Class<?> tgtClass) {
        return getDataIDFromPath(path, tgtClass, getRootContainer());
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getDataIDFromPath(java.lang.String, java.lang.Class, org.seasar.framework.container.S2Container)
     */
    public List<Object> getDataIDFromPath(String path, Class<?> tgtClass, S2Container container) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setTargetDtoClass(tgtClass);
        systemContext.setContainer(container);
        // フローのプロセスの取得
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        // 検索処理の実施
        List<Object> dataList = dataUtil.getIDListByPathName(path, (DataAccessFactory) execProcess, systemContext);
        return dataList;
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getPathFromData(java.lang.String, java.lang.Object)
     */
    public List<BuriPath> getPathFromData(String path, Object data) {
        return getPathFromData(path, data, getRootContainer());
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#getPathFromData(java.lang.String, java.lang.Object, org.seasar.framework.container.S2Container)
     */
    public List<BuriPath> getPathFromData(String path, Object data, S2Container container) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(data, null, null, null);
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setContainer(container);
        // フローのプロセスの取得
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        // BuriPathの検索処理の実施
        List<BuriPath> pathList = dataUtil.getBuriPathByDto(data, (DataAccessFactory) execProcess, systemContext);
        return pathList;
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#countByPathAndDatas(java.lang.String, java.util.List)
     */
    public long countByPathAndDatas(String path, List<Object> datas) {
        return countByPathAndDatas(path, datas, getRootContainer());
    }

    /*
     * @see org.escafe.buri.engine.processor.SimpleBuriProcessor#countByPathAndDatas(java.lang.String, java.util.List, org.seasar.framework.container.S2Container)
     */
    public long countByPathAndDatas(String path, List<Object> datas, S2Container container) {
        // BuriUserContextの作成
        BuriUserContext userContext = engine.createUserContext(null, null, null, null);
        // BuriSystemContextの作成
        BuriSystemContext systemContext = engine.createSystemContext(path, userContext);
        systemContext.setContainer(container);
        // フローのプロセスの取得
        BuriExecProcess execProcess = engine.selectPackage(systemContext).getProcess(systemContext.getCallPath());
        // 検索処理の実施
        long count = dataUtil.countByPathAndDatas(path, datas, (DataAccessFactory) execProcess, systemContext);
        return count;
    }

    /**
     * S2コンテナからルートのコンテナを取得して返します。
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
     * 
     * @param container
     */
    public void setContainer(S2Container container) {
        this.container = container;
    }

    /**
     * 検索処理用ユーティリティを返します。
     * 
     * @return 検索処理用ユーティリティ
     */
    public BuriDataUtil getDataUtil() {
        return dataUtil;
    }

    /**
     * 検索処理用ユーティリティを登録します。
     * 
     * @param dataUtil 検索処理用ユーティリティ
     */
    public void setDataUtil(BuriDataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    /**
     * ぶりのエンジンを返します。
     * 
     * @return ぶりのエンジン
     */
    public BuriEngine getEngine() {
        return engine;
    }

    /**
     * ぶりのエンジンを登録します。
     * 
     * @param engine
     */
    public void setEngine(BuriEngine engine) {
        this.engine = engine;
    }

}
