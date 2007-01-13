/*
 * 作成日: 2006/06/11
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;

public class ParticipantBuriActivitySelector extends AbstractBuriActivitySelector {

    protected Set getActivityList(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
        Set result = new HashSet();
        Iterator ite = activitys.iterator();
        BuriExePackages packages = execProcess.getBuriExePackages();
        ParticipantProvider provider = packages.getParticipantProvider();
        while(ite.hasNext()) {
            BuriActivityType actType = (BuriActivityType)ite.next();
            String roleName = actType.getRoleName();
            String roleType = actType.getRoleType();
            boolean inRole = provider.isUserInRole(systemContext.getUserContext().getUserData(),roleName,roleType);
            if(inRole) {
                result.add(actType);
            }
        }
        return result;
    }

    protected boolean checkCanActivitySelect(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if(activitys.size()>0) {
            BuriExePackages packages = execProcess.getBuriExePackages();
            if(packages.getParticipantProvider() != null) {
                return true;
            }
        }
        return false;
    }

}
