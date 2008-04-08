package org.escafe.buri.dataaccess.impl;

import java.util.HashMap;
import java.util.Map;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.script.ScriptFactory;

public class BuriDataAccessScriptFactoryImpl extends BuriDataAccessFactoryImpl {
    private BuriDataFieldCompilePreprocessor preprocessor;
    private Map classToUtil = new HashMap();
    private ScriptFactory scriptFactory;
    private ClassDefUtil classDefUtil;
    
    
    public void destroy() {
    	dispose();
    }
    
    public void dispose() {
    	super.dispose();
    	preprocessor = null;
    	classToUtil.clear();
    	scriptFactory = null;
    	classDefUtil = null;
    }

    @Override
    public DataAccessUtil getDataAccessUtil(Class tgtClass) {
        String className = classDefUtil.getClassName(tgtClass);
        if (classToUtil.containsKey(className)) {
            return (DataAccessUtil) classToUtil.get(className);
        }
        BuriDataFieldType fieldType = new BuriDataFieldType();
        fieldType.setId(className);
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

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

}
