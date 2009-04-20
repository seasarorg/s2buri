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
package org.escafe.buri.engine;

import java.util.HashMap;

/**
 * ワークフローの実行の際に使用する情報を保持するクラスです。
 * <p>
 * {@link HashMap}のサブクラスとして実装されています。 このクラスにセットされたデータは、ワークフローにOGNL式を記述しておく事で、
 * コンパイル済みのワークフローから参照可能となります。
 * </p>
 * <p>
 * 以下に各属性の説明を記します。 ここで設定した値は、OGNL式で{@code #data}等とする事で内容を参照する事が可能です。
 * <ul>
 * <li>{@code data}</li> <div>ぶりに管理させたいデータのDtoを指定します。</div>
 * <li>{@code userData}</li> <div>権限による制御を行うワークフローを実行する際に使用する
 * ユーザ情報となるDtoを指定します。</div>
 * <li>{@code action}</li> <div>ワークフローのトランジションの経路を指定する為の{@code action}
 * を指定します。通常文字列を使用します。</div>
 * <li>{@code callPath}</li> <div>実行するワークフローを表すパスの{@link BuriPath}を指定します。</div>
 * </ul>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/21
 */
public class BuriUserContext extends HashMap<String, Object> {
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * {@code "data"}として登録されたオブジェクトを返します。
	 * 
	 * @return {@code "data"}として登録されたオブジェクト
	 */
	public Object getData() {
		return super.get("data");
	}

	/**
	 * オブジェクトを{@code "data"}として登録します。
	 * 
	 * @param data
	 *            {@code "data"}として登録したいデータのDto
	 */
	public void setData(Object data) {
		super.put("data", data);
	}

	/**
	 * {@code "userData"}として登録されているオブジェクトを返します。
	 * 
	 * @return {@code "userData"}として登録されているオブジェクト
	 */
	public Object getUserData() {
		return super.get("userData");
	}

	/**
	 * オブジェクトを{@code "userData"}として登録します。
	 * 
	 * @param data
	 *            {@code "userData"}として登録したいオブジェクト
	 */
	public void setUserData(Object data) {
		super.put("userData", data);
	}

	/**
	 * {@code action}を文字列として返します。
	 * 
	 * @return {@code action}
	 */
	public String getAction() {
		Object action = super.get("action");
		if (action == null) {
			return null;
		}
		return action.toString();
	}

	/**
	 * {@code "action"}を登録します。
	 * 
	 * @param data
	 *            {@code "action"}として登録したいオブジェクト
	 */
	public void setAction(Object data) {
		super.put("action", data);
	}

	/**
	 * {@code "callPath"}として登録されている{@link BuriPath}を返します。
	 * 
	 * @return {@code "callPath"}として登録されている{@link BuriPath}
	 */
	public BuriPath getCallPath() {
		return (BuriPath) super.get("callPath");
	}

	/**
	 * {@code "callPath"}として{@link BuriPath}を登録します。
	 * 
	 * @param callPath
	 *            登録したい{@link BuriPath}
	 */
	public void setCallPath(BuriPath callPath) {
		super.put("callPath", callPath);
	}
}
