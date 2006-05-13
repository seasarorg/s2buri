/*
 * çÏê¨ì˙: 2006/04/04
 *
 */
package org.seasar.buri.component.impl;

import org.seasar.buri.component.BuriComponent;
import org.seasar.buri.component.BuriComponentUtil;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriApplicationType;
import org.seasar.buri.oouo.internal.structure.BuriToolType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.framework.container.S2Container;

public class BuriComponentUtilImpl implements BuriComponentUtil {
    private S2Container container;

    public String getJavaProcessCode(BuriToolType toolType,BuriActivityType actType) {
        BuriWorkflowProcessType processType = actType.getWorkflowProcess(); 
        if( processType == null) {
            processType = actType.getActivitySet().getProcessType();
        }
        BuriApplicationType appType = processType.getApplicationById(toolType.getId());
        BuriComponent component = (BuriComponent)container.getComponent(appType.getName());
        String source = component.getBuriExecuteSource(toolType);
        return source;
    }
    
    public String convScriptToJavaString(String text) {
        if(text==null) {
            return "";
        }
        text = text.replaceAll("\\\\","\\\\\\\\");
        text = text.replaceAll("\"","\\\\\"");
        text = text.replaceAll("\n","\\\\n");
        return text;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

}
