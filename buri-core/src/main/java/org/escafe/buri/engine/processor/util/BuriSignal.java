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
package org.escafe.buri.engine.processor.util;

import java.util.List;

/**
 * シグナルを送る為のAPIセットです。
 * <p>
 * 権限による制御を行うフローを実行する際にあえてその権限を無視したい場合に、 アプリケーションから任意に呼び出して使用します。
 * </p>
 * 
 * @author a-conv
 * @author imai78(JavaDoc)
 */
public interface BuriSignal {
	/**
	 * 1件のデータの状態を次に進めます。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 */
	public void signal(String callPath, Object data);

	/**
	 * 1件のデータの状態を次に進めます。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param action
	 *            Actionの文字列、ぶりのなかでは{@code #action}として参照可能
	 */
	public void signal(String callPath, Object data, String action);

	/**
	 * 複数1件のデータの状態を次に進めます。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param datas
	 *            ぶりに管理させたい(させてる)Dtoのリスト
	 */
	public void signal(String callPath, List<Object> datas);

	/**
	 * 複数件のデータの状態を次に進めます。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param datas
	 *            ぶりに管理させたい(させてる)Dtoのリスト
	 * @param action
	 *            Actionの文字列、ぶりのなかでは{@code #action}として参照可能
	 */
	public void signal(String callPath, List<Object> datas, String action);
}
