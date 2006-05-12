/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;


import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.wfmc.x2002.xpdl10.ToolDocument.Tool;

public class ToolTagSelectImpl implements ToolTagSelect {
    private ActivityTagSelect activityTagSelect;
    private Tool tool;

    public ActivityTagSelect getActivityTagSelect() {
        return activityTagSelect;
    }

    public void setActivityTagSelect(ActivityTagSelect activityTagSelect) {
        this.activityTagSelect = activityTagSelect;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public void clear() {
    }

    public XmlObject getCurrentXmlObject() {
        return getTool();
    }

    public XmlObject getXmlObject() {
        return getTool();
    }

}
