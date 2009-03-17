/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.escafe.buri.bao.BaoFunctionMetadata;
import org.escafe.buri.bao.BaoMetadata;
import org.seasar.framework.util.StringUtil;

public abstract class AbstractBaoMetadata implements BaoFunctionMetadata {
    private List<String> argName = Arrays.asList(new String[] { "data" });
    private String activityName = "";
    private List<String> activityNames = new ArrayList<String>();
    private BaoMetadata baoMetadata = null;
    private List<String> validateAction = new ArrayList<String>();

    public void setBaoMetadata(BaoMetadata baoMetadata) {
        this.baoMetadata = baoMetadata;
    }

    public BaoMetadata getBaoMetadata() {
        return baoMetadata;
    }

    public List<String> getArgName() {
        return argName;
    }

    public void setArgName(List<String> argName) {
        this.argName = argName;
    }

    public String getActivityName() {
        return activityName;
    }

    public List<String> getActivityNames() {
        return activityNames;
    }

    public void setActivityNames(List<String> activityNames) {
        this.activityNames = activityNames;
    }

    public void addActivityNames(String actName) {
        if (StringUtil.isEmpty(activityName)) {
            activityName = actName;
        }
        this.activityNames.add(actName);
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<String> getValidateAction() {
        return validateAction;
    }

    public void setValidateAction(List<String> validateAction) {
        this.validateAction = validateAction;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("argName=").append(argName);
        buff.append("activityName=").append(activityName);
        buff.append("activityNames=").append(activityNames);
        buff.append("/baoMetadata=").append(baoMetadata.getBaoClass());
        buff.append("/validateAction=").append(validateAction);
        buff.append("]");
        return buff.toString();
    }

}
