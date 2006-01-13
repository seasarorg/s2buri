/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ApplicationUutil;
import org.seasar.buri.xpdl.util.TagSelect;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.wfmc.x2002.xpdl10.ApplicationDocument.Application;


public class ApplicationUutilImpl implements ApplicationUutil {
    private XPathUtil pathUtil;
    private WorkFlowsUtil flowsUtil;
    
    protected String getApplicationXPath() {
        String xpath = "./xpdl:Applications/xpdl:Application";
        return xpath;
    }
    
    public Application getApplication(BuriPath buriPath,String name) {
        if(buriPath.getWorkflowProcess().length() > 0) {
            WorkflowProcessTagSelect process = flowsUtil.getWorkflowProcess(buriPath);
            Application result = getApplication(process.getXmlObject(),name);
            if(result != null) {
                return result;
            }
        }
        WorkFlowPackageTagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        Application result = getApplication(tagSelect.getXmlObject(),name);
        return result;
    }
    
    public Application getApplication(TagSelect tagSelect,String name) {
        return getApplication(tagSelect.getXmlObject(),name);
    }
    protected Application getApplication(XmlObject xmlObj,String name) {
        Application[] AppArray = getApplicationArray(xmlObj,name);
        if(AppArray!= null && AppArray.length > 0) {
            return AppArray[0];
        }
        return null;
    }

    public Application[] getApplicationArray(BuriPath buriPath,String name) {
        WorkFlowPackageTagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        return getApplicationArray(tagSelect.getXmlObject(),name);
    }
    public Application[] getApplicationArray(TagSelect tagSelect,String name) {
        return getApplicationArray(tagSelect.getXmlObject(),name);
    }
    public Application[] getApplicationArray(XmlObject xmlObj,String name) {
        XmlObject[] xmlObjs = pathUtil.getXmlObjArrayFromXPath(xmlObj,getApplicationXPath(),name,name);
        if(xmlObjs.length==0) {
            return null;
        }
        Application[] applications = (Application[])xmlObjs;
        return applications;
    }

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }
    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }
    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

}
