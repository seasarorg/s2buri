/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dto;

import java.util.Date;

import jp.starlogic.util.datetime.DateUtil;

public class BuriWaitingUserEntityDto {
    public static final String TABLE = "BuriWaitingUser";

    public static final String waitingUserID_ID = "sequence, sequenceName=BuriWaitingUserID";
    private long waitingUserID;
    private Long waitingID;
    private Long buriUserID;
    private Date insertDate = new Date();
    private Date deleteDate = DateUtil.getSQLMaxDate();

    public BuriWaitingUserEntityDto() {
    }

    public Long getBuriUserID() {
        return buriUserID;
    }

    public void setBuriUserID(Long buriUserID) {
        this.buriUserID = buriUserID;
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

    public Long getWaitingID() {
		return waitingID;
	}

	public void setWaitingID(Long waitingID) {
		this.waitingID = waitingID;
	}

	public long getWaitingUserID() {
		return waitingUserID;
	}

	public void setWaitingUserID(long waitingUserID) {
		this.waitingUserID = waitingUserID;
	}

	@Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/waitingUserID=").append(waitingUserID);
        buff.append("/waitingID=").append(waitingID);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/insertDate=").append(insertDate);
        buff.append("/deleteDate=").append(deleteDate);
        buff.append("]");
        return buff.toString();
    }

}
