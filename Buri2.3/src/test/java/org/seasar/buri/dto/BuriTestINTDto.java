package org.seasar.buri.dto;


public class BuriTestINTDto {
	public static final String TABLE = "BuriTestINT";

	public static final String testID_ID = "sequence, sequenceName=BuriTestINTID";
	private long testID;
	private String value = "";
	private int versionNo;
	
	public long getTestID() {
		return testID;
	}

	public void setTestID(long testID) {
		this.testID = testID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/testID=").append(testID);
		buff.append("/value=").append(value);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
	
}
