/*
 * ì¬“ú: 2005/06/02
 *
 */
package org.seasar.buri.xpdl.rule.impl;


import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.select.BuriManySelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectProcessException;
import org.seasar.buri.xpdl.rule.WorkflowProcessSelectRule;
import org.wfmc.x2002.xpdl10.PackageDocument.Package;
import org.wfmc.x2002.xpdl10.WorkflowProcessDocument.WorkflowProcess;

/**
 * @author makotan
 *
 */
public class WakanagoWorkflowProcessSelectRule implements
        WorkflowProcessSelectRule {
    public WorkflowProcess getWorkflowProcess(Package packageDoc,WorkflowProcess[] wkfp,BuriPath path) {
        WorkflowProcess process = null;
        if(wkfp!= null) {
	        if(wkfp.length==1) {
	            process = getOneSelectProcess(wkfp,packageDoc,path);
	        } else if(wkfp.length > 1) {
	            process = getManySelectProcess(wkfp,packageDoc,path);
	        }
        }
        if(process == null) {
            throw new BuriNotSelectProcessException(path);
        }
        return process;
        
    }
    
    protected WorkflowProcess getOneSelectProcess(WorkflowProcess[] wkfp,Package packageDoc,BuriPath path) {
        return wkfp[0];
    }
    
    protected WorkflowProcess getManySelectProcess(WorkflowProcess[] wkfp,Package packageDoc,BuriPath path) {
        throw new BuriManySelectProcessException(path);
    }

}
