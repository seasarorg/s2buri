package org.escafe.buri.service;

import static org.escafe.buri.names.BuriTestINTNames.testId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.escafe.buri.dto.BuriTestINTFindDto;
import org.escafe.buri.entity.BuriTestINT;
import org.seasar.framework.beans.util.BeanMap;

public class BuriTestINTService extends AbstractService<BuriTestINT> {
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
		BeanMap bm = new BeanMap();
		bm.put("dto", dto);
		bm.put("paths", paths);
		return selectBySqlFile(BuriTestINT.class, "find.sql", bm)
		    .getResultList();
	}

	public List<BuriTestINT> findAndUser(BuriTestINTFindDto dto, List<?> paths,
	        List<?> userIds) {
		BeanMap bm = new BeanMap();
		bm.put("dto", dto);
		bm.put("paths", paths);
		bm.put("userIds", userIds);
		return selectBySqlFile(BuriTestINT.class, "findAndUser.sql", bm)
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
