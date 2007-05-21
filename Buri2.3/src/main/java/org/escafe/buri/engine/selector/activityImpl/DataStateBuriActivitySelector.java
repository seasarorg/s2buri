/*
 * 作成日: 2006/05/23
 *
 */
package org.escafe.buri.engine.selector.activityImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * 登録済みのデータが現在所属しているアクティビティ群を選択します。
 * <p>
 * 分岐フローによって1つのデータが複数アクティビティにデータが所属する場合には、
 * 複数のアクティビティが選択されます。
 * </p>
 * <p>
 * 呼び出されれば常に適用されます。
 * </p>
 * 
 * @author $Author$
 */
public class DataStateBuriActivitySelector extends AbstractBuriActivitySelector {

    private BuriDataUtil dataUtil;
    private BuriPathUtil pathUtil;

    @Override
    protected void applyRule(Set<BuriActivityType> activities, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        Set<BuriActivityType> result = new HashSet<BuriActivityType>();
        long dataID = dataUtil.getBuriDataId((DataAccessFactory) execProcess, systemContext);
        List pathList = pathUtil.getPathListByDataId(dataID);
        Iterator ite = pathList.iterator();
        while (ite.hasNext()) {
            BuriPath path = (BuriPath) ite.next();
            String actID = path.getActivityId().get(0).toString();
            BuriActivityType act = execProcess.getBuriWorkflowProcessType().getActivityById(actID);
            result.add(act);
        }
        activities.clear();
        activities.addAll(result);
    }

    @Override
    protected boolean isTarget(Set<BuriActivityType> activitys, BuriSystemContext systemContext,
            BuriExecProcess execProcess) {
        return true;
    }

    public BuriDataUtil getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(BuriDataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public BuriPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(BuriPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

}
