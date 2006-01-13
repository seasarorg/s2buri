/*
 * çÏê¨ì˙: 2005/11/18
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

public interface ActivityTagSelectUtil {
    ActivityTagSelect convTagSelect(Activity activity);
    ActivityTagSelect convTagSelect(Activity activity,BuriPath path);
    List convTagSelectList(List actList);
    List convTagSelectList(Activity[] activitys);
    
}
