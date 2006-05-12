/*
 * ì¬“ú: 2005/06/04
 *
 */
package org.seasar.buri.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.framework.util.StringUtil;

/**
 * @author makotan
 *
 */
public class BuriRealPath {
    private String workflowPackage = "";
    private String workflowProcess = "";
    private List activity = new ArrayList();
    private String pathStr = "";

    public BuriRealPath() {
        this("");
    }

    public BuriRealPath(String path) {
        setPath(path);
    }
    
    public void setPath(String path) {
        if(StringUtil.isEmpty(path)) {return;}
        StringTokenizer st = new StringTokenizer(path, ".");
        setPathFromToken(st);
        pathStr = path;
    }
    
    public Object clone() {
        BuriRealPath clonePath = new BuriRealPath();
        clonePath.workflowPackage = workflowPackage;
        clonePath.workflowProcess = workflowProcess;
        clonePath.activity.addAll(activity);
        clonePath.pathStr = pathStr;
        return clonePath;
    }

    protected void setPathFromToken(StringTokenizer st) {
        if(st.hasMoreTokens()) {
            setWorkflowPackage(st.nextToken());
        }
        if(st.hasMoreTokens()) {
            setWorkflowProcess(st.nextToken());
        }
        List activity = new ArrayList();
        while(st.hasMoreTokens()) {
            activity.add(st.nextToken());
        }
        setActivity(activity);
    }
    public void changePash(String newPath) {
        if(activity.size()==0) { return; }
        if(StringUtil.isEmpty(newPath)) { 
            newPath=""; 
        }
        activity.remove(activity.size()-1);
        activity.add(newPath);
        setupPlainName();
    }
    public void changePath(ActivityTagSelect newAct) {
        changePash(newAct.getActivity().getName());
        setupPlainName();
    }
    
    public void moveChildPath(String childPath) {
        activity.add(childPath);
        setupPlainName();
    }
    public void moveChildPath(ActivityTagSelect newAct) {
        moveChildPath(newAct.getActivity().getName());
        setupPlainName();
    }
    
    public void moveUpPath() {
        if(activity.size()==0) { return; }
        activity.remove(activity.size()-1);
        setupPlainName();
    }
    
    public List getActivity() {
        return activity;
    }
    public void setActivity(List activity) {
        this.activity = activity;
        setupPlainName();
    }
    public String getWorkflowProcess() {
        return workflowProcess;
    }
    public void setWorkflowProcess(String process) {
        if(StringUtil.isEmpty(process)) { 
            process=""; 
        }
        this.workflowProcess = process;
        setupPlainName();
    }
    public void setWorkflowProcess(WorkflowProcessTagSelect wkfProcess) {
        setWorkflowProcess(wkfProcess.getWorkflowProcess().getName());
        setupPlainName();
    }
    public String getWorkflowPackage() {
        return workflowPackage;
    }
    public void setWorkflowPackage(String workflow) {
        if(StringUtil.isEmpty(workflow)) {
            return;
        }
        this.workflowPackage = workflow;
        setupPlainName();
    }
    public void setWorkflowPackage(WorkFlowPackageTagSelect packageDoc) {
        setWorkflowPackage(packageDoc.getPackageDocument().getName());
        setupPlainName();
    }
    
    public String getPlainName() {
        return pathStr;
    }
    
    protected void setupPlainName() {
        String path = workflowPackage + "." + workflowProcess;
        String act = "";
        String koron = "";
        Iterator ite = activity.iterator();
        while(ite.hasNext()) {
            act = act + koron + ite.next();
            koron = ".";
        }
        pathStr = path + koron + act;
    }
    
    public String toString() {
        return pathStr;
    }
    
    protected boolean isCorrect() {
        if(hasPathName(getWorkflowPackage())) {
            if(hasPathName(getWorkflowProcess())) {
                return isCorrectActivitys(getActivity());
            }
        }
        return false;
    }
    
    protected boolean isCorrectActivitys(List activitis) {
        Iterator ite = activitis.iterator();
        while(ite.hasNext()) {
            String pathName = (String)ite.next();
            if( ! hasPathName(pathName)) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean hasPathName(String pathName) {
        return pathName.length() > 0;
    }
    
    
    
}
