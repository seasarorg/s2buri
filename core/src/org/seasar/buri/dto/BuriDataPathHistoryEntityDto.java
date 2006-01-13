package org.seasar.buri.dto;

import java.util.Date;
import java.sql.Timestamp;

public class BuriDataPathHistoryEntityDto {
	public static final String TABLE = "BuriDataPathHistory";

	public static final String historyID_ID = "sequence, sequenceName=BurihistoryID";
	private long historyID;
	private Long pathID;
	private Long dataID;
    private String userIDVal;
    private Long userIDNum;
	private Date insertDate = new Timestamp((new Date()).getTime());

	public BuriDataPathHistoryEntityDto() {
	}
	
	public long getHistoryID() {
		return historyID;
	}

	public void setHistoryID(long historyID) {
		this.historyID = historyID;
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
	
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/historyID=").append(historyID);
		buff.append("/pathID=").append(pathID);
		buff.append("/dataID=").append(dataID);
		buff.append("/insertDate=").append(insertDate);
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
	
	
}