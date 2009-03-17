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
		return findAll();
	}

	public BuriTransactionEntity select(Long btId) {
		return select().where(eq(btId(), btId)).getSingleResult();
	}

	public List<BuriTransactionEntity> selectFromDto(BuriTransactionEntity dto) {
		return null;
	}
}
