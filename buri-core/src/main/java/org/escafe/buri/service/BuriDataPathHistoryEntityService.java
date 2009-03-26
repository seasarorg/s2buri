package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriDataPathHistoryEntity;

public class BuriDataPathHistoryEntityService extends
        AbstractService<BuriDataPathHistoryEntity> {
	public List<BuriDataPathHistoryEntity> getAllBuriDataPathHistory() {
		return findAll();
	}

	public BuriDataPathHistoryEntity getBuriDataPathHistory(Long historyId) {
		return selectById(historyId);
	}
}
