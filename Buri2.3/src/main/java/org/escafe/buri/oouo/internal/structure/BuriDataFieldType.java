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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XPDLの{@code DataField}ノードをオブジェクト化する為のクラスです。
 * <p>
 * {@code DataField}は、XPDLのパッケージに対して定義される要素で、
 * IDにはフローで使用されるDtoのクラスを指定します。<br/>
 * ぶりではこのエレメントの{@code id}、{@code ExtendedAttributes}という属性しか使用しません。
 * このクラスのメンバーである{@code insert}、{@code update}、{@code delete}、{@code selectMany}、
 * {@code preprocess}、{@code tableName}、{@code keys}、{@code cache}などは、
 * すべて{@code ExtendedAttributes}に集約される{@code ExtendedAttribute}エレメントとして定義される情報です。
 * </p>
 * <p>
 * XPDLのDataFieldは以下のようにスキーマが定義されているエレメントです。
 * DataFieldで{@code "Package"}、{@code "WorkflowProcess"}に紐づくエレメントですが、
 * 厳密には{@code "Package"}-&gt;{@code "DataFields}-&gt;{@code DataField}という階層で構成されます。
 * <pre>{@code <xsd:element name="DataField">
 *   <xsd:complexType>
 *     <xsd:sequence>
 *        <xsd:element ref="xpdl:DataType"/>
 *        <xsd:element ref="xpdl:InitialValue" minOccurs="0"/>
 *        <xsd:element ref="xpdl:Length" minOccurs="0"/>
 *        <xsd:element ref="xpdl:Description" minOccurs="0"/>
 *        <xsd:element ref="xpdl:ExtendedAttributes" minOccurs="0"/>
 *     </xsd:sequence>
 *     <xsd:attribute name="Id" type="xsd:NMTOKEN" use="required"/>
 *     <xsd:attribute name="Name" type="xsd:string"/>
 *     <xsd:attribute name="IsArray" default="FALSE">
 *        <xsd:simpleType>
 *           <xsd:restriction base="xsd:NMTOKEN">
 *              <xsd:enumeration value="TRUE"/>
 *              <xsd:enumeration value="FALSE"/>
 *           </xsd:restriction>
 *        </xsd:simpleType>
 *     </xsd:attribute>
 *   </xsd:complexType>
 * </xsd:element>}</pre>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/07
 */
public class BuriDataFieldType {
	/**
	 * {@code DataField}のID
	 */
	private String id;
	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリスト
	 */
	private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();

	/**
	 * {@code "insert"}として登録されたinsert処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String insert;
	/**
	 * {@code "update"}として登録されたupdate処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String update;
	/**
	 * {@code "select"}として登録されたselect処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String select;
	/**
	 * {@code "delete"}として登録されたdelete処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String delete;
	/**
	 * {@code "selectMany"}として登録された検索処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String selectMany;
	/**
	 * {@code "preprocess"}として登録された検索処理の為のOGNL式
	 * <p>
	 * DataFieldに含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String preprocess;
	/**
	 * 対象のテーブル名
	 * <p>
	 * {@code "tableName"}として登録された検索処理の為のOGNL式
	 * </p>
	 * <p>
	 * {@code DataField}に含まれる{@code ExtendedAttributes}の{@code ExtendedAttribute}というノードで定義される情報です。
	 * </p>
	 */
	private String tableName;

	/**
	 * {@code DataField}に指定されたDtoの属性名とその値を保持する{@link Map}
	 */
	private Map<String, String> keys = new HashMap<String, String>();
	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}の内容を一時的にキャッシュする為の{@link Map}
	 */
	private Map<String, Object> cache = new HashMap<String, Object>();

	/**
	 * {@code DataField}要素の要素名
	 */
	public final static String OOUOTHIS = "DataField";

	/**
	 * {@code DataField}の{@code Id}属性の属性名
	 */
	public final static String setId_ATTRI = "Id";

	/**
	 * {@code DataField}のIDを返します。
	 * 
	 * @return {@code DataField}のID
	 */
	public String getId() {
		return id;
	}

	/**
	 * {@code DataField}のIDを登録します。
	 * 
	 * @param id {@code DataField}のID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}要素の要素名
	 */
	public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}を追加します。
	 * 
	 * @param attri {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}
	 */
	public void addExtendedAttribute(BuriExtendedAttributeType attri) {
		ExtentedAttribute.add(attri);
	}

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリストを返します。
	 * 
	 * @return {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリスト
	 */
	public List<BuriExtendedAttributeType> getExtentedAttribute() {
		return ExtentedAttribute;
	}

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリストを登録します。
	 * 
	 * @param extentedAttribute {@code DataField}に紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリスト
	 */
	public void setExtentedAttribute(List<BuriExtendedAttributeType> extentedAttribute) {
		ExtentedAttribute = extentedAttribute;
	}

	/**
	 * {@code "insert"}として登録されたinsert処理の為のOGNL式を返します。
	 * 
	 * @return {@code "insert"}として登録されたinsert処理の為のOGNL式
	 */
	public String getInsert() {
		return insert;
	}

	/**
	 * {@code "insert"}として登録されたinsert処理の為のOGNL式を登録します。
	 * 
	 * @param insert {@code "insert"}として登録されたinsert処理の為のOGNL式
	 */
	public void setInsert(String insert) {
		this.insert = insert;
	}

	/**
	 * {@code "preprocess"}として登録された検索処理の為のOGNL式を返します。
	 * 
	 * @return {@code "preprocess"}として登録された検索処理の為のOGNL式
	 */
	public String getPreprocess() {
		return preprocess;
	}

	/**
	 * {@code "preprocess"}として登録された検索処理の為のOGNL式を登録します。
	 * 
	 * @param preprocess {@code "preprocess"}として登録された検索処理の為のOGNL式
	 */
	public void setPreprocess(String preprocess) {
		this.preprocess = preprocess;
	}

	/**
	 * {@code "select"}として登録されたselect処理の為のOGNL式を返します。
	 * 
	 * @return {@code "select"}として登録されたselect処理の為のOGNL式
	 */
	public String getSelect() {
		return select;
	}

	/**
	 * {@code "select"}として登録されたselect処理の為のOGNL式を登録します。
	 * 
	 * @param select {@code "select"}として登録されたselect処理の為のOGNL式
	 */
	public void setSelect(String select) {
		this.select = select;
	}

	/**
	 * {@code "selectMany"}として登録された検索処理の為のOGNL式を返します。
	 * 
	 * @return {@code "selectMany"}として登録された検索処理の為のOGNL式
	 */
	public String getSelectMany() {
		return selectMany;
	}

	/**
	 * {@code "selectMany"}として登録された検索処理の為のOGNL式を登録します。
	 * 
	 * @param selectMany {@code "selectMany"}として登録された検索処理の為のOGNL式
	 */
	public void setSelectMany(String selectMany) {
		this.selectMany = selectMany;
	}

	/**
	 * {@code "update"}として登録されたupdate処理の為のOGNL式を返します。
	 * 
	 * @return {@code "update"}として登録されたupdate処理の為のOGNL式
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * {@code "update"}として登録されたupdate処理の為のOGNL式を登録します。
	 * 
	 * @param update {@code "update"}として登録されたupdate処理の為のOGNL式
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * {@code "delete"}として登録されたdelete処理の為のOGNL式を返します。
	 * 
	 * @return {@code "delete"}として登録されたdelete処理の為のOGNL式
	 */
	public String getDelete() {
		return delete;
	}

	/**
	 * {@code "delete"}として登録されたdelete処理の為のOGNL式を登録します。
	 * 
	 * @param delete {@code "delete"}として登録されたdelete処理の為のOGNL式
	 */
	public void setDelete(String delete) {
		this.delete = delete;
	}

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}の内容を一時的にキャッシュする為の{@link Map}を返します。
	 * 
	 * @return {@code DataField}に紐づく{@code ExtendedAttribute}の内容を一時的にキャッシュする為の{@link Map}
	 */
	public Map<String, Object> getCache() {
		return cache;
	}

	/**
	 * {@code DataField}に紐づく{@code ExtendedAttribute}の内容を一時的にキャッシュする為の{@link Map}を登録します。
	 * 
	 * @param cache {@code DataField}に紐づく{@code ExtendedAttribute}の内容を一時的にキャッシュする為の{@link Map}
	 */
	public void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}

	/**
	 * 対象のテーブル名を返します。
	 * 
	 * @return 対象のテーブル名
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 対象のテーブル名を登録します。
	 * 
	 * @param tableName 対象のテーブル名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("id=").append(id);
		buff.append("cache=").append(cache);
		buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
		buff.append("]");
		return buff.toString();
	}

	/**
	 * Dtoの属性名とその値を保持する{@link Map}を返します。
	 * 
	 * @return Dtoの属性名とその値を保持する{@link Map}
	 */
	public Map<String, String> getKeys() {
		return keys;
	}

	/**
	 * Dtoの属性名とその値を保持する{@link Map}を登録します。
	 * 
	 * @param keys Dtoの属性名とその値を保持する{@link Map}
	 */
	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}

}
