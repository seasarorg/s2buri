package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriPathEntity;

import static org.escafe.buri.names.BuriPathEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriPathEntityService extends AbstractService<BuriPathEntity> {
	public List<BuriPathEntity> getAllBuriPath() {
		return select().orderBy(asc(pathId())).getResultList();
	}

	public List<BuriPathEntity> getPathListByLikePathName(String pathName,
	        Long pathType) {
		return select().where(
		    like(pathName(), pathName),
		    eq(pathType(), pathType)).orderBy(asc(pathId())).getResultList();
	}

	public BuriPathEntity getBuriPath(Long pathId) {
		return select().where(eq(pathId(), pathId)).getSingleResult();
	}

	public List<BuriPathEntity> getBuriPathFromPath(String pathName,
	        Long pathType) {
		return select().where(
		    eq(pathName(), pathName),
		    eq(pathType(), pathType)).orderBy(asc(pathId())).getResultList();
	}

	public BuriPathEntity getBuriPathFromRealPath(String realPathName) {
		return select()
		    .where(eq(realPathName(), realPathName))
		    .getSingleResult();
	}

	public List<BuriPathEntity> getPathListByPathAndData(String path,
	        Long dataId, Long pathType) {
		class Param {
			@SuppressWarnings("unused")
			public String path;

			@SuppressWarnings("unused")
			public Long dataId;

			@SuppressWarnings("unused")
			public Long pathType;
		}
		Param param = new Param();
		param.path = path;
		param.dataId = dataId;
		param.pathType = pathType;
		return selectBySqlFile(
		    BuriPathEntity.class,
		    "getPathListByPathAndData.sql",
		    param).getResultList();
	}

	public List<BuriPathEntity> getBuriPathByDataId(Long dataId) {
		class Param {
			@SuppressWarnings("unused")
			public Long dataId;
		}
		Param param = new Param();
		param.dataId = dataId;
		return selectBySqlFile(
		    BuriPathEntity.class,
		    "getBuriPathByDataId.sql",
		    param).getResultList();
	}
}
