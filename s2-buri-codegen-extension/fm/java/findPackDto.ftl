<#include "macro/FindProperty.ftl"/>
package ${package}.${defaultDir?lower_case};

import ${package}.finddto.${table.tableName?cap_first}FindDto;
<#assign parentTables=table.parentTable>
<#assign importList=table.fieldImportDef>
<#list table.fieldImportDef as importName>
import ${importName};
</#list>
import java.util.List;

public class ${table.tableName?cap_first}PackFindDto extends ${table.tableName?cap_first}FindDto {

	<#list parentTables as parentTable>
	<#assign parentTableData=tableMap[parentTable.tableName]>
	<@field table=parentTableData tableName=parentTable.tableName?uncap_first+"Dto_"/>
	</#list>

	<#list parentTables as parentTable>
	<#assign parentTableData=tableMap[parentTable.tableName]>
	<@proerty table=parentTableData tableName=parentTable.tableName+"Dto_"/>
	</#list>

	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(super.toString());
		<#list parentTables as parentTable>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<@toString table=parentTableData tableName=parentTable.tableName+"Dto_"/>
		</#list>
		return buff.toString();
	}

}
