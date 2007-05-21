/*
 * 作成日: 2005/06/04
 *
 */
package org.escafe.buri.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.framework.util.StringUtil;

/**
 * @author makotan
 *
 */
public class BuriRealPath {

    private String workflowPackage = "";
    private String workflowProcess = "";
    private List<String> activity = new ArrayList<String>();
    private String pathStr = "";

    public BuriRealPath() {
        this("");
    }

    public BuriRealPath(String path) {
        if (StringUtil.isEmpty(path)) {
            return;
        }
        StringTokenizer st = new StringTokenizer(path, ".");
        setPathFromToken(st);
        pathStr = path;
    }

    public void changePath(String newPath, int pos) {
        if (StringUtil.isEmpty(newPath)) {
            newPath = "";
        }
        if (activity.size() == 0) {
            getActivity().add(newPath);
        } else {
            assert pos < activity.size();
            activity.set(pos, newPath);
        }
        setupPlainName();
    }

    public void changePath(BuriActivityType newAct, int pos) {
        changePath(newAct.getName(), pos);
    }

    public BuriRealPath copyRealPath() {
        BuriRealPath clonePath = new BuriRealPath();
        clonePath.workflowPackage = workflowPackage;
        clonePath.workflowProcess = workflowProcess;
        clonePath.activity.addAll(activity);
        clonePath.pathStr = pathStr;
        return clonePath;
    }

    public List<String> getActivity() {
        return new ArrayList<String>(activity);
    }

    public String getPlainName() {
        return pathStr;
    }

    public String getWorkflowPackage() {
        return workflowPackage;
    }

    public String getWorkflowProcess() {
        return workflowProcess;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode
                + (workflowPackage == null ? 0 : workflowPackage.hashCode());
        hashCode = 31 * hashCode
                + (workflowProcess == null ? 0 : workflowProcess.hashCode());
        hashCode = 31 * hashCode + (activity == null ? 0 : activity.hashCode());
        hashCode = 31 * hashCode + (pathStr == null ? 0 : pathStr.hashCode());
        return hashCode;
    }

    public void moveChildPath(BuriActivityType newAct) {
        moveChildPath(newAct.getName());
        setupPlainName();
    }

    public void moveChildPath(String childPath) {
        activity.add(childPath);
        setupPlainName();
    }

    public void moveUpPath() {
        if (activity.size() == 0) {
            return;
        } else {
            activity.remove(activity.size() - 1);
        }
        setupPlainName();
    }

    public void setActivity(List<String> activity) {
        activity.clear();
        activity.addAll(activity);
        setupPlainName();
    }

    public void setWorkflowPackage(BuriPackageType packageDoc) {
        setWorkflowPackage(packageDoc.getName());
        setupPlainName();
    }

    public void setWorkflowPackage(String workflow) {
        if (StringUtil.isEmpty(workflow)) {
            workflowPackage = "";
        } else {
            workflowPackage = workflow;
        }
        setupPlainName();
    }

    public void setWorkflowProcess(BuriWorkflowProcessType wkfProcess) {
        setWorkflowProcess(wkfProcess.getName());
        setupPlainName();
    }

    public void setWorkflowProcess(String process) {
        if (StringUtil.isEmpty(process)) {
            workflowProcess = "";
        } else {
            workflowProcess = process;
        }
        setupPlainName();
    }

    @Override
    public String toString() {
        return pathStr;
    }

    protected boolean hasPathName(String pathName) {
        return pathName.length() > 0;
    }

    protected boolean isCorrect() {
        if (hasPathName(getWorkflowPackage())) {
            if (hasPathName(getWorkflowProcess())) {
                return isCorrectActivitys(getActivity());
            }
        }
        return false;
    }

    protected boolean isCorrectActivitys(List activitis) {
        Iterator ite = activitis.iterator();
        while (ite.hasNext()) {
            String pathName = (String) ite.next();
            if (!hasPathName(pathName)) {
                return false;
            }
        }
        return true;
    }

    protected void setPathFromToken(StringTokenizer st) {
        if (st.hasMoreTokens()) {
            workflowPackage = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            workflowProcess = st.nextToken();
        }
        List<String> activity = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            activity.add(st.nextToken());
        }
        this.activity = activity;
        setupPlainName();
    }

    protected void setupPlainName() {
        String path = workflowPackage + "." + workflowProcess;
        String act = "";
        String koron = "";
        Iterator ite = activity.iterator();
        while (ite.hasNext()) {
            act = act + koron + ite.next();
            koron = ".";
        }
        pathStr = path + koron + act;
    }

}
