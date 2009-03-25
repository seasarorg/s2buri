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
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * XPDLの{@code Split}要素を表すクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 */
public class BuriSplitType {
    /**
     * {@code type}属性の値
     */
    private String type;
    /**
     * 関係する{@code Transition}のIDのリスト
     */
    private List<String> transitionRef = new ArrayList<String>();
    /**
     * {@code Split}が{@code XOR}か{@code AND}かを示すフラグ
     */
    private boolean isXOR = true;

    /**
     * {@code Split}要素の要素名
     */
    public static final String OOUOTHIS = "Split";

    /**
     * {@code type}属性の値を返します。
     * 
     * @return {@code type}属性の値
     */
    public String getType() {
        return type;
    }

    /**
     * {@code Type}属性の属性名
     */
    public static final String setType_ATTRI = "Type";

    /**
     * {@code type}属性の値を登録します。
     * 
     * @param type {@code type}属性の値
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@code TransitionRef}要素の要素名
     */
    public static final String addTransitionRef_ELEMENT = "TransitionRef";
    /**
     * {@code TransitionRef}要素の{@code Id}属性の属性名
     */
    public static final String addTransitionRef_ATTRI = "Id";

    /**
     * 関係する{@code Transition}のIDを追加します。
     * 
     * @param ref 関係する{@code Transition}のID
     */
    public void addTransitionRef(String ref) {
        transitionRef.add(ref);
    }

    /**
     * 読み込み終了を示す文字
     */
    public static final String setupEnd_OOUOFIN = "";

    /**
     * 読み込み終了時の処理を行います。
     */
    public void setupEnd() {
        if (type.endsWith("AND")) {
            isXOR = false;
        }
    }

    /**
     * {@code Split}が{@code XOR}かどうかを判定します。
     * 
     * @return {@code XOR}の場合{@code true}、そうではない場合{@code false}
     */
    public boolean isXor() {
        return isXOR;
    }

    /**
     * {@code Split}が{@code AND}かどうかを判定します。
     * 
     * @return {@code AND}の場合{@code true}、そうではない場合{@code false}
     */
    public boolean isAnd() {
        return (!isXOR);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/type=").append(type);
        buff.append("/transitionRef=").append(transitionRef);
        buff.append("]");
        return buff.toString();
    }

}
