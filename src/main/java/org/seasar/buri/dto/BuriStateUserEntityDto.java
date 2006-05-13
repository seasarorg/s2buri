/*
 * çÏê¨ì˙: 2006/05/01
 *
 */
package org.seasar.buri.dto;

public class BuriStateUserEntityDto {
    public static final String TABLE = "BuriStateUser";

    public static final String stateUserID_ID = "sequence, sequenceName=BuriStateUserID";
    private long stateUserID;
    private Long stateID;
    private Long buriUserID;

    public BuriStateUserEntityDto() {
    }
    
    public Long getBuriUserID() {
        return buriUserID;
    }

    public void setBuriUserID(Long buriUserID) {
        this.buriUserID = buriUserID;
    }

    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    public long getStateUserID() {
        return stateUserID;
    }

    public void setStateUserID(long stateUserID) {
        this.stateUserID = stateUserID;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/stateUserID=").append(stateUserID);
        buff.append("/stateID=").append(stateID);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("]");
        return buff.toString();
    }

}
