/*
 * 作成日: 2005/05/13
 *
 */
package org.escafe.buri.engine;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;

/**
 * @author makotan
 *
 */
public class BuriPath {

    private long buriPathId = 0;
    private BuriRealPath thisPath = new BuriRealPath();
    private BuriRealPath realPath = new BuriRealPath();
    private Long pathType;

    public BuriPath() {
        this("");
    }

    public BuriPath(String path) {
        thisPath = new BuriRealPath(path);
    }

    public BuriPath(String path, String realStrPath) {
        thisPath = new BuriRealPath(path);
        realPath = new BuriRealPath(realStrPath);
    }

    public BuriPath(String path, String realStrPath, long pathId) {
        thisPath = new BuriRealPath(path);
        realPath = new BuriRealPath(realStrPath);
        buriPathId = pathId;
    }

    public BuriPath(String path, String realStrPath, long pathId, Long pathType) {
        thisPath = new BuriRealPath(path);
        realPath = new BuriRealPath(realStrPath);
        buriPathId = pathId;
        this.pathType = pathType;
    }

    public BuriPath(String path, String realStrPath, Long pathType) {
        thisPath = new BuriRealPath(path);
        realPath = new BuriRealPath(realStrPath);
        this.pathType = pathType;
    }

    public BuriPath copy() {
        BuriPath clonePath = new BuriPath();
        clonePath.thisPath = thisPath.copyRealPath();
        clonePath.realPath = realPath.copyRealPath();
        clonePath.buriPathId = buriPathId;
        clonePath.pathType = pathType;
        return clonePath;
    }

    /*
     これを使わずにいけると思ったので・・・封印！
     封印解除はこれを使わないと絶対に実装できないとき
     public BuriPath changePath(String newPath,int pos) {
     BuriPath clonePath = copy();
     clonePath.thisPath = thisPath.changePash(newPath,pos);
     assert realPath.getActivity().size() == 0: "realPathを設定しているのに論理パスだけ設定";
     return clonePath;
     }
     public BuriPath changePath(String newPath,String newRealPath,int pos) {
     BuriPath clonePath = copy();
     clonePath.thisPath = thisPath.changePash(newPath,pos);
     clonePath.realPath = realPath.changePash(newRealPath,pos);
     return clonePath;
     }
     public BuriPath changePath(BuriActivityType newAct,int pos) {
     BuriPath clonePath = copy();
     if(newAct==null) {
     return clonePath;
     }
     clonePath.thisPath = thisPath.changePash(newAct.getName(),pos);
     clonePath.realPath = realPath.changePash(newAct.getId(),pos);
     return clonePath;
     }
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BuriPath) {
            BuriPath tgtPath = (BuriPath) obj;
            if (tgtPath.getBuriPathId() != 0 && this.getBuriPathId() != 0) {
                if (tgtPath.getBuriPathId() == this.getBuriPathId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getActivityId() {
        return new ArrayList<String>(realPath.getActivity());
    }

    public List<String> getActivityName() {
        return new ArrayList<String>(thisPath.getActivity());
    }

    public long getBuriPathId() {
        return buriPathId;
    }

    public Long getPathType() {
        return pathType;
    }

    public String getPlainName() {
        return thisPath.getPlainName();
    }

    public BuriRealPath getRealPath() {
        return realPath;
    }

    public String getWorkflowPackage() {
        return thisPath.getWorkflowPackage();
    }

    public String getWorkflowProcess() {
        return thisPath.getWorkflowProcess();
    }

    /**
     * Override hashCode.
     *
     * @return the Objects hashcode.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (int) (+buriPathId ^ (buriPathId >>> 32));
        hashCode = 31 * hashCode + (thisPath == null ? 0 : thisPath.hashCode());
        hashCode = 31 * hashCode + (realPath == null ? 0 : realPath.hashCode());
        return hashCode;
    }

    public boolean isCorrect() {
        if (getBuriPathId() != 0) {
            return true;
        }
        if (getRealPath().isCorrect()) {
            return true;
        }
        return false;
    }

    public BuriPath moveChildPath(BuriActivityType newAct) {
        BuriPath clonePath = moveChildPath(newAct.getName(), newAct.getId());
        return clonePath;
    }

    public BuriPath moveChildPath(String newPath, String newRealPath) {
        BuriPath clonePath = copy();
        clonePath = clonePath.setBuriPathId(0);
        clonePath.thisPath.moveChildPath(newPath);
        clonePath.realPath.moveChildPath(newRealPath);
        return clonePath;
    }

    public BuriPath moveUpPath() {
        BuriPath clonePath = copy();
        clonePath = clonePath.setBuriPathId(0);
        clonePath.thisPath.moveUpPath();
        clonePath.realPath.moveUpPath();
        return clonePath;
    }

    public BuriPath setBuriPathId(long buriPathId) {
        BuriPath clonePath = copy();
        clonePath.buriPathId = buriPathId;
        return clonePath;
    }

    public BuriPath setPathType(Long pathType) {
        BuriPath clonePath = copy();
        clonePath.pathType = pathType;
        return clonePath;
    }

    public BuriPath setWorkflowPackage(BuriPackageType packageDoc) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowPackage(packageDoc.getName());
        clonePath.realPath.setWorkflowPackage(packageDoc.getId());
        return clonePath;
    }

    public BuriPath setWorkflowPackage(String workflow) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowPackage(workflow);
        assert realPath.getWorkflowPackage().length() == 0 : "realPathを設定しているのに論理パスだけ設定";
        return clonePath;
    }

    public BuriPath setWorkflowPackage(String workflow, String realWorkflow) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowPackage(workflow);
        clonePath.realPath.setWorkflowPackage(realWorkflow);
        return clonePath;
    }

    public BuriPath setWorkflowProcess(BuriWorkflowProcessType wkfProcess) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowProcess(wkfProcess);
        clonePath.realPath.setWorkflowProcess(wkfProcess.getId());
        return clonePath;
    }

    public BuriPath setWorkflowProcess(String process) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowProcess(process);
        assert realPath.getWorkflowProcess().length() == 0 : "realPathを設定しているのに論理パスだけ設定";
        return clonePath;
    }

    public BuriPath setWorkflowProcess(String process, String realProcess) {
        BuriPath clonePath = copy();
        clonePath.thisPath.setWorkflowProcess(process);
        clonePath.realPath.setWorkflowProcess(realProcess);
        return clonePath;
    }

    @Override
    public String toString() {
        String str = thisPath.getPlainName();
        str = str + "[" + realPath.getPlainName() + "]";
        return str;
    }

}
