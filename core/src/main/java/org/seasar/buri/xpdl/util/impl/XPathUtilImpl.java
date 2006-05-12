/*
 * çÏê¨ì˙: 2005/05/30
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.List;


import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.common.Constants;
import org.seasar.buri.common.util.ArrayUtil;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author makotan
 *
 */
public class XPathUtilImpl implements XPathUtil{
    
    public XmlObject[] getXmlObjArrayFromXPath(XmlObject xmlObj,String xpath) {
        return getXmlObjArrayFromXPath(xmlObj,xpath,null,null);
    }
    public XmlObject[] getXmlObjArrayFromXPath(XmlObject xmlObj,String xpath,String name,String id) {
        String selectPath = Constants.XPDLQueryPrefix+xpath+createNameOrID(name,id);
        XmlObject[] xmlObjs = xmlObj.selectPath(selectPath);
        return xmlObjs;
    }
    
    public List getXmlObjListFromXPath(XmlObject xmlObj,String xpath) {
        return getXmlObjListFromXPath(xmlObj,xpath,null,null);
    }
    
    public List getXmlObjListFromXPath(XmlObject xmlObj,String xpath,String name,String id) {
        XmlObject[] xmlObjs = getXmlObjArrayFromXPath(xmlObj,xpath,name,id);
        return ArrayUtil.arrayToList(xmlObjs);
    }
    
    public String createNameOrID(String name,String id) {
        String selectPath = "";
        if(isDoubleEmpty(name,id)) {
            selectPath = "";

        } else if( ! StringUtil.isEmpty(name) && StringUtil.isEmpty(id)) {
            selectPath = "[@Name='" + name + "']";
        
        } else if(id.equals(name)) {
            selectPath = "[@Name='" + name + "' or" + " @Id='" + id + "']";
        
        } else{
            selectPath = "[@Id='" + id + "']";
        }
        return selectPath;
    }
    
    private boolean isDoubleEmpty(String str1,String str2) {
        return StringUtil.isEmpty(str1) && StringUtil.isEmpty(str2);
    }
    
    
}
