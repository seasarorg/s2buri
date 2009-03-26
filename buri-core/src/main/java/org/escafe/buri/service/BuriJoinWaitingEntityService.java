package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriJoinWaitingEntity;

import static org.escafe.buri.names.BuriJoinWaitingEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriJoinWaitingEntityService extends
        AbstractService<BuriJoinWaitingEntity> {
	public List<BuriJoinWaitingEntity> getAllBuriState() {
		return select().orderBy(asc(waitingId())).getResultList();
	}

	public BuriJoinWaitingEntity getBuriJoinWaiting(Long waitingId) {
		return selectById(waitingId);
	}

	public List<BuriJoinWaitingEntity> getNowWaiting() {
		return select().where("processDate > CURRENT_TIMESTAMP").orderBy(
		    asc(waitingId())).getResultList();
	}

	public void updateClearWaitingInfo(Long branchId) {
		class Param {
			@SuppressWarnings("unused")
			public Long branchId;
		}
		Param param = new Param();
		param.branchId = branchId;
		updateBySqlFile("updateClearWaitingInfo.sql", param).execute();
	}
}
