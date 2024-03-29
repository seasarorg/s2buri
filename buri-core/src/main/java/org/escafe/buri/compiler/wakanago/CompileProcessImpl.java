/*
 * 作成日: 2006/05/02
 *
 */
package org.escafe.buri.compiler.wakanago;

import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.escafe.buri.compiler.CompileProcess;
import org.escafe.buri.compiler.DataFieldCompiler;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.event.boot.BuriCompileEvent;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.escafe.buri.util.packages.abst.AbstBuriExeProcessDataAccess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public class CompileProcessImpl implements CompileProcess {
    //    private static Logger logger = Logger.getLogger(CompileProcessImpl.class);

    private S2Container container;

    private BasicCompler compler;

    private String processTemplateName = "ftl/wakanagoProcess.ftl";
    private DataFieldCompiler dataFieldCompiler;
    private BuriComplieEventCaller eventCaller;


    public void compile(BuriExePackages result, BuriWorkflowProcessType process, ParticipantProvider provider,ClassLoader classLoader) {
        BuriExecProcess exeProcess = compileProcess(process, result.getBuriPackageType(), provider,classLoader);
        result.setProcess(process.getId(), exeProcess);
        exeProcess.setBuriExePackages(result);
        BuriDataAccessFactory accessFactory = (BuriDataAccessFactory) exeProcess;
        accessFactory.addChildFactory(result.getBuriPackageType().getId(), (DataAccessFactory) result);
        compileDataAccess(exeProcess, process);
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory) container.getComponent("rootDataAccessFactory");
        dataAccessFactory.addChildFactory(process.getId(), (BuriDataAccessFactory) exeProcess);
    	String tgt = result.getBuriPackageType().getName() + "." + process.getName();
        eventCaller.callEndObjectInit(this,BuriCompileEvent.CompileTargetType.Process,tgt,exeProcess);
    }

    protected void compileDataAccess(BuriExecProcess exeProcess, BuriWorkflowProcessType process) {
        BuriPackageType buriPackage = process.getPackageType();
        BuriDataAccessFactory factory = (BuriDataAccessFactory) exeProcess;
        dataFieldCompiler.compileAndSetting(factory, buriPackage, process,exeProcess.getClass().getClassLoader());
    }

    protected BuriExecProcess compileProcess(BuriWorkflowProcessType process, BuriPackageType buriPackage, ParticipantProvider provider,ClassLoader classLoader) {
    	String tgt = buriPackage.getName() + "." + process.getName();
        eventCaller.callStartCompile(this,BuriCompileEvent.CompileTargetType.Process,tgt);
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName("" + buriPackage.getId() + "." + process.getId());
        info.setBaseClass(getAbstractProcessClass(provider));
        info.setInterfaceClass(new Class[] {});
        info.setBaseObjectName("process");
        info.setBaseObject(process);
        info.setTemplateFileName(processTemplateName);
        info.setParentClassLoader(classLoader);
        BuriExecProcess exeProcess = (BuriExecProcess) compler.Compile(info);
        eventCaller.callEndCompile(this,BuriCompileEvent.CompileTargetType.Process,tgt,exeProcess);
        eventCaller.callStartObjectInit(this,BuriCompileEvent.CompileTargetType.Process,tgt);
        container.injectDependency(exeProcess, getAbstractProcessClass(provider));
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

	public BuriComplieEventCaller getEventCaller() {
		return eventCaller;
	}

	public void setEventCaller(BuriComplieEventCaller eventCaller) {
		this.eventCaller = eventCaller;
	}

}
