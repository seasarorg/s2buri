/*
 * 作成日: 2006/04/04
 *
 */
package org.escafe.buri.component.impl;

import org.escafe.buri.component.BuriComponent;
import org.escafe.buri.component.BuriComponentUtil;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriApplicationType;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.seasar.framework.container.S2Container;

public class BuriComponentUtilImpl implements BuriComponentUtil {
    private S2Container container;

    public String getJavaProcessCode(String tgtObjName,BuriToolType toolType,BuriActivityType actType) {
        BuriWorkflowProcessType processType = actType.getWorkflowProcess(); 
        if( processType == null) {
            processType = actType.getActivitySet().getProcessType();
        }
        BuriApplicationType appType = processType.getApplicationById(toolType.getId());
        BuriExtendedAttributeType attriType = ExtentedAttributeUtil.getExtendedAttribute(appType.getExtendedAttributeList(),"after");
        if( attriType != null) {
            return "sysContext.addAfterCallMethods(\"" + actType.getId() + "\");";
        }
        BuriComponent component = (BuriComponent)container.getComponent(appType.getName());
        String source = component.getBuriExecuteSource(tgtObjName,toolType);
        return source;
    }
    
    public String getJavaAfterProcessCode(String tgtObjName,BuriToolType toolType,BuriActivityType actType) {
        BuriWorkflowProcessType processType = actType.getWorkflowProcess(); 
        if( processType == null) {
            processType = actType.getActivitySet().getProcessType();
        }
        BuriApplicationType appType = processType.getApplicationById(toolType.getId());
        if(ExtentedAttributeUtil.getExtendedAttribute(appType.getExtendedAttributeList(),"after") != null) {
            BuriComponent component = (BuriComponent)container.getComponent(appType.getName());
            String source = component.getBuriExecuteSource(tgtObjName,toolType);
            return source;
        }
        return "";
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
