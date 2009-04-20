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
package org.escafe.buri.engine.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;

/**
 * プロセッサの情報を保持するためのクラスです。
 * <p>
 * プロセッサからエンジンに制御を委譲する際に、引き渡すデータを格納します。 使用するS2コンテナ、トランジションに使用する{@code action}、
 * {@code toNextStatus}の戻り値を作成する為のOGNL式と 権限主体を適用した場合のアクティビティ名のリストを設定する事ができます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/14
 */
public class BuriProcessorInfo {
	/**
	 * S2のコンテナ(ぶりが設定するので通常設定する必要なし)
	 */
	private S2Container container;

	/**
	 * {@code #action}に指定したい任意のデータ
	 */
	private Object action;

	/**
	 * それ以外で渡したい情報 ぶりのなかでは{@code #name}として値を参照可能
	 */
	private final Map<String, Object> context = new HashMap<String, Object>();

	/**
	 * 戻り値のOGNL式
	 */
	private String resultExp;

	/**
	 * 実行制約用のアクティビティー名のリスト entityの状態がこれ以外だったらエラーになる
	 */
	private List<String> actNames;

	/**
	 * {@code #action}を返します。
	 * 
	 * @return {@code #action}
	 */
	public Object getAction() {
		return action;
	}

	/**
	 * {@code #action}を登録します。
	 * 
	 * @param action
	 */
	public void setAction(Object action) {
		this.action = action;
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

	/**
	 * 戻り値のONGL式を返します。
	 * 
	 * @return 戻り値のONGL式
	 */
	public String getResultExp() {
		return resultExp;
	}

	/**
	 * 戻り値のOGNL式を登録します。
	 * 
	 * @param resultExp
	 */
	public void setResultExp(String resultExp) {
		this.resultExp = resultExp;
	}

	/**
	 * コンテキストに値を登録します。
	 * 
	 * @param key
	 *            キー値
	 * @param val
	 *            値となるオブジェクト
	 */
	public void put(String key, Object val) {
		context.put(key, val);
	}

	/**
	 * コンテキストを登録します。
	 * 
	 * @param datas
	 *            コンテキスト
	 */
	public void putAll(Map<String, Object> datas) {
		context.putAll(datas);
	}

	/**
	 * 指定したキーを持つコンテキストの値を返します。
	 * 
	 * @param key
	 *            キーとなる値
	 * @return コンテキストの値
	 */
	public Object get(String key) {
		return context.get(key);
	}

	/**
	 * コンテキストを返します。
	 * 
	 * @return コンテキスト
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * 実行制約用のアクティビティのリストを返します。
	 * 
	 * @return アクティビティ名のリスト
	 */
	public List<String> getActNames() {
		return actNames;
	}

	/**
	 * 実行制約用のアクティビティのリストを登録します。
	 * 
	 * @param actNames
	 *            実行制約用のアクティビティー名のリスト
	 */
	public void setActNames(List<String> actNames) {
		this.actNames = actNames;
	}
}
