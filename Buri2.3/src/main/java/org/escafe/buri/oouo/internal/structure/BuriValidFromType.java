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
 * XPDLの{@code ValidFrom}要素を表すクラスです。
 * <p>
 * この{@code ValidFrom}に指定された日時が、そのフローが有効になる日時です。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/07/14
 */
public class BuriValidFromType {
    /**
     * {@code ValidFrom}に指定された日付
     */
    private String fromDate;
    /**
     * {@code ValidFrom}要素の要素名
     */
    public final static String OOUOTHIS = "ValidFrom";

    /**
     * {@code ValidFrom}に指定された日付を返します。
     * 
     * @return {@code ValidFrom}に指定された日付
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * {@code FromDate}属性がテキストの値を持つ事を表すアノテーション
     */
    public final static String setFromDate_OOUOTEXT = "";

    /**
     * {@code ValidFrom}に指定された日付を登録します。
     * 
     * @param fromDate {@code ValidFrom}に指定された日付
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/fromDate=").append(fromDate);
        buff.append("]");
        return buff.toString();
    }

}
