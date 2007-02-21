<#assign findType=["","_not","_large","_moreLarge","_from","_to","_moreSmall","_small"]>
<#assign findStrType=["_matchFull","_matchFront","_matchBack"]>
<#assign findStrTypeAddPre=["\"%\"+","","\"%\"+"]>
<#assign findStrTypeAddPost=["+\"%\"","+\"%\"",""]>
<#assign findNullType=["_isNull","_isNotNull"]>

<#macro field table tableName>
	<#assign primarys=table.primaryKey>
	<#list table.tableField as tableField>
	<#assign fieldName=tableName+tableField.fieldNameForDto?uncap_first>
	<#assign fieldName=fieldName?uncap_first>
	<#assign javaTypeName=tableField.dataType.langDataType.className>
	<#assign javaTypeName=Typeconvert[javaTypeName]>
	<#list findType as findStr>
	private ${javaTypeName} ${fieldName}${findStr} = null;
	</#list>
	<#if javaTypeName == "String">
	<#list findStrType as findStr>
	private ${javaTypeName} ${fieldName}${findStr} = null;
	</#list>
	</#if>
	<#assign javaTypeName="List">
	private ${javaTypeName} ${fieldName}_in = null;
	<#assign deleteflagCheck=tableField.fieldName?lower_case>
	<#if deleteflagCheck?starts_with("deleteflg") || deleteflagCheck?starts_with("delflg") || deleteflagCheck?starts_with("deleteflag")>
	private Boolean ${fieldName}_isNull = Boolean.TRUE;
	private Boolean ${fieldName}_isNotNull = null;
	<#else>
	<#list findNullType as findStr>
	private Boolean ${fieldName}${findStr} = null;
	</#list>
	</#if>
	private boolean ${fieldName}_isASC = true;
	</#list>
</#macro>
	
<#macro proerty table tableName>
	<#list table.tableField as tableField>
	<#assign propertyName=tableName?cap_first+tableField.fieldNameForDto?cap_first>
	<#assign fieldName=tableName+tableField.fieldNameForDto?uncap_first>
	<#assign fieldName=fieldName?uncap_first>
	<#assign javaTypeName=tableField.dataType.langDataType.className>
	<#assign javaTypeName=Typeconvert[javaTypeName]>
	<#list findType as findStr>
	public ${javaTypeName} get${propertyName}${findStr}() {
		return ${fieldName}${findStr};
	}

	public void set${propertyName}${findStr}(${javaTypeName} ${fieldName}${findStr}) {
		this.${fieldName}${findStr} = ${fieldName}${findStr};
	}
	</#list>
	<#if javaTypeName == "String">
	<#list findStrType as findStr>
	public ${javaTypeName} get${propertyName}${findStr}() {
		if(${fieldName}${findStr}==null) {
			return null;
		}
		return ${findStrTypeAddPre[findStr_index]}${fieldName}${findStr}${findStrTypeAddPost[findStr_index]};
	}

	public void set${propertyName}${findStr}(${javaTypeName} ${fieldName}${findStr}) {
		this.${fieldName}${findStr} = ${fieldName}${findStr};
	}
	</#list>
	</#if>
	<#assign javaTypeName="List">
	<#assign findStr="_in">
	public ${javaTypeName} get${propertyName}${findStr}() {
		return ${fieldName}${findStr};
	}

	public void set${propertyName}${findStr}(${javaTypeName} ${fieldName}${findStr}) {
		this.${fieldName}${findStr} = ${fieldName}${findStr};
	}
	<#assign javaTypeName="Boolean">
	<#list findNullType as findStr>
	public ${javaTypeName} get${propertyName}${findStr}() {
		return ${fieldName}${findStr};
	}

	public void set${propertyName}${findStr}(${javaTypeName} ${fieldName}${findStr}) {
		this.${fieldName}${findStr} = ${fieldName}${findStr};
	}
	</#list>
	<#assign javaTypeName="boolean">
	<#assign findStr="_isASC">
	public ${javaTypeName} get${propertyName}${findStr}() {
		return ${fieldName}${findStr};
	}

	public void set${propertyName}${findStr}(${javaTypeName} ${fieldName}${findStr}) {
		this.${fieldName}${findStr} = ${fieldName}${findStr};
	}
	</#list>

</#macro>
	
<#macro toString table tableName>
		<#list table.tableField as tableField>
		<#assign fieldName=tableName+tableField.fieldNameForDto?uncap_first>
		<#assign fieldName=fieldName?uncap_first>
		<#list findType as findStr>
		buff.append("/${fieldName}${findStr}=").append(${fieldName}${findStr});
		</#list>
		<#assign findStr="_in">
		buff.append("/${fieldName}${findStr}=").append(${fieldName}${findStr});
		<#if javaTypeName == "String">
		<#list findStrType as findStr>
		buff.append("/${fieldName}${findStr}=").append(${fieldName}${findStr});
		</#list>
		</#if>
		<#list findNullType as findStr>
		buff.append("/${fieldName}${findStr}=").append(${fieldName}${findStr});
		</#list>
		<#assign findStr="_isASC">
		buff.append("/${fieldName}${findStr}=").append(${fieldName}${findStr});
		</#list>
</#macro>
	