/*
 * 作成日: 2006/04/01
 *
 */
package org.seasar.buri.engine.impl;

import java.util.List;

import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.RoleInfo;

public class DefaultParticipantProvider implements ParticipantProvider {

    public boolean isUserInRole(Object userData, String participantName, String participantType) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    public String getUserIDString(Object userData, String participantType) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public Long getUserIDNum(Object userData, String participantType) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public Object getUserData(Long userIDNum, String userIDString) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public boolean hasRoleUser(ParticipantContext context) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    public RoleInfo getSingleUser(ParticipantContext context) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public List getUser(ParticipantContext context) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }


}
