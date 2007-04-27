/*
 * 作成日: 2006/06/11
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Set;

import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;

/**
 * ロールなどの権限に基づいてアクティビティを選択します。
 * <p>
 * 既に他のセレクタによって選択されたアクティビティ群を権限によって絞り込むために利用されます。
 * 権限持っているかどうかの判定には指定された{@link ParticipantProvider}が使用されます。
 * </p>
 * <p>
 * アクティビティが1つ以上選択され、かつ、{@link ParticipantProvider}が指定されている場合のみ適用されます。
 * </p>
 * 
 * @author $Author$
 */
public class ParticipantBuriActivitySelector extends AbstractBuriActivitySelector {

    private BuriUserUtil buriUserUtil;

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        Set<BuriActivityType> result = new HashSet<BuriActivityType>();
        BuriExePackages packages = execProcess.getBuriExePackages();
        ParticipantProvider provider = packages.getParticipantProvider();
        for (BuriActivityType actType : activities) {
            ParticipantContext pc = new ParticipantContext();
            pc.setInsertUserId(buriUserUtil.getInsertUserId(systemContext));
            pc.setCurrentUserId(systemContext.getAppUserId());
            pc.setCurrentUserData(systemContext.getUserContext().getUserData());
            pc.setStartRoleName(systemContext.getStartRoleName());
            pc.setParticipantName(actType.getRoleName());
            pc.setParticipantType(actType.getRoleType());
            pc.setData(systemContext.getUserContext().getData());
            pc.setProcess(execProcess);
            pc.setUserContext(systemContext.getUserContext());
            if (provider.hasAuthority(pc)) {
                result.add(actType);
            }
        }
        activities.clear();
        activities.addAll(result);
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        if (activities.size() > 0) {
            BuriExePackages packages = execProcess.getBuriExePackages();
            if (packages.getParticipantProvider() != null) {
                return true;
            }
        }
        return false;
    }

    public void setBuriUserUtil(BuriUserUtil buriUserUtil) {
        this.buriUserUtil = buriUserUtil;
    }

}
