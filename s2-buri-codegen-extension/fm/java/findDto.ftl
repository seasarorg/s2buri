<#include "macro/FindProperty.ftl"/>
package ${package}.${defaultDir?lower_case};

<#assign importList=table.fieldImportDef>
<#list table.fieldImportDef as importName>
import ${importName};
</#list>
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class ${table.tableNameForDto?cap_first}${defaultDir} {
	public static final String TABLE = "${table.tableName}";
    private ArrayList orderList = new ArrayList();
	
	<@field table=table tableName=""/>

	<@proerty table=table tableName=""/>

    public void addOrderList(String order) {
        orderList.add(order);
    }

    public void addOrderList(String order,boolean isAsc) {
        orderList.add(order);
        ScriptProcessor processor = new ScriptProcessor();
        processor.setValue(order.replace('.','_') + "_isASC",this,new Boolean(isAsc));
    }
    
    public String getOrderList() {
        String order = "";
        String ORDER = "ORDER BY ";
        Iterator ite = orderList.iterator();
        ScriptProcessor processor = new ScriptProcessor();
        while(ite.hasNext()) {
            String orderTgt = (String)ite.next();
            order = ORDER + order + orderTgt.replace('_','.') + " ";
            Boolean var = (Boolean)processor.getValue(orderTgt + "_isASC", this);
            if( ! var.booleanValue()) {
                order = order + "DESC ";
            }
            ORDER = "";
        }
        return order;
    }

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		<@toString table=table tableName=""/>
		buff.append("]");
		return buff.toString();
	}
	
}
