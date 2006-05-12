/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;


import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

public interface ActivityTagSelect extends TagSelect {
    
    Activity getActivity();
    void setActivity(Activity act);
    void setActivityLevel(int level);
    int getActivityLevel();
    
    List getXorSplitTransitions();
    List getAndSplitTransitions();
    List getXorJoinTransitions();
    List getAndJoinTransitions();
    
    boolean isProcessedXorJoin();
    boolean isEmulateXorJoin();
    
    boolean isSplitAnd();
    boolean isSplitXor();
    boolean isJoinAnd();
    boolean isJoinXor();

    boolean isFinishAuto();
    boolean isFinishManual();
    
    void setBuriPath(BuriPath thisPath);
    BuriPath getBuriPath();

}
