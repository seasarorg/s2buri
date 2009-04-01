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

import org.escafe.buri.compiler.BuriCompiler;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * ぶりのエンジンの実装です。
 * <p>
 * 権限管理を行うフローを実施する際に使用するエンジンです。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/06
 */
public class BuriStandardEngineImpl extends BuriSimpleEngineImpl {
	/**
	 * ユーザ情報ユーティリティ
	 */
	private BuriUserUtil userUtil;

	/*
	 * @see
	 * org.escafe.buri.engine.impl.BuriSimpleEngineImpl#readWorkFlowFromResource
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName) {
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.BuriSimpleEngineImpl#readWorkFlowFromResource
	 * (java.lang.String, java.lang.String,
	 * org.escafe.buri.engine.ParticipantProvider)
	 */
	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName, ParticipantProvider provider) {
		readFromResource(workFlowName, resourceName, provider);
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.BuriSimpleEngineImpl#setupUserID(org.escafe
	 * .buri.engine.BuriSystemContext)
	 */
	@Override
	public void setupUserID(BuriSystemContext sysContext) {
		BuriExePackages wPackageObj = selectPackage(sysContext);
		BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
		updateUserInfo(sysContext, wp, wPackageObj);
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.WakanagoEngineImpl#updateUserInfo(org.escafe
	 * .buri.engine.BuriSystemContext,
	 * org.escafe.buri.util.packages.BuriExecProcess,
	 * org.escafe.buri.util.packages.BuriExePackages)
	 */
	@Override
	protected void updateUserInfo(BuriSystemContext sysContext,
	        BuriExecProcess wp, BuriExePackages wPackageObj) {
		super.updateUserInfo(sysContext, wp, wPackageObj);
		IdentityInfo appUserId = sysContext.getAppUserId();
		if ((appUserId.getIdNumber() == null)
		    && (appUserId.getIdString() == null)) {
			return;
		}
		long userID = userUtil.convertBuriUserId(appUserId);
		sysContext.setBuriUserId(userID);
	}

	/**
	 * ユーザ情報ユーティリティを返します。
	 * 
	 * @return ユーザ情報ユーティリティ
	 */
	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	/**
	 * ユーザ情報ユーティリティを登録します。
	 * <p>
	 * DIコンテナに自動的にバインドさせる為のメソッドです。
	 * </p>
	 * 
	 * @param userUtil
	 *            ユーザ情報ユーティリティ
	 */
	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	/*
	 * @see org.escafe.buri.engine.impl.BuriSimpleEngineImpl#getBuriCompiler()
	 */
	@Override
	public BuriCompiler getBuriCompiler() {
		return buriCompiler;
	}

	/*
	 * @see
	 * org.escafe.buri.engine.impl.BuriSimpleEngineImpl#setBuriCompiler(org.
	 * escafe.buri.compiler.BuriCompiler)
	 */
	@Override
	public void setBuriCompiler(BuriCompiler buriCompiler) {
		this.buriCompiler = buriCompiler;
	}
}
