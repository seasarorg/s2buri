/*
 * 作成日: 2006/03/19
 *
 */
package org.seasar.buri.oouo.internal.structure;

public class BuriExtendedAttributeType {
    private String name;
    private String value;
    
    public static final String OOUOTHIS = "ExtendedAttribute";

    public String getName() {
        return name;
    }

    public static final String setName_ATTRI = "Name";
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public static final String setValue_ATTRI = "Value";
    public void setValue(String value) {
        this.value = value;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer("[");
        buffer.append("name=").append(name);
        buffer.append("/value=").append(value);
        buffer.append("]");
        return buffer.toString();
    }
}
