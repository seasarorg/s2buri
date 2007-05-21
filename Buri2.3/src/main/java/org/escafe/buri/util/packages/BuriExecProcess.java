/*
 * 作成日: 2006/03/21
 *
 */
package org.escafe.buri.util.packages;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;

public interface BuriExecProcess {
    void setup(BuriWorkflowProcessType process);

    String selectActivityId(BuriSystemContext sysContext);

    void entryActivity(String actId, BuriSystemContext sysContext, BranchWalker walker);

    void setBuriExePackages(BuriExePackages packages);

    BuriExePackages getBuriExePackages();

    BranchWalker readBranchWalker(BuriSystemContext sysContext);

    BuriWorkflowProcessType getBuriWorkflowProcessType();
}
