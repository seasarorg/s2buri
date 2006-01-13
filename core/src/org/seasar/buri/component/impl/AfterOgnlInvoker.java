/*
 * çÏê¨ì˙: 2005/12/01
 *
 */
package org.seasar.buri.component.impl;

import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.component.AfterProcessBuriComponent;
import org.seasar.buri.component.OgnlInvoker;
import org.seasar.buri.xpdl.util.ToolTagSelect;

public class AfterOgnlInvoker implements AfterProcessBuriComponent {
    private OgnlInvoker ognlInvoker;

    public void buriExecute(ScriptProcessor processor, ToolTagSelect tool,
            Map contextData) {
        ognlInvoker.buriExecute(processor,tool,contextData);
    }

    public OgnlInvoker getOgnlInvoker() {
        return ognlInvoker;
    }

    public void setOgnlInvoker(OgnlInvoker ognlInvoker) {
        this.ognlInvoker = ognlInvoker;
    }

}
