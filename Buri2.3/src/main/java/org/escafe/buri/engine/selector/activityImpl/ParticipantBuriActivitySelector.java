/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Set;

import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.ParticipantContext;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

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
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/11
 */
public class ParticipantBuriActivitySelector extends AbstractBuriActivitySelector {

    /**
     * ユーザ情報ユーティリティ
     */
    private BuriUserUtil buriUserUtil;

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#applyRule(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext, BuriExecProcess execProcess) {
        Set<BuriActivityType> result = new HashSet<BuriActivityType>();
        BuriExePackages packages = execProcess.getBuriExePackages();
        ParticipantProvider provider = packages.getParticipantProvider();

        for (BuriActivityType actType : activities) {
            ParticipantContext pc = new ParticipantContext();
            pc.setInsertUserId(buriUserUtil.getInsertUserId(systemContext));
            pc.setUserId(systemContext.getAppUserId());
            pc.setUserData(systemContext.getUserContext().getUserData());
            pc.setStartParticipantName(systemContext.getStartParticipantName());
            pc.setParticipantName(actType.getParticipantName());
            pc.setParticipantType(actType.getParticipantType());
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

    /*
     * @see org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector#isTarget(java.util.Set, org.escafe.buri.engine.BuriSystemContext, org.escafe.buri.util.packages.BuriExecProcess)
     */
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

    /**
     * ユーザ情報ユーティリティを登録します。
     * 
     * @param buriUserUtil ユーザ情報ユーティリティ
     */
    public void setBuriUserUtil(BuriUserUtil buriUserUtil) {
        this.buriUserUtil = buriUserUtil;
    }

}
