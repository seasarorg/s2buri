package org.escafe.buri.dto;

import org.seasar.nadejako.annotation.NakoProperties;
import org.seasar.nadejako.annotation.NakoProperty;

@NakoProperties({
	@NakoProperty(name="あいでぃー", property="userID"),
	@NakoProperty(name="ユーザ名", property="userName"),
	@NakoProperty(name="権限名", property="roleName"),
	@NakoProperty(name="上司", property="parentUserID")
	})
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

	@Override
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
