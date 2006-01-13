/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;

public interface ToolUtil {
    List getTools(ActivityTagSelect tagSelect);
    void processActivity(ActivityTagSelect tagSelect,S2Container container,Object root,Map contextData);
    
}
