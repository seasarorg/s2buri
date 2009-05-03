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

import org.escafe.buri.engine.service.impl.BuriTimerService;

/**
 * XPDLの{@code Activity}に従属する{@code Limit}要素を表すクラスです。
 * <p>
 * ここの{@code Limit}に指定された文字列は日付として解釈されます。
 * そして、ぶりはここに指定された日時にそのアクティビティ上のデータを自動的に実行します。 ただしその実行をぶりに指示する為に
 * {@link BuriTimerService}を使用する必要があります。 この{@link BuriTimerService}の設定は、
 * 開発者が定義する{@code buri/dicon/buriTimer.dicon}にて行います。
 * </p>
 * <p>
 * この{@code Limit}のスキーマは、以下のように定義されています。
 * 
 * <pre>
 * &#064;code  &lt;xsd:element name=&quot;Limit&quot;&gt;
 *     &lt;xsd:complexType&gt;
 *         &lt;xsd:simpleContent&gt;
 *             &lt;xsd:extension base=&quot;xsd:string&quot;&gt;
 *                 &lt;xsd:anyAttribute namespace=&quot;##other&quot; processContents=&quot;lax&quot;/&gt;
 *             &lt;/xsd:extension&gt;
 *         &lt;/xsd:simpleContent&gt;
 *     &lt;/xsd:complexType&gt;
 * &lt;/xsd:element&gt;}
 * </pre>
 * 
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/19
 */
public class BuriActivityLimitType {
	/**
	 * {@code Limit}の値
	 */
	private String limit = "";

	/**
	 * {@code Limit}の要素名
	 */
	public final static String OOUOTHIS = "Limit";

	/**
	 * {@code Limit}の値を返します。
	 * 
	 * @return {@code Limit}の値
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * 読み込み終了を表す文字列
	 */
	public final static String setLimit_OOUOTEXT = "";

	/**
	 * {@code Limit}の値を登録します。
	 * 
	 * @param limit {@code Limit}の値
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/limit=").append(limit);
		buff.append("]");
		return buff.toString();
	}
}
