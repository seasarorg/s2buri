package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriDataPathHistoryEntity;

public class BuriDataPathHistoryEntityService extends
		AbstractService<BuriDataPathHistoryEntity> {

	public List<BuriDataPathHistoryEntity> getAllBuriDataPathHistory() {
		return findAll();
	}

	public BuriDataPathHistoryEntity getBuriDataPathHistory(long historyId) {
		return selectById(historyId);
	}

}
