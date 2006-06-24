package org.seasar.buri.dto;


public class BuriPathEntityDto {
	public static final String TABLE = "BuriPath";

	public static final String pathID_ID = "sequence, sequenceName=BuriPathID";
	private long pathID;
	private String pathName;
	private String realPathName;

	public BuriPathEntityDto() {
	}
	
	public long getPathID() {
		return pathID;
	}

	public void setPathID(long pathID) {
		this.pathID = pathID;
	}
	
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	public String getRealPathName() {
		return realPathName;
	}

	public void setRealPathName(String realPathName) {
		this.realPathName = realPathName;
	}
	

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/pathID=").append(pathID);
		buff.append("/pathName=").append(pathName);
		buff.append("/realPathName=").append(realPathName);
		buff.append("]");
		return buff.toString();
	}
	
	
}