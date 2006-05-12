/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;


import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;

public interface WorkflowProcessTagSelect extends TagSelect {
    WorkflowProcess getWorkflowProcess();
    void setWorkflowProcess(WorkflowProcess process);

    BuriPath getBuriPath();
    void setBuriPath(BuriPath path);
    
}
