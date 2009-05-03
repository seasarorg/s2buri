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
package org.escafe.buri.common.util.template;

/**
 * ソースコードのテンプレートを表すクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2005/09/13
 */
public interface TextTemplate {
	/**
	 * テンプレートの変換処理を実施します。
	 * 
	 * @param templateName テンプレートとなるリソース名
	 * @param data テンプレートの変換に必要なコンテキスト
	 * @return 変換結果
	 */
	String process(String templateName, Object data);

	/**
	 * テンプレートの変換処理を実施します。
	 * <p>
	 * テンプレートの指定をリソース名にて行う為のメソッドです。
	 * </p>
	 * 
	 * @param templateName テンプレートを表すリソース名
	 * @param data テンプレートの変換に必要なコンテキスト
	 * @return 変換結果
	 */
	String processResource(String templateName, Object data);

	/**
	 * テンプレートの変換処理を実施します。
	 * <p>
	 * テンプレートの指定をファイル名にて行う為のメソッドです。
	 * </p>
	 * 
	 * @param templateName テンプレートを表すファイル名
	 * @param data テンプレートの変換に必要なコンテキスト
	 * @return 変換結果
	 */
	String processFile(String templateName, Object data);
}
