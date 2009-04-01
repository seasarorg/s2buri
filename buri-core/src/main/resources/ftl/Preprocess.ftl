
public Object getTrueData(Object orgData) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getPreprocess())>
    String execScript = "${script}";
    return runScript(orgData,execScript);
}
