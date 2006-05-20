import java.util.List;

import org.seasar.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.script.Script;

<#assign keys = fieldType.getKeys()?keys>
<#assign pkey = keys?first>
<#assign condition = fieldType.getKeys()[pkey]>

public List get(List keyVals) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getSelectMany())>
    String execScript = "${script}";
    return getDataList(keyVals,execScript);
}


public Long getKey(Object key) {
    return getLongPkey(key,"${pkey}");
}

public Object getObjectFromKey(Long keyObj) {
    ${fieldType.getId()} dto = new ${fieldType.getId()}();
    Script scriptEngine = scriptFactory.getScript("ognl");
    scriptEngine.setVal(dto,"${pkey}",keyObj,null);
    return getDataFromDto(dto);
}

public Object Store(Object data) {
    String execScript = "";
    if(hasAvailableKey(data)) {
		<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getUpdate())>
        execScript = "${script}";
    } else {
		<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getInsert())>
        execScript = "${script}";
    }
    runScript(data,execScript);
    return data;
}
    
public boolean hasAvailableKey(Object keyVal) {
	<#assign script = buriComponentUtil.convScriptToJavaString(condition)>
    Object evalResult = scriptFactory.getScript("ognl").eval(keyVal,"${script}",null);
    return ((Boolean)evalResult).booleanValue();
}


public int delete(Object data) {
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getDelete())>
    String execScript = "${script}";
    return deleteData(data,execScript);
}

public Object getDataFromDto(Object keyVal) {
    ${fieldType.getId()} dto = new ${fieldType.getId()}();
	<#assign script = buriComponentUtil.convScriptToJavaString(fieldType.getSelect())>
    String execScript = "${script}";
    return runScript(dto,execScript);
}
