package org.escafe.buri.dao.util.impl;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.dao.BuriBranchDao;
import org.escafe.buri.dao.BuriJoinWaitingDao;
import org.escafe.buri.dao.util.BuriJoinWaitingUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.dto.BuriBranchEntityDto;
import org.escafe.buri.dto.BuriJoinWaitingEntityDto;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriJoinWaitingUtilImpl implements BuriJoinWaitingUtil{
	private BuriJoinWaitingDao joinWaitingDao;
	private BuriPathUtil pathUtil;
	private BuriBranchDao branchDao;

	public void addWaiting(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		BuriPath nowPath = walker.getNowPath();
		BuriPath nextPath = nowPath.moveChildPath(nextName, nextId);
		BuriPath newPath = pathUtil.getBuriPathFromRealPath(nextPath);
		long pathId = newPath.getBuriPathId();
		BranchWalker nextWalker = walker.moveNext(nextName, nextId);
		BuriJoinWaitingEntityDto dto = new BuriJoinWaitingEntityDto();
		dto.setBranchID(walker.getBranchID());
		dto.setDataID(sysContext.getDataID());
		dto.setPathID(pathId);

		joinWaitingDao.insert(dto);
		saveWaitingUser(factory,dto,sysContext, nextWalker);
	}
	
	protected void saveWaitingUser(DataAccessFactory factory, BuriJoinWaitingEntityDto dto,BuriSystemContext sysContext, BranchWalker nextWalker) {
	}

	public void clearWaiting(DataAccessFactory factory,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		clearBranch(factory, sysContext, walker);
	}

    public void clearBranch(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
    	clearParentBranchID(walker.getParentBranchID(), factory, sysContext);
    }

    protected void clearParentBranchID(long parentBranchId, DataAccessFactory factory, BuriSystemContext sysContext) {
        if (parentBranchId == 0) {
            return;
        }
        List childs = branchDao.getBranchByParentID(parentBranchId);
        Iterator ite = childs.iterator();
        while (ite.hasNext()) {
            BuriBranchEntityDto child = (BuriBranchEntityDto) ite.next();
            clearParentBranchID(child.getBranchID(), factory, sysContext);
        }
        joinWaitingDao.updateClearWaitingInfo(parentBranchId);
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

	public BuriBranchDao getBranchDao() {
		return branchDao;
	}

	public void setBranchDao(BuriBranchDao branchDao) {
		this.branchDao = branchDao;
	}

}
