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

import java.util.List;

/**
 * 権限主体に関する操作を行うクラスです。
 * <p>
 * 権限チェック処理と、ユーザ情報とユーザIDとの変換を行います。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2005/08/18
 */
public interface ParticipantProvider {

	/**
	 * ユーザ情報オブジェクトに含まれるユーザIDを返します。
	 * <p>
	 * ユーザ情報とユーザIDとの変換メソッドです。
	 * </p>
	 * 
	 * @param userData ユーザ情報オブジェクト
	 * @return ユーザ情報オブジェクトに含まれるユーザID
	 */
	IdentityInfo getUserId(Object userData);

	/**
	 * ユーザIDを元にユーザ情報オブジェクトを取得して返します。
	 * <p>
	 * ユーザ情報とユーザIDとの変換メソッドです。
	 * </p>
	 * 
	 * @param appUserId アプリケーションが管理するユーザID
	 * @return ユーザ情報オブジェクト
	 */
	Object getUserData(IdentityInfo appUserId);

	/**
	 * 指定のコンテキストにおいて権限を持っているかどうかを返します。
	 * <p>
	 * dataに対してactionUserがparticipantType/participantNameの権限を 持っているかどうかをチェックします。
	 * </p>
	 * 
	 * @param context 使用する{@link ParticipantContext}
	 * @return 権限を持っている場合は{@code true}。持っていない場合は{@code false}。
	 */
	boolean hasAuthority(ParticipantContext context);

	/**
	 * 指定のコンテキストにおいて権限を持っているユーザID群を返します。
	 * <p>
	 * dataに対してparticipantType/participantNameの権限を 持っている全ユーザのDTOを取得して返します。
	 * </p>
	 * 
	 * @param context 使用する{@link ParticipantContext}
	 * @return ユーザID群
	 */
	List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context);
}
