/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriWaitingUserEntity;

public class BuriWaitingUserEntityService extends
        AbstractService<BuriWaitingUserEntity> {
	public List<BuriWaitingUserEntity> getAllWaitingUser() {
		return findAll();
	}

	public BuriWaitingUserEntity getBuriWaitingUser(long waitingUserID) {
		return null;
	}

	public void insertList(List<BuriWaitingUserEntity> entities) {
		insertBatch(entities);
	}
}
