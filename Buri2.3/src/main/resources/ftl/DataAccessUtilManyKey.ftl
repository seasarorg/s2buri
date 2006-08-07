import java.util.List;

<#assign keys = fieldType.getKeys()?keys>
private static String params[] = new String[]{
<#list keys as pkeyName>
"${pkeyName}"<#if pkeyName_has_next>,</#if>
</#list>
};
private static String condition[] = new String[]{
<#list keys as pkeyName>
	<#assign condition = fieldType.getKeys()[pkeyName]>
	<#assign script = buriComponentUtil.convScriptToJavaString(condition)>
"${script}"<#if pkeyName_has_next>,</#if>
</#list>
};

protected String[] getConditions() {
	return condition;
}

public List get(List keyStr) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getSelectMany())>
    String execScript = "${script}";
    return getDataList(keyStr,execScript);
}

public String getKey(Object key) {
    return getKey(key,params);
}


protected ${fieldType.getId()} convKeyStringToObj(String keyObj) {
    ${fieldType.getId()} dto = new ${fieldType.getId()}();
    setStringKeyToObj(dto,keyObj);
    return dto;
}

public Object getObjectFromKey(String keyObj) {
    ${fieldType.getId()} dto = convKeyStringToObj(keyObj);
    return getDataFromDto(dto);
}

public Object Store(Object data) {
    String execScript = "";
    if(hasPkey(data,condition)) {
		<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getUpdate())>
        execScript = "${script}";
    } else {
		<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getInsert())>
        execScript = "${script}";
    }
    runScript(data,execScript);
    return data;
}

public int delete(Object data) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getDelete())>
    String execScript = "${script}";
    return deleteData(data,execScript);
}

public Object getDataFromDto(Object dto) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getSelect())>
    String execScript = "${script}";
    return runScript(dto,execScript);
}
