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

import org.escafe.buri.dao.BuriStateDao;
import org.escafe.buri.dao.BuriUserDao;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriUserEntityDto;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriSignal;
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
	 * ぶり固有のテーブル{@code BuriUser}のDao
	 */
	private BuriUserDao userDao;
	/**
	 * ぶり固有のテーブル{@code BuriState}のDao
	 */
	private BuriStateDao stateDao;

	/**
	 * 指定されたDtoの主キーを返します。
	 * <p>
	 * 指定したデータの主キーがLong型の単一項目の場合、に使用します。
	 * </p>
	 * 
	 * @param data ぶりに管理しているDto
	 * @param accessFactory 使用する{@link DataAccessFactory}
	 * @return データの主キー
	 */
	private Long getLongKey(Object data, DataAccessFactory accessFactory) {
		Long longKey = null;
		DataAccessUtil accessUtil = accessFactory.getDataAccessUtil(data
				.getClass());
		// 主キーのクラスから判定
		if (accessUtil instanceof DataAccessUtilLongKey) {
			DataAccessUtilLongKey longKeyUtil = (DataAccessUtilLongKey) accessUtil;
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
	 * @param data ぶりで管理しているDto
	 * @param accessFactory 使用する{@link DataAccessFactory}
	 * @return データの主キー
	 */
	private String getManyKey(Object data, DataAccessFactory accessFactory) {
		String manyKey = null;
		DataAccessUtil accessUtil = accessFactory.getDataAccessUtil(data
				.getClass());
		if (accessUtil instanceof DataAccessUtilManyKey) {
			DataAccessUtilManyKey manyKeyUtil = (DataAccessUtilManyKey) accessUtil;
			manyKey = manyKeyUtil.getKey(data);
		}
		return manyKey;
	}

	/**
	 * 状態を次に進めます。
	 * 
	 * @param callPath パッケージ名.プロセス名[.アクティビティ名]
	 * @param data ぶりが管理しているDto
	 * @param userData 権限管理に使用するユーザ情報のDto
	 * @param action Actionの文字列、ぶりのなかでは<code>#action</code>として参照可能
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
	 * @param callPath パッケージ名.プロセス名[.アクティビティ名]
	 * @param data ぶりが管理しているDto
	 * @param action Actionの文字列、ぶりのなかでは<code>#action</code>として参照可能
	 */
	private void processSignal(String callPath, Object data, String action) {
		DataAccessFactory accessFactory = processor
				.getDataAccessFactory(callPath);
		Long longKey = getLongKey(data, accessFactory);
		String manyKey = getManyKey(data, accessFactory);
		List<BuriUserEntityDto> buriUsers = userDao.getBuriUserFromPathAndPkey(
				callPath, longKey, manyKey);
		if (buriUsers != null && buriUsers.size() > 0) {
			for (BuriUserEntityDto userEntity : buriUsers) {
				IdentityInfo appUserId = new IdentityInfo(userEntity
						.getUserIDNum(), userEntity.getUserIDVal());
				Object userData = userUtil.getUserData(accessFactory,
						userEntity.getBuriUserID(), appUserId);

				toNextStatus(callPath, data, userData, action);
			}
		} else {
			int cnt = stateDao.countBuriStateByPathNameAndPkey(callPath,
					longKey, manyKey);
			if (cnt != 0) {
				toNextStatus(callPath, data, null, action);
			}
		}
	}
	/*
	 * @see org.escafe.buri.engine.processor.util.BuriSignal#signal(java.lang.String, java.lang.Object)
	 */
	public void signal(String callPath, Object data) {
		processSignal(callPath, data, null);
	}
	/*
	 * @see org.escafe.buri.engine.processor.util.BuriSignal#signal(java.lang.String, java.lang.Object, java.lang.String)
	 */
	public void signal(String callPath, Object data, String action) {
		processSignal(callPath, data, action);
	}
	/*
	 * @see org.escafe.buri.engine.processor.util.BuriSignal#signal(java.lang.String, java.util.List)
	 */
	public void signal(String callPath, List<Object> datas) {
		for (Object data : datas) {
			signal(callPath, data);
		}
	}
	/*
	 * @see org.escafe.buri.engine.processor.util.BuriSignal#signal(java.lang.String, java.util.List, java.lang.String)
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
	 * @param processor プロセッサ
	 */
	public void setProcessor(BuriAutoSelectProcessor processor) {
		this.processor = processor;
	}
	/**
	 * ぶり固有のテーブル{@code BuriUser}のDaoを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriUser}のDao
	 */
	public BuriUserDao getUserDao() {
		return userDao;
	}
	/**
	 * ぶり固有のテーブル{@code BuriUser}のDaoを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param userDao ぶり固有のテーブル{@code BuriUser}のDao
	 */
	public void setUserDao(BuriUserDao userDao) {
		this.userDao = userDao;
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
	 * @param userUtil ユーザ情報
	 */
	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}
	/**
	 * ぶり固有のテーブル{@code BuriState}のDaoを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriState}のDao
	 */
	public BuriStateDao getStateDao() {
		return stateDao;
	}
	/**
	 * ぶり固有のテーブル{@code BuriState}のDaoを登録します。
	 * <p>
	 * コンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param stateDao ぶり固有のテーブル{@code BuriState}のDao
	 */
	public void setStateDao(BuriStateDao stateDao) {
		this.stateDao = stateDao;
	}

}
