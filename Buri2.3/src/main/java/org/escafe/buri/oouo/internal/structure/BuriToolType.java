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
 * XPDLの{@code Tool}要素を表すクラスです。
 * <p>
 * 属性{@code Type}には、{@code Application}と{@code Procedure}のいずれかが選択可能です。
 * </p>
 * <p>
 * この{@code Tool}では、紐づく{@code ExtendedAttribute}にOGNL式を実装することで、
 * 任意の処理を行うことが可能です。
 * 例えば、{@code #result = "A"}などとする事で、フロー実行時の戻り値を定義する事ができます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 *
 */
public class BuriToolType {
    /**
     * {@code Tool}のID
     */
    private String id;
    /**
     * {@code Tool}の{@code Type}
     */
    private String type;
    /**
     * {@code Tool}に紐づく{@code ExtendedAttribute}のリスト
     */
    private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();

    /**
     * {@code Tool}の要素名
     */
    public static final String OOUOTHIS = "Tool";

    /**
     * {@code Tool}のIDを返します。
     * 
     * @return {@code Tool}のID
     */
    public String getId() {
        return id;
    }

    /**
     * {@code Tool}のID
     */
    public static final String setId_ATTRI = "Id";

    /**
     * {@code Tool}のIDを登録します。
     * 
     * @param id {@code Tool}のID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@code Tool}の{@code Type}を返します。
     * 
     * @return {@code Tool}の{@code Type}
     */
    public String getType() {
        return type;
    }

    /**
     * {@code Tool}の{@code Type}の属性名
     */
    public static final String setType_ATTRI = "Type";

    /**
     * {@code Tool}の{@code Type}を登録します。
     * 
     * @param type {@code Tool}の{@code Type}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@code Tool}に紐づく{@code ExtendedAttribute}の要素名
     */
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    /**
     * {@code Tool}に紐づく{@code ExtendedAttribute}を追加します。
     * 
     * @param attri {@code Tool}に紐づく{@code ExtendedAttribute}
     */
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }

    /**
     * {@code Tool}に紐づく{@code ExtendedAttribute}のリストを返します。
     * 
     * @return {@code Tool}に紐づく{@code ExtendedAttribute}のリスト
     */
    public List<BuriExtendedAttributeType> getExtendedAttribute() {
        return ExtentedAttribute;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/type=").append(type);
        buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
        return buff.toString();
    }

}
