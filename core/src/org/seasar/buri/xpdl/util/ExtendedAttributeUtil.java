/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.Map;

import org.apache.xmlbeans.XmlObject;

public interface ExtendedAttributeUtil {
    Map getExtendedAttribute(TagSelect tagSelect);
    Map get(TagSelect tagSelect);
    Map getExtendedAttribute(XmlObject xmlObj);
    Map get(XmlObject xmlObj);
}
