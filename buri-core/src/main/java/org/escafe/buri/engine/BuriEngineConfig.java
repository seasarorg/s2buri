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

import java.util.List;

/**
 * ぶりのエンジンの設定を保持する為のクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2006/07/16
 */
public interface BuriEngineConfig {
	/**
	 * リソース情報を返します。
	 * 
	 * @return リソース情報
	 */
	List<BuriConfigDto> getResourceConfigs();

	/**
	 * ファイル情報を返します。
	 * 
	 * @return ファイル情報
	 */
	List<BuriConfigDto> getFileConfigs();
}
