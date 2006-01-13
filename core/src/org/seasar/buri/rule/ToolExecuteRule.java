/*
 * çÏê¨ì˙: 2005/06/10
 *
 */
package org.seasar.buri.rule;


import java.util.Map;

import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.framework.container.S2Container;

/**
 * @author makotan
 *
 */
public interface ToolExecuteRule {
    void executeTool(S2Container container,ActivityTagSelect targetTagSelect,ToolTagSelect tool,Object root,Map contextData);
    void afterExecuteTool();
}
