/*
 * 作成日: 2006/06/19
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExecProcess;

public class BuriActivityPathFilter extends AbstractBuriActivitySelector {

    protected Set getActivityList(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
        Set result = new HashSet();
        Set acts = actNameListToSet(systemContext);
        Iterator ite = activitys.iterator();
        while(ite.hasNext()) {
            BuriActivityType actType = (BuriActivityType)ite.next();
            String tgtName = actType.getName();
            if(acts.contains(tgtName)) {
                result.add(actType);
            }
        }
        activitys.clear();
        return result;
    }
    
    protected Set actNameListToSet(BuriSystemContext systemContext) {
        Set acts = new HashSet();
        if(systemContext.getActNames() == null || systemContext.getActNames().size() == 0) {
            acts.add(systemContext.getCallPath().getActivityName().get(0));
        } else {
            acts.addAll(systemContext.getActNames());
        }
        return acts;
    }

    protected boolean checkCanActivitySelect(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if(activitys.size() > 0 && systemContext.getCallPath().getActivityName().size() > 0) {
            return true;
        }
        return false;
    }

}
