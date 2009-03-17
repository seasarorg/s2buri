/*
 * 作成日: 2006/06/08
 *
 */
package org.escafe.buri.engine;

import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 権限主体に関する操作で利用される情報を格納するクラスです。
 * 
 * @author $Author: nobeans $
 */
public class ParticipantContext {

    private Object data;

    private IdentityInfo insertUserId;
    private IdentityInfo userId;
    private Object userData;

    private String startParticipantName;
    private String participantName;
    private String participantType;

    private BuriExecProcess process;
    private BuriUserContext userContext;

    /**
     * 対象データをフロー上に一番最初に投入したユーザIDを返します。
     * 
     * @return
     */
    public IdentityInfo getInsertUserId() {
        return insertUserId;
    }

    /**
     * 対象データをフロー上に一番最初に投入したユーザIDを設定します。
     * 
     * @param insertAppUserId
     */
    public void setInsertUserId(IdentityInfo insertAppUserId) {
        this.insertUserId = insertAppUserId;
    }

    /**
     * 現在のユーザIDを返します。
     * 
     * @return
     */
    public IdentityInfo getUserId() {
        return userId;
    }

    /**
     * 現在のユーザIDを設定します。
     * 
     * @param appUserId
     */
    public void setUserId(IdentityInfo appUserId) {
        this.userId = appUserId;
    }

    /**
     * 現在のユーザ情報を返します。
     * 
     * @return
     */
    public Object getUserData() {
        return userData;
    }

    /**
     * 現在のユーザ情報データを設定します。
     * 
     * @param userData
     */
    public void setUserData(Object userData) {
        this.userData = userData;
    }

    /**
     * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を返します。
     * <p>
     * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが
     * 所属するスイムレーンの権限主体名を返します。
     * </p>
     * <p>
     * ここでの「開始アクティビティ」とは以下のアクティビティを指します。
     * <ul>
     * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
     * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
     * </ul>
     * </p>
     * @return
     */
    public String getStartParticipantName() {
        return startParticipantName;
    }

    /**
     * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を設定します。
     * <p>
     * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが
     * 所属するスイムレーンの権限主体名を返します。
     * </p>
     * <p>
     * ここでの「開始アクティビティ」とは以下のアクティビティを指します。
     * <ul>
     * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
     * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
     * </ul>
     * </p>
     * @param startParticipantName
     */
    public void setStartParticipantName(String startParticipantName) {
        this.startParticipantName = startParticipantName;
    }

    /**
     * 対象データを返します。
     * 
     * @return
     */
    public Object getData() {
        return data;
    }

    /**
     * 対象データを設定します。
     * 
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 現在のコンテキストで対象としている権限主体の名前を返します。
     * 
     * @return
     */
    public String getParticipantName() {
        return participantName;
    }

    /**
     * 現在のコンテキストで対象としている権限主体の名前を設定します。
     * 
     * @param participantName
     */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /**
     * 現在のコンテキストで対象としている権限主体の種別を返します。
     * 
     * @return
     */
    public String getParticipantType() {
        return participantType;
    }

    /**
     * 現在のコンテキストで対象としている権限主体の種別を設定します。
     * 
     * @param participantType
     */
    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    /**
     * プロセスオブジェクトを返します。
     * <p>
     * XPDLの情報にアクセスすることが出来ます。
     * </p>
     * @return
     */
    public BuriExecProcess getProcess() {
        return process;
    }

    /**
     * プロセスオブジェクトを設定します。
     * 
     * @param process
     */
    public void setProcess(BuriExecProcess process) {
        this.process = process;
    }

    /**
     * ユーザコンテキスト情報を返します。
     * <p>
     * Baoのメソッドの引数の値など、実行時のコンテキスト情報にアクセスすることが出来ます。
     * </p>
     * @return
     */
    public BuriUserContext getUserContext() {
        return userContext;
    }

    /**
     * ユーザコンテキスト情報を設定します。
     * 
     * @param userContext
     */
    public void setUserContext(BuriUserContext userContext) {
        this.userContext = userContext;
    }

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
