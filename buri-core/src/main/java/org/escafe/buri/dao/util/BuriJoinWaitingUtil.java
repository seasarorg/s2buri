package org.escafe.buri.dao.util;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriJoinWaitingUtil {
	void addWaiting(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
	void clearWaiting(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
}
