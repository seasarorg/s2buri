package org.escafe.buri.dao.util.impl;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.dao.util.BuriJoinWaitingUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.entity.BuriBranchEntity;
import org.escafe.buri.entity.BuriJoinWaitingEntity;
import org.escafe.buri.service.BuriBranchEntityService;
import org.escafe.buri.service.BuriJoinWaitingEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriJoinWaitingUtilImpl implements BuriJoinWaitingUtil {
	private BuriJoinWaitingEntityService buriJoinWaitingEntityService;

	private BuriPathUtil pathUtil;

	private BuriBranchEntityService buriBranchEntityService;

	public void addWaiting(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker, String nextName,
	        String nextId) {
		BranchWalker nextWalker = walker.moveNext(nextName, nextId);
		BuriPath nextPath = nextWalker.getNowPath();
		BuriPath newPath = pathUtil.getBuriPathFromRealPath(nextPath);
		long pathId = newPath.getBuriPathId();
		BuriJoinWaitingEntity dto = new BuriJoinWaitingEntity();
		dto.branchId = walker.getBranchID();
		dto.dataId = sysContext.getDataID();
		dto.pathId = pathId;
		buriJoinWaitingEntityService.insert(dto);
		saveWaitingUser(factory, dto, sysContext, nextWalker);
	}

	protected void saveWaitingUser(DataAccessFactory factory,
	        BuriJoinWaitingEntity dto, BuriSystemContext sysContext,
	        BranchWalker nextWalker) {
	}

	public void clearWaiting(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker, String nextName,
	        String nextId) {
		clearBranch(factory, sysContext, walker);
	}

	public void clearBranch(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		clearParentBranchID(walker.getParentBranchID(), factory, sysContext);
	}

	protected void clearParentBranchID(long parentBranchId,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		if (parentBranchId == 0) {
			return;
		}
		List<BuriBranchEntity> childs =
		    buriBranchEntityService.getBranchByParentID(parentBranchId);
		Iterator<BuriBranchEntity> ite = childs.iterator();
		while (ite.hasNext()) {
			BuriBranchEntity child = ite.next();
			clearParentBranchID(child.branchId, factory, sysContext);
		}
		buriJoinWaitingEntityService.updateClearWaitingInfo(parentBranchId);
	}

	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}

	public BuriJoinWaitingEntityService getBuriJoinWaitingEntityService() {
		return buriJoinWaitingEntityService;
	}

	public void setBuriJoinWaitingEntityService(
	        BuriJoinWaitingEntityService buriJoinWaitingEntityService) {
		this.buriJoinWaitingEntityService = buriJoinWaitingEntityService;
	}

	public BuriBranchEntityService getBuriBranchEntityService() {
		return buriBranchEntityService;
	}

	public void setBuriBranchEntityService(
	        BuriBranchEntityService buriBranchEntityService) {
		this.buriBranchEntityService = buriBranchEntityService;
	}
}
