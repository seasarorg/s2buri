/*
 * 作成日: 2006/03/21
 *
 */
package org.seasar.buri.engine;

import java.util.HashMap;

public class BuriUserContext extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Object getData() {
        return super.get("data");
    }

    public void setData(Object data) {
        super.put("data", data);
    }

    public Object getUserData() {
        return super.get("userData");
    }

    public void setUserData(Object data) {
        super.put("userData", data);
    }

    public String getAction() {
        Object action = super.get("action");
        if (action == null) {
            return null;
        }
        return action.toString();
    }

    public void setAction(Object data) {
        super.put("action", data);
    }

    public void setCallPath(BuriPath callPath) {
        super.put("callPath", callPath);
    }

    public BuriPath getCallPath() {
        return (BuriPath) super.get("callPath");
    }
}
