package org.escafe.buri.dao.util.impl;

import org.escafe.buri.dao.BuriJoinWaitingDao;
import org.escafe.buri.dao.util.BuriJoinWaitingUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.dto.BuriJoinWaitingEntityDto;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;

public class BuriJoinWaitingUtilImpl implements BuriJoinWaitingUtil{
	private BuriJoinWaitingDao joinWaitingDao;
	private BuriPathUtil pathUtil;

	public void addWaiting(BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		BuriPath nowPath = walker.getNowPath();
		BuriPath nextPath = nowPath.moveChildPath(nextName, nextId);
		long pathId = pathUtil.getBuriPathFromRealPath(nextPath).getBuriPathId();
		BuriJoinWaitingEntityDto dto = new BuriJoinWaitingEntityDto();
		dto.setBranchID(walker.getBranchID());
		dto.setDataID(sysContext.getDataID());
		dto.setPathID(pathId);
		if(sysContext.getAppUserId() != null) {
			dto.setUserIDNum(sysContext.getAppUserId().getIdNumber());
			dto.setUserIDVal(sysContext.getAppUserId().getIdString());
		}

		joinWaitingDao.insert(dto);
	}

	public void clearWaiting(BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		joinWaitingDao.updateClearWaitingInfo(walker.getBranchID());
	}

	public BuriJoinWaitingDao getJoinWaitingDao() {
		return joinWaitingDao;
	}

	public void setJoinWaitingDao(BuriJoinWaitingDao joinWaitingDao) {
		this.joinWaitingDao = joinWaitingDao;
	}

	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}

}
