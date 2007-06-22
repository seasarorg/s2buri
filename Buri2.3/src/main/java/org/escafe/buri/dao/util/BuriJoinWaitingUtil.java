package org.escafe.buri.dao.util;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;

public interface BuriJoinWaitingUtil {
	void addWaiting(BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
	void clearWaiting(BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
}
