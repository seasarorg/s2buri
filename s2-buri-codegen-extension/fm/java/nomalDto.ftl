package ${package}.${defaultDir?lower_case};

<#assign importList=table.fieldImportDef>
<#list table.fieldImportDef as importName>
import ${importName};
</#list>

public class ${table.tableName?cap_first}Dto {
	public static final String TABLE = "${table.tableName}";

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName?uncap_first>
	public static final String ${primaryName}_ID = "sequence, sequenceName=${primaryName}";
	</#if>
	<#list table.tableField as tableField>
	<#assign fieldName=tableField.fieldName?uncap_first>
	<#assign javaTypeName=tableField.dataType.langDataType.className>
	<#assign defaultVal=tableField.dataType.langDataType.defVal>
	private ${javaTypeName} ${fieldName}<#if defaultVal?length != 0> = ${defaultVal}</#if>;
	</#list>
	
	<#list table.tableField as tableField>
	<#assign propertyName=tableField.fieldName?cap_first>
	<#assign fieldName=tableField.fieldName?uncap_first>
	<#assign javaTypeName=tableField.dataType.langDataType.className>
	public ${javaTypeName} get${propertyName}() {
		return ${fieldName};
	}

	public void set${propertyName}(${javaTypeName} ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}

	</#list>
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		<#list table.tableField as tableField>
		<#assign fieldName=tableField.fieldName?uncap_first>
		buff.append("/${fieldName}=").append(${fieldName});
		</#list>
		buff.append("]");
		return buff.toString();
	}
	
}
