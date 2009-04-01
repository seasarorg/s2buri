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
 * Oouo用クラス定義ユーティリティです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/12
 */
public interface OouoClassDef {
    /**
     * 対象のクラスを登録します。
     * 
     * @param clazz 対象のクラス
     */
    void setClass(Class<?> clazz);

    /**
     * 対象のクラスを返します。
     * @return 対象のクラス
     */
    Class<?> getTgtClass();

    /**
     * 指定した名称の子要素が存在するかどうかを判定します。
     * 
     * @param elename 子要素の名前
     * @return 存在した場合<code>true</code>、そうでない場合<code>false</code>
     */
    boolean isChildElement(String elename);

    /**
     * 対象の要素名を返します。
     * 
     * @return 要素名
     */
    String getElementName();

    /**
     * 子要素を登録します。
     * 
     * @param now 現在位置
     * @param name 子要素の名前
     * @param child 子要素オブジェクト
     */
    void setChild(Object now, String name, Object child);

    /**
     * 指定した名前、指定したタイプの属性があるかどうかを判定します。
     * 
     * @param name 属性名
     * @param type 属性のタイプ
     * @return 存在する場合{@code true}、そうでない場合は{@code false}
     */
    boolean isChildAttribute(String name, String type);

    /**
     * 属性を登録します。
     * 
     * @param now 現在位置を示すオブジェクト
     * @param name 属性名
     * @param type 属性のタイプ
     * @param value 属性の値
     */
    void setChildAttribute(Object now, String name, String type, String value);

    /**
     * 指定した文字列が要素を示すかどうかを判定します。
     * 
     * @param type 名前
     * @return 要素の名前だった場合は{@code true}、そうでない場合は{@code false}
     */
    boolean isAttribute(String type);

    /**
     * 子要素を登録します。
     * 
     * @param now 現在位置を示すオブジェクト
     * @param type 要素の名前
     * @param value 要素の値
     */
    void setAttribute(Object now, String type, String value);

    /**
     * 指定した子要素名に対応した要素のオブジェクトを生成して返します。
     * 
     * @param name 取得したいオブジェクトの名前
     * @return 要素のオブジェクト
     */
    Object getChildObject(String name);

    /**
     * このクラスが終了メソッドを呼ばれた後かどうかを判定して返します。
     * 
     * @return 終了メソッドが呼ばれていたら{@code true}、そうでなければ{@code false}
     */
    boolean isCallFinMethod();

    /**
     * 終了メソッドを実行します。
     * 
     * @param now 対象のオブジェクト
     * @param parent 終了メソッドに渡すパラメータ
     */
    void fin(Object now, Object parent);

    /**
     * テキストをセットする為のメソッド名が設定されているかどうかを判定します。
     * 
     * @return 設定されている場合は{@code true}、そうでない場合は{@code false}
     */
    boolean isSetTextMethod();

    /**
     * 対象のオブジェクトにテキスト値をセットします。
     * 
     * @param now 対象のオブジェクト
     * @param value セットしたいテキスト値
     */
    void setText(Object now, String value);

}
