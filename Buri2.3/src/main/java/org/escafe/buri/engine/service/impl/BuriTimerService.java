/*
 * 作成日: 2006/06/28
 *
 */
package org.escafe.buri.engine.service.impl;

import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.abst.AbstractGetRunService;

import org.escafe.buri.dao.BuriPathDataDao;
import org.escafe.buri.dto.BuriPathDataEntityDto;
import org.escafe.buri.engine.processor.util.BuriTimerInvoker;

public class BuriTimerService extends AbstractGetRunService {
    private BuriTimerInvoker timerInvoker;
    private BuriPathDataDao dataDao;

    public boolean canService() {
        return true;
    }

    public void execute() {
        List timerOverList = dataDao.getTimeOrverState();
        Iterator ite = timerOverList.iterator();
        while(ite.hasNext()) {
            BuriPathDataEntityDto callDto = (BuriPathDataEntityDto)ite.next();
            timerInvoker.invoke(callDto);
        }
    }

    public BuriPathDataDao getDataDao() {
        return dataDao;
    }

    public void setDataDao(BuriPathDataDao dataDao) {
        this.dataDao = dataDao;
    }

    public BuriTimerInvoker getTimerInvoker() {
        return timerInvoker;
    }

    public void setTimerInvoker(BuriTimerInvoker timerInvoker) {
        this.timerInvoker = timerInvoker;
    }

}
