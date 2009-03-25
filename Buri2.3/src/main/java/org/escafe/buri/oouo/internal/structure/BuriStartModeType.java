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
 * XPDLの{@code StartMode}要素を表すクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 *
 */
public class BuriStartModeType {
    /**
     * {@code Automatic}に設定されているかどうかを表すフラグ
     */
    private boolean isAuto = true;
    /**
     * {@code StartMode}要素の要素名
     */
    public final static String OOUOTHIS = "StartMode";

    /**
     * {@code StartMode}要素に紐づく{@code Automatic}要素の要素名
     */
    public final static String setAutomatic_ELEMENT = "Automatic";

    /**
     * {@code StartMode}を{@code Automatic}に設定します。
     */
    public void setAutomatic() {
        isAuto = true;
    }

    /**
     * {@code StartMode}要素に紐づく{@code Manual}要素の要素名
     */
    public final static String setManual_ELEMENT = "Manual";

    /**
     * {@code StartMode}を{@code Manual}に設定します。
     */
    public void setManual() {
        isAuto = false;
    }

    /**
     * {@code StartMode}が{@code Automatic}かどうかを判定します。
     * 
     * @return {@code Automatic}の場合{@code true}、そうでない場合は{@code false}
     */
    public boolean isAutomatic() {
        return isAuto;
    }

    /**
     * {@code StartMode}が{@code Manual}かどうかを判定します。
     * 
     * @return {@code Manual}の場合{@code true}、そうでない場合は{@code false}
     */
    public boolean isManual() {
        return (!isAuto);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (isAuto) {
            return "StartMode=Automatic";
        } else {
            return "StartMode=Manual";
        }
    }
}
