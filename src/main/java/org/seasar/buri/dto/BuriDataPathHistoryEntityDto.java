package org.seasar.buri.dto;

import java.util.Date;
import java.sql.Timestamp;

public class BuriDataPathHistoryEntityDto {
	public static final String TABLE = "BuriDataPathHistory";

	public static final String historyID_ID = "sequence, sequenceName=BurihistoryID";
	private long historyID;
	private Long pathID;
	private Long dataID;
    private String action;
    private Long buriUserID;
    private long BTID;
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
	
	public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getBTID() {
        return BTID;
    }

    public void setBTID(long btid) {
        BTID = btid;
    }

    public Long getBuriUserID() {
        return buriUserID;
    }

    public void setBuriUserID(Long buriUserID) {
        this.buriUserID = buriUserID;
    }

    public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/historyID=").append(historyID);
		buff.append("/pathID=").append(pathID);
		buff.append("/dataID=").append(dataID);
        buff.append("/action=").append(action);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/BTID=").append(BTID);
		buff.append("/insertDate=").append(insertDate);
		buff.append("]");
		return buff.toString();
	}
	
	
}