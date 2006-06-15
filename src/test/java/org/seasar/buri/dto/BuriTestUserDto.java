/*
 * çÏê¨ì˙: 2006/06/14
 *
 */
package org.seasar.buri.dto;

public class BuriTestUserDto {
    public static final String TABLE = "BuriTestUser";

    // public static final String UserID_ID = "sequence,sequenceName=BuriTestUserID";
    private long UserID;

    private String UserName;

    private String RoleName;

    private Long ParentUserID;

    public Long getParentUserID() {
        return ParentUserID;
    }

    public void setParentUserID(Long parentUserID) {
        ParentUserID = parentUserID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

}
