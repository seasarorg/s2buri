/*
 * �쐬��: 2006/03/21
 *
 */
package org.seasar.buri.engine;

import java.util.HashMap;

public class BuriUserContext extends HashMap{
    
    private static final long serialVersionUID = 1L;

    public Object getData() {
        return super.get("data");
    }
    public void setData(Object data) {
        super.put("data",data);
    }
    public Object getUserData() {
        return super.get("UserData");
    }
    public void setUserData(Object data) {
        super.put("UserData",data);
    }
    public String getAction() {
        return super.get("action").toString();
    }
    public void setAction(Object data) {
        super.put("action",data);
    }
    
}