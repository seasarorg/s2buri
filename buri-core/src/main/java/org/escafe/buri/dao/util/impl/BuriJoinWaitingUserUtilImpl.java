package org.escafe.buri.dao.util.impl;

import java.util.List;

import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.entity.BuriJoinWaitingEntity;
import org.escafe.buri.entity.BuriWaitingUserEntity;
import org.escafe.buri.service.BuriWaitingUserEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriJoinWaitingUserUtilImpl extends BuriJoinWaitingUtilImpl {
	private BuriUserUtil userUtil;

	private BuriWaitingUserEntityService buriWaitingUserEntityService;

	protected void saveWaitingUser(DataAccessFactory factory,
	        BuriJoinWaitingEntity dto, BuriSystemContext sysContext,
	        BranchWalker nextWalker) {
		List<IdentityInfo> idenDtos =
		    userUtil.getUserIds(factory, sysContext, nextWalker);
		for (IdentityInfo info : idenDtos) {
			BuriWaitingUserEntity waitDto = new BuriWaitingUserEntity();
			long buriUserId = userUtil.convertBuriUserId(info);
			waitDto.buriUserId = buriUserId;
			waitDto.waitingId = dto.waitingId;
			buriWaitingUserEntityService.insert(waitDto);
		}
	}

	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public BuriWaitingUserEntityService getBuriWaitingUserEntityService() {
		return buriWaitingUserEntityService;
	}

	public void setBuriWaitingUserEntityService(
	        BuriWaitingUserEntityService buriWaitingUserEntityService) {
		this.buriWaitingUserEntityService = buriWaitingUserEntityService;
	}
}
