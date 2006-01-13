/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.DataFieldUtil;
import org.seasar.buri.xpdl.util.TagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.wfmc.x2002.xpdl10.DataFieldDocument.DataField;


public class DataFieldUtilImpl implements DataFieldUtil {
    private XPathUtil pathUtil;
    private WorkFlowsUtil flowsUtil;

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }
    
    public DataField getDataField(BuriPath buriPath,String className) {
        TagSelect tagSelect;
        if(buriPath.getWorkflowProcess().length() > 0) {
            tagSelect = flowsUtil.getWorkflowProcess(buriPath);
            DataField result = getDataField(tagSelect,className);
            if(result != null) {
                return result;
            }
        }
        tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        return getDataField(tagSelect,className);
    }
    protected DataField getDataField(TagSelect tagSelect,String className) {
        XmlObject[] xmlObjs = pathUtil.getXmlObjArrayFromXPath(tagSelect.getXmlObject(),getDataFieldXPath(),null,className);
        if(xmlObjs.length==0) {
            return null;
        }
        DataField[] dataFields = (DataField[])xmlObjs;
        return dataFields[0];
    }
    
    protected String getDataFieldXPath() {
        String xpath = "./xpdl:DataFields/xpdl:DataField";
        return xpath;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }
    
}
