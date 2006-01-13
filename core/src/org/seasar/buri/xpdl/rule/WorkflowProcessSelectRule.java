/*
 * çÏê¨ì˙: 2005/06/02
 *
 */
package org.seasar.buri.xpdl.rule;


import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.PackageDocument.Package;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;

/**
 * @author makotan
 *
 */
public interface WorkflowProcessSelectRule {
    WorkflowProcess getWorkflowProcess(Package packageDoc,WorkflowProcess[] wkfp,BuriPath path);
}
