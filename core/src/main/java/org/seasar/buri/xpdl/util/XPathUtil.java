/*
 * çÏê¨ì˙: 2005/11/08
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.List;

import org.apache.xmlbeans.XmlObject;

public interface XPathUtil {
    XmlObject[] getXmlObjArrayFromXPath(XmlObject xmlObj,String xpath);
    XmlObject[] getXmlObjArrayFromXPath(XmlObject xmlObj,String xpath,String name,String id);
    
    List getXmlObjListFromXPath(XmlObject xmlObj,String xpath);
    List getXmlObjListFromXPath(XmlObject xmlObj,String xpath,String name,String id);
    String createNameOrID(String name,String id);

}
