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

import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.seasar.framework.util.StringUtil;

/**
 * XPDLの{@code Transition}要素を表すクラスです。
 * <p>
 * {@code Transition}はフロー上でアクティビティ同士を繋ぐ線を表します。
 * 1つの{@code Transition}は必ず1つのアクティビティから1つのアクティビティに
 * 接続されます。
 * </p>
 * <p>
 * {@code Transition}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="Transition">
 *     <xsd:complexType>
 *         <xsd:sequence>
 *             <xsd:element ref="xpdl:Condition" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Description" minOccurs="0"/>
 *             <xsd:element ref="xpdl:ExtendedAttributes" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Assignments" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Object" minOccurs="0"/>
 *             <xsd:element ref="xpdl:ConnectorGraphicsInfos" minOccurs="0"/>
 *             <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *         </xsd:sequence>
 *         <xsd:attribute name="Id" type="xpdl:Id" use="required"/>
 *         <xsd:attribute name="From" type="xpdl:IdRef" use="required"/>
 *         <xsd:attribute name="To" type="xpdl:IdRef" use="required"/>
 *         <xsd:attribute name="Name" type="xsd:string" use="optional"/>
 *         <xsd:attribute name="Quantity" type="xsd:int" use="optional" default="1">
 *             <xsd:annotation>
 *                 <xsd:documentation>Used only in BPMN. Specifies number of tokens on outgoing transition.</xsd:documentation>
 *             </xsd:annotation>
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
public class BuriTransitionType {
    /**
     * {@code Transition}のID
     */
    private String id;
    /**
     * 接続元のアクティビティのID
     */
    private String from;
    /**
     * 接続先のアクティビティのID
     */
    private String to;
    /**
     * {@code Transition}の{@code Condition}
     */
    private BuriConditionType condition;

    /**
     * トランジションに指定された条件式
     */
    private String conditionStr = "";
    /**
     * {@code Transition}に紐づく{@code ExtendedAttribute}のリスト
     */
    private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();

    /**
     * {@code Transition}要素の要素名
     */
    public final static String OOUOTHIS = "Transition";

    /**
     * 接続元のアクティビティのIDを返します。
     * 
     * @return 接続元のアクティビティのID
     */
    public String getFrom() {
        return from;
    }

    /**
     * 接続元のアクティビティのIDの属性名
     */
    public final static String setFrom_ATTRI = "From";

    /**
     * 接続元のアクティビティのIDを登録します。
     * 
     * @param from 接続元のアクティビティのID
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * {@code Transition}のIDを返します。
     * 
     * @return {@code Transition}のID
     */
    public String getId() {
        return id;
    }

    /**
     * {@code Transition}のIDの属性名
     */
    public final static String setId_ATTRI = "Id";

    /**
     * {@code Transition}のIDを登録します。
     * 
     * @param id {@code Transition}のID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 接続先のアクティビティのIDを返します。
     * 
     * @return 接続先のアクティビティのID
     */
    public String getTo() {
        return to;
    }

    /**
     * 接続先のアクティビティのIDの属性名
     */
    public final static String setTo_ATTRI = "To";

    /**
     * 接続先のアクティビティのIDを登録します。
     * 
     * @param to 接続先のアクティビティのID
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * {@code Transition}の{@code Condition}を返します。
     * 
     * @return {@code Transition}の{@code Condition}
     */
    public BuriConditionType getCondition() {
        return condition;
    }

    /**
     * {@code Transition}の{@code Condition}の要素名
     */
    public static final String setCondition_ELEMENT = "Condition";

    /**
     * {@code Transition}の{@code Condition}を登録します。
     * 
     * @param condition {@code Transition}の{@code Condition}
     */
    public void setCondition(BuriConditionType condition) {
        this.condition = condition;
    }

    /**
     * {@code Transition}に紐づく{@code ExtendedAttribute}の要素名
     */
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    /**
     * {@code Transition}に紐づく{@code ExtendedAttribute}を追加します。
     * 
     * @param attri {@code Transition}に紐づく{@code ExtendedAttribute}
     */
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }

    /**
     * {@code Transition}に紐づく{@code ExtendedAttribute}のリストを返します。
     * 
     * @return {@code Transition}に紐づく{@code ExtendedAttribute}のリスト
     */
    public List<BuriExtendedAttributeType> getExtendedAttributeList() {
        return ExtentedAttribute;
    }

    /**
     * {@code Transition}に{@code Condition}が設定されているかどうかを判定します。
     * 
     * @return {@code Condition}が設定されている場合は{@code true}、そうでない場合は{@code false}
     */
    public boolean hasCondition() {
        if (condition == null) {
            return false;
        }
        return true;
    }

    /**
     * トランジションに指定された条件式を返します。
     * 
     * @return トランジションに指定された条件式
     */
    public String getConditionStr() {
        return conditionStr;
    }

    /**
     * 読み込み終了を表す文字列
     */
    public static String setupEnd_OOUOFIN = "";

    /**
     * 読み込み終了時の処理を行います。
     */
    public void setupEnd() {
        String action = null;
        if (condition != null) {
            conditionStr = condition.getCondition();
            action = ExtentedAttributeUtil.getAttributeVal(getExtendedAttributeList(), "action");
        }
        if (StringUtil.isEmpty(conditionStr) && (action != null)) {
            condition = new BuriConditionType();
            conditionStr = "#action == \"" + action + "\"";
            condition.setType("CONDITION");
            condition.setCondition(conditionStr);
        }
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("\n[");
        buff.append("id=").append(id);
        buff.append("/from=").append(from);
        buff.append("/to=").append(to);
        buff.append("/condition=").append(condition);
        buff.append("]");
        return buff.toString();
    }

}
