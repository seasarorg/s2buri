package org.escafe.buri.service;

import java.math.BigDecimal;
import java.util.List;

import org.escafe.buri.entity.BuriPathDataUserEntity;
import org.seasar.framework.beans.util.BeanMap;

import static org.escafe.buri.names.BuriPathDataUserEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriPathDataUserEntityService extends
        AbstractService<BuriPathDataUserEntity> {
	public BuriPathDataUserEntity getDto(Long stateId) {
		return select().where(eq(stateId(), stateId)).getSingleResult();
	}

	public List<BuriPathDataUserEntity> getTimeOrverByState(Long stateId) {
		return select().where(
		    "autoRunTime < CURRENT_TIMESTAMP AND stateId = ?",
		    stateId).orderBy(asc(buriUserId())).getResultList();
	}

	public Long getCountByPathKeysAndUser(String className,
	        List<Long> longList, List<String> strList, String pathName,
	        Long pathType, Long userID) {
		BeanMap result =
		    selectBySqlFile(BeanMap.class, "getCountByPathKeysAndUser.sql")
		        .getSingleResult();
		BigDecimal bd = (BigDecimal) result.get("count(*)");
		return bd.longValue();
	}

	public List<BuriPathDataUserEntity> getListByPathNameAndUser(
	        String className, String pathName, Long pathType, Long userId) {
		return select()
		    .where(
		        eq(dataType(), className),
		        eq(pathName(), pathName),
		        eq(pathType(), pathType),
		        eq(buriUserId(), userId))
		    .orderBy(asc(buriUserId()))
		    .getResultList();
	}
}
