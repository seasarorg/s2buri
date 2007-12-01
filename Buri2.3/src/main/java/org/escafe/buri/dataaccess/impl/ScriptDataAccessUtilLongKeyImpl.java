package org.escafe.buri.dataaccess.impl;

import java.util.List;

import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.util.ClassUtil;

public class ScriptDataAccessUtilLongKeyImpl extends AbstDataAccessUtilLongKey {
    private BuriDataFieldType fieldType;
    private String pkey;
    private String condition;
    private ScriptFactory scriptFactory;
    private String dataAccessScriptType = "ognl";
    private String pkeyExpressionScriptType = "ognl";
    private Script dataAccessScript;
    private Script pkeyExpressionScript;

    public ScriptDataAccessUtilLongKeyImpl(BuriDataFieldType fieldType) {
        this.fieldType = fieldType;
        pkey = (String) fieldType.getKeys().keySet().toArray()[0];
        condition = (String) fieldType.getKeys().get(pkey);
    }

    @SuppressWarnings("unchecked")
	public List get(List keyVals) {
        String script = fieldType.getSelectMany();
        return getDataList(keyVals, script);
    }

    public Long getKey(Object key) {
        return getLongPkey(key, pkey);
    }

    public Object getObjectFromKey(Long keyObj) {
        Object dto = ClassUtil.newInstance(fieldType.getId());
        getPkeyExpressionScript().setVal(dto, pkey, keyObj, null);
        return getDataFromDto(dto);
    }

    public Object Store(Object data) {
        String execScript = "";
        if (hasAvailableKey(data)) {
            execScript = fieldType.getUpdate();
        } else {
            execScript = fieldType.getInsert();
        }
        runScript(data, execScript);
        return data;
    }

    public int delete(Object data) {
        String execScript = fieldType.getDelete();
        return deleteData(data, execScript);
    }

    public Object getDataFromDto(Object dto) {
        String execScript = fieldType.getSelect();
        return runScript(dto, execScript);
    }

    public boolean hasAvailableKey(Object keyVal) {
        Object evalResult = getPkeyExpressionScript().eval(keyVal, condition, null);
        return ((Boolean) evalResult).booleanValue();
    }

	public String getTableName(Object data) {
		return fieldType.getTableName();
	}

    @Override
    public Script getPkeyExpressionScript() {
        if (pkeyExpressionScript == null) {
            pkeyExpressionScript = scriptFactory.getScript(pkeyExpressionScriptType);
        }
        return pkeyExpressionScript;
    }

    @Override
    public Script getDataAccessScript() {
        if (dataAccessScript == null) {
            dataAccessScript = scriptFactory.getScript(dataAccessScriptType);
        }
        return dataAccessScript;
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

}
