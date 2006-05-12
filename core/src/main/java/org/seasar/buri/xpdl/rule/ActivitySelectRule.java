/*
 * çÏê¨ì˙: 2005/06/02
 *
 */
package org.seasar.buri.xpdl.rule;


import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

/**
 * @author makotan
 *
 */
public interface ActivitySelectRule {
    ActivityTagSelect selectActivity(Activity[] activitys,BuriPath path, BuriParticipant participant);
}
