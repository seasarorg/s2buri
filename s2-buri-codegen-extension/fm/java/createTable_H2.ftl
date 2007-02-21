<#list tableList as table>


CREATE TABLE ${table.tableName} (
	<#assign primarys=table.primaryKey>
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName>
	<#assign identity="">
	<#assign conma = "">
	<#list table.tableField as tableField>
		<#assign fieldName=tableField.fieldName>
		<#assign fieldDataType=tableField.dataType>
		<#assign rdbDataType=fieldDataType.rdbTypeName>
		<#assign IsNotNull=fieldDataType.isNotNullString>
		<#assign colmnSize="("+fieldDataType.colmnSize+")">
		<#assign colmnSizePoint="("+fieldDataType.colmnSize+"."+fieldDataType.pointNumber+")">
		${conma}
		<#if colmnSize == "(0)">
			<#assign colmnSize = "">
		<#else>
			<#assign colmnSizePoint = "">
		</#if>
		<#if colmnSizePoint == "(0.0)">
			<#assign colmnSizePoint = "">
		</#if>
		<#if fieldName == primaryName>
       ${fieldName}           ${rdbDataType}${colmnSize}${colmnSizePoint} ${IsNotNull}
			<#if table.primaryKey?size=1>
				AUTO_INCREMENT(1,1)
			</#if>
				PRIMARY KEY
		<#else>
       ${fieldName}              ${rdbDataType}${colmnSize}${colmnSizePoint} ${IsNotNull}
        </#if>
		<#assign conma = ",">        
	</#list>
);

<#assign primaryKey=table.primaryKey>
<#if primaryKey?size = 1>
--CREATE SEQUENCE ${primaryName}
-- START WITH 1
-- INCREMENT BY 1
--;
</#if>

</#list>

<#list tableList as table>
	<#list table.parentTable as linkTable>
		<#assign fkName=linkTable.childFieldName>
		ALTER TABLE ${table.tableName} 
			ADD CONSTRAINT FK_${fkName} FOREIGN KEY(${fkName}) 
			REFERENCES ${linkTable.tableName}(${linkTable.parentFieldName})
	</#list>
</#list>
