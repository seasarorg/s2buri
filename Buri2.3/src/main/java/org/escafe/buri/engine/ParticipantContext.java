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

import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 権限主体に関する操作で利用される情報を格納するクラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/08
 */
public class ParticipantContext {

	/**
	 * 対象データ
	 */
	private Object data;

	/**
	 * 対象データをフロー上に一番最初に投入したユーザID
	 */
	private IdentityInfo insertUserId;
	/**
	 * 現在のユーザID
	 */
	private IdentityInfo userId;
	/**
	 * 現在のユーザ情報データ
	 */
	private Object userData;

	/**
	 * データの開始アクティビティの権限主体名
	 */
	private String startParticipantName;
	/**
	 * 現在のコンテキストで対象としている権限主体
	 */
	private String participantName;
	/**
	 * 現在のコンテキストで対象としている権限主体
	 */
	private String participantType;

	/**
	 * プロセスオブジェクト
	 */
	private BuriExecProcess process;
	/**
	 * ユーザコンテキスト情報
	 */
	private BuriUserContext userContext;

	/**
	 * 対象データをフロー上に一番最初に投入したユーザIDを返します。
	 * 
	 * @return 対象データをフロー上に一番最初に投入したユーザID
	 */
	public IdentityInfo getInsertUserId() {
		return insertUserId;
	}

	/**
	 * 対象データをフロー上に一番最初に投入したユーザIDを設定します。
	 * 
	 * @param insertAppUserId 対象データをフロー上に一番最初に投入したユーザID
	 */
	public void setInsertUserId(IdentityInfo insertAppUserId) {
		this.insertUserId = insertAppUserId;
	}

	/**
	 * 現在のユーザIDを返します。
	 * 
	 * @return 現在のユーザID
	 */
	public IdentityInfo getUserId() {
		return userId;
	}

	/**
	 * 現在のユーザIDを設定します。
	 * 
	 * @param appUserId 現在のユーザID
	 */
	public void setUserId(IdentityInfo appUserId) {
		this.userId = appUserId;
	}

	/**
	 * 現在のユーザ情報を返します。
	 * 
	 * @return 現在のユーザ情報
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * 現在のユーザ情報データを設定します。
	 * 
	 * @param userData 現在のユーザ情報
	 */
	public void setUserData(Object userData) {
		this.userData = userData;
	}

	/**
	 * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を返します。
	 * <p>
	 * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが 所属するスイムレーンの権限主体名を返します。
	 * </p>
	 * <p>
	 * ここでの「開始アクティビティ」とは以下のアクティビティを指します。
	 * <ul>
	 * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
	 * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
	 * </ul>
	 * </p>
	 * 
	 * @return データの開始アクティビティの権限主体名
	 */
	public String getStartParticipantName() {
		return startParticipantName;
	}

	/**
	 * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を設定します。
	 * <p>
	 * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが 所属するスイムレーンの権限主体名を返します。
	 * </p>
	 * <p>
	 * ここでの「開始アクティビティ」とは以下のアクティビティを指します。
	 * <ul>
	 * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
	 * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
	 * </ul>
	 * </p>
	 * 
	 * @param startParticipantName データの開始アクティビティの権限主体名
	 */
	public void setStartParticipantName(String startParticipantName) {
		this.startParticipantName = startParticipantName;
	}

	/**
	 * 対象データを返します。
	 * 
	 * @return 対象データ
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 対象データを設定します。
	 * 
	 * @param data 対象データ
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 現在のコンテキストで対象としている権限主体の名前を返します。
	 * 
	 * @return 現在のコンテキストで対象としている権限主体の名前
	 */
	public String getParticipantName() {
		return participantName;
	}

	/**
	 * 現在のコンテキストで対象としている権限主体の名前を設定します。
	 * 
	 * @param participantName 現在のコンテキストで対象としている権限主体の名前
	 */
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	/**
	 * 現在のコンテキストで対象としている権限主体の種別を返します。
	 * 
	 * @return 現在のコンテキストで対象としている権限主体の種別
	 */
	public String getParticipantType() {
		return participantType;
	}

	/**
	 * 現在のコンテキストで対象としている権限主体の種別を設定します。
	 * 
	 * @param participantType 現在のコンテキストで対象としている権限主体の種別
	 */
	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	/**
	 * プロセスオブジェクトを返します。
	 * <p>
	 * XPDLの情報にアクセスすることが出来ます。
	 * </p>
	 * 
	 * @return 対象の{@link BuriExecProcess}
	 */
	public BuriExecProcess getProcess() {
		return process;
	}

	/**
	 * プロセスオブジェクトを設定します。
	 * 
	 * @param process 対象の{@link BuriExecProcess}
	 */
	public void setProcess(BuriExecProcess process) {
		this.process = process;
	}

	/**
	 * ユーザコンテキスト情報を返します。
	 * <p>
	 * Baoのメソッドの引数の値など、実行時のコンテキスト情報にアクセスすることが出来ます。
	 * </p>
	 * 
	 * @return 対象の{@link BuriUserContext}
	 */
	public BuriUserContext getUserContext() {
		return userContext;
	}

	/**
	 * ユーザコンテキスト情報を設定します。
	 * 
	 * @param userContext 対象の{@link BuriUserContext}
	 */
	public void setUserContext(BuriUserContext userContext) {
		this.userContext = userContext;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("data=").append(data);
		buff.append("/insertUserId=").append(insertUserId);
		buff.append("/userId=").append(userId);
		buff.append("/userData=").append(userData);
		buff.append("/startParticipantName=").append(startParticipantName);
		buff.append("/participantName=").append(participantName);
		buff.append("/participantType=").append(participantType);
		buff.append("/process=").append(process);
		buff.append("/userContext=").append(userContext);
		buff.append("]");
		return buff.toString();
	}

}
