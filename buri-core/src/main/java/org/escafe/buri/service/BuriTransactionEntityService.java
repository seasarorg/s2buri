/*
 * 作成日: 2006/05/13
 *
 */
package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriTransactionEntity;

import static org.escafe.buri.names.BuriTransactionEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriTransactionEntityService extends
        AbstractService<BuriTransactionEntity> {
	public List<BuriTransactionEntity> getAllBuriBranch() {
		return select().orderBy(asc(btId())).getResultList();
	}

	public BuriTransactionEntity select(Long btId) {
		return select().where(eq(btId(), btId)).getSingleResult();
	}

	public List<BuriTransactionEntity> selectFromEntity(
	        BuriTransactionEntity entity) {
		return select()
		    .where(
		        eq(btId(), entity.btId),
		        eq(insertDate(), entity.insertDate),
		        eq(versionNo(), entity.versionNo))
		    .orderBy(asc(btId()))
		    .getResultList();
	}
}
