/*
 * 作成日: 2006/05/02
 *
 */
package org.escafe.buri.compiler.wakanago;

import org.escafe.buri.compiler.CompilePackage;
import org.escafe.buri.compiler.DataFieldCompiler;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.event.boot.BuriCompileEvent;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.abst.AbstBuriExePackages;
import org.seasar.framework.container.S2Container;

public class CompilePackageImpl implements CompilePackage {
    //    private static Logger logger = Logger.getLogger(CompilePackageImpl.class);

    private S2Container container;

    private String packageTemplateName = "ftl/wakanagoPackage.ftl";
    private DataFieldCompiler dataFieldCompiler;
    private BuriComplieEventCaller eventCaller;

    public BuriExePackages compile(BuriPackageType buriPackage) {
        eventCaller.callStartObjectInit(this,BuriCompileEvent.CompileTargetType.Package,buriPackage.getName());
        BuriExePackages result = new AbstBuriExePackages();
        container.injectDependency(result, AbstBuriExePackages.class);
        result.setup(buriPackage);
        compileDataAccess(result, buriPackage);
        eventCaller.callEndObjectInit(this,BuriCompileEvent.CompileTargetType.Package,buriPackage.getName(),result);
        return result;
    }

    protected void compileDataAccess(BuriExePackages exePackages, BuriPackageType buriPackage) {
        BuriDataAccessFactory factory = (BuriDataAccessFactory) exePackages;
        dataFieldCompiler.compileAndSetting(factory, buriPackage, null,exePackages.getClass().getClassLoader());
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getPackageTemplateName() {
        return packageTemplateName;
    }

    public void setPackageTemplateName(String packageTemplateName) {
        this.packageTemplateName = packageTemplateName;
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
