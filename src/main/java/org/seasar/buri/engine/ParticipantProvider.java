/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.engine;

import java.util.List;

public interface ParticipantProvider {
    boolean isUserInRole(Object userData,String participantName);
    String getUserIDString(Object userData);
    Long getUserIDNum(Object userData);
    boolean hasRoleUser(Object userData,String roleType);

    BuriRole getSingleUser(Object userData,String roleType);
    List getUser(Object userData,String roleType);

}
