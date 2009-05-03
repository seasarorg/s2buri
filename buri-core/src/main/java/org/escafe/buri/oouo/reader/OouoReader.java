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
package org.escafe.buri.oouo.reader;

import java.io.InputStream;

import org.escafe.buri.oouo.internal.structure.BuriPackageType;

/**
 * フローを読み込む為のリーダーです。
 * <p>
 * フローを読み込んでぶりがコンパイルする際に参照するツリー構造のオブジェクト群として返します。 対象がXPDLであれば、返すオブジェクト群のトップノードには
 * {@link BuriPackageType}の オブジェクトが設定されます。
 * </p>
 * <p>
 * デフォルトの実装ではXPDLで記述されたフローに対応しています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/06
 */
public interface OouoReader {
	/**
	 * {@link InputStream}で指定されたフローを読み込んで{@link BuriPackageType}のオブジェクトを返します。
	 * 
	 * @param workFlowIs フローの{@link InputStream}
	 * @return {@link BuriPackageType}を頂点としたツリー構造のオブジェクト（フローがXPDLの場合）
	 */
	Object readInputStream(InputStream workFlowIs);

	/**
	 * リソース名で指定されたフローを読み込んで{@link BuriPackageType}のオブジェクトを返します。
	 * 
	 * @param resourceName フローのリソース名
	 * @return {@link BuriPackageType}を頂点としたツリー構造のオブジェクト（フローがXPDLの場合）
	 */
	Object readResource(String resourceName);

	/**
	 * ファイル名で指定されたフローを読み込んで{@link BuriPackageType}のオブジェクトを返します。
	 * 
	 * @param fileName フローのファイル名
	 * @return {@link BuriPackageType}を頂点としたツリー構造のオブジェクト（フローがXPDLの場合）
	 */
	Object readFile(String fileName);

	/**
	 * ツリー構造の頂点となるクラスを追加します。
	 * 
	 * @param clazz ツリー構造の頂点となるクラス
	 */
	void addRootClass(Class<?> clazz);
}
