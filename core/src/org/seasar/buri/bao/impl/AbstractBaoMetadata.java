/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import java.util.Arrays;
import java.util.List;

import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoMetadata;

public abstract class AbstractBaoMetadata implements BaoFunctionMetadata {
    private List argName = Arrays.asList(new String[]{"data"});
    private String activityName = "";
    private BaoMetadata baoMetadata = null;
    
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

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("argName=").append(argName);
        buff.append("activityName=").append(activityName);
        buff.append("/baoMetadata=").append(baoMetadata.getBaoClass());
        buff.append("]");
        return buff.toString();
    }

}
