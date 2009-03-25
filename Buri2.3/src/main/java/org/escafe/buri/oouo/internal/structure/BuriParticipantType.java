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
 * フロー上のステークホルダー（スイムレーン）を表すクラスです。
 * <p>
 * XPDL上では{@code Participant}要素として表現されます。
 * </p>
 * <p>
 * この{@code Participant}要素の{@code Type}には、以下のような選択肢があらかじめ用意されています。
 * <ul>
 * <li>Resource set</li>
 * <li>Role</li>
 * <li>Human</li>
 * <li>Resource</li>
 * <li>Organization unit</li>
 * <li>System</li>
 * </ul>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/07
 */
public class BuriParticipantType {
	/**
	 * ステークホルダーの名称
	 */
	private String name;
	/**
	 * ステークホルダーのID
	 */
	private String id;
	/**
	 * ステークホルダーの種別
	 */
	private String type;

	/**
	 * {@code Participant}要素の要素名
	 */
	public final static String OOUOTHIS = "Participant";

	/**
	 * ステークホルダーのIDの属性名
	 */
	public final static String setId_ATTRI = "Id";

	/**
	 * ステークホルダーのIDを返します。
	 * 
	 * @return ステークホルダーのID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ステークホルダーのIDを登録します。
	 * 
	 * @param id ステークホルダーのID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ステークホルダーの名称の属性名
	 */
	public final static String setName_ATTRI = "Name";

	/**
	 * ステークホルダーの名称を返します。
	 * 
	 * @return ステークホルダーの名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * ステークホルダーの名称を登録します。
	 * 
	 * @param name ステークホルダーの名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@code ParticipantType}要素の要素名
	 */
	public final static String setType_ELEMENT = "ParticipantType";
	/**
	 * {@code ParticipantType}要素の{@code Type}属性の属性名
	 */
	public final static String setType_ATTRI = "Type";

	/**
	 * ステークホルダーの種別を返します。
	 * 
	 * @return ステークホルダーの種別
	 */
	public String getType() {
		return type;
	}

	/**
	 * ステークホルダーの種別を登録します。
	 * 
	 * @param type ステークホルダーの種別
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("id=").append(id);
		buff.append("/name=").append(name);
		buff.append("/type=").append(type);
		buff.append("]");
		return buff.toString();
	}

}
