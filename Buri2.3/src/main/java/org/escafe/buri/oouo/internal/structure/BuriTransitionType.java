/*
 * ?ｿｽ?成?ｿｽ?ｿｽ: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.seasar.framework.util.StringUtil;


public class BuriTransitionType {
    private String id;
    private String from;
    private String to;
    private BuriConditionType condition;
    private String conditionStr = "";
    private List ExtentedAttribute = new ArrayList();
    
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
    
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtendedAttributeList() {
        return ExtentedAttribute;
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
    	String action = null;
        if(condition != null) {
            conditionStr = condition.getCondition();
        	action = ExtentedAttributeUtil.getAttributeVal(getExtendedAttributeList(), "action");
        }
        if(StringUtil.isEmpty(conditionStr) && action != null) {
        	condition = new BuriConditionType();
        	conditionStr = "#action == \"" + action + "\"" ;
        	condition.setType("CONDITION");
        	condition.setCondition(conditionStr);
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
