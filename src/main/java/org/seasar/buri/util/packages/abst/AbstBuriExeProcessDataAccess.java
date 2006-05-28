/*
 * çÏê¨ì˙: 2006/05/06
 *
 */
package org.seasar.buri.util.packages.abst;

import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.util.packages.BranchWalker;

public class AbstBuriExeProcessDataAccess extends AbstBuriExecProcess {
    private BuriStateUtil stateUtil;

    public BranchWalker readBranchWalker(BuriSystemContext sysContext) {
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        return walker;
    }
    
    protected BranchWalker setupStatus(String actId,BuriSystemContext sysContext,BranchWalker walker) {
        BranchWalker startWalker = walker.moveNext(getActivityNameById(actId),actId);
        long statusID = stateUtil.loadStatus(this,sysContext,startWalker);
        sysContext.setStatusID(new Long(statusID));
        BranchWalker loadWalker = stateUtil.loadBranchWalker(sysContext);
        return loadWalker;
    }
    
    protected void exitFlow(BuriSystemContext sysContext,BranchWalker walker) {
        stateUtil.saveBranch(walker,this,sysContext);
        long statusId = stateUtil.saveStatus(this,sysContext,walker);
        sysContext.setStatusID(new Long(statusId));
    }
    
    protected void restartActivity(BuriSystemContext sysContext,BranchWalker walker) {
        stateUtil.processed(this,sysContext,walker);
    }
    
    protected void noSelectActivity(BuriSystemContext sysContext,BranchWalker walker) {
        throw new BuriNotSelectedActivityException(walker.getNowPath(),null);
    }
    
    protected void oneSelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void oneSelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        throw new BuriManySelectActivityException(walker.getNowPath(),null);
    }
    
    
    protected BranchWalker splitAndPreprocess(BuriSystemContext sysContext,BranchWalker walker) {
        stateUtil.saveBranch(walker,this,sysContext);
        return walker;
    }
    
    protected BranchWalker getSplitAndWalker(BuriSystemContext sysContext,BranchWalker walker,BranchWalker parentBranch) {
        BranchWalker child = stateUtil.branchChild(walker,this,sysContext);
        return child;
    }
    
    protected void joinXorFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        if(walker.getParentBranchID()!=0) {
            stateUtil.abortBranch(this,sysContext,walker);
        }
    }
    
    protected boolean joinAndFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        long count = stateUtil.countNoProcessedSiblingStatus(this,sysContext,walker);
        if(count==0) {
            return true;
        }
        return false;
    }
    
    public BuriStateUtil getStateUtil() {
        return stateUtil;
    }

    public void setStateUtil(BuriStateUtil stateUtil) {
        this.stateUtil = stateUtil;
    }

    
}
