package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriJoinWaitingEntity;

public class BuriJoinWaitingEntityService extends
        AbstractService<BuriJoinWaitingEntity> {
	public List<BuriJoinWaitingEntity> getAllBuriState() {
		return findAll();
	}

	public BuriJoinWaitingEntity getBuriJoinWaiting(Long waitingId) {
		return selectById(waitingId);
	}

	public List<BuriJoinWaitingEntity> getNowWaiting() {
		return select()
		    .where("processDate > CURRENT_TIMESTAMP")
		    .getResultList();
	}

	public void updateClearWaitingInfo(Long branchId) {
		class Param {
			public Long branchId;
		}
		Param param = new Param();
		param.branchId = branchId;
		updateBySqlFile("updateClearWaitingInfo.sql", param).execute();
	}
}
