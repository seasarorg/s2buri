/*
 * ì¬“ú: 2005/08/26
 *
 */
package org.seasar.buri.engine.invoker.impl;

import org.seasar.buri.bao.test.UserData;
import org.seasar.buri.engine.ParticipantProvider;

public class TestParticipantProviderImpl implements ParticipantProvider {

    public boolean isUserInRole(Object userData, String participantName) {
        if(userData==null) {
            return true;
        }
        String userStr;
        if(userData instanceof UserData) {
            userStr = ((UserData)userData).getUser();
        } else {
            userStr = userData.toString();
        }
        int idx = userStr.indexOf(participantName);
        return idx > 0;
    }

    public String getUserIDString(Object userData) {
        if(userData==null) {
            return "";
        }
        String userStr;
        if(userData instanceof UserData) {
            userStr = ((UserData)userData).getUser();
        } else {
            userStr = userData.toString();
        }
        return userStr;
    }

    public Long getUserIDNum(Object userData) {
        return null;
    }

}
