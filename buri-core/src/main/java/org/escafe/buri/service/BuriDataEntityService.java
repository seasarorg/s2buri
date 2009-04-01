package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriDataEntity;

import static org.escafe.buri.names.BuriDataEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriDataEntityService extends AbstractService<BuriDataEntity> {
	public List<BuriDataEntity> getAllBuriData() {
		return select().orderBy(asc(dataId())).getResultList();
	}

	public BuriDataEntity getBuriData(Long dataID) {
		return selectById(dataID);
	}

	public List<BuriDataEntity> getBuriDataFromDto(BuriDataEntity buriDataEntity) {
		return select()
		    .where(
		        eq(dataType(), buriDataEntity.dataType),
		        eq(pkeyVal(), buriDataEntity.pkeyVal),
		        eq(pkeyNum(), buriDataEntity.pkeyNum))
		    .orderBy(asc(dataId()))
		    .getResultList();
	}
}
