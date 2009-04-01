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
package org.escafe.buri.oouo.reader.impl;

import java.lang.reflect.Field;

/**
 * Oouoで使用するシグネチャ定義を表すクラスです。
 * <p>
 * XPDLを読み込む際に、XPDLの各要素を抽象化したクラスの属性読み込み用の定数を参照する際に使用されます。
 * </p>
 * <p>
 * 主に{@link OouoClassDefImpl}で利用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/15
 */
public class OouoSig {
    /**
     * 属性名
     */
    private String sig;
    /**
     * シグネチャの種別
     */
    private int action;
    /**
     * 属性名とシグネチャの種別から特定されたクラスのプロパティ
     */
    private Field field;

    /**
     * 属性の始点を表すマーク値
     */
    public static final int THIS = 64;
    /**
     * 属性の終点を表すマーク値
     */
    public static final int FIN = 128;

    /**
     * ネストした要素
     * <p>
     * {@code action}にセットされる定数です。
     * </p>
     */
    public static final int NESTELEMENT = 3;
    /**
     * 要素
     * <p>
     * {@code action}にセットされる定数です。
     * </p>
     */
    public static final int ELEMENT = 2;
    /**
     * 属性
     * <p>
     * {@code action}にセットされる定数です。
     * </p>
     */
    public static final int ATTRI = 4;
    /**
     * 値
     * <p>
     * {@code action}にセットされる定数です。
     * </p>
     */
    public static final int TEXT = 8;

    /**
     * オブジェクトの複製を作成して後、指定された{@link Field}を差し替えて返します。
     * 
     * @param tgt 差し替えたい{@link Field}
     * @return {@link Field}を差し替えられたオブジェクトの複製
     */
    public OouoSig copy(Field tgt) {
        OouoSig sig = new OouoSig();
        sig.sig = this.sig;
        sig.action = this.action;
        sig.field = tgt;
        return sig;
    }

    /**
     * シグネチャの種別を返します。
     * 
     * @return シグネチャの種別
     */
    public int getAction() {
        return action;
    }

    /**
     * シグネチャの種別を登録します。
     * 
     * @param action シグネチャの種別
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * 属性名を返します。
     * 
     * @return 属性名
     */
    public String getSig() {
        return sig;
    }

    /**
     * 属性名を登録します。
     * 
     * @param sig 属性名
     */
    public void setSig(String sig) {
        this.sig = sig;
    }

    /**
     * 属性名とシグネチャの種別から特定されたクラスのプロパティを返します。
     * 
     * @return 属性名とシグネチャの種別から特定されたクラスのプロパティ
     */
    public Field getField() {
        return field;
    }

    /**
     * 属性名とシグネチャの種別から特定されたクラスのプロパティを登録します。
     * 
     * @param field 属性名とシグネチャの種別から特定されたクラスのプロパティ
     */
    public void setField(Field field) {
        this.field = field;
    }

}
