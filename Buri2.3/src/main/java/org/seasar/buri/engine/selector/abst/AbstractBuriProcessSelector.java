/*
 * 作成日: 2006/05/29
 *
 */
package org.seasar.buri.engine.selector.abst;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.BuriProcessSelector;
import org.seasar.buri.util.packages.BuriExePackages;

public abstract class AbstractBuriProcessSelector implements BuriProcessSelector {
    public int select(List procs, BuriSystemContext systemContext, BuriExePackages exePackages) {
        if( ! checkCanProcessSelect(procs, systemContext,exePackages)) {
            return SELECT_NEXT;
        }
        List acts = getProcessList(procs, systemContext,exePackages);
        procs.addAll(acts);
        return getDefaultResultType();
    }
    
    protected int getDefaultResultType() {
        return SELECT_NEXT;
    }
    
    protected abstract boolean checkCanProcessSelect(List procs, BuriSystemContext systemContext, BuriExePackages exePackages);
    
    protected abstract List getProcessList(List procs, BuriSystemContext systemContext, BuriExePackages exePackages);

}
