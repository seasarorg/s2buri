/*
 * 作成日: 2006/03/07
 *
 */
package org.seasar.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

public class BuriApplicationType {
    private String id;
    private String name;
    private List ExtentedAttribute = new ArrayList();

    public final static String OOUOTHIS = "Application";
    
    public final static String setId_ATTRI = "Id";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public final static String setName_ATTRI = "Name";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtendedAttributeList() {
        return ExtentedAttribute;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
        buff.append("]");
        return buff.toString();
    }
    
}
