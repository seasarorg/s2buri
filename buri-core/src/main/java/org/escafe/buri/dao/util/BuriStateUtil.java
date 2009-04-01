/*
 * 作成日: 2006/05/10
 *
 */
package org.escafe.buri.dao.util;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriStateUtil {
	BranchWalker loadBranchWalker(BuriSystemContext sysContext);

	void saveBranch(BranchWalker walker, DataAccessFactory factory,
	        BuriSystemContext sysContext);

	BranchWalker branchChild(BranchWalker parentWalker,
	        DataAccessFactory factory, BuriSystemContext sysContext);

	Long loadStatus(DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker);

	Long saveStatus(DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker);

	void processed(DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker);

	void abortStatus(DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker);

	void abortBranch(DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker);

	Long countNoProcessedSiblingStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker);
}
