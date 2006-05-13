/*
 * çÏê¨ì˙: 2006/03/21
 *
 */
package org.seasar.buri.util.packages;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;

public interface BuriExecProcess {
    void setup(BuriWorkflowProcessType process);
    String selectActivityId(BuriSystemContext sysContext);
    void entryActivity(String actId,BuriSystemContext sysContext,BranchWalker walker);
    
    void setBuriExePackages(BuriExePackages packages);
    
    BuriWorkflowProcessType getBuriWorkflowProcessType();
}
