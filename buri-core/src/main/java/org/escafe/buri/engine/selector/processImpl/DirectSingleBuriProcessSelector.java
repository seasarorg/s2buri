/*
 * 作成日: 2006/05/29
 *
 */
package org.escafe.buri.engine.selector.processImpl;

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriProcessSelector;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 直接指定されている1つのプロセスを選択します。
 * <p>
 * まだプロセスが選択されておらず、かつ、
 * 実行対象として指定されたプロセスが1件のみ検出された場合に適用されます。
 * 適用された場合、ここで決定した1つのみが結果に含まれます。
 * </p>
 * 
 * @author $Author: nobeans $
 */
public class DirectSingleBuriProcessSelector extends AbstractBuriProcessSelector {

    @Override
    protected boolean isTarget(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages execPackages) {
        if (processes.size() > 0) {
            return false;
        }
        String processName = systemContext.getCallPath().getWorkflowProcess();
        List<BuriWorkflowProcessType> targets = execPackages.getBuriPackageType().getProcessByName(processName);
        if (targets.size() > 1) {
            return false;
        }
        return true;
    }

    @Override
    protected void applyRule(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages execPackages) {
        String processName = systemContext.getCallPath().getWorkflowProcess();
        List<BuriWorkflowProcessType> result = execPackages.getBuriPackageType().getProcessByName(processName);
        processes.clear();
        processes.addAll(result);
    }

}
