/*
 * 作成日: 2006/05/29
 *
 */
package org.escafe.buri.engine.selector.abst;

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 実行対象のプロセスを選択するための抽象クラスです。
 * 
 * @author $Author$
 */
public abstract class AbstractBuriProcessSelector implements BuriProcessSelector {

    /**
     * @see org.escafe.buri.engine.selector.BuriProcessSelector#select(java.util.List, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExePackages)
     */
    public int select(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages execPackages) {
        if (!isTarget(processes, systemContext, execPackages)) {
            return SELECT_NEXT;
        }
        applyRule(processes, systemContext, execPackages);
        return getDefaultResultType();
    }

    /**
     * デフォルトの結果種別を返します。
     * 
     * @return
     */
    protected int getDefaultResultType() {
        return SELECT_NEXT;
    }

    /**
     * プロセス選択ルールを適用します。
     * <p>
     * 実行の結果、第1引数のListに変更が反映されます。
     * </p>
     * 
     * @param processes 
     * @param systemContext
     * @param execPackages
     */
    protected abstract boolean isTarget(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages execPackages);

    /**
     * ルールの適用を行う対象かどうか。
     * 
     * @param processes
     * @param systemContext
     * @param execPackages
     * @return 実行対象である場合は<code>true</code>。実行対象でない場合は<code>false</code>。
     */
    protected abstract void applyRule(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages execPackages);

}
