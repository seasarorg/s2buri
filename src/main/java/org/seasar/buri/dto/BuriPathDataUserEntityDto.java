/*
 * çÏê¨ì˙: 2006/05/18
 *
 */
package org.seasar.buri.dto;

public class BuriPathDataUserEntityDto {
    public static final String TABLE = "BuriPathDataUser";

    private long pathID;
    private String pathName;
    private String realPathName;
    private Long pathType;
    private Long pkeyNum;
    private String pkeyVal;
    private String dataType;
    private long dataID;
    private long stateID;
    private long buriUserID;
    private String userIDVal;
    private Long userIDNum;
    
    public long getDataID() {
        return dataID;
    }
    public void setDataID(long dataID) {
        this.dataID = dataID;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
    public Long getPathType() {
        return pathType;
    }
    public void setPathType(Long pathType) {
        this.pathType = pathType;
    }
    public Long getPkeyNum() {
        return pkeyNum;
    }
    public void setPkeyNum(Long pkeyNum) {
        this.pkeyNum = pkeyNum;
    }
    public String getPkeyVal() {
        return pkeyVal;
    }
    public void setPkeyVal(String pkeyVal) {
        this.pkeyVal = pkeyVal;
    }
    public String getRealPathName() {
        return realPathName;
    }
    public void setRealPathName(String realPathName) {
        this.realPathName = realPathName;
    }
    public long getStateID() {
        return stateID;
    }
    public void setStateID(long stateID) {
        this.stateID = stateID;
    }
    public long getBuriUserID() {
        return buriUserID;
    }
    public void setBuriUserID(long userID) {
        this.buriUserID = userID;
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
        buff.append("/pathID=").append(pathID);
        buff.append("/pathName=").append(pathName);
        buff.append("/realPathName=").append(realPathName);
        buff.append("/pathType=").append(pathType);
        buff.append("/pkeyNum=").append(pkeyNum);
        buff.append("/pkeyVal=").append(pkeyVal);
        buff.append("/dataType=").append(dataType);
        buff.append("/dataID=").append(dataID);
        buff.append("/stateID=").append(stateID);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/userIDNum=").append(userIDNum);
        buff.append("/userIDVal=").append(userIDVal);
        buff.append("]");
        return buff.toString();
    }
    
}
