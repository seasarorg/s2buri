<#include "macro/FindProperty.ftl"/>
package ${package}.${defaultDir?lower_case};

import ${package}.finddto.${table.tableNameForDto?cap_first}FindDto;
<#assign parentTables=table.parentTableName>
<#assign tableNames = parentTables?keys>
<#assign importList=table.fieldImportDef>
<#list table.fieldImportDef as importName>
import ${importName};
</#list>
import java.util.List;

public class ${table.tableNameForDto?cap_first}PackFindDto extends ${table.tableNameForDto?cap_first}FindDto {

<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	<#assign parentTableData=tableMap[parentTable.tableName]>
	<@field table=parentTableData tableName=tableName?uncap_first+"_"/>
</#list>

<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	<#assign parentTableData=tableMap[parentTable.tableName]>
	<@proerty table=parentTableData tableName=tableName?uncap_first+"_"/>
</#list>

	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(super.toString());
<#list tableNames as tableName>
	<#assign parentTable=parentTables[tableName]>
	<#assign parentTableData=tableMap[parentTable.tableName]>
	<@toString table=parentTableData tableName=tableName?uncap_first+"_"/>
</#list>
		return buff.toString();
	}

}
