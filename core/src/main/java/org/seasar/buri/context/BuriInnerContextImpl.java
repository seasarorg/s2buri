/*
 * ì¬“ú: 2005/10/08
 *
 */
package org.seasar.buri.context;

import java.util.HashMap;

public class BuriInnerContextImpl extends HashMap implements BuriInnerContext{

    private static final long serialVersionUID = 6968540754685952416L;

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
    
}
