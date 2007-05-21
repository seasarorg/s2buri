/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

public class BuriToolType {
    private String id;
    private String type;
    private List ExtentedAttribute = new ArrayList();

    public static final String OOUOTHIS = "Tool";

    public String getId() {
        return id;
    }

    public static final String setId_ATTRI = "Id";

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public static final String setType_ATTRI = "Type";

    public void setType(String type) {
        this.type = type;
    }

    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }

    public List getExtendedAttribute() {
        return ExtentedAttribute;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/type=").append(type);
        buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
        return buff.toString();
    }

}
