package ${package}.${defaultDir?lower_case};

import ${package}.dto.${table.tableName?cap_first}Dto;
<#assign parentTables=table.parentTable>
<#list parentTables as parentTable>
<#if table.tableName != parentTable.tableName >
import ${package}.dto.${parentTable.tableName?cap_first}Dto;
</#if>
</#list>

public class ${table.tableName?cap_first}PackDto extends ${table.tableName?cap_first}Dto {

	<#list parentTables as parentTable>
	public static final int ${parentTable.tableName?uncap_first}Dto_RELNO = ${parentTable_index};
		<#assign childFieldName=parentTable.childFieldName>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<#assign parentTablePkey=parentTableData.primaryKey?first.field.fieldName>
	public static final String ${parentTable.tableName?uncap_first}Dto_RELKEYS = "${parentTablePkey}:${childFieldName}";

	</#list>

	<#list parentTables as parentTable>
	private ${parentTable.tableName?cap_first}Dto ${parentTable.tableName?uncap_first};
	
	</#list>

	<#list parentTables as parentTable>
	public ${parentTable.tableName?cap_first}Dto get${parentTable.tableName?cap_first}Dto() {
		return this.${parentTable.tableName?uncap_first};
	}

	public void set${parentTable.tableName?cap_first}Dto(${parentTable.tableName?cap_first}Dto ${parentTable.tableName?uncap_first}) {
		this.${parentTable.tableName?uncap_first} = ${parentTable.tableName?uncap_first};
	}
	</#list>

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toString());
		<#list parentTables as parentTable>
		buf.append("${parentTable.tableName?uncap_first}Dto").append(${parentTable.tableName?uncap_first});
		</#list>
		return buf.toString();
	}

}
