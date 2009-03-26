package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriBranchEntity;

import static org.escafe.buri.names.BuriBranchEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriBranchEntityService extends AbstractService<BuriBranchEntity> {
	public List<BuriBranchEntity> getAllBuriBranch() {
		return select().orderBy(asc(branchId())).getResultList();
	}

	public BuriBranchEntity select(Long branchId) {
		return selectById(branchId);
	}

	public List<BuriBranchEntity> getBranchByParentId(Long parentBranchId) {
		return select().where(eq(parentBranchId(), parentBranchId)).orderBy(
		    asc(branchId())).getResultList();
	}
}
