/*
 * 作成日: 2006/05/01
 *
 */
package org.seasar.buri.dto;

public class BuriUserEntityDto {
    public static final String TABLE = "BuriUser";

    public static final String buriUserID_ID = "sequence, sequenceName=BuriUserID";
    private long buriUserID;
    private String userIDVal;
    private Long userIDNum;

    public BuriUserEntityDto() {
    }
    
    public long getBuriUserID() {
        return buriUserID;
    }

    public void setBuriUserID(long buriUserID) {
        this.buriUserID = buriUserID;
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

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/userIDVal=").append(userIDVal);
        buff.append("/userIDNum=").append(userIDNum);
        buff.append("]");
        return buff.toString();
    }

}
