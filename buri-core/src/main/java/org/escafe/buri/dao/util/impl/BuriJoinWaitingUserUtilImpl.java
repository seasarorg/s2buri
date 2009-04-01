package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.dao.BuriWaitingUserDao;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriJoinWaitingEntityDto;
import org.escafe.buri.dto.BuriWaitingUserEntityDto;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriJoinWaitingUserUtilImpl extends BuriJoinWaitingUtilImpl {
	private BuriUserUtil userUtil;
	private BuriWaitingUserDao waitingUserDao;
	
	
	protected void saveWaitingUser(DataAccessFactory factory,BuriJoinWaitingEntityDto dto,BuriSystemContext sysContext, BranchWalker nextWalker) {
		List<IdentityInfo> idenDtos = userUtil.getUserIds(factory, sysContext, nextWalker);
		for (IdentityInfo info : idenDtos) {
			BuriWaitingUserEntityDto waitDto = new BuriWaitingUserEntityDto();
			long buriUserId = userUtil.convertBuriUserId(info);
			waitDto.setBuriUserID(buriUserId);
			waitDto.setWaitingID(dto.getWaitingID());
			waitingUserDao.insert(waitDto);
		}
		
	}


	public BuriUserUtil getUserUtil() {
		return userUtil;
	}


	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}


	public BuriWaitingUserDao getWaitingUserDao() {
		return waitingUserDao;
	}


	public void setWaitingUserDao(BuriWaitingUserDao waitingUserDao) {
		this.waitingUserDao = waitingUserDao;
	}

}
