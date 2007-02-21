package ${package}.${defaultDir?lower_case};

import ${package}.dto.${table.tableNameForDto?cap_first}Dto;
<#assign parentTables=table.parentTableName>
<#assign tableNames = parentTables?keys>
<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	<#if table.tableName != parentTable.tableName >
import ${package}.dto.${parentTable.tableNameForDto?cap_first}Dto;
	</#if>
</#list>

public class ${table.tableNameForDto?cap_first}PackDto extends ${table.tableNameForDto?cap_first}Dto {

<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	public static final int ${tableName?uncap_first}_RELNO = ${tableName_index};
		<#assign childFieldName=parentTable.childFieldNameForDto>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<#assign parentTablePkey=parentTableData.primaryKey?first.field.fieldName>
	public static final String ${tableName?uncap_first}_RELKEYS = "${parentTablePkey}:${childFieldName}";

</#list>

<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	private ${parentTable.tableNameForDto?cap_first}Dto ${tableName?uncap_first};
	
</#list>

<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	public ${parentTable.tableNameForDto?cap_first}Dto get${tableName?cap_first}() {
		return this.${tableName?uncap_first};
	}

	public void set${tableName?cap_first}(${parentTable.tableNameForDto?cap_first}Dto ${tableName?uncap_first}) {
		this.${tableName?uncap_first} = ${tableName?uncap_first};
	}
</#list>

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toString());
<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
		buf.append("${tableName?uncap_first}").append(${tableName?uncap_first});
</#list>
		return buf.toString();
	}

}
