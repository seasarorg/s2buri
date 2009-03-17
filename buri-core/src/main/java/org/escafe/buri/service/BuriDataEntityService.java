package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriDataEntity;

import static org.escafe.buri.names.BuriDataEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriDataEntityService extends AbstractService<BuriDataEntity> {
	public List<BuriDataEntity> getAllBuriData() {
		return findAll();
	}

	public BuriDataEntity getBuriData(Long dataID) {
		return selectById(dataID);
	}

	public List<BuriDataEntity> getBuriDataFromDto(BuriDataEntity buriDataEntity) {
		return select().where(
		    eq(dataId(), buriDataEntity.dataId),
		    eq(dataType(), buriDataEntity.dataType),
		    eq(pkeyVal(), buriDataEntity.pkeyVal),
		    eq(pkeyNum(), buriDataEntity.pkeyNum),
		    eq(tableName(), buriDataEntity.tableName),
		    eq(insertUserId(), buriDataEntity.insertUserId)).getResultList();
	}
}
