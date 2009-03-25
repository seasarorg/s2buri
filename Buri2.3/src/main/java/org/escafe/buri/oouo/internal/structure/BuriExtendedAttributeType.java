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
 * XPDLの{@code ExtendedAttribute}要素を表すクラスです。
 * <p>
 * この{@code ExtendedAttribute}要素は、XPDLの様々な要素を拡張する為の要素です。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/19
 */
public class BuriExtendedAttributeType {
	/**
	 * {@code ExtendedAttribute}の名前
	 */
	private String name;
	/**
	 * {@code ExtendedAttribute}の値
	 */
	private String value;

	/**
	 * {@code ExtendedAttribute}の要素名
	 */
	public static final String OOUOTHIS = "ExtendedAttribute";

	/**
	 * {@code ExtendedAttribute}の名前を返します。
	 * 
	 * @return {@code ExtendedAttribute}の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@code ExtendedAttribute}の名前の属性名
	 */
	public static final String setName_ATTRI = "Name";

	/**
	 * {@code ExtendedAttribute}の名前を登録します。
	 * 
	 * @param name
	 *            {@code ExtendedAttribute}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@code ExtendedAttribute}の値を返します。
	 * 
	 * @return {@code ExtendedAttribute}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * {@code ExtendedAttribute}の値の属性名
	 */
	public static final String setValue_ATTRI = "Value";

	/**
	 * {@code ExtendedAttribute}の値を登録します。
	 * 
	 * @param value {@code ExtendedAttribute}の値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("[");
		buffer.append("name=").append(name);
		buffer.append("/value=").append(value);
		buffer.append("]");
		return buffer.toString();
	}
}
