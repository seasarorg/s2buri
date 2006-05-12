/*
 * çÏê¨ì˙: 2005/05/13
 *
 */
package org.seasar.buri.engine;

import java.util.List;


import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

/**
 * @author makotan
 *
 */
public class BuriPath extends BuriRealPath {
    
    private long buriPathID = 0;
    
    private BuriRealPath realPath = new BuriRealPath();
    
    public BuriPath() {
        this("");
    }

    public BuriPath(String path) {
        setPath(path);
        realPath.setPath(path);
    }

    public BuriPath(String path,String realStrPath) {
        setPath(path);
        realPath.setPath(realStrPath);
    }
    
    public Object clone() {
        BuriPath clonePath = new BuriPath();
        clonePath.setPath(super.getPlainName());
        clonePath.realPath = (BuriRealPath)realPath.clone();;
        clonePath.buriPathID = buriPathID;
        return clonePath;
    }
    public void changePash(String newPath) {
        super.changePash(newPath);
        realPath.changePash(newPath);
    }
    public void changePath(String newPath,String newRealPath) {
        super.changePash(newPath);
        realPath.changePash(newRealPath);
    }
    public void changePath(ActivityTagSelect newAct) {
        if(newAct==null) {
            return;
        }
        String name = newAct.getActivity().getName();
        String id = newAct.getActivity().getId();
        changePath(name,id);
    }

    public void moveChildPath(Activity newAct) {
        super.moveChildPath(newAct.getName());
        realPath.moveChildPath(newAct.getId());
    }

    public void moveChildPath(ActivityTagSelect newAct) {
        super.moveChildPath(newAct.getActivity().getName());
        realPath.moveChildPath(newAct.getActivity().getId());
    }
    
    public void moveUpPath() {
        super.moveUpPath();
        realPath.moveUpPath();
    }
    
    public void setActivity(List activity) {
        super.setActivity(activity);
        realPath.setActivity(activity);
    }
    public void setWorkflowProcess(String process) {
        super.setWorkflowProcess(process);
        realPath.setWorkflowProcess("");
    }
    public void setWorkflowProcess(String process,String realProcess) {
        super.setWorkflowProcess(process);
        realPath.setWorkflowProcess(realProcess);
    }
    public void setWorkflowProcess(WorkflowProcessTagSelect wkfProcess) {
        super.setWorkflowProcess(wkfProcess);
        realPath.setWorkflowProcess(wkfProcess.getWorkflowProcess().getId());
    }
    public void setWorkflowPackage(String workflow) {
        super.setWorkflowPackage(workflow);
        realPath.setWorkflowProcess(workflow);
    }
    public void setWorkflowPackage(String workflow,String realWorkflow) {
        super.setWorkflowPackage(workflow);
        realPath.setWorkflowProcess(realWorkflow);
    }
    public void setWorkflowPackage(WorkFlowPackageTagSelect packageDoc) {
        super.setWorkflowPackage(packageDoc.getPackageDocument().getName());
        realPath.setWorkflowPackage(packageDoc.getPackageDocument().getId());
    }
    public String toString() {
        String str = getPlainName();
        str = str + "[" + realPath.getPlainName() + "]";
        return str;
    }
    public BuriRealPath getRealPath() {
        return realPath;
    }
    public void setRealPath(BuriRealPath realBuriPath) {
        this.realPath = realBuriPath;
    }

    public long getBuriPathID() {
        return buriPathID;
    }
    
    protected void setupPlainName() {
        super.setupPlainName();
        buriPathID = 0;
    }

    public void setBuriPathID(long buriPathID) {
        this.buriPathID = buriPathID;
    }

    public boolean isCorrect() {
        if(getBuriPathID() != 0) {
            return true;
        }
        if(getRealPath().isCorrect()) {
//            if(super.isCorrect()) {
                return true;
//            }
        }
        return false;
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof BuriPath) {
            BuriPath tgtPath = (BuriPath)obj;
            if(tgtPath.getBuriPathID() != 0 && this.getBuriPathID() != 0) {
                if(tgtPath.getBuriPathID() == this.getBuriPathID()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
