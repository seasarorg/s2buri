/*
 * çÏê¨ì˙: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector.activityImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriActivitySelector;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class StateActivitySelector extends AbstractBuriActivitySelector {
    private BuriDataUtil dataUtil;
    private BuriPathUtil pathUtil;

    protected Set getActivityList(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
        long dataID = dataUtil.getBuriDataId((DataAccessFactory)execProcess,systemContext);
        List pathList = pathUtil.getPathListByDataId(dataID);
        Iterator ite = pathList.iterator();
        while(ite.hasNext()) {
            BuriPath path = (BuriPath)ite.next();
            String actID = path.getActivityId().get(0).toString();
            BuriActivityType act = execProcess.getBuriWorkflowProcessType().getActivityById(actID);
            activitys.add(act);
        }
        return activitys;
    }

    protected boolean checkCanActivitySelect(Set activitys,BuriSystemContext systemContext, BuriExecProcess execProcess) {
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
