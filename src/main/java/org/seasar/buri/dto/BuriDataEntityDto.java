package org.seasar.buri.dto;


public class BuriDataEntityDto {
	public static final String TABLE = "BuriData";

//	public static final String dataID_ID = "assigned";;
    public static final String dataID_ID = "sequence, sequenceName=BuriDataID";
	private long dataID;
	private String pkeyVal;
	private Long pkeyNum;
    private Long insertUserID;
	private String dataType;

	public BuriDataEntityDto() {
	}
	
	public long getDataID() {
		return dataID;
	}

	public void setDataID(long dataID) {
		this.dataID = dataID;
	}
	
	public String getPkeyVal() {
		return pkeyVal;
	}

	public void setPkeyVal(String pkeyVal) {
		this.pkeyVal = pkeyVal;
	}
	
	public Long getPkeyNum() {
		return pkeyNum;
	}

	public void setPkeyNum(Long pkeyNum) {
		this.pkeyNum = pkeyNum;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

    public Long getInsertUserID() {
        return insertUserID;
    }

    public void setInsertUserID(Long insertUserID) {
        this.insertUserID = insertUserID;
    }
	

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/dataID=").append(dataID);
		buff.append("/pkeyVal=").append(pkeyVal);
		buff.append("/pkeyNum=").append(pkeyNum);
		buff.append("/dataType=").append(dataType);
        buff.append("/insertUserID=").append(insertUserID);
		buff.append("]");
		return buff.toString();
	}
	
	
}