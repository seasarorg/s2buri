/*
 * çÏê¨ì˙: 2005/12/01
 *
 */
package org.seasar.buri.component;

import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.xpdl.util.ToolTagSelect;

public interface AfterProcessBuriComponent {
    public void buriExecute(ScriptProcessor processor,ToolTagSelect tool,Map contextData);

}
