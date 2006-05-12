/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;


public class WorkflowProcessTagSelectImpl extends AbstractTagSelect implements
        WorkflowProcessTagSelect {

    private WorkflowProcess process;
    private BuriPath path;
    
    public WorkflowProcess getWorkflowProcess() {
        return process;
    }

    public void setWorkflowProcess(WorkflowProcess process) {
        this.process = process;
    }

    public BuriPath getBuriPath() {
        return path;
    }

    public void setBuriPath(BuriPath path) {
        this.path = path;
    }

    public XmlObject getCurrentXmlObject() {
        return getWorkflowProcess();
    }

    public XmlObject getXmlObject() {
        return getWorkflowProcess();
    }
    
    public String toString() {
        return "WorkflowProcessTagSelect/" + path;
    }
}
