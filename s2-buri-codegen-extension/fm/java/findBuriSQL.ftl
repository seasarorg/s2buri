select
	<#assign kannma="">
	<#list table.tableField as tableField>
	<#assign tgtTableSqlName="">
	<#assign fieldName=tableField.fieldName?uncap_first>
	${kannma}${table.tableName}.${fieldName} as ${fieldName}
        <#assign kannma=",">
	</#list>
from
	${table.tableName}
	,BuriPathData
/*BEGIN*/
where
	<#assign and="">
	<#list table.tableField as tableField>
	
	<#assign tgtTableName="">
	<#assign tgtTableSqlName="">
	<#assign fieldName=tableField.fieldName?uncap_first>
	<#assign javaTypeName=tableField.dataType.langDataType.className>
	<#assign fieldSample=Sample[javaTypeName]>
	<#if javaTypeName == "String">
	/*IF dto.${tgtTableName}${fieldName}_matchFull != null*/${and} ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldName}_matchFull*/'%${fieldSample}%'/*END*/
	/*IF dto.${tgtTableName}${fieldName}_matchFront != null*/AND ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldName}_matchFront*/'${fieldSample}%'/*END*/
	/*IF dto.${tgtTableName}${fieldName}_matchBack != null*/AND ${tgtTableSqlName}${fieldName} Like /*dto.${tgtTableName}${fieldName}_matchBack*/'%${fieldSample}'/*END*/
	<#assign and="AND">
	<#assign fieldSample="'"+Sample[javaTypeName]+"'">
	</#if>
	/*IF dto.${tgtTableName}${fieldName} != null*/${and} ${tgtTableSqlName}${fieldName} = /*dto.${tgtTableName}${fieldName}*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_not != null*/AND ${tgtTableSqlName}${fieldName} != /*dto.${tgtTableName}${fieldName}_not*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_large != null*/AND  /*dto.${tgtTableName}${fieldName}_large*/${fieldSample} < ${tgtTableSqlName}${fieldName}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_moreLarge != null*/AND  /*dto.${tgtTableName}${fieldName}_moreLarge*/${fieldSample} <= ${tgtTableSqlName}${fieldName}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_from != null*/AND  /*dto.${tgtTableName}${fieldName}_from*/${fieldSample} <= ${tgtTableSqlName}${fieldName}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_to != null*/AND ${tgtTableSqlName}${fieldName} <= /*dto.${tgtTableName}${fieldName}_to*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_moreSmall != null*/AND ${tgtTableSqlName}${fieldName} <= /*dto.${tgtTableName}${fieldName}_moreSmall*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_small != null*/AND ${tgtTableSqlName}${fieldName} < /*dto.${tgtTableName}${fieldName}_small*/${fieldSample}/*END*/
	/*IF dto.${tgtTableName}${fieldName}_isNull != null*/AND ${tgtTableSqlName}${fieldName} is null /*END*/
	/*IF dto.${tgtTableName}${fieldName}_isNotNull != null*/AND ${tgtTableSqlName}${fieldName} is not null/*END*/
	/*IF dto.${tgtTableName}${fieldName}_in != null*/AND ${tgtTableSqlName}${fieldName} in /*dto.${tgtTableName}${fieldName}_in*/(${fieldSample})/*END*/

	<#assign and="AND">
	</#list>
	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName?uncap_first>
	${and} ${table.tableName}.${primaryName} = BuriPathData.pkeyNum
	</#if>
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
