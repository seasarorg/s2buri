package org.seasar.buri.dto;


public class BuriTestUserDto {
	public static final String TABLE = "BuriTestUser";

	public static final String userID_ID = "sequence, sequenceName=BuriTestUserID";
	private long userID;
	private String userName = "";
	private String roleName = "";
	private int parentUserID;
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getParentUserID() {
		return parentUserID;
	}

	public void setParentUserID(int parentUserID) {
		this.parentUserID = parentUserID;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/userID=").append(userID);
		buff.append("/userName=").append(userName);
		buff.append("/roleName=").append(roleName);
		buff.append("/parentUserID=").append(parentUserID);
		buff.append("]");
		return buff.toString();
	}
	
}
