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
package org.escafe.buri.engine;

/**
 * ぶりが使用するフローの情報を保持するDtoです。
 * <p>
 * 1つのパッケージのファイル名、パッケージ名、権限主体をこのクラスで管理します。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/07/16
 */
public class BuriConfigDto {
	/**
	 * フローのファイル名
	 * <p>
	 * XPDLでフローが記述される事を前提としています。
	 * </p>
	 */
	private String fileName;

	/**
	 * フローのパッケージ名
	 */
	private String packageName;

	/**
	 * フローで使用する権限主体
	 */
	private ParticipantProvider provider;

	/**
	 * フローのファイル名を返します。
	 * 
	 * @return フローのファイル名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * フローのファイル名を登録します。
	 * 
	 * @param fileName
	 *            フローのファイル名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * フローで使用する権限主体を返します。
	 * 
	 * @return フローで使用する権限主体
	 */
	public ParticipantProvider getProvider() {
		return provider;
	}

	/**
	 * フローで使用する権限主体を登録します。
	 * 
	 * @param provider
	 *            フローで使用する権限主体
	 */
	public void setProvider(ParticipantProvider provider) {
		this.provider = provider;
	}

	/**
	 * フローのパッケージ名を返します。
	 * 
	 * @return フローのパッケージ名
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * フローのパッケージ名を登録します。
	 * 
	 * @param resourceName
	 *            フローのパッケージ名
	 */
	public void setPackageName(String resourceName) {
		this.packageName = resourceName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("fileName=").append(fileName);
		buff.append("/packageName=").append(packageName);
		buff.append("/provider=").append(provider);
		buff.append("]");
		return buff.toString();
	}
}
