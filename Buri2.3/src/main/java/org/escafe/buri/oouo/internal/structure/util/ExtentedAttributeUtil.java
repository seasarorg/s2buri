/*
 * 作成日: 2006/03/26
 *
 */
package org.escafe.buri.oouo.internal.structure.util;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;

public class ExtentedAttributeUtil {
    public static String getAttributeVal(List attriList,String key) {
        BuriExtendedAttributeType attri = getExtendedAttribute(attriList,key);
        if(attri!=null) {
            return attri.getValue();
        }
        return null;
    }
    
    public static BuriExtendedAttributeType getExtendedAttribute(List attriList,String key) {
        Iterator ite = attriList.iterator();
        while(ite.hasNext()) {
            BuriExtendedAttributeType attri = (BuriExtendedAttributeType)ite.next();
            if(attri.getName().compareToIgnoreCase(key)==0) {
                return attri;
            }
        }
        return null;
    }
}
