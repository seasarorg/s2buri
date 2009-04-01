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
 * XPDLの{@code Condition}要素を表すクラスです。
 * <p>
 * {@code Condition}はトランジションに対して紐づく要素です。
 * ぶりはこのトランジションの{@code Condition}に指定された条件を評価した結果、
 * ルートとして適切なアクティビティを選択し遷移先とします。
 * </p>
 * <p>
 * {@code Condition}の{@code Type}には以下のような種類があります。
 * <ul>
 * <li>{@code Condition}</li>
 * <li>{@code Default exception}</li>
 * <li>{@code Exception}</li>
 * <li>{@code Otherwise}</li>
 * </ul>
 * </p>
 * <p>
 * {@code Condition}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="Condition">
 *     <xsd:complexType mixed="true">
 *         <xsd:choice minOccurs="0">
 *             <xsd:element ref="deprecated:Xpression" minOccurs="0"/>
 *             <xsd:element name="Expression" type="xpdl:ExpressionType" minOccurs="0"/>
 *         </xsd:choice>
 *         <xsd:attribute name="Type">
 *             <xsd:simpleType>
 *                 <xsd:restriction base="xsd:NMTOKEN">
 *                     <xsd:enumeration value="CONDITION"/>
 *                     <xsd:enumeration value="OTHERWISE"/>
 *                     <xsd:enumeration value="EXCEPTION"/>
 *                     <xsd:enumeration value="DEFAULTEXCEPTION"/>
 *                 </xsd:restriction>
 *             </xsd:simpleType>
 *         </xsd:attribute>
 *         <xsd:anyAttribute namespace="##other" processContents="lax"/>
 *     </xsd:complexType>
 * </xsd:element>}</pre>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 */
public class BuriConditionType {
    /**
     * {@code Condition}の種別
     */
    private String type;
    /**
     * 条件として指定されたOGNL式
     */
    private String condition = "";
    /**
     * {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}のリスト
     */
    private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();

    /**
     * {@code Condition}要素の要素名
     */
    public final static String OOUOTHIS = "Condition";

    /**
     * 条件として指定されたOGNL式を返します。
     * 
     * @return 条件として指定されたOGNL式
     */
    public String getCondition() {
        return condition;
    }

    /**
     * 読み取り終了を表す文字列
     */
    public final static String setCondition_OOUOTEXT = "";

    /**
     * 条件として指定されたOGNL式を登録します。
     * 
     * @param condition 条件として指定されたOGNL式
     */
    public void setCondition(String condition) {
        String con = this.condition;
        if (con == null) {
            con = "";
        }
        this.condition = con + condition;
    }

    /**
     * {@code Condition}の種別を返します。
     * 
     * @return {@code Condition}の種別
     */
    public String getType() {
        return type;
    }

    /**
     * {@code Condition}要素の{@code Type}属性の属性名
     */
    public final static String setType_ATTRI = "Type";

    /**
     * {@code Condition}の種別を登録します。
     * 
     * @param type {@code Condition}の種別
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の要素名
     */
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    /**
     * {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}を追加します。
     * 
     * @param attri {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}
     */
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }

    /**
     * {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}のリストを返します。
     * @return {@code Condition}要素に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}のリスト
     */
    public List<BuriExtendedAttributeType> getExtendedAttributeList() {
        return ExtentedAttribute;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("type=").append(type);
        buff.append("/condition=").append(condition);
        buff.append("]");
        return buff.toString();
    }

}
