select
	*
from
	${table.tableName}
/*BEGIN*/
where
	<#assign and="">
	<#list table.tableField as tableField>
	
	<#assign tgtTableName="">
	<#assign tgtTableSqlName="">
	<#assign fieldName=tableField.fieldName>
	<#assign fieldNameForDto=tableField.fieldNameForDto?uncap_first>

	<#assign javaTypeName=tableField.dataType.langDataType.className>
	<#assign fieldSample=Sample[javaTypeName]>
	<#if javaTypeName == "String">
	/*IF dto.${tgtTableName}${fieldNameForDto}_matchFull != null*/${and} ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldNameForDto}_matchFull*/'%${fieldSample}%'/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_matchFront != null*/AND ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldNameForDto}_matchFront*/'${fieldSample}%'/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_matchBack != null*/AND ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldNameForDto}_matchBack*/'%${fieldSample}'/*END*/
	<#assign and="AND">
	<#assign fieldSample="'"+Sample[javaTypeName]+"'">
	</#if>
	/*IF dto.${tgtTableName}${fieldNameForDto} != null*/${and} ${tgtTableSqlName}${fieldName} = /*dto.${tgtTableName}${fieldNameForDto}*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_not != null*/AND ${tgtTableSqlName}${fieldName} != /*dto.${tgtTableName}${fieldNameForDto}_not*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_large != null*/AND  /*dto.${tgtTableName}${fieldName}_large*/${fieldSample} < ${tgtTableSqlName}${fieldNameForDto}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_moreLarge != null*/AND  /*dto.${tgtTableName}${fieldName}_moreLarge*/${fieldSample} <= ${tgtTableSqlName}${fieldNameForDto}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_from != null*/AND  /*dto.${tgtTableName}${fieldName}_from*/${fieldSample} <= ${tgtTableSqlName}${fieldNameForDto}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_to != null*/AND ${tgtTableSqlName}${fieldName} <= /*dto.${tgtTableName}${fieldNameForDto}_to*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_moreSmall != null*/AND ${tgtTableSqlName}${fieldName} <= /*dto.${tgtTableName}${fieldNameForDto}_moreSmall*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_small != null*/AND ${tgtTableSqlName}${fieldName} < /*dto.${tgtTableName}${fieldNameForDto}_small*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_isNull != null*/AND ${tgtTableSqlName}${fieldName} is null /*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_isNotNull != null*/AND ${tgtTableSqlName}${fieldName} is not null/*END*/
	/*IF dto.${tgtTableName}${fieldNameForDto}_in != null*/AND ${tgtTableSqlName}${fieldName} in /*dto.${tgtTableName}${fieldNameForDto}_in*/(${fieldSample})/*END*/

	<#assign and="AND">
	</#list>
	
/*END*/
/*$dto.orderList*/
