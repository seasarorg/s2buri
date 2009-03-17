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

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;

/**
 * フローを実行する上で必要なシステムのコンテキスト情報を保持するクラスです。
 * <p>
 * ぶりのプロセッサはこのクラスに必要な情報を与えて、ぶりのエンジンに引き渡します。
 * そしてぶりのエンジンは、受け取ったこのクラスを参照することでその動作を決定します。
 * その為、このクラスのオブジェクト生成はぶりのエンジン
 * {@link WakanagoEngine#createSystemContext(String, BuriUserContext)}
 * を使用する事をお勧めします。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/21
 */
public class BuriSystemContext {

	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * ユーザコンテキスト
	 * <p>
	 * ワークフロー実行時に使用する{@link BuriUserContext}
	 * </p>
	 */
	private BuriUserContext userContext;
	/**
	 * ぶりが管理するデータのDtoのクラス
	 */
	private Class<?> targetDtoClass;
	/**
	 * 処理対象となるパスを表す{@link BuriPath}
	 */
	private BuriPath callPath;
	/**
	 * 現在のコンテキストで対象としているデータID
	 * <p>
	 * 実行対象となるぶり固有のテーブル{@code BuriData}のID
	 * </p>
	 */
	private Long dataID;
	/**
	 * 使用する{@link S2Container}
	 */
	private S2Container container;
	/**
	 * ID情報
	 */
	private IdentityInfo appUserId;
	/**
	 * ぶり固有のテーブル{@code BuriUser}のID
	 */
	private Long buriUserID;
	/**
	 * ぶり固有のテーブル{@code BuriState}のID
	 */
	private Long statusID;
	/**
	 * 権限管理をする際の権限名
	 */
	private String startParticipantName;
	/**
	 * 実行可能なアクティビティのリスト
	 */
	private List<String> activityNames;
	/**
	 * 後処理として実行するメソッド
	 */
	private List<String> afterCallMethods = new ArrayList<String>();
	/**
	 * 実行時例外
	 */
	private RuntimeException exception;

	/**
	 * 現在のコンテキスト上でのメインのDIコンテナを返します。
	 * 
	 * @return S2コンテナ
	 */
	public S2Container getContainer() {
		return container;
	}

	/**
	 * 現在のコンテキスト上でのメインのDIコンテナを設定します。
	 * 
	 * @param container S2コンテナ
	 */
	public void setContainer(S2Container container) {
		this.container = container;
	}

	/**
	 * ユーザコンテキストを返します。
	 * 
	 * @return ユーザコンテキスト
	 */
	public BuriUserContext getUserContext() {
		return userContext;
	}

	/**
	 * ユーザコンテキストを設定します。
	 * 
	 * @param userContext ユーザコンテキスト
	 */
	public void setUserContext(BuriUserContext userContext) {
		this.userContext = userContext;
	}

	/**
	 * 処理対象のアクティビティのパスを返します。
	 * 
	 * @return 処理対象のアクティビティのパス
	 */
	public BuriPath getCallPath() {
		return callPath;
	}

	/**
	 * 処理対象のアクティビティのパスを設定します。
	 * 
	 * @param callPath 処理対象を示す{@link BuriPath}
	 */
	public void setCallPath(BuriPath callPath) {
		this.callPath = callPath;
	}

	/**
	 * 現在のコンテキストで対象としているデータIDを返します。
	 * 
	 * @return 現在のコンテキストで対象としているデータID
	 */
	public Long getDataID() {
		return dataID;
	}

	/**
	 * 現在のコンテキストで対象としているデータIDを設定します。
	 * 
	 * @param dataID 現在のコンテキストで対象としているデータID
	 */
	public void setDataID(Long dataID) {
		this.dataID = dataID;
	}

	/**
	 * 現在のコンテキストで対象としているデータのステータスIDを返します。
	 * 
	 * @return 現在のコンテキストで対象としているデータのステータスID
	 */
	public Long getStatusID() {
		return statusID;
	}

	/**
	 * 現在のコンテキストで対象としているデータのステータスIDを設定します。
	 * 
	 * @param statusID データのステータスID
	 */
	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}

	/**
	 * 現在のコンテキストで対象としているデータのDTOクラス型を返します。
	 * 
	 * @return 現在のコンテキストで対象としているデータのDTOクラス型
	 */
	public Class<?> getTargetDtoClass() {
		return targetDtoClass;
	}

	/**
	 * 現在のコンテキストで対象としているデータのDTOクラス型を設定します。
	 * 
	 * @param targetClass データのDTOクラス型
	 */
	public void setTargetDtoClass(Class<?> targetClass) {
		this.targetDtoClass = targetClass;
	}

	/**
	 * アプリケーション側で定義されるユーザIDを返します。
	 * 
	 * @return アプリケーション側で定義されるユーザID
	 */
	public IdentityInfo getAppUserId() {
		return appUserId;
	}

	/**
	 * アプリケーション側で定義されるユーザIDを設定します。
	 * 
	 * @param appUserId アプリケーション側で定義されるユーザID
	 */
	public void setAppUserId(IdentityInfo appUserId) {
		this.appUserId = appUserId;
	}

	/**
	 * ぶり側で定義されるユーザIDを返します。
	 * <p>
	 * ぶりではアプリケーション側で定義されているユーザをぶり側でも独自に管理します。 本メソッドではこの独自管理上のIDを返します。
	 * </p>
	 * 
	 * @return ぶり側で定義されるユーザID
	 */
	public Long getBuriUserID() {
		return buriUserID;
	}

	/**
	 * ぶり側で定義されるユーザIDを設定します。
	 * <p>
	 * ぶりではアプリケーション側で定義されているユーザをぶり側でも独自に管理します。 本メソッドではこの独自管理上のIDを設定します。
	 * </p>
	 * 
	 * @param buriUserID
	 */
	public void setBuriUserID(Long buriUserID) {
		this.buriUserID = buriUserID;
	}

	/**
	 * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を返します。
	 * <p>
	 * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが 所属するスイムレーンの権限主体名を返します。
	 * </p>
	 * <p>
	 * 「開始アクティビティ」とは以下のアクティビティを指します。
	 * <ul>
	 * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
	 * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
	 * </ul>
	 * </p>
	 * 
	 * @return アクティビティの権限主体名
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
	 * 「開始アクティビティ」とは以下のアクティビティを指します。
	 * <ul>
	 * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
	 * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
	 * </ul>
	 * </p>
	 * 
	 * @param startParticipantName
	 */
	public void setStartParticipantName(String startParticipantName) {
		this.startParticipantName = startParticipantName;
	}

	/**
	 * 実行時例外を返します。
	 * <p>
	 * {@code WakanagoProcess}での例外処理の実現で使用されます。
	 * </p>
	 * 
	 * @return 実行時例外
	 */
	public RuntimeException getException() {
		return exception;
	}

	/**
	 * 実行時例外を設定します。
	 * <p>
	 * {@code WakanagoProcess}での例外処理の実現で使用されます。
	 * </p>
	 * 
	 * @param exception 実行時例外
	 */
	public void setException(RuntimeException exception) {
		this.exception = exception;
	}

	/**
	 * アクティビティ名群を返します
	 * <p>
	 * BAOのアノテーションで指定したアクティビティ名が含まれます。
	 * </p>
	 * 
	 * @return アクティビティ名のリスト
	 */
	public List<String> getActivityNames() {
		return activityNames;
	}

	/**
	 * アクティビティ名群を設定します。
	 * <p>
	 * BAOのアノテーションで指定したアクティビティ名が含まれます。
	 * </p>
	 * 
	 * @param actNames アクティビティ名のリスト
	 */
	public void setActivityNames(List<String> actNames) {
		this.activityNames = actNames;
	}

	/**
	 * 後処理として実行するメソッドのメソッド名を返します。
	 * 
	 * @return 後処理として実行するメソッドのメソッド名
	 */
	public List<String> getAfterCallMethods() {
		return afterCallMethods;
	}

	/**
	 * 後処理として実行するメソッドのメソッド名のリストを登録します。
	 * 
	 * @param afterCallMethods 後処理として実行するメソッドのメソッド名のリスト
	 */
	public void setAfterCallMethods(List<String> afterCallMethods) {
		this.afterCallMethods = afterCallMethods;
	}

	/**
	 * 後処理として実行するメソッドのメソッド名を追加します。
	 * 
	 * @param afterCallName 後処理として実行するメソッドのメソッド名
	 */
	public void addAfterCallMethods(String afterCallName) {
		this.afterCallMethods.add(afterCallName);
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("userContext=").append(userContext);
		buff.append("/callPath=").append(callPath);
		buff.append("/dataID=").append(dataID);
		buff.append("/buriUserID=").append(buriUserID);
		buff.append("/appUserId=").append(appUserId);
		buff.append("/statusID=").append(statusID);
		buff.append("/targetDtoClass=").append(targetDtoClass);
		buff.append("/startParticipantName=").append(startParticipantName);
		buff.append("/activityNames=").append(activityNames);
		buff.append("/afterCallMethods=").append(afterCallMethods);
		buff.append("/exception=").append(exception);
		buff.append("]");
		return buff.toString();
	}

}
