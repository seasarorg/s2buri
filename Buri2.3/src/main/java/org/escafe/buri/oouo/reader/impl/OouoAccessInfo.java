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

import java.util.HashSet;
import java.util.Set;

/**
 * XPDLの各要素を抽象化したクラスを参照する際に使用されるクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/15
 *
 */
public class OouoAccessInfo {
    /**
     * 属性名
     */
    private String tgtName;
    /**
     * 定数で定義された属性への参照のリスト
     */
    private Set<OouoSig> sigSet = new HashSet<OouoSig>();
    /**
     * 属性に対する振る舞いを決める{@code int}の値
     * <p>
     * 定数で設定されます。
     * </p>
     */
    private int typeSum = 0;

    /**
     * 定数で定義された属性への参照のリストを返します。
     * 
     * @return 定数で定義された属性への参照のリスト
     */
    public Set<OouoSig> getSigSet() {
        return sigSet;
    }

    /**
     * 定数で定義された属性への参照のリストを登録します。
     * 
     * @param sigSet 定数で定義された属性への参照のリスト
     */
    public void setSigSet(Set<OouoSig> sigSet) {
        this.sigSet = sigSet;
    }

    /**
     * 属性名を返します。
     * 
     * @return 属性名
     */
    public String getTgtName() {
        return tgtName;
    }

    /**
     * 属性名を登録します。
     * 
     * @param tgtName 属性名
     */
    public void setTgtName(String tgtName) {
        this.tgtName = tgtName;
    }

    /**
     * 属性に対する振る舞いを決める{@code int}の値を返します。
     * 
     * @return 属性に対する振る舞いを決める{@code int}の値
     */
    public int getTypeSum() {
        return typeSum;
    }

    /**
     * 属性に対する振る舞いを決める{@code int}の値を登録します。
     * 
     * @param typeSum 属性に対する振る舞いを決める{@code int}の値
     */
    public void setTypeSum(int typeSum) {
        this.typeSum = typeSum;
    }

    /**
     * {@link OouoSig}を元に、属性に対する振る舞いを決める{@code int}の値を更新します。
     * 
     * @param sig {@link OouoSig}
     */
    public void addType(OouoSig sig) {
        typeSum = typeSum + sig.getAction();
    }

}
