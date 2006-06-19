/*
 * çÏê¨ì˙: 2006/03/21
 *
 */
package org.seasar.buri.engine;

import java.util.HashMap;
import java.util.Stack;

import org.seasar.framework.container.S2Container;

public class BuriSystemContext extends HashMap {
    
    private static final long serialVersionUID = 1L;
    
    private Class tgtClass;
    private BuriPath callPath;
    private Stack pathStack;
    private Long dataID;
    private Long userID;
    private Long userPkeyNum;
    private String userPkeyVal;
    private Long statusID;
    private Long BTID;
    private long buriExecMode = RELEASED;
    public static long UNDER_TEST = 1;
    public static long RELEASED = 0;
    private S2Container container;
    private String startRoleName;
    private RuntimeException ex;

    public S2Container getContainer() {
        return container;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public BuriUserContext getUserContext() {
        return (BuriUserContext)super.get("userContext");
    }
    public void setUserContext(BuriUserContext context) {
        super.put("userContext",context);
    }
    public BuriPath getCallPath() {
        return callPath;
    }
    public void setCallPath(BuriPath callPath) {
        this.callPath = callPath;
    }
    public Stack getPathStack() {
        return pathStack;
    }
    public void setPathStack(Stack pathStack) {
        this.pathStack = pathStack;
    }
    
    public Long getDataID() {
        return dataID;
    }
    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public Long getStatusID() {
        return statusID;
    }
    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }
    public Long getBTID() {
        return BTID;
    }
    public void setBTID(Long btid) {
        BTID = btid;
    }
    public long getBuriExecMode() {
        return buriExecMode;
    }
    public void setBuriExecMode(long buriExecMode) {
        this.buriExecMode = buriExecMode;
    }
    public Class getTgtClass() {
        return tgtClass;
    }
    public void setTgtClass(Class tgtClass) {
        this.tgtClass = tgtClass;
    }
    public Long getUserPkeyNum() {
        return userPkeyNum;
    }
    public void setUserPkeyNum(Long userPkeyNum) {
        this.userPkeyNum = userPkeyNum;
    }
    public String getUserPkeyVal() {
        return userPkeyVal;
    }
    public void setUserPkeyVal(String userPkeyVal) {
        this.userPkeyVal = userPkeyVal;
    }
    public String getStartRoleName() {
        return startRoleName;
    }
    public void setStartRoleName(String startRoleName) {
        this.startRoleName = startRoleName;
    }
    public RuntimeException getException() {
        return ex;
    }
    public void setException(RuntimeException ex) {
        this.ex = ex;
    }
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append(super.toString());
        buff.append("/callPath=").append(callPath);
        buff.append("/pathStack=").append(pathStack);
        buff.append("/dataID=").append(dataID);
        buff.append("/userID=").append(userID);
        buff.append("/userPkeyNum=").append(userPkeyNum);
        buff.append("/userPkeyVal=").append(userPkeyVal);
        buff.append("/statusID=").append(statusID);
        buff.append("/BTID=").append(BTID);
        buff.append("/buriExecMode=").append(buriExecMode);
        buff.append("/tgtClass=").append(tgtClass);
        buff.append("/startRoleName=").append(startRoleName);
        buff.append("/ex=").append(ex);
        buff.append("]");
        return buff.toString();
    }
    
}
