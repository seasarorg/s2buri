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
 * XPDLの{@code Performer}要素を表すクラスです。
 * <p>
 * {@code Performer}はアクティビティの所有者を表します。
 * この{@code Performer}には、{@code Participant}で定義された権限のIDが指定されます。
 * </p>
 * <p>
 * {@code Performer}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="Performer">
 *     <xsd:annotation>
 *         <xsd:documentation>A String or Expression designating the Performer</xsd:documentation>
 *     </xsd:annotation>
 *     <xsd:complexType>
 *         <xsd:simpleContent>
 *             <xsd:extension base="xsd:string">
 *                 <xsd:anyAttribute namespace="##other" processContents="lax"/>
 *             </xsd:extension>
 *         </xsd:simpleContent>
 *     </xsd:complexType>
 * </xsd:element>}</pre>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 */
public class BuriPerformerType {
    /**
     * {@code Performer}のID
     */
    private String id;

    /**
     * {@code Performer}要素の要素名
     */
    public final static String OOUOTHIS = "Performer";

    /**
     * {@code Performer}のIDを返します。
     * 
     * @return {@code Performer}のID
     */
    public String getId() {
        return id;
    }

    /**
     * {@code Performer}のIDの属性名
     */
    public final static String setId_OOUOTEXT = "";

    /**
     * {@code Performer}のIDをセットします。
     * <p>
     * 複数のIDが使用されていた場合は、追記されます。
     * </p>
     * 
     * @param id {@code Performer}のID
     */
    public void setId(String id) {
        String con = this.id;
        if (con == null) {
            con = "";
        }
        this.id = con + id;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("]");
        return buff.toString();
    }
}
