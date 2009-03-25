package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriStateEntity;
import org.seasar.framework.beans.util.BeanMap;

import static org.escafe.buri.names.BuriStateEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriStateEntityService extends AbstractService<BuriStateEntity> {
	public List<BuriStateEntity> getAllBuriState() {
		return findAll();
	}

	public List<BuriStateEntity> getNoProcessBuriState() {
		return select()
		    .where("processDate > CURRENT_TIMESTAMP")
		    .getResultList();
	}

	public BuriStateEntity getBuriState(Long stateId) {
		return select().where(eq(stateId(), stateId)).getSingleResult();
	}

	public void updateProceesByStateId(Long stateId) {
		updateBySqlFile("updateProceesByStateId.sql", stateId).execute();
	}

	public void updateAbortByStateId(Long stateId) {
		updateBySqlFile("updateAbortByStateId.sql", stateId).execute();
	}

	public void updateAbortByData(Long longKey, String manyKey, String dataType) {
		class Param {
			@SuppressWarnings("unused")
			public Long longKey;

			@SuppressWarnings("unused")
			public String manyKey;

			@SuppressWarnings("unused")
			public String dataType;
		}
		Param param = new Param();
		param.longKey = longKey;
		param.manyKey = manyKey;
		param.dataType = dataType;
		updateBySqlFile("updateAbortByData.sql", param).execute();
	}

	public void updateAbortByBranchId(Long branchId) {
		updateBySqlFile("updateAbortByBranchId.sql", branchId).execute();
	}

	public Long countByBranchIdAndNotProcessed(Long branchId) {
		BeanMap bm =
		    selectBySqlFile(
		        BeanMap.class,
		        "countByBranchIdAndNotProcessed.sql",
		        branchId).getSingleResult();
		return (Long) bm.get("count(*)");
	}

	public BuriStateEntity getBuriStateByPathAndData(Long pathId, Long dataId) {
		return select().where(
		    "pathId = ? AND dataId = ? AND processDate > CURRENT_TIMESTAMP",
		    pathId,
		    dataId).getSingleResult();
	}

	public Long countBuriStateByPathNameAndPkey(String pathName, Long longKey,
	        String manyKey) {
		class Param {
			@SuppressWarnings("unused")
			public String pathName;

			@SuppressWarnings("unused")
			public String manyKey;

			@SuppressWarnings("unused")
			public Long longKey;
		}
		Param param = new Param();
		param.pathName = pathName;
		param.manyKey = manyKey;
		param.longKey = longKey;
		BeanMap bm =
		    selectBySqlFile(
		        BeanMap.class,
		        "countBuriStateByPathNameAndPkey.sql",
		        param).getSingleResult();
		return (Long) bm.get("count(*)");
	}
	/*
	 * public Long getNewBuriStateId() { return null; }
	 */
}
