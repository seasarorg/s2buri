package org.escafe.buri.service;

import static org.escafe.buri.names.BuriBranchEntityNames.parentBranchId;
import static org.seasar.extension.jdbc.operation.Operations.eq;

import java.util.List;

import org.escafe.buri.entity.BuriBranchEntity;

public class BuriBranchEntityService extends AbstractService<BuriBranchEntity> {
	public List<BuriBranchEntity> getAllBuriBranch() {
		return findAll();
	}

	public BuriBranchEntity select(long branchId) {
		return selectById(branchId);
	}

	public List<BuriBranchEntity> getBranchByParentID(long parentBranchID) {
		return select().where(eq(parentBranchId(), parentBranchID))
				.getResultList();
	}
}
