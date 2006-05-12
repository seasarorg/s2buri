/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.engine;

import java.util.Map;

import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;

/**
 * @author makotan
 *
 */
public interface WakanagoWorkFlows {
    void readWorkFlowFromResource(String workFlowName,String resourceName);
    void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider);

    WorkFlowPackageTagSelect getPackageTagSelect(BuriPath path);
    
    Map getCache();
}
