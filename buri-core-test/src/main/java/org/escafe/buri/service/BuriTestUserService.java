package org.escafe.buri.service;

import static org.escafe.buri.names.BuriTestUserNames.parentUserId;
import static org.escafe.buri.names.BuriTestUserNames.roleName;
import static org.escafe.buri.names.BuriTestUserNames.userId;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.escafe.buri.dto.BuriTestUserFindDto;
import org.escafe.buri.entity.BuriTestUser;

public class BuriTestUserService extends AbstractService<BuriTestUser> {
	public List<BuriTestUser> getAllBuriTestUser() {
		return findAll();
	}

	public BuriTestUser getBuriTestUser(Long userId) {
		return select().id(userId).getSingleResult();
	}

	public List<BuriTestUser> getBuriTestUserByIds(List<Long> userIds) {
		return select().where(in(userId(), userIds)).getResultList();
	}

	public List<BuriTestUser> find(BuriTestUserFindDto dto, List<String> paths) {
		class Param {
			public BuriTestUserFindDto dto;

			public List<String> paths;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		return selectBySqlFile(BuriTestUser.class, "find.sql", param)
		    .getResultList();
	}

	public List<BuriTestUser> findAndUser(BuriTestUserFindDto dto,
	        List<String> paths, List<Long> userIds) {
		class Param {
			public BuriTestUserFindDto dto;

			public List<String> paths;

			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		param.userIds = userIds;
		return selectBySqlFile(BuriTestUser.class, "findAndUser.sql", param)
		    .getResultList();
	}

	public List<BuriTestUser> getUserListByParentUserId(Long userId,
	        String roleName) {
		return select().where(
		    eq(parentUserId(), userId),
		    eq(roleName(), roleName)).getResultList();
	}

	public List<BuriTestUser> getUserListByUserId(Long userId, String roleName) {
		return select()
		    .where(eq(userId(), userId), eq(roleName(), roleName))
		    .getResultList();
	}
}
