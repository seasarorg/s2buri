package org.seasar.buri.dataaccess.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.script.ScriptFactory;

public class BuriDataAccessScriptFactoryImpl extends BuriDataAccessFactoryImpl {
	private BuriDataFieldCompilePreprocessor preprocessor;
    private Map classToUtil = new HashMap();
    private ScriptFactory scriptFactory;
	
	public DataAccessUtil getDataAccessUtil(Class tgtClass) {
		String className = tgtClass.getName();
		if(classToUtil.containsKey(className)) {
			return (DataAccessUtil)classToUtil.get(className);
		}
        BuriDataFieldType fieldType = new BuriDataFieldType();
        fieldType.setId(tgtClass.getName());
        fieldType = preprocessor.preprocess(fieldType);
        ScriptDataAccessUtilLongKeyImpl dataAccessUtil = new ScriptDataAccessUtilLongKeyImpl(fieldType);
        dataAccessUtil.setScriptFactory(scriptFactory);
        classToUtil.put(className, dataAccessUtil);
        return dataAccessUtil;
    }

	public BuriDataFieldCompilePreprocessor getPreprocessor() {
		return preprocessor;
	}

	public void setPreprocessor(BuriDataFieldCompilePreprocessor preprocessor) {
		this.preprocessor = preprocessor;
	}

	public ScriptFactory getScriptFactory() {
		return scriptFactory;
	}

	public void setScriptFactory(ScriptFactory scriptFactory) {
		this.scriptFactory = scriptFactory;
	}
	

}
