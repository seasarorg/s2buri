/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.BuriUserEntity;

import static org.escafe.buri.names.BuriUserEntityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

public class BuriUserEntityService extends AbstractService<BuriUserEntity> {
	public List<BuriUserEntity> getAllBuriBranch() {
		return findAll();
	}

	public BuriUserEntity getBuriUser(long buriUserId) {
		return selectById(buriUserId);
	}

	public Long getNewBuriStateUserId() {
		return null;
	}

	public BuriUserEntity getBuriUserFromIds(Long userIdNum, String userIdVal) {
		return select().where(eq(userIdNum(), userIdNum),
				eq(userIdVal(), userIdVal)).getSingleResult();
	}

	public List<BuriUserEntity> getBuriUserFromPathAndPkey(String path,
			long pkeyNum, String pkeyVal) {
		class Param {
			public String path;
			public Long pkeyNum;
			public String pkeyVal;
		}
		Param param = new Param();
		param.path = path;
		param.pkeyNum = pkeyNum;
		param.pkeyVal = pkeyVal;
		return selectBySqlFile(BuriUserEntity.class,
				"getBuriUserFromPathAndPkey.sql", param).getResultList();
	}
}
