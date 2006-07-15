/*
 * ì¬“ú: 2006/05/29
 *
 */
package org.seasar.buri.engine.selector.processImpl;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriProcessSelector;
import org.seasar.buri.util.packages.BuriExePackages;

public class DirectProcessSelector extends AbstractBuriProcessSelector {

    protected boolean checkCanProcessSelect(List procs,BuriSystemContext systemContext, BuriExePackages exePackages) {
        if(procs.size() > 0) {
            return false;
        }
        String processName = systemContext.getCallPath().getWorkflowProcess();
        List tgts = exePackages.getBuriPackageType().getProcessByName(processName);
        if(tgts.size() > 1) {
            return false;
        }
        return true;
    }

    protected List getProcessList(List procs, BuriSystemContext systemContext,BuriExePackages exePackages) {
        String processName = systemContext.getCallPath().getWorkflowProcess();
        return exePackages.getBuriPackageType().getProcessByName(processName);
    }

}
