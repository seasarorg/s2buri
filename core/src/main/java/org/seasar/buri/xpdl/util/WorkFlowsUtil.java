/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;

import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.rule.ActivitySelectRule;



public interface WorkFlowsUtil {
    WorkFlowPackageTagSelect getWorkFlowPackage(BuriPath path);
    WorkflowProcessTagSelect getWorkflowProcess(BuriPath path);
    ActivityTagSelect getActivity(BuriPath path);
    
    ActivityTagSelect getActivityTagSelectFromBuriPath(BuriPath path);
    ActivityTagSelect getActivityTagSelectFromPathAndParticipant(BuriPath path,BuriParticipant participant);
//    ActivityTagSelect getFirstActivityFromPathAndParticipant(BuriPath path, BuriParticipant participant);
    ActivityTagSelect getActivityTagSelectFromPathAndParticipant(BuriPath path, BuriParticipant participant,ActivitySelectRule activitySelectRule);

    List getActivityTagSelectList(BuriPath path);
    List getStartActivityTagSelectList(BuriPath path);
    List getStartActivityTagSelectList(BuriParticipant participant,BuriPath path);
    
    List getActivityTagSelectByPathList(List pathList);
    List getActivityListByParticipant(List activityList,BuriParticipant participant);
    
}
