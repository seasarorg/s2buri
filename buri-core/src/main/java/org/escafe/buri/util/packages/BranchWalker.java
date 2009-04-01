/*
 * 作成日: 2006/03/30
 *
 */
package org.escafe.buri.util.packages;

import org.escafe.buri.engine.BuriPath;

public class BranchWalker {
    private long parentBranchId;
    private long branchId;
    private BuriPath parentPath;
    private BuriPath nowPath;

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchID) {
        this.branchId = branchID;
    }

    public long getParentBranchId() {
        return parentBranchId;
    }

    public void setParentBranchId(long parentBranchID) {
        this.parentBranchId = parentBranchID;
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

    public BranchWalker moveNext(String actName, String realName) {
        BranchWalker result = new BranchWalker();
        result.branchId = branchId;
        result.parentBranchId = parentBranchId;
        result.parentPath = parentPath;
        result.nowPath = parentPath.moveChildPath(actName, realName);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("parentBranchId=").append(parentBranchId);
        buff.append("/branchId=").append(branchId);
        buff.append("/parentPath=").append(parentPath);
        buff.append("/nowPath=").append(nowPath);
        buff.append("]");
        return buff.toString();
    }
}
