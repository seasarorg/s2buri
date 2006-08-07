/*
 * çÏê¨ì˙: 2006/05/02
 *
 */
package org.seasar.buri.compiler.wakanago;

import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.seasar.buri.compiler.CompileProcess;
import org.seasar.buri.compiler.DataFieldCompiler;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.buri.util.packages.abst.AbstBuriExeProcessDataAccess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class CompileProcessImpl implements CompileProcess {
//    private static Logger logger = Logger.getLogger(CompileProcessImpl.class);

    private S2Container container;
    
    private BasicCompler compler;

    private String processTemplateName ="ftl/wakanagoProcess.ftl";
    private DataFieldCompiler dataFieldCompiler;


    public void compile(BuriExePackages result,BuriWorkflowProcessType process,ParticipantProvider provider) {
        BuriExecProcess exeProcess = compileProcess(process,result.getBuriPackageType(),provider);
        result.setProcess(process.getId(),exeProcess);
        exeProcess.setBuriExePackages(result);
        BuriDataAccessFactory accessFactory = (BuriDataAccessFactory)exeProcess;
        accessFactory.addChildFactory(result.getBuriPackageType().getId(),(DataAccessFactory)result);
        compileDataAccess(exeProcess,process);
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)container.getComponent("rootDataAccessFactory");
        dataAccessFactory.addChildFactory(process.getId(),(BuriDataAccessFactory)exeProcess);
    }
    
    protected void compileDataAccess(BuriExecProcess exeProcess,BuriWorkflowProcessType process) {
        BuriPackageType buriPackage = process.getPackageType();
        BuriDataAccessFactory factory = (BuriDataAccessFactory)exeProcess;
        dataFieldCompiler.compileAndSetting(factory,buriPackage,process);
    }
    
    protected BuriExecProcess compileProcess(BuriWorkflowProcessType process,BuriPackageType buriPackage,ParticipantProvider provider) {
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName("" + buriPackage.getId() + "." + process.getId());
        info.setBaseClass(getAbstractProcessClass(provider));
        info.setInterfaceClass(new Class[]{});
        info.setBaseObjectName("process");
        info.setBaseObject(process);
        info.setTemplateFileName(processTemplateName);
        BuriExecProcess exeProcess = (BuriExecProcess)compler.Compile(info);
        container.injectDependency(exeProcess,getAbstractProcessClass(provider));
        exeProcess.setup(process);
        return exeProcess;
    }
    
    
    protected Class getAbstractProcessClass(ParticipantProvider provider) {
        return AbstBuriExeProcessDataAccess.class;
    }

    public String getProcessTemplateName() {
        return processTemplateName;
    }

    public void setProcessTemplateName(String processTemplateName) {
        this.processTemplateName = processTemplateName;
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
    
    public DataFieldCompiler getDataFieldCompiler() {
        return dataFieldCompiler;
    }

    public void setDataFieldCompiler(DataFieldCompiler dataFieldCompiler) {
        this.dataFieldCompiler = dataFieldCompiler;
    }
    
}
