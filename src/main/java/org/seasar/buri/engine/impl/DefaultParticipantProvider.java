/*
 * 作成日: 2006/04/01
 *
 */
package org.seasar.buri.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.buri.engine.BuriRole;
import org.seasar.buri.engine.ParticipantProvider;

public class DefaultParticipantProvider implements ParticipantProvider {

    public boolean isUserInRole(Object userData, String participantName) {
        return true;
    }

    public String getUserIDString(Object userData) {
        return null;
    }

    public Long getUserIDNum(Object userData) {
        return null;
    }

    public boolean hasRoleUser(Object userData, String roleType) {
        return true;
    }

    public BuriRole getSingleUser(Object userData, String roleType) {
        return new BuriRole();
    }

    public List getUser(Object userData, String roleType) {
        return new ArrayList();
    }

    public boolean hasRoleUser(Object userData, Object argData, String roleName) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    public BuriRole getSingleUser(Object userData, Object argData, String roleName) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public List getUser(Object userData, Object argData, String roleName) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
