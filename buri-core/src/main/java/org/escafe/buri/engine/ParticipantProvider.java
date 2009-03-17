/*
 * 作成日: 2005/08/18
 *
 */
package org.escafe.buri.engine;

import java.util.List;

/**
 * 権限主体に関する操作を行うクラスです。
 * <p>
 * 権限チェック処理と、ユーザ情報とユーザIDとの変換を行います。
 * </p>
 * 
 * @author $Author: nobeans $
 */
public interface ParticipantProvider {

    /**
     * ユーザ情報オブジェクトに含まれるユーザIDを返します。
     * <p>
     * ユーザ情報とユーザIDとの変換メソッドです。
     * </p>
     * @param userData
     * @return
     */
    IdentityInfo getUserId(Object userData);

    /**
     * ユーザIDを元にユーザ情報オブジェクトを取得して返します。
     * <p>
     * ユーザ情報とユーザIDとの変換メソッドです。
     * </p>
     * @param appUserId
     * @return
     */
    Object getUserData(IdentityInfo appUserId);

    /**
     * 指定のコンテキストにおいて権限を持っているかどうかを返します。
     * <p>
     * dataに対してactionUserがparticipantType/participantNameの権限を
     * 持っているかどうかをチェックします。
     * </p>
     * @param context
     * @return 権限を持っている場合は{@code true}。持っていない場合は{@code false}。
     */
    boolean hasAuthority(ParticipantContext context);

    /**
     * 指定のコンテキストにおいて権限を持っているユーザID群を返します。
     * <p>
     * dataに対してparticipantType/participantNameの権限を
     * 持っている全ユーザのDTOを取得して返します。
     * </p>
     * @param context
     * @return
     */
    List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context);
}
