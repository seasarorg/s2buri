<#assign parentTables=table.parentTable>
select
	<#assign kanma="">
	<#list table.tableField as tableField>
		<#assign fieldName=tableField.fieldName?uncap_first>
	${kanma}${table.tableName}.${fieldName}
		<#assign kanma=",">
	</#list>
	<#list parentTables as parentTable>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<#list parentTableData.tableField as tableField>
			<#assign fieldName=tableField.fieldName?uncap_first>
	,${parentTable.tableName?uncap_first}Dto.${fieldName} AS ${fieldName}_${parentTable_index}
			<#assign kanma=",">
		</#list>
	</#list>
from
	${table.tableName}
	<#list parentTables as parentTable>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<#assign childFieldName=parentTable.childFieldName>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<#assign parentTablePkey=parentTableData.primaryKey?first.field.fieldName>
	LEFT OUTER JOIN ${parentTable.tableName} ${parentTable.tableName?uncap_first}Dto ON ${table.tableName}.${parentTablePkey} = ${parentTable.tableName?uncap_first}Dto.${childFieldName}
	</#list>
	,BuriPathData
/*BEGIN*/
where
	<#assign and="">
	<@fieldOut table=table tableName="" TableSqlName=table.tableName/>
	<#list parentTables as parentTable>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<@fieldOut table=parentTableData tableName=parentTableData.tableName?uncap_first+"Dto" TableSqlName=parentTableData.tableName?uncap_first+"Dto"/>
	</#list>
<#macro fieldOut table tableName TableSqlName>

	<#list table.tableField as tableField>
		<#if tableName?length != 0 >
			<#assign tgtTableName=tableName+"_">
		<#else>
			<#assign tgtTableName="">
		</#if>
		<#if TableSqlName?length != 0 >
			<#assign tgtTableSqlName=TableSqlName+".">
		<#else>
			<#assign tgtTableSqlName="">
		</#if>
		<#assign sqlFieldName=tableField.fieldName?cap_first>
		<#assign fieldName=tgtTableName+tableField.fieldName?cap_first>
		<#assign fieldName=fieldName?uncap_first>
		<#assign javaTypeName=tableField.dataType.langDataType.className>
		<#assign fieldSample=Sample[javaTypeName]>
		<#if javaTypeName == "String">
	/*IF dto.${fieldName}_matchFull != null*/${and} ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldName}_matchFull*/'%${fieldSample}%'/*END*/
	/*IF dto.${fieldName}_matchFront != null*/AND ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldName}_matchFront*/'${fieldSample}%'/*END*/
	/*IF dto.${fieldName}_matchBack != null*/AND ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldName}_matchBack*/'%${fieldSample}'/*END*/
			<#assign and="AND">
			<#assign fieldSample="'"+Sample[javaTypeName]+"'">
		</#if>
	/*IF dto.${fieldName} != null*/${and} ${tgtTableSqlName}${sqlFieldName} = /*dto.${fieldName}*/${fieldSample}/*END*/
	/*IF dto.${fieldName}_not != null*/AND ${tgtTableSqlName}${sqlFieldName} != /*dto.${fieldName}_not*/${fieldSample}/*END*/
	/*IF dto.${fieldName}_large != null*/AND  /*dto.${fieldName}_large*/${fieldSample} < ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldName}_moreLarge != null*/AND  /*dto.${fieldName}_moreLarge*/${fieldSample} <= ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldName}_from != null*/AND  /*dto.${fieldName}_from*/${fieldSample} <= ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldName}_to != null*/AND ${tgtTableSqlName}${sqlFieldName} <= /*dto.${fieldName}_to*/${fieldSample}/*END*/
	/*IF dto.${fieldName}_moreSmall != null*/AND ${tgtTableSqlName}${sqlFieldName} <= /*dto.${fieldName}_moreSmall*/${fieldSample}/*END*/
	/*IF dto.${fieldName}_small != null*/AND ${tgtTableSqlName}${sqlFieldName} < /*dto.${fieldName}_small*/${fieldSample}/*END*/
	/*IF dto.${fieldName}_isNull != null*/AND ${tgtTableSqlName}${sqlFieldName} is null /*END*/
	/*IF dto.${fieldName}_isNotNull != null*/AND ${tgtTableSqlName}${sqlFieldName} is not null/*END*/
	/*IF dto.${fieldName}_in != null*/AND ${tgtTableSqlName}${sqlFieldName} in /*dto.${fieldName}_in*/(${fieldSample})/*END*/
		<#assign and="AND">
	</#list>
</#macro>

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
		<#assign primaryField=primarys?first>
		<#assign primaryName=primaryField.field.fieldName?uncap_first>
	${table.tableName}.${primaryName} = BuriPathData.pkeyNum
	</#if>
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
