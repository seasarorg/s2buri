/*
 * ?ｿｽ?成?ｿｽ?ｿｽ: 2006/03/20
 *
 */
package org.seasar.buri.oouo.internal.structure;


public class BuriTransitionType {
    private String id;
    private String from;
    private String to;
    private BuriConditionType condition;
    private String conditionStr = "";
    
    public final static String OOUOTHIS = "Transition";
    
    public String getFrom() {
        return from;
    }
    public final static String setFrom_ATTRI = "From";
    public void setFrom(String from) {
        this.from = from;
    }
    public String getId() {
        return id;
    }
    public final static String setId_ATTRI = "Id";
    public void setId(String id) {
        this.id = id;
    }
    public String getTo() {
        return to;
    }
    public final static String setTo_ATTRI = "To";
    public void setTo(String to) {
        this.to = to;
    }
    public BuriConditionType getCondition() {
        return condition;
    }
    public static final String setCondition_ELEMENT = "Condition";
    public void setCondition(BuriConditionType condition) {
        this.condition = condition;
    }
    
    public boolean hasCondition() {
        if(condition == null ) {
            return false;
        }
        return true;
    }
    
    public String getConditionStr() {
        return conditionStr;
    }

    public static String setupEnd_OOUOFIN = "";
    public void setupEnd() {
        if(condition != null) {
            conditionStr = condition.getCondition();
        }
    }
    
    
    public String toString() {
        StringBuffer buff = new StringBuffer("\n[");
        buff.append("id=").append(id);
        buff.append("/from=").append(from);
        buff.append("/to=").append(to);
        buff.append("/condition=").append(condition);
        buff.append("]");
        return buff.toString();
    }
    

}
