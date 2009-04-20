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
package org.escafe.buri.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.BuriConfigDto;
import org.escafe.buri.engine.BuriEngineConfig;
import org.escafe.buri.engine.ParticipantProvider;

/**
 * ぶりのエンジンの設定を保持する為のクラスの実装です。
 * <p>
 * {@code /buri/dicon/buri-user.dicon}で事前にフローを登録する際などに、のクラスへの定義を行います。
 * </p>
 * 
 * @author makotan
 * @author noebans
 * @author imai78(JavaDoc)
 * @since 2006/07/17
 */
public class BuriEngineConfigImpl implements BuriEngineConfig {
	/**
	 * フローのリソース情報
	 */
	private final List<BuriConfigDto> resourceConfigs =
	    new ArrayList<BuriConfigDto>();

	/**
	 * フローのファイル情報
	 */
	private final List<BuriConfigDto> fileConfigs =
	    new ArrayList<BuriConfigDto>();

	/**
	 * {@inheritDoc}
	 */
	public List<BuriConfigDto> getResourceConfigs() {
		return resourceConfigs;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BuriConfigDto> getFileConfigs() {
		return fileConfigs;
	}

	/**
	 * フローを登録します。
	 * 
	 * @param fileName
	 *            フローのファイル名
	 * @param packageName
	 *            フローのパッケージ名
	 */
	public void addResourceConfig(String fileName, String packageName) {
		addResourceConfig(fileName, packageName, null);
	}

	/**
	 * フローを登録します。
	 * 
	 * @param fileName
	 *            フローのファイル名
	 * @param packageName
	 *            フローのパッケージ名
	 * @param provider
	 *            フローに対して適用する権限主体
	 */
	public void addResourceConfig(String fileName, String packageName,
	        ParticipantProvider provider) {
		BuriConfigDto dto = new BuriConfigDto();
		dto.setFileName(fileName);
		dto.setPackageName(packageName);
		dto.setProvider(provider);
		resourceConfigs.add(dto);
	}

	/**
	 * ファイル情報を登録します。
	 * 
	 * @param fileName
	 *            ファイル名
	 * @param packageName
	 *            パッケージ名
	 */
	public void addFileConfig(String fileName, String packageName) {
		addFileConfig(fileName, packageName, null);
	}

	/**
	 * ファイル情報を登録します。
	 * 
	 * @param fileName
	 *            ファイル名
	 * @param packageName
	 *            パッケージ名
	 * @param provider
	 *            フローに対して適用する権限主体
	 */
	public void addFileConfig(String fileName, String packageName,
	        ParticipantProvider provider) {
		BuriConfigDto dto = new BuriConfigDto();
		dto.setFileName(fileName);
		dto.setPackageName(packageName);
		dto.setProvider(provider);
		fileConfigs.add(dto);
	}
}
