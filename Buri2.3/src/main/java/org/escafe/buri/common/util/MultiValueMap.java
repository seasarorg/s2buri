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
package org.escafe.buri.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 1つの{@code key}で複数の{@code value}を持てるように拡張された{@link HashMap}のWrapperクラスです。
 * <p>
 * オブジェクトと{@code List<T>}を紐づける<code>Map</code>のWrapperです。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @param <T> 適用させるクラス
 * @since 2006/03/26 
 */
public class MultiValueMap<T> {
    /**
     * 実体となる{@link Map}
     */
    private Map<Object, List<T>> inMap = new HashMap<Object, List<T>>();

    /**
     * 実体となる{@link Map}の件数を返します。
     * 
     * @return 実体となる{@link Map}の件数
     */
    public int size() {
        return inMap.size();
    }

    /**
     * 実体となる{@link Map}を初期化します。
     */
    public void clear() {
        inMap.clear();
    }

    /**
     * 実体となる{@link Map}が空かどうかを判定します。
     * 
     * @return 空の場合は<code>true</code>、そうではない場合は<code>false</code>
     */
    public boolean isEmpty() {
        return inMap.isEmpty();
    }

    /**
     * 指定したオブジェクトに紐づけられたオブジェクトが存在するかどうかを判定します。
     * 
     * @param keyObj <code>key</code>となるオブジェクト
     * @return 存在する場合は{@code true}、そうでない場合は{@code false}
     */
    public boolean containsKey(Object keyObj) {
        return inMap.containsKey(keyObj);
    }

    /**
     * 指定したオブジェクトを<code>value</code>として登録されているかどうかを判定します。
     * <p>
     * 今の実装では、無条件に<code>fasle</code>を返しています。
     * </p>
     * 
     * @param valueObj <code>value</code>オブジェクト
     * @return <code>fasle</code>
     */
    public boolean containsValue(Object valueObj) {
        return false;
    }

    /**
     * 何をしたかったメソッドなのか分からない＞＜ 無条件に<code>null</code>を返しています。
     * 
     * @return <code>null</code>
     */
    public Collection<?> values() {
        return null;
    }

    /**
     * オブジェクトを上書きします。
     * 
     * @param mvMap {@link MultiValueMap}
     */
    public void putAll(MultiValueMap<T> mvMap) {
        inMap.putAll(mvMap.inMap);
    }

    /**
     * 実体となる{@link Map}のコレクションビューを{@link Set}として返します。
     * 
     * @return 実体となる{@link Map}のコレクションビュー
     */
    public Set<Entry<Object, List<T>>> entrySet() {
        return inMap.entrySet();
    }

    /**
     * 実体となる{@link Map}のキーのセットビューを{@link Set}として返します。
     * 
     * @return 実体となる{@link Map}のキーのセットビュー
     */
    public Set<Object> keySet() {
        return inMap.keySet();
    }

    /**
     * 実体となる{@link Map}の1要素を返します。
     * 
     * @param keyObj キーとなるオブジェクト
     * @return {@code keyObj}に対応する実体となる{@link Map}
     */
    public List<T> get(Object keyObj) {
        return inMap.get(keyObj);
    }

    /**
     * 実体となる{@link Map}の1要素を抜き出して返します。
     * <p>
     * {@code keyObj}で指定されたオブジェクトは実体となる{@link Map}から削除されます。
     * </p>
     * 
     * @param keyObj <code>key</code>オブジェクト
     * @return {@code keyObj}に対応する実体となる{@link Map}
     */
    public List<T> remove(Object keyObj) {
        return inMap.remove(keyObj);
    }

    /**
     * 実体となる{@link Map}に対して、同一の<code>key</code>で複数の<code>value</code>
     * を持てるように追加します。
     * <p>
     * すでにリスト（<code>List&lt;Object&gt;</code>）で定義されている要素か、
     * 過去に使用されていなかった要素以外では使用できません。
     * </p>
     * 
     * @param keyObj キーとなるオブジェクト
     * @param valueObj 値となるオブジェクト
     */
    @SuppressWarnings("unchecked")
    public void put(Object keyObj, T valueObj) {
        List<T> valList;
        if (containsKey(keyObj)) {
            valList = inMap.get(keyObj);
        } else {
            valList = new ArrayList<T>();
            inMap.put(keyObj, valList);
        }
        valList.add(valueObj);
    }

    @Override
    public String toString() {
        return inMap.toString();
    }

    /**
     * 実体となる{@link Map}を返します。
     * 
     * @return 実体となる{@link Map}
     */
    public Map<Object, List<T>> convertMap() {
        return inMap;
    }

}
