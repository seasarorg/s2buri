/*
 * çÏê¨ì˙: 2006/05/10
 *
 */
package org.seasar.buri.dao.util;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriStateUtil {
    BranchWalker getNowPathBranchWalker(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BuriPath callPath);
    long saveStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void processed(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void abortStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    void abortBranch(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    long countNoProcessedSiblingStatus(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
}
