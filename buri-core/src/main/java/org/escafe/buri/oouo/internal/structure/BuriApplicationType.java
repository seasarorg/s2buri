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
 * XPDLの{@code Application}要素を表すクラスです。
 * <p>
 * この{@code Application}要素には{@link BuriComponent}インターフェイスを元に
 * 実装されたコンポーネントを定義することで、フローの実行の際にJavaコンポーネントを
 * 利用することができます。
 * </p>
 * <p>
 * {@code Application}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="Application">
 *     <xsd:complexType>
 *         <xsd:sequence>
 *             <xsd:element ref="xpdl:Description" minOccurs="0"/>
 *             <xsd:element name="Type" type="xpdl:ApplicationType" minOccurs="0"/>
 *             <xsd:choice>
 *                 <xsd:element ref="xpdl:FormalParameters"/>
 *                 <xsd:element ref="xpdl:ExternalReference" minOccurs="0"/>
 *             </xsd:choice>
 *             <xsd:element ref="xpdl:ExtendedAttributes" minOccurs="0"/>
 *             <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *         </xsd:sequence>
 *         <xsd:attribute name="Id" type="xsd:NMTOKEN" use="required"/>
 *         <xsd:attribute name="Name" type="xsd:string" use="optional"/>
 *         <xsd:anyAttribute namespace="##other" processContents="lax"/>
 *     </xsd:complexType>
 * </xsd:element>}</pre>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/07
 */
public class BuriApplicationType {
	/**
	 * {@code Application}のID
	 */
	private String id;
	/**
	 * {@code Application}の名前
	 */
	private String name;
	/**
	 * {@code Application}に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}のリスト
	 */
	private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();

	/**
	 * {@code Application}要素の要素名
	 */
	public final static String OOUOTHIS = "Application";

	/**
	 * {@code Application}のIDの属性名
	 */
	public final static String setId_ATTRI = "Id";

	/**
	 * {@code Application}のIDを返します。
	 * 
	 * @return {@code Application}のID
	 */
	public String getId() {
		return id;
	}

	/**
	 * {@code Application}のIDを登録します。
	 * 
	 * @param id {@code Application}のID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@code Application}の名前の属性名
	 */
	public final static String setName_ATTRI = "Name";

	/**
	 * {@code Application}の名前を返します。
	 * 
	 * @return {@code Application}の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@code Application}の名前を登録します。
	 * 
	 * @param name {@code Application}の名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@code Application}に紐づけられた{@code ExtendedAttribute}の要素名
	 */
	public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

	/**
	 * {@code Application}に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}を追加します。
	 * 
	 * @param attri {@code Application}に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}
	 */
	public void addExtendedAttribute(BuriExtendedAttributeType attri) {
		ExtentedAttribute.add(attri);
	}

	/**
	 * {@code Application}に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}のリストを返します。
	 * 
	 * @return {@code Application}に紐づけられた{@code ExtendedAttribute}の{@link BuriExtendedAttributeType}
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
		buff.append("id=").append(id);
		buff.append("/name=").append(name);
		buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
		buff.append("]");
		return buff.toString();
	}

}
