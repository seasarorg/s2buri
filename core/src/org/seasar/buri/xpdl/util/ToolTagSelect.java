/*
 * çÏê¨ì˙: 2005/06/15
 *
 */
package org.seasar.buri.xpdl.util;

import org.wfmc.x2002.xpdl10.ToolDocument.Tool;

/**
 * @author makotan
 *
 */
public interface ToolTagSelect extends TagSelect{
    ActivityTagSelect getActivityTagSelect();
    void setActivityTagSelect(ActivityTagSelect activityTagSelect);
    
    Tool getTool();
    void setTool(Tool tool);
}
