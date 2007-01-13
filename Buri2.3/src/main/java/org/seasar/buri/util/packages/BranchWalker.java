/*
 * 作成日: 2006/03/30
 *
 */
package org.seasar.buri.util.packages;

import org.seasar.buri.engine.BuriPath;

public class BranchWalker {
    private long parentBranchID;
    private long branchID;
    private BuriPath parentPath;
    private BuriPath nowPath;
    
    public long getBranchID() {
        return branchID;
    }
    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }
    public long getParentBranchID() {
        return parentBranchID;
    }
    public void setParentBranchID(long parentBranchID) {
        this.parentBranchID = parentBranchID;
    }
    public BuriPath getParentPath() {
        return parentPath;
    }
    public void setParentPath(BuriPath parentPath) {
        this.parentPath = parentPath;
    }
    public BuriPath getNowPath() {
        return nowPath;
    }
    public void setNowPath(BuriPath nowPath) {
        this.nowPath = nowPath;
    }
    
    public BranchWalker moveNext(String actName,String realName) {
        BranchWalker result = new BranchWalker();
        result.branchID = branchID;
        result.parentBranchID = parentBranchID;
        result.parentPath = parentPath;
        result.nowPath = parentPath.moveChildPath(actName,realName);
        return result;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("parentBranchID=").append(parentBranchID);
        buff.append("/branchID=").append(branchID);
        buff.append("/parentPath=").append(parentPath);
        buff.append("/nowPath=").append(nowPath);
        buff.append("]");
        return buff.toString();
    }
}
