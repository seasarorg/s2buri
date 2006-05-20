/*
 * çÏê¨ì˙: 2006/05/10
 *
 */
package org.seasar.buri.dao.util;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriStateUtil {
    BranchWalker loadBranchWalker(BuriSystemContext sysContext);
    void saveBranch(BranchWalker walker,DataAccessFactory factory,BuriSystemContext sysContext);
    BranchWalker branchChild(BranchWalker parentWalker,DataAccessFactory factory,BuriSystemContext sysContext);

    long loadStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    long saveStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void processed(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void abortStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void abortBranch(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    long countNoProcessedSiblingStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
}
