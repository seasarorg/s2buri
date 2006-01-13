/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;


import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.ParticipantDocument.Participant;

public interface ParticipantUtil {
    Participant[] getParticipantArray(BuriPath buriPath,String name);
    Participant getParticipant(BuriPath buriPath,String name);
    List findParticipant(BuriPath buriPath,List activityList,BuriParticipant participant);
    
    List getParticipantList(BuriPath buriPath);
    Participant getParticipantFromName(BuriPath buriPath,String name);
    
}
