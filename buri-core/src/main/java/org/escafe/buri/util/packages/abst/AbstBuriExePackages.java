/*
 * 作成日: 2006/03/21
 *
 */
package org.escafe.buri.util.packages.abst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.escafe.buri.common.util.MultiValueMap;
import org.escafe.buri.compiler.CompileProcess;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;

public class AbstBuriExePackages implements BuriExePackages, BuriDataAccessFactory {

    protected Map applications = new HashMap();
    protected Map variables = new HashMap();
    protected MultiValueMap participant = new MultiValueMap();

    protected ParticipantProvider participantProvider;

    protected Map<String, BuriExecProcess> buriExecProcessMap = new HashMap<String, BuriExecProcess>();

    protected BuriPackageType buriPackage;
    protected S2Container container;
    protected BuriDataAccessFactory dataAccessFactory;
    
    protected CompileProcess compileProcess;

    protected String conditionExpressionType = "ognl";
    protected String pkeyExpressionType = "ognl";
    protected String dataAccessScriptType = "ognl";
    protected String defaultExpressionType = "ognl";
    protected String preprocessScriptType = "ognl";
    protected String timeLimitExpressionType = "ognl";

    public void destroy() {
    	for(BuriExecProcess process : buriExecProcessMap.values()) {
    		process.destroy();
    	}
    	if(dataAccessFactory != null) {
    		dataAccessFactory.destroy();
    	}
    	buriExecProcessMap.clear();
    	applications.clear();
    	variables.clear();
    	participant.clear();
    }
    
    public void dispose() {
    	destroy();
    }
    
    public String getTimeLimitExpressionType() {
        return timeLimitExpressionType;
    }

    public void setTimeLimitExpressionType(String timeLimitExpressionType) {
        this.timeLimitExpressionType = timeLimitExpressionType;
    }

    public String getPreprocessScriptType() {
        return preprocessScriptType;
    }

    public void setPreprocessScriptType(String preprocessScriptType) {
        this.preprocessScriptType = preprocessScriptType;
    }

    public String getConditionExpressionType() {
        return conditionExpressionType;
    }

    public void setConditionExpressionType(String conditionExpressionType) {
        this.conditionExpressionType = conditionExpressionType;
    }

    public String getDataAccessScriptType() {
        return dataAccessScriptType;
    }

    public void setDataAccessScriptType(String dataAccessScriptType) {
        this.dataAccessScriptType = dataAccessScriptType;
    }

    public String getDefaultExpressionType() {
        return defaultExpressionType;
    }

    public void setDefaultExpressionType(String defaultExpressionType) {
        this.defaultExpressionType = defaultExpressionType;
    }

    public String getPkeyExpressionType() {
        return pkeyExpressionType;
    }

    public void setPkeyExpressionType(String pkeyExpressionType) {
        this.pkeyExpressionType = pkeyExpressionType;
    }

    public void setup(BuriPackageType buriPackage) {
        this.buriPackage = buriPackage;
        //        setupApplication();
        variables.putAll(buriPackage.getApplication());
        participant.putAll(buriPackage.getParticipantByName());
        setupExpressionType();
    }

    public BuriPackageType getBuriPackageType() {
        return buriPackage;
    }

    //    protected void setupApplication() {
    //        Set keys = buriPackage.getApplication().keySet();
    //        Iterator ite = keys.iterator();
    //        while(ite.hasNext()) {
    //            String key = ite.next().toString();
    //            BuriApplicationType app = buriPackage.getApplicationById(key);
    //            applications.put(key,container.getComponent(app.getName()));
    //        }
    //    }

    public void setProcess(String procID, BuriExecProcess execProcess) {
        buriExecProcessMap.put(procID, execProcess);
    }

    public BuriExecProcess getProcess(BuriPath path) {
        String processId = getProcessId(path);
        assert !StringUtil.isEmpty(processId);
        BuriExecProcess processObj = buriExecProcessMap.get(processId);
        if(processObj == null) {
        	compileProcess.compile(this, buriPackage.getProcessById(processId), participantProvider,this.getClass().getClassLoader());
        	processObj = buriExecProcessMap.get(processId);
        }
        return processObj;
    }

    private void setupExpressionType() {
        List extAttri = buriPackage.getExtendedAttribute();
        String type = ExtentedAttributeUtil.getAttributeVal(extAttri, "conditionExpressionType");
        if (!StringUtil.isEmpty(type)) {
            conditionExpressionType = type;
        }

        type = ExtentedAttributeUtil.getAttributeVal(extAttri, "pkeyExpressionType");
        if (!StringUtil.isEmpty(type)) {
            pkeyExpressionType = type;
        }

        type = ExtentedAttributeUtil.getAttributeVal(extAttri, "dataAccessScriptType");
        if (!StringUtil.isEmpty(type)) {
            dataAccessScriptType = type;
        }

        type = ExtentedAttributeUtil.getAttributeVal(extAttri, "defaultExpressionType");
        if (!StringUtil.isEmpty(type)) {
            defaultExpressionType = type;
        }

        type = ExtentedAttributeUtil.getAttributeVal(extAttri, "preprocessScriptType");
        if (!StringUtil.isEmpty(type)) {
            preprocessScriptType = type;
        }

        type = ExtentedAttributeUtil.getAttributeVal(extAttri, "timeLimitExpressionType");
        if (!StringUtil.isEmpty(type)) {
            timeLimitExpressionType = type;
        }

    }

    private String getProcessId(BuriPath path) {
        BuriWorkflowProcessType process = null;
        if (path.getBuriPathId() != 0) {
            String id = path.getRealPath().getWorkflowProcess();
            assert !StringUtil.isEmpty(id);
            process = buriPackage.getProcessById(id);
        } else {
            String name = path.getWorkflowProcess();
            assert !StringUtil.isEmpty(name);
            List list = buriPackage.getProcessByName(name);
            assert list != null;
            process = (BuriWorkflowProcessType) list.get(0);
        }
        return process.getId();
    }

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilLongKey utilLongKey) {
        dataAccessFactory.setDataAccessUtil(tgtClass, utilLongKey);
    }

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilManyKey utilManyKey) {
        dataAccessFactory.setDataAccessUtil(tgtClass, utilManyKey);
    }

    public void setFilterAccessUtil(Class tgtClass, FilterAccessUtil accessUtil) {
        dataAccessFactory.setFilterAccessUtil(tgtClass, accessUtil);
    }

    public void setPreprocessAccessUtil(Class tgtClass, PreprocessAccessUtil accessUtil) {
        dataAccessFactory.setPreprocessAccessUtil(tgtClass, accessUtil);
    }

    public void addChildFactory(String key, DataAccessFactory factory) {
        dataAccessFactory.addChildFactory(key, factory);
    }

    public DataAccessUtil getDataAccessUtil(Class tgtClass) {
        return dataAccessFactory.getDataAccessUtil(tgtClass);
    }

    public FilterAccessUtil getFilterAccessUtil(Class tgtClass) {
        return dataAccessFactory.getFilterAccessUtil(tgtClass);
    }

    public PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass) {
        return dataAccessFactory.getPreprocessAccessUtil(tgtClass);
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriDataAccessFactory getDataAccessFactory() {
        return dataAccessFactory;
    }

    public void setDataAccessFactory(BuriDataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    public void setParticipantProvider(ParticipantProvider provider) {
        participantProvider = provider;

    }

    public ParticipantProvider getParticipantProvider() {
        return participantProvider;
    }

	public CompileProcess getCompileProcess() {
		return compileProcess;
	}

	public void setCompileProcess(CompileProcess compileProcess) {
		this.compileProcess = compileProcess;
	}

}
