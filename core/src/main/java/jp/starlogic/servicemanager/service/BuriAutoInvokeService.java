/*
 * çÏê¨ì˙: 2005/12/28
 *
 */
package jp.starlogic.servicemanager.service;

import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.abst.AbstractCronTypeService;
import jp.starlogic.util.datetime.UtilCalendar;

import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.invoker.impl.StateBuriInvoker;

public class BuriAutoInvokeService extends AbstractCronTypeService {
    
    private StateBuriInvoker invoker;
    private BuriStateDao stateDao;

    public BuriAutoInvokeService() {
        super.setWaitTime(0);
        super.setRunningTime(60*1000);
        super.setStopCheckInterval(5*60*1000);
    }
    
    protected void executeCron() {
        List stats = stateDao.getTimeOrverState();
        Iterator ite = stats.iterator();
        while(ite.hasNext()) {
            BuriStateEntityDto dto = (BuriStateEntityDto)ite.next();
            invoker.invoke(dto);
        }
    }

    protected UtilCalendar createNextRunCalender(UtilCalendar lastRun) {
        return (UtilCalendar)lastRun.addMinute(1);
    }

    public StateBuriInvoker getInvoker() {
        return invoker;
    }

    public void setInvoker(StateBuriInvoker invoker) {
        this.invoker = invoker;
    }

    public BuriStateDao getStateDao() {
        return stateDao;
    }

    public void setStateDao(BuriStateDao stateDao) {
        this.stateDao = stateDao;
    }

}
