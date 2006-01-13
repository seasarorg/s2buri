/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.engine;


/**
 * @author makotan
 *
 */
public class BuriParticipant {
    private Object userData;
    private String userIDVal;
    private Long userIDNum;
    
    public BuriParticipant() {
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userInfo) {
        this.userData = userInfo;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("BuriParticipant");
        buff.append("/userData=").append(userData);
        buff.append("/userIDVal=").append(userIDVal);
        buff.append("/userIDNum=").append(userIDNum);
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
    
}
