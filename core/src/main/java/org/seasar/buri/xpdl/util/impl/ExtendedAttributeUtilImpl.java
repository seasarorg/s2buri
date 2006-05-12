/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.TagSelect;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.wfmc.x2002.xpdl10.ExtendedAttributeDocument.ExtendedAttribute;

public class ExtendedAttributeUtilImpl implements ExtendedAttributeUtil {
    private XPathUtil pathUtil;

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

    public Map get(TagSelect tagSelect) {
        return getExtendedAttribute(tagSelect);
    }
    public Map getExtendedAttribute(TagSelect tagSelect) {
        return getExtendedAttribute(tagSelect.getXmlObject());
    }

    public Map getExtendedAttribute(XmlObject xmlObj) {
        if(xmlObj==null) {
            return new HashMap();
        }
        HashMap attri = new HashMap();
        String xpath = getExtendedAttributeXpath();
        XmlObject[] xmlObjs = pathUtil.getXmlObjArrayFromXPath(xmlObj,xpath);
        for(int i=0; i < xmlObjs.length ; i++ ) {
            ExtendedAttribute extendedAttribute = (ExtendedAttribute)xmlObjs[i];
            attri.put(extendedAttribute.getName(),extendedAttribute.getValue());
        }
        return attri;
    }
    
    protected String getExtendedAttributeXpath() {
        String xpath = "./xpdl:ExtendedAttributes/xpdl:ExtendedAttribute";
        return xpath;
    }
    public Map get(XmlObject xmlObj) {
        return getExtendedAttribute(xmlObj);
    }

}
