/*
 * 作成日: 2006/05/23
 *
 */
package org.escafe.buri.engine.selector.abst;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 実行対象のアクティビティを選択するための抽象クラスです。
 * 
 * @author $Author: nobeans $
 */
public abstract class AbstractBuriActivitySelector implements BuriActivitySelector {

    /**
     * @see org.escafe.buri.engine.selector.BuriActivitySelector#select(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
    public int select(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if (!isTarget(activities, systemContext, execProcess)) {
            return SELECT_NEXT;
        }
        applyRule(activities, systemContext, execProcess);
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
     * アクティビティ選択ルールを適用します。
     * <p>
     * 実行の結果、第1引数のSetに変更が反映されます。
     * </p>
     * 
     * @param activities 
     * @param systemContext
     * @param execProcess
     */
    protected abstract void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess);

    /**
     * ルールの適用を行う対象かどうか。
     * 
     * @param activities
     * @param systemContext
     * @param execProcess
     * @return 実行対象である場合は<code>true</code>。実行対象ではないは<code>false</code>。
     */
    protected abstract boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess);

}
