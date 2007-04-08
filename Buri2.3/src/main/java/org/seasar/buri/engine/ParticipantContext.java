/*
 * 作成日: 2006/06/08
 *
 */
package org.seasar.buri.engine;

/**
 * 権限主体に関する操作で利用される情報を格納するクラスです。
 * 
 * @author $Author$
 */
public class ParticipantContext {

    private IdentityInfo startUserId;
    private IdentityInfo currentUserId;

    private Object data;
    private String participantName;
    private String participantType;

    /**
     * 現在のユーザIDを返します。
     * 
     * @return
     */
    public IdentityInfo getCurrentUserId() {
        return currentUserId;
    }

    /**
     * 現在のユーザIDを設定します。
     * 
     * @param currentAppUserId
     */
    public void setCurrentUserId(IdentityInfo currentAppUserId) {
        this.currentUserId = currentAppUserId;
    }

    /**
     * 対象データをフロー上に一番最初に投入したユーザIDを返します。
     * 
     * @return
     */
    public IdentityInfo getStartUserId() {
        return startUserId;
    }

    /**
     * 対象データをフロー上に一番最初に投入したユーザIDを設定します。
     * 
     * @param startAppUserId
     */
    public void setStartUserId(IdentityInfo startAppUserId) {
        this.startUserId = startAppUserId;
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

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("startUserId=").append(startUserId);
        buff.append("/currentUserId=").append(currentUserId);
        buff.append("/data=").append(data);
        buff.append("/participantName=").append(participantName);
        buff.append("/participantType=").append(participantType);
        buff.append("]");
        return buff.toString();
    }

}
