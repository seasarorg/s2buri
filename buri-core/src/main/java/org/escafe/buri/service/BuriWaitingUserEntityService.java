/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriWaitingUserEntity;

import static org.escafe.buri.names.BuriWaitingUserEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriWaitingUserEntityService extends
        AbstractService<BuriWaitingUserEntity> {
	public List<BuriWaitingUserEntity> getAllWaitingUser() {
		return select().orderBy(asc(waitingUserId())).getResultList();
	}

	public BuriWaitingUserEntity getBuriWaitingUser(Long waitingUserId) {
		return selectById(waitingUserId);
	}

	public void insertList(List<BuriWaitingUserEntity> entities) {
		insertBatch(entities);
	}
}
