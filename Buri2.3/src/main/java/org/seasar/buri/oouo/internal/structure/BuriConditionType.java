/*
 * 作成日: 2006/03/20
 *
 */
package org.seasar.buri.oouo.internal.structure;

public class BuriConditionType {
    private String type;
    private String condition="";

    public final static String OOUOTHIS = "Condition";

    
    
    public String getCondition() {
        return condition;
    }

    public final static String setCondition_OOUOTEXT = "";
    public void setCondition(String condition) {
        String con = this.condition;
        if(con==null) {
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


    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("type=").append(type);
        buff.append("/condition=").append(condition);
        buff.append("]");
        return buff.toString();
    }

}
