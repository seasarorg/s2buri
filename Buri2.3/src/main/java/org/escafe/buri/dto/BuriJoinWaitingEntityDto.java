package org.escafe.buri.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class BuriJoinWaitingEntityDto {
    public static final String TABLE = "BuriJoinWaiting";

    //	public static final String stateID_ID = "assigned";;
    public static final String waitingID_ID = "sequence, sequenceName=BuriWaitingID";
    private long waitingID;
    private Long pathID;
    private Long dataID;
    private Long branchID;
    private String userIDVal = null;
    private Long userIDNum = null;
    private long BTID;
    private Date insertDate = new Timestamp((new Date()).getTime());
    private Date processDate = (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();
    private Date abortDate = (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();
    private int versionNo;

    public BuriJoinWaitingEntityDto() {
    }

    public long getWaitingID() {
		return waitingID;
	}

	public void setWaitingID(long waitingID) {
		this.waitingID = waitingID;
	}

	public Long getPathID() {
        return pathID;
    }

    public void setPathID(Long pathID) {
        this.pathID = pathID;
    }

    public Long getDataID() {
        return dataID;
    }

    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }

    public Long getBranchID() {
        return branchID;
    }

    public void setBranchID(Long branchID) {
        this.branchID = branchID;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/waitingID=").append(waitingID);
        buff.append("/pathID=").append(pathID);
        buff.append("/dataID=").append(dataID);
        buff.append("/branchID=").append(branchID);
        buff.append("/userIDNum=").append(userIDNum);
        buff.append("/userIDVal=").append(userIDVal);
        buff.append("/BTID=").append(BTID);
        buff.append("/insertDate=").append(insertDate);
        buff.append("/processDate=").append(processDate);
        buff.append("/abortDate=").append(abortDate);
        buff.append("/versionNo=").append(versionNo);
        buff.append("]");
        return buff.toString();
    }

    public Long getUserIDNum() {
        return userIDNum;
    }

    public void setUserIDNum(Long userIDNum) {
        this.userIDNum = userIDNum;
    }

    public String getUserIDVal() {
        return userIDVal;
    }

    public void setUserIDVal(String userIDVal) {
        this.userIDVal = userIDVal;
    }

    public Date getAbortDate() {
        return abortDate;
    }

    public void setAbortDate(Date abortTime) {
        this.abortDate = abortTime;
    }

    public long getBTID() {
        return BTID;
    }

    public void setBTID(long btid) {
        BTID = btid;
    }

}
