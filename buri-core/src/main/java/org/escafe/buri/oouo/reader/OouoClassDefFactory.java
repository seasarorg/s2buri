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

/**
 * Oouoで使用するクラス定義用クラスのファクトリです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/12
 */
public interface OouoClassDefFactory {

    /**
     * 指定されたクラスのクラス定義オブジェクトを作成して返します。
     * 
     * @param clazz クラス定義オブジェクトにしたいクラス
     * @return 対応する{@link OouoClassDef}
     */
    OouoClassDef create(Class<?> clazz);

    /**
     * 指定した要素名に対応する要素オブジェクトを返します。
     * 
     * @param eleName 取得したい要素名
     * @return 要素名に対応した要素オブジェクト
     */
    Object getRoot(String eleName);

}
