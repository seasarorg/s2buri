/*
 * çÏê¨ì˙: 2005/11/23
 *
 */
package org.seasar.buri.tablepickup;

import java.util.Collection;

import jxl.Cell;

public interface Pickup {

    void setValColName(String valColName);

    void setSheetName(String name);

    void addPickUp(String str, String str2, Cell valCell);
    
    Object pickup(Object data);
    Collection getValues(String title);
    Object pickupLite(Object yObject, Object xObject);
    Object lookup(Object data,String valColName);
    Object lookup(Object data);
    Object lookupLite(Object obj,String valColName);
    Object lookupLite(Object obj);

}
