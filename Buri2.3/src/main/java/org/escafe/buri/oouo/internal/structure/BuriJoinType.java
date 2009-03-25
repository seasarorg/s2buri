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

/**
 * XPDLの{@code Join}要素を表すクラスです。
 * <p>
 * この{@code Join}要素は、いくつかのアクティビティからトランジションを
 * 集約する際に使用されます。
 * {@code Type}に{@code XOR}が指定された場合は、複数のトランジションのうち
 * 1つがこの属性を持つアクティビティに到達した時点で、アクティビティが有効と判断され、
 * 残された未到達のアクティビティは破棄されます。
 * <br/>
 * {@code Type}に{@code AND}が指定された場合は、複数のトランジションの全てが
 * この属性を持つアクティビティに到達しない限り、アクティビティが有効と判断される事はありません。
 * </p>
 * <p>
 * 余談ながら、この{@code Join}が{@code AND}となるケースは、開発の対象となる実業務では非常に稀なので、
 * 特に使用しなくても済む筈です。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 */
public class BuriJoinType {
    /**
     * {@code type}属性の値
     */
    private String type;
    /**
     * {@code Join}が{@code XOR}か{@code AND}かを示すフラグ
     */
    private boolean isXOR = true;

    /**
     * {@code Join}要素の要素名
     */
    public static final String OOUOTHIS = "Join";

    /**
     * {@code type}属性の値を返します。
     * 
     * @return {@code type}属性の値
     */
    public String getType() {
        return type;
    }

    /**
     * {@code type}属性の属性名
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
     * 読み込み終了を示す文字
     */
    public static final String setupEnd_OOUOFIN = "";

    /**
     * 読み込む終了時の処理を行います。
     */
    public void setupEnd() {
        if (type.endsWith("AND")) {
            isXOR = false;
        }
    }

    /**
     * {@code Join}が{@code XOR}かどうかを判定します。
     * 
     * @return {@code XOR}の場合{@code true}、そうではない場合{@code false}
     */
    public boolean isXor() {
        return isXOR;
    }

    /**
     * {@code Join}が{@code AND}かどうかを判定します。
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
        buff.append("]");
        return buff.toString();
    }

}
