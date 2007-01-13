/*
 * 作成日: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.abst;

import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.BuriActivitySelector;
import org.seasar.buri.util.packages.BuriExecProcess;

public abstract class AbstractBuriActivitySelector implements BuriActivitySelector {
    public int select(Set activitys, BuriSystemContext systemContext,BuriExecProcess execProcess) {
        if( ! checkCanActivitySelect(activitys, systemContext,execProcess)) {
            return SELECT_NEXT;
        }
        Set acts = getActivityList(activitys, systemContext,execProcess);
        activitys.addAll(acts);
        return getDefaultResultType();
    }
    
    protected int getDefaultResultType() {
        return SELECT_NEXT;
    }
    
    protected abstract Set getActivityList(Set activitys, BuriSystemContext systemContext,BuriExecProcess execProcess);
    
    protected abstract boolean checkCanActivitySelect(Set activitys, BuriSystemContext systemContext,BuriExecProcess execProcess);

}
