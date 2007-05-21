/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dto;

import java.util.Date;

import jp.starlogic.util.datetime.DateUtil;

public class BuriStateUserEntityDto {
    public static final String TABLE = "BuriStateUser";

    public static final String stateUserID_ID = "sequence, sequenceName=BuriStateUserID";
    private long stateUserID;
    private Long stateID;
    private Long buriUserID;
    private Date insertDate = new Date();
    private Date deleteDate = DateUtil.getSQLMaxDate();

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

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/stateUserID=").append(stateUserID);
        buff.append("/stateID=").append(stateID);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/insertDate=").append(insertDate);
        buff.append("/deleteDate=").append(deleteDate);
        buff.append("]");
        return buff.toString();
    }

}
