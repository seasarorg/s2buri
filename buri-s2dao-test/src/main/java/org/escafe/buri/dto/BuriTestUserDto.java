package org.escafe.buri.dto;

import org.seasar.nadejako.annotation.NakoProperties;
import org.seasar.nadejako.annotation.NakoProperty;

@NakoProperties( {
    @NakoProperty(name = "あいでぃー", property = "userId"),
    @NakoProperty(name = "ユーザ名", property = "userName"),
    @NakoProperty(name = "権限名", property = "roleName"),
    @NakoProperty(name = "上司", property = "parentUserId") })
public class BuriTestUserDto {
	public static final String TABLE = "BURI_TEST_USER";

	public static final String userId_ID =
	    "sequence, sequenceName=BURI_TEST_USER_SEQ";

	private Long userId;

	private String userName = "";

	private String roleName = "";

	private Long parentUserId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/userId=").append(userId);
		buff.append("/userName=").append(userName);
		buff.append("/roleName=").append(roleName);
		buff.append("/parentUserId=").append(parentUserId);
		buff.append("]");
		return buff.toString();
	}
}
