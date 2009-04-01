/*
 * 作成日: 2006/05/13
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.Date;

import org.escafe.buri.dao.util.BTIDUtil;
import org.escafe.buri.entity.BuriTransactionEntity;
import org.escafe.buri.service.BuriTransactionEntityService;

public class BTIDUtilImpl implements BTIDUtil {
	private BuriTransactionEntityService buriTransactionEntityService;

	private ThreadLocal<Long> btidLocal = new ThreadLocal<Long>();

	public void dispose() {
		btidLocal = new ThreadLocal<Long>();
	}

	public long createBtId() {
		BuriTransactionEntity dto = new BuriTransactionEntity();
		dto.insertDate = new Date();
		buriTransactionEntityService.insert(dto);
		return dto.btId;
	}

	public long getCurrentBtId() {
		if (btidLocal.get() == null) {
			setBtId(0);
		}
		long result = (btidLocal.get()).longValue();
		return result;
	}

	public void setBtId(long btid) {
		Long val = Long.valueOf(btid);
		btidLocal.set(val);
	}

	public BuriTransactionEntityService getBuriTransactionEntityService() {
		return buriTransactionEntityService;
	}

	public void setBuriTransactionEntityService(
	        BuriTransactionEntityService buriTransactionEntityService) {
		this.buriTransactionEntityService = buriTransactionEntityService;
	}
}
