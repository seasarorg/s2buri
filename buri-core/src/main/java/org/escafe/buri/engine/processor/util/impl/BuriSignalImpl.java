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
package org.escafe.buri.engine.processor.util.impl;

import java.util.List;

import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriSignal;
import org.escafe.buri.entity.BuriUserEntity;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.service.BuriUserEntityService;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;

/**
 * シグナルを送る為のAPIセットです。
 * <p>
 * 高位APIセットである{@link BuriAutoSelectProcessor}のWrapperとして実装されています。
 * </p>
 * 
 * @author makotan
 * @author a-conv
 * @author imai78(JavaDoc)
 */
public class BuriSignalImpl implements BuriSignal {
	/**
	 * プロセッサ
	 */
	private BuriAutoSelectProcessor processor;

	/**
	 * ユーザ情報
	 */
	private BuriUserUtil userUtil;

	/**
	 * ぶり固有のテーブル{@code BuriUserEntity}のService
	 */
	private BuriUserEntityService buriUserEntityService;

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のService
	 */
	private BuriStateEntityService buriStateEntityService;

	/**
	 * 指定されたDtoの主キーを返します。
	 * <p>
	 * 指定したデータの主キーがLong型の単一項目の場合、に使用します。
	 * </p>
	 * 
	 * @param data
	 *            ぶりに管理しているDto
	 * @param accessFactory
	 *            使用する{@link DataAccessFactory}
	 * @return データの主キー
	 */
	private Long getLongKey(Object data, DataAccessFactory accessFactory) {
		Long longKey = null;
		DataAccessUtil accessUtil =
		    accessFactory.getDataAccessUtil(data.getClass());
		// 主キーのクラスから判定
		if (accessUtil instanceof DataAccessUtilLongKey) {
			DataAccessUtilLongKey longKeyUtil =
			    (DataAccessUtilLongKey) accessUtil;
			longKey = longKeyUtil.getKey(data);
		}
		return longKey;
	}

	/**
	 * 指定されたDtoの主キーを返します。
	 * <p>
	 * 主キーが複合項目で構成されている場合に使用します。
	 * </p>
	 * 
	 * @param data
	 *            ぶりで管理しているDto
	 * @param accessFactory
	 *            使用する{@link DataAccessFactory}
	 * @return データの主キー
	 */
	private String getManyKey(Object data, DataAccessFactory accessFactory) {
		String manyKey = null;
		DataAccessUtil accessUtil =
		    accessFactory.getDataAccessUtil(data.getClass());
		if (accessUtil instanceof DataAccessUtilManyKey) {
			DataAccessUtilManyKey manyKeyUtil =
			    (DataAccessUtilManyKey) accessUtil;
			manyKey = manyKeyUtil.getKey(data);
		}
		return manyKey;
	}

	/**
	 * 状態を次に進めます。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりが管理しているDto
	 * @param userData
	 *            権限管理に使用するユーザ情報のDto
	 * @param action
	 *            Actionの文字列、ぶりのなかでは<code>#action</code>として参照可能
	 */
	private void toNextStatus(String callPath, Object data, Object userData,
	        Object action) {
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("signalAction", Boolean.TRUE);
		processor.toNextStatusAction(callPath, data, userData, action);
	}

	/**
	 * シグナルを実行します。
	 * 
	 * @param callPath
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりが管理しているDto
	 * @param action
	 *            Actionの文字列、ぶりのなかでは<code>#action</code>として参照可能
	 */
	private void processSignal(String callPath, Object data, String action) {
		DataAccessFactory accessFactory =
		    processor.getDataAccessFactory(callPath);
		Long longKey = getLongKey(data, accessFactory);
		String manyKey = getManyKey(data, accessFactory);
		List<BuriUserEntity> buriUsers =
		    buriUserEntityService.getBuriUserFromPathAndPkey(
		        callPath,
		        longKey,
		        manyKey);
		if (buriUsers != null && buriUsers.size() > 0) {
			for (BuriUserEntity userEntity : buriUsers) {
				IdentityInfo appUserId =
				    new IdentityInfo(userEntity.userIdNum, userEntity.userIdVal);
				Object userData =
				    userUtil.getUserData(
				        accessFactory,
				        userEntity.buriUserId,
				        appUserId);
				toNextStatus(callPath, data, userData, action);
			}
		} else {
			Long cnt =
			    buriStateEntityService.countBuriStateByPathNameAndPkey(
			        callPath,
			        longKey,
			        manyKey);
			if (cnt != 0) {
				toNextStatus(callPath, data, null, action);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void signal(String callPath, Object data) {
		processSignal(callPath, data, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void signal(String callPath, Object data, String action) {
		processSignal(callPath, data, action);
	}

	/**
	 * {@inheritDoc}
	 */
	public void signal(String callPath, List<Object> datas) {
		for (Object data : datas) {
			signal(callPath, data);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void signal(String callPath, List<Object> datas, String action) {
		for (Object data : datas) {
			signal(callPath, data, action);
		}
	}

	/**
	 * プロセッサを返します。
	 * 
	 * @return プロセッサ
	 */
	public BuriAutoSelectProcessor getProcessor() {
		return processor;
	}

	/**
	 * プロセッサを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param processor
	 *            プロセッサ
	 */
	public void setProcessor(BuriAutoSelectProcessor processor) {
		this.processor = processor;
	}

	/**
	 * ぶり固有のテーブル{@code BuriUserEntity}のServiceを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriUserEntity}のService
	 */
	public BuriUserEntityService getBuriUserEntityService() {
		return buriUserEntityService;
	}

	/**
	 * ぶり固有のテーブル{@code BuriUserEntity}のServiceを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriUserEntityService
	 *            ぶり固有のテーブル{@code BuriUserEntity}のService
	 */
	public void setBuriUserEntityService(
	        BuriUserEntityService buriUserEntityService) {
		this.buriUserEntityService = buriUserEntityService;
	}

	/**
	 * ユーザ情報を返します。
	 * 
	 * @return ユーザ情報
	 */
	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	/**
	 * ユーザ情報を登録します。
	 * 
	 * @param userUtil
	 *            ユーザ情報
	 */
	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のServiceを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriStateEntity}のService
	 */
	public BuriStateEntityService getBuriStateEntityService() {
		return buriStateEntityService;
	}

	/**
	 * ぶり固有のテーブル{@code BuriStateEntity}のServiceを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param buriStateEntityService
	 *            ぶり固有のテーブル{@code BuriStateEntity}のService
	 */
	public void setBuriStateEntityService(
	        BuriStateEntityService buriStateEntityService) {
		this.buriStateEntityService = buriStateEntityService;
	}
}
