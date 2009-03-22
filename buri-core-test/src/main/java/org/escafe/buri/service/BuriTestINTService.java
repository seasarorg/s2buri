package org.escafe.buri.service;

import static org.escafe.buri.names.BuriTestINTNames.testId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.escafe.buri.dto.BuriTestINTFindDto;
import org.escafe.buri.entity.BuriTestINT;

public class BuriTestINTService extends AbstractService<BuriTestINT> {
	@Override
	public int delete(BuriTestINT entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

	@Override
	public int insert(BuriTestINT entity) {
		// TODO Auto-generated method stub
		return super.insert(entity);
	}

	@Override
	public int update(BuriTestINT entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public BuriTestINT selectById(Object... ids) {
		// TODO Auto-generated method stub
		return super.selectById(ids);
	}

	public List<BuriTestINT> getAllBuriTestINT() {
		return findAll();
	}

	public BuriTestINT getBuriTestINT(Long testId) {
		return select().id(testId).getSingleResult();
	}

	public List<BuriTestINT> getBuriTestINTByIds(List<Long> testIds) {
		return select().where(in(testId(), testIds)).getResultList();
	}

	public List<BuriTestINT> find(BuriTestINTFindDto dto, List<?> paths) {
		class Param {
			public BuriTestINTFindDto dto;

			public List<?> paths;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		return selectBySqlFile(BuriTestINT.class, "find.sql", param)
		    .getResultList();
	}

	public List<BuriTestINT> findAndUser(BuriTestINTFindDto dto, List<?> paths,
	        List<?> userIds) {
		class Param {
			public BuriTestINTFindDto dto;

			public List<?> paths;

			public List<?> userIds;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		param.userIds = userIds;
		return selectBySqlFile(BuriTestINT.class, "findAndUser.sql", param)
		    .getResultList();
	}

	public List<BuriTestINT> getBuriTestINTByIdAndDto(List<Long> testIds,
	        BuriTestINT entity) {
		return select().where(
		    "testId IN (?) AND value = ?",
		    testIds,
		    entity.value).getResultList();
	}
}
