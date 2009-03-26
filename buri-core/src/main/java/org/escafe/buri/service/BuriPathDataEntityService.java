package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriPathDataEntity;

import static org.escafe.buri.names.BuriPathDataEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriPathDataEntityService extends
        AbstractService<BuriPathDataEntity> {
	public List<BuriPathDataEntity> getListByPathName(String className,
	        String pathName, Long pathType) {
		List<BuriPathDataEntity> result =
		    select()
		        .where(
		            eq(dataType(), className),
		            eq(pathName(), pathName),
		            eq(pathType(), pathType))
		        .orderBy(asc(pathId()))
		        .getResultList();
		return result;
	}

	public List<BuriPathDataEntity> getListByPkeyNum(String className,
	        Long pkeyNum, Long pathType) {
		return select().where(
		    eq(dataType(), className),
		    eq(pkeyNum(), pkeyNum),
		    eq(pathType(), pathType)).orderBy(asc(pathId())).getResultList();
	}

	public List<BuriPathDataEntity> getListByPkeyVal(String className,
	        String pkeyVal, Long pathType) {
		return select().where(
		    eq(dataType(), className),
		    eq(pkeyVal(), pkeyVal),
		    eq(pathType(), pathType)).orderBy(asc(pathId())).getResultList();
	}

	public BuriPathDataEntity getDtoByPathKey(String className, Long pkeyNum,
	        String pkeyVal, String pathName, Long pathType) {
		BuriPathDataEntity result =
		    select()
		        .where(
		            eq(dataType(), className),
		            eq(pkeyNum(), pkeyNum),
		            eq(pkeyVal(), pkeyVal),
		            like(pathName(), pathName),
		            eq(pathType(), pathType))
		        .orderBy(asc(pathId()))
		        .limit(1)
		        .getSingleResult();
		return result;
	}

	public long getCountByPathKeys(String className, List<Long> pkeyNums,
	        List<String> pkeyVals, String pathName, Long pathType) {
		long count =
		    select().where(
		        eq(dataType(), className),
		        in(pkeyNum(), pkeyNums),
		        in(pkeyVal(), pkeyVals),
		        eq(pathName(), pathName),
		        eq(pathType(), pathType)).getCount();
		return count;
	}

	public List<BuriPathDataEntity> getTimeOrverState() {
		return select().where("autoRunTime < CURRENT_TIMESTAMP").orderBy(
		    asc(pathId())).getResultList();
	}
}
