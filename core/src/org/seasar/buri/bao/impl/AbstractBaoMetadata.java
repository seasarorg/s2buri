/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoMetadata;

public abstract class AbstractBaoMetadata implements BaoFunctionMetadata {
    private List argName = Arrays.asList(new String[]{"data"});
    private String activityName = "";
    private BaoMetadata baoMetadata = null;
    private List validateAction = new ArrayList();
    
    public void setBaoMetadata(BaoMetadata baoMetadata) {
        this.baoMetadata = baoMetadata;
    }
    
    public BaoMetadata getBaoMetadata() {
        return baoMetadata;
    }

    public List getArgName() {
        return argName;
    }

    public void setArgName(List argName) {
        this.argName = argName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List getValidateAction() {
        return validateAction;
    }

    public void setValidateAction(List validateAction) {
        this.validateAction = validateAction;
    }


    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("argName=").append(argName);
        buff.append("activityName=").append(activityName);
        buff.append("/baoMetadata=").append(baoMetadata.getBaoClass());
        buff.append("/validateAction=").append(validateAction);
        buff.append("]");
        return buff.toString();
    }

}
