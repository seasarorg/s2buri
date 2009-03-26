/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriStateUserEntity;

import static org.escafe.buri.names.BuriStateUserEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriStateUserEntityService extends
        AbstractService<BuriStateUserEntity> {
	public List<BuriStateUserEntity> getAllBuriBranch() {
		return select().orderBy(asc(stateUserId())).getResultList();
	}

	public BuriStateUserEntity getBuriStateUser(Long stateUserId) {
		return select().where(eq(stateUserId(), stateUserId)).getSingleResult();
	}

	public void insertList(List<BuriStateUserEntity> entities) {
		insertBatch(entities);
	}
}
