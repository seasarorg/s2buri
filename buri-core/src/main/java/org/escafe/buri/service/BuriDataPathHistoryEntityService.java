package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriDataPathHistoryEntity;

import static org.escafe.buri.names.BuriDataPathHistoryEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriDataPathHistoryEntityService extends
        AbstractService<BuriDataPathHistoryEntity> {
	public List<BuriDataPathHistoryEntity> getAllBuriDataPathHistory() {
		return select().orderBy(asc(historyId())).getResultList();
	}

	public BuriDataPathHistoryEntity getBuriDataPathHistory(Long historyId) {
		return selectById(historyId);
	}
}
