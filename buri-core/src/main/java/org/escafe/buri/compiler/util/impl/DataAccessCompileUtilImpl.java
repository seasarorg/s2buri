/*
 * 作成日: 2006/06/05
 *
 */
package org.escafe.buri.compiler.util.impl;

import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.escafe.buri.compiler.util.DataAccessCompileUtil;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.event.boot.BuriCompileEvent;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;

public class DataAccessCompileUtilImpl implements DataAccessCompileUtil {

    private ScriptFactory scriptFactory;
    private S2Container container;
    private BasicCompler compler;
    private String longKeyTemplateName = "ftl/DataAccessUtilLongKey.ftl";
    private String manyKeyTemplateName = "ftl/DataAccessUtilManyKey.ftl";
    private BuriComplieEventCaller eventCaller;

    
    public void setupDataAccessUtil(BuriDataAccessFactory factory, String className, BuriDataFieldType fieldType, BuriPackageType buriPackage,
            BuriWorkflowProcessType process,ClassLoader classLoader) {
        String processID = "";
        if (process != null) {
            processID = process.getId();
        }
        setupDataAccessUtil(factory, className, fieldType, buriPackage.getId(), processID,classLoader);
    }

    public void setupDataAccessUtil(BuriDataAccessFactory factory, String className, BuriDataFieldType fieldType, String packageId, String processId,ClassLoader classLoader) {
        Class clazz = ClassUtil.forName(className);
        DataAccessUtil accessUtil = compileDataAccess(fieldType, processId, packageId,classLoader);
        BuriExePackages exePackage = null;
        if (factory instanceof BuriExePackages) {
            exePackage = (BuriExePackages) factory;
        } else if (factory instanceof BuriExecProcess) {
            BuriExecProcess execProc = (BuriExecProcess) factory;
            exePackage = execProc.getBuriExePackages();
        }
        scriptSetup(exePackage, accessUtil);
        if (accessUtil instanceof DataAccessUtilLongKey) {
            factory.setDataAccessUtil(clazz, (DataAccessUtilLongKey) accessUtil);
        } else {
            factory.setDataAccessUtil(clazz, (DataAccessUtilManyKey) accessUtil);
        }
    }

    protected void scriptSetup(BuriExePackages exePackage, DataAccessUtil accessUtil) {
        String dasName = "ognl";
        String pkesName = "ognl";
        if (exePackage != null) {
            dasName = exePackage.getDataAccessScriptType();
            pkesName = exePackage.getPkeyExpressionType();
        }
        Script dataAccessScript = scriptFactory.getScript(dasName);
        Script pkeyExpressionScript = scriptFactory.getScript(pkesName);
        accessUtil.setDataAccessScript(dataAccessScript);
        accessUtil.setPkeyExpressionScript(pkeyExpressionScript);
    }

    public boolean isDataAccessUtil(BuriDataFieldType fieldType) {
        if (fieldType.getKeys().size() == 0) {
            return false;
        }
        return true;
    }

    public DataAccessUtil compileDataAccess(BuriDataFieldType fieldType, BuriPackageType buriPackage, BuriWorkflowProcessType process,ClassLoader classLoader) {
        return compileDataAccess(fieldType, process.getId(), buriPackage.getId(),classLoader);
    }

    public DataAccessUtil compileDataAccess(BuriDataFieldType fieldType, String processId, String packageId,ClassLoader classLoader) {
        String createClassName = getDataAccessClassName(fieldType, processId, packageId);
        eventCaller.callStartCompile(this,BuriCompileEvent.CompileTargetType.DataField,createClassName);
        DataAccessUtil util = null;
        if (isUseLongKeyType(fieldType)) {
        	util = comileDataAccessUtilLongKey(fieldType, createClassName,classLoader);
        } else {
        	util = comileDataAccessUtilManyKey(fieldType, createClassName,classLoader);
        }
        eventCaller.callEndCompile(this,BuriCompileEvent.CompileTargetType.DataField,createClassName,util);
        return util;
    }

    private String getDataAccessClassName(BuriDataFieldType fieldType, String processId, String packageId) {
        String lastClassName = fieldType.getId().replaceAll("\\.", "_") + "_DataAccessUtil";
        if (processId != null) {
            return packageId + "." + processId + "_" + lastClassName;
        }
        return packageId + "." + processId + "_" + lastClassName;
    }

    private DataAccessUtilLongKey comileDataAccessUtilLongKey(BuriDataFieldType fieldType, String createClassName,ClassLoader classLoader) {
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName(createClassName);
        info.setBaseClass(AbstDataAccessUtilLongKey.class);
        info.setInterfaceClass(new Class[] {});
        info.setBaseObjectName("fieldType");
        info.setBaseObject(fieldType);
        info.setTemplateFileName(longKeyTemplateName);
        info.setParentClassLoader(classLoader);
        Object result = compler.Compile(info);
        DataAccessUtilLongKey dataAccessUtil = (DataAccessUtilLongKey) result;
        container.injectDependency(dataAccessUtil, AbstDataAccessUtilLongKey.class);
        return dataAccessUtil;
    }

    private DataAccessUtilManyKey comileDataAccessUtilManyKey(BuriDataFieldType fieldType, String createClassName,ClassLoader classLoader) {
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName(createClassName);
        info.setBaseClass(AbstDataAccessUtilManyKey.class);
        info.setInterfaceClass(new Class[] {});
        info.setBaseObjectName("fieldType");
        info.setBaseObject(fieldType);
        info.setTemplateFileName(manyKeyTemplateName);
        info.setParentClassLoader(classLoader);
        Object result = compler.Compile(info);
        DataAccessUtilManyKey dataAccessUtil = (DataAccessUtilManyKey) result;
        container.injectDependency(dataAccessUtil, AbstDataAccessUtilManyKey.class);
        return dataAccessUtil;
    }

    private boolean isUseLongKeyType(BuriDataFieldType fieldType) {
        if (fieldType.getKeys().size() == 1) {
            String prop = (String) fieldType.getKeys().keySet().toArray()[0];
            Class tgtClass = ClassUtil.forName(fieldType.getId());
            BeanDesc beanDesc = BeanDescFactory.getBeanDesc(tgtClass);
            PropertyDesc pkeyPropertyDesc = beanDesc.getPropertyDesc(prop);
            if (pkeyPropertyDesc.getPropertyType().isAssignableFrom(Long.TYPE)) {
                return true;
            }
        }
        return false;
    }

    public BasicCompler getCompler() {
        return compler;
    }

    public void setCompler(BasicCompler compler) {
        this.compler = compler;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getLongKeyTemplateName() {
        return longKeyTemplateName;
    }

    public void setLongKeyTemplateName(String longKeyTemplateName) {
        this.longKeyTemplateName = longKeyTemplateName;
    }

    public String getManyKeyTemplateName() {
        return manyKeyTemplateName;
    }

    public void setManyKeyTemplateName(String manyKeyTemplateName) {
        this.manyKeyTemplateName = manyKeyTemplateName;
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

	public BuriComplieEventCaller getEventCaller() {
		return eventCaller;
	}

	public void setEventCaller(BuriComplieEventCaller eventCaller) {
		this.eventCaller = eventCaller;
	}

}
