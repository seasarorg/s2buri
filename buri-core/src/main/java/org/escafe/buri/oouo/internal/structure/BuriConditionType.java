/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

public class BuriConditionType {
    private String type;
    private String condition = "";
    private List ExtentedAttribute = new ArrayList();

    public final static String OOUOTHIS = "Condition";

    public String getCondition() {
        return condition;
    }

    public final static String setCondition_OOUOTEXT = "";

    public void setCondition(String condition) {
        String con = this.condition;
        if (con == null) {
            con = "";
        }
        this.condition = con + condition;
    }

    public String getType() {
        return type;
    }

    public final static String setType_ATTRI = "Type";

    public void setType(String type) {
        this.type = type;
    }

    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }

    public List getExtendedAttributeList() {
        return ExtentedAttribute;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("type=").append(type);
        buff.append("/condition=").append(condition);
        buff.append("]");
        return buff.toString();
    }

}
