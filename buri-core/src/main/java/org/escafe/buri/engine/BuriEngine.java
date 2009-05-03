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

import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.escafe.buri.engine.impl.WakanagoEngineImpl;

/**
 * ぶりのエンジンです。
 * <p>
 * ぶりのエンジンの基幹となるのは、このインターフェイスのスーパーインターフェイスである {@link WakanagoEngine}の実装である
 * {@link WakanagoEngineImpl}です。 そして、その{@link WakanagoEngineImpl}のサブクラスとして
 * {@link BuriSimpleEngineImpl} と{@link BuriStandardEngineImpl}が存在しています。 この
 * {@link BuriSimpleEngineImpl}と{@link BuriStandardEngineImpl}は、 それぞれこの
 * {@link BuriEngine}の実装となっています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2006/03/26
 */
public interface BuriEngine extends WakanagoEngine {
	/**
	 * 指定された{@link BuriSystemContext}のユーザ情報を ぶりの権限管理に使うようにセットアップします。
	 * <p>
	 * このインターフェイスの実装である{@link BuriSimpleEngineImpl}では、 権限管理を行う必要がないため空の実装がされます。
	 * </p>
	 * 
	 * @param sysContext フローを実行する上で必要なシステムのコンテキスト情報
	 */
	void setupUserID(BuriSystemContext sysContext);
}
