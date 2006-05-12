/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;

import org.wfmc.x2002.xpdl10.TransitionDocument.Transition;

public interface TransitionUtil {
    List getTransitions(ActivityTagSelect tagSelect,String splitOrJoin , String andOrXor);
    List getJoinTransitions(ActivityTagSelect tagSelect,String andOrXor);
    boolean hasSplitTransitionRestriction(ActivityTagSelect tagSelect,String andOrXor);
    boolean isSplitAnd(ActivityTagSelect tagSelect);
    boolean isSplitXor(ActivityTagSelect tagSelect);
    boolean isJoinAnd(ActivityTagSelect tagSelect);
    boolean isJoinXor(ActivityTagSelect tagSelect);
    
    List getTransitionFromActivityId(WorkflowProcessTagSelect tagSelect,String activityId);
    List getTransitionToActivityId(WorkflowProcessTagSelect tagSelect,String activityId);
    Transition getTransitionFromId(WorkflowProcessTagSelect tagSelect,String transitionId);

}
