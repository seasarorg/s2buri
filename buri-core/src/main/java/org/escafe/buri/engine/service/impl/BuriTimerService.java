/*
 * 作成日: 2006/06/28
 *
 */
package org.escafe.buri.engine.service.impl;

import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.abst.AbstractGetRunService;

import org.escafe.buri.engine.processor.util.BuriTimerInvoker;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.service.BuriPathDataEntityService;

public class BuriTimerService extends AbstractGetRunService {
	private BuriTimerInvoker timerInvoker;

	private BuriPathDataEntityService buriPathDataEntityService;

	public boolean canService() {
		return true;
	}

	public void execute() {
		List<BuriPathDataEntity> timerOverList =
		    buriPathDataEntityService.getTimeOrverState();
		Iterator<BuriPathDataEntity> ite = timerOverList.iterator();
		while (ite.hasNext()) {
			BuriPathDataEntity callDto = ite.next();
			timerInvoker.invoke(callDto);
		}
	}

	public BuriTimerInvoker getTimerInvoker() {
		return timerInvoker;
	}

	public void setTimerInvoker(BuriTimerInvoker timerInvoker) {
		this.timerInvoker = timerInvoker;
	}

	public BuriPathDataEntityService getBuriPathDataService() {
		return buriPathDataEntityService;
	}

	public void setBuriPathDataService(BuriPathDataEntityService buriPathDataEntityService) {
		this.buriPathDataEntityService = buriPathDataEntityService;
	}
}
