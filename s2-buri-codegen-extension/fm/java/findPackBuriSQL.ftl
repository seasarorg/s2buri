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
	LEFT OUTER JOIN ${parentTable.tableName} ${parentTable.tableNameForDto}Dto ON ${table.tableName}.${parentTablePkey} = ${parentTable.tableName}Dto.${childFieldName}
	</#list>
	,BuriPathData
/*BEGIN*/
where
	<#assign and="">
	<@fieldOut table=table tableName="" TableSqlName=table.tableName/>
	<#list parentTables as parentTable>
		<#assign parentTableData=tableMap[parentTable.tableName]>
		<@fieldOut table=parentTableData tableName=parentTableData.tableName?uncap_first+"Dto" TableSqlName=parentTableData.tableNameForDto?uncap_first+"Dto"/>
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
		<#assign fieldName=fieldName>
		<#assign fieldNameForDto=tableField.fieldNameForDto?uncap_first>
		<#assign javaTypeName=tableField.dataType.langDataType.className>
		<#assign fieldSample=Sample[javaTypeName]>
		<#if javaTypeName == "String">
	/*IF dto.${fieldNameForDto}_matchFull != null*/${and} ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldNameForDto}_matchFull*/'%${fieldSample}%'/*END*/
	/*IF dto.${fieldNameForDto}_matchFront != null*/AND ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldNameForDto}_matchFront*/'${fieldSample}%'/*END*/
	/*IF dto.${fieldNameForDto}_matchBack != null*/AND ${tgtTableSqlName}${sqlFieldName} Like /*dto.${fieldNameForDto}_matchBack*/'%${fieldSample}'/*END*/
			<#assign and="AND">
			<#assign fieldSample="'"+Sample[javaTypeName]+"'">
		</#if>
	/*IF dto.${fieldNameForDto} != null*/${and} ${tgtTableSqlName}${sqlFieldName} = /*dto.${fieldNameForDto}*/${fieldSample}/*END*/
	/*IF dto.${fieldNameForDto}_not != null*/AND ${tgtTableSqlName}${sqlFieldName} != /*dto.${fieldNameForDto}_not*/${fieldSample}/*END*/
	/*IF dto.${fieldNameForDto}_large != null*/AND  /*dto.${fieldNameForDto}_large*/${fieldSample} < ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldNameForDto}_moreLarge != null*/AND  /*dto.${fieldNameForDto}_moreLarge*/${fieldSample} <= ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldNameForDto}_from != null*/AND  /*dto.${fieldNameForDto}_from*/${fieldSample} <= ${tgtTableSqlName}${sqlFieldName}/*END*/
	/*IF dto.${fieldNameForDto}_to != null*/AND ${tgtTableSqlName}${sqlFieldName} <= /*dto.${fieldNameForDto}_to*/${fieldSample}/*END*/
	/*IF dto.${fieldNameForDto}_moreSmall != null*/AND ${tgtTableSqlName}${sqlFieldName} <= /*dto.${fieldNameForDto}_moreSmall*/${fieldSample}/*END*/
	/*IF dto.${fieldNameForDto}_small != null*/AND ${tgtTableSqlName}${sqlFieldName} < /*dto.${fieldNameForDto}_small*/${fieldSample}/*END*/
	/*IF dto.${fieldNameForDto}_isNull != null*/AND ${tgtTableSqlName}${sqlFieldName} is null /*END*/
	/*IF dto.${fieldNameForDto}_isNotNull != null*/AND ${tgtTableSqlName}${sqlFieldName} is not null/*END*/
	/*IF dto.${fieldNameForDto}_in != null*/AND ${tgtTableSqlName}${sqlFieldName} in /*dto.${fieldNameForDto}_in*/(${fieldSample})/*END*/
		<#assign and="AND">
	</#list>
</#macro>

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
		<#assign primaryField=primarys?first>
		<#assign primaryName=primaryField.field.fieldName?uncap_first>
	${table.tableName}.${primaryName} = BuriPathData.pkeyNum
	and '${package}.dto.${table.tableNameForDto?cap_first}Dto' = BuriPathData.dataType
	</#if>
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
