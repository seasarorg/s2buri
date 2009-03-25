package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriStateUndoLogEntity;

public class BuriStateUndoLogEntityService extends
        AbstractService<BuriStateUndoLogEntity> {
	public List<BuriStateUndoLogEntity> getAllBuriState() {
		return findAll();
	}

	public void addUndoLog(Long stateId, Long branchId, Long btId) {
		class Param {
			@SuppressWarnings("unused")
			public Long stateId;

			@SuppressWarnings("unused")
			public Long branchId;

			@SuppressWarnings("unused")
			public Long btId;
		}
		Param param = new Param();
		param.stateId = stateId;
		param.branchId = branchId;
		param.btId = btId;
		updateBySqlFile("addUndoLog.sql", param).execute();
	}
}
