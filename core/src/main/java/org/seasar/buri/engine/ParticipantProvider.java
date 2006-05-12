/*
 * ì¬“ú: 2005/08/18
 *
 */
package org.seasar.buri.engine;

public interface ParticipantProvider {
    boolean isUserInRole(Object userData,String participantName);
    String getUserIDString(Object userData);
    Long getUserIDNum(Object userData);

}
