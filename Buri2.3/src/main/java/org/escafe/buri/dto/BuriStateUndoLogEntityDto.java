package org.escafe.buri.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class BuriStateUndoLogEntityDto {
	public static final String TABLE = "BuriStateUndoLog";

//	public static final String stateID_ID = "assigned";;
    public static final String StateUndoLogID_ID = "sequence, sequenceName=BuriStateUndoLogID";
    private long StateUndoLogID;
	private long stateID;
	private Long pathID;
	private Long dataID;
	private Long branchID;
    private String userIDVal = null;
    private Long userIDNum = null;
    private long BTID;
    private long createBTID;
	private Date insertDate = new Timestamp((new Date()).getTime());
    private Date autoRunTime = (new GregorianCalendar(9999,11,31,23,59,59)).getTime();
	private Date processDate = (new GregorianCalendar(9999,11,31,23,59,59)).getTime();
    private Date abortDate = (new GregorianCalendar(9999,11,31,23,59,59)).getTime();
	private int versionNo;

	public BuriStateUndoLogEntityDto() {
	}
    
    public BuriStateUndoLogEntityDto(BuriStateEntityDto dto,long newBTID) {
        stateID = dto.getStateID();
        pathID = dto.getPathID();
        dataID = dto.getDataID();
        branchID = dto.getBranchID();
        userIDVal = dto.getUserIDVal();
        userIDNum = dto.getUserIDNum();
        BTID = dto.getBTID();
        createBTID = newBTID;
        insertDate = dto.getInsertDate();
        autoRunTime = dto.getAutoRunTime();
        processDate = dto.getProcessDate();
        abortDate = dto.getAbortDate();
    }
    
    public BuriStateEntityDto getBuriStateEntityDto() {
        BuriStateEntityDto dto = new BuriStateEntityDto();
        dto.setStateID(stateID);
        dto.setPathID(pathID);
        dto.setDataID(dataID);
        dto.setBranchID(branchID);
        dto.setUserIDVal(userIDVal);
        dto.setUserIDNum(userIDNum);
        dto.setBTID(BTID);
        dto.setInsertDate(insertDate);
        dto.setAutoRunTime(autoRunTime);
        dto.setProcessDate(processDate);
        dto.setAbortDate(abortDate);
        return dto;
    }
	
	public long getStateUndoLogID() {
        return StateUndoLogID;
    }

    public void setStateUndoLogID(long stateUndoLogID) {
        StateUndoLogID = stateUndoLogID;
    }

    public long getStateID() {
		return stateID;
	}

	public void setStateID(long stateID) {
		this.stateID = stateID;
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
	

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
        buff.append("/StateUndoLogID=").append(StateUndoLogID);
		buff.append("/stateID=").append(stateID);
		buff.append("/pathID=").append(pathID);
		buff.append("/dataID=").append(dataID);
		buff.append("/branchID=").append(branchID);
        buff.append("/userIDNum=").append(userIDNum);
        buff.append("/userIDVal=").append(userIDVal);
        buff.append("/BTID=").append(BTID);
        buff.append("/createBTID=").append(createBTID);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/processDate=").append(processDate);
        buff.append("/autoRunTime=").append(autoRunTime);
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

    public Date getAutoRunTime() {
        return autoRunTime;
    }

    public void setAutoRunTime(Date autoRunTime) {
        this.autoRunTime = autoRunTime;
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

    public long getCreateBTID() {
        return createBTID;
    }

    public void setCreateBTID(long createBTID) {
        this.createBTID = createBTID;
    }
	
	
}