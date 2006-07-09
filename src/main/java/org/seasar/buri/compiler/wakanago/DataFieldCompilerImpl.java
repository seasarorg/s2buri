/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.compiler.wakanago;

import java.util.Iterator;
import java.util.Map;

import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.seasar.buri.compiler.DataFieldCompiler;
import org.seasar.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.seasar.buri.compiler.util.DataAccessCompileUtil;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.buri.util.packages.abst.AbstPreprocessAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class DataFieldCompilerImpl implements DataFieldCompiler {
    
    private DataAccessCompileUtil dataAccessCompileUtil;

    private ScriptFactory scriptFactory;
    private S2Container container;
    private BasicCompler compler;
    private String preprocessTemplateName ="ftl/Preprocess.ftl";
    private BuriDataFieldCompilePreprocessor bdfcp;
    
    
    public void compileAndSetting(BuriDataAccessFactory factory,BuriPackageType buriPackage,BuriWorkflowProcessType process) {
        Map dataField = getDataField(buriPackage,process);
        Iterator ite = dataField.keySet().iterator();
        while(ite.hasNext()) {
            String className = (String)ite.next();
            BuriDataFieldType fieldType = (BuriDataFieldType)dataField.get(className);
            compileAndSettingOne(fieldType,factory,buriPackage,process);
        }
    }
    
    public void compileAndSettingOne(BuriDataFieldType fieldType,BuriDataAccessFactory factory,BuriPackageType buriPackage,BuriWorkflowProcessType process) {
        String className = fieldType.getId();
        fieldType = bdfcp.preprocess(fieldType);
        if(dataAccessCompileUtil.isDataAccessUtil(fieldType)) {
            dataAccessCompileUtil.setupDataAccessUtil(factory,className,fieldType,buriPackage,process);
        }
        if(isPreProcessUtil(fieldType)) {
            setupPreprocessUtil(factory,className,fieldType,process,buriPackage);
        }
    }
    
    protected void setupPreprocessUtil(BuriDataAccessFactory factory,String className,BuriDataFieldType fieldType,BuriWorkflowProcessType process,BuriPackageType buriPackage) {
        PreprocessAccessUtil util = compile(fieldType,process,buriPackage);
        Class clazz = ClassUtil.forName(className);
        factory.setPreprocessAccessUtil(clazz,util);
        BuriExePackages exePackage = null;
        if(factory instanceof BuriExePackages) {
            exePackage = (BuriExePackages)factory;
        } else {
            BuriExecProcess execProc = (BuriExecProcess)factory;
            exePackage = execProc.getBuriExePackages();
        }
        Script preprocessScript = scriptFactory.getScript(exePackage.getPreprocessScriptType());
        util.setPreprocessScript(preprocessScript);
    }
    
    protected PreprocessAccessUtil compile(BuriDataFieldType fieldType,BuriWorkflowProcessType process,BuriPackageType buriPackage) {
        String createCalssName = getPreprocessClassName(fieldType,process,buriPackage);
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName(createCalssName);
        info.setBaseClass(AbstPreprocessAccessUtil.class);
        info.setInterfaceClass(new Class[]{});
        info.setBaseObjectName("fieldType");
        info.setBaseObject(fieldType);
        info.setTemplateFileName(preprocessTemplateName);
        Object result = compler.Compile(info);
        PreprocessAccessUtil preprocessUtil = (PreprocessAccessUtil)result;
        container.injectDependency(preprocessUtil,AbstPreprocessAccessUtil.class);
        return preprocessUtil;
    }
    
    
    private String getPreprocessClassName(BuriDataFieldType fieldType,BuriWorkflowProcessType process,BuriPackageType buriPackage) {
        String baseClassName = "" + buriPackage.getId() ;
        String lastClassName = fieldType.getId().replaceAll("\\.","_")+ "_Preprocess";
        if(process!=null) {
            return  baseClassName + "." + process.getId() + "_" + lastClassName;
        }
        return baseClassName + "." + buriPackage.getId() + "_" + lastClassName;
    }
    
    protected boolean isPreProcessUtil(BuriDataFieldType fieldType) {
        if(StringUtil.isEmpty(fieldType.getPreprocess())) {
            return false;
        }
        return true;
    }
    

    protected Map getDataField(BuriPackageType buriPackage,BuriWorkflowProcessType process) {
        Map dataField;
        if(process==null) {
            dataField = buriPackage.getDataField();
        } else {
            dataField = process.getDataField();
        }
        return dataField;
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

    public DataAccessCompileUtil getDataAccessCompileUtil() {
        return dataAccessCompileUtil;
    }

    public void setDataAccessCompileUtil(DataAccessCompileUtil dataAccessCompileUtil) {
        this.dataAccessCompileUtil = dataAccessCompileUtil;
    }

    public String getPreprocessTemplateName() {
        return preprocessTemplateName;
    }

    public void setPreprocessTemplateName(String preprocessTemplateName) {
        this.preprocessTemplateName = preprocessTemplateName;
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

    public BuriDataFieldCompilePreprocessor getBdfcp() {
        return bdfcp;
    }

    public void setBdfcp(BuriDataFieldCompilePreprocessor bdfcp) {
        this.bdfcp = bdfcp;
    }

}
