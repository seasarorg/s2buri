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

	@Override
	public int delete(BuriDataEntity entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

	@Override
	public int insert(BuriDataEntity entity) {
		// TODO Auto-generated method stub
		return super.insert(entity);
	}

	@Override
	public int update(BuriDataEntity entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	public List<BuriDataEntity> getBuriDataFromDto(BuriDataEntity buriDataEntity) {
		return select().where(eq(dataId(), buriDataEntity.dataId),
				eq(dataType(), buriDataEntity.dataType),
				eq(pkeyVal(), buriDataEntity.pkeyVal),
				eq(pkeyNum(), buriDataEntity.pkeyNum),
				eq(tableName(), buriDataEntity.tableName),
				eq(insertUserId(), buriDataEntity.insertUserId))
				.getResultList();
	}
}
