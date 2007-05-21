/*
 * 作成日: 2006/05/02
 *
 */
package org.escafe.buri.compiler.wakanago;

import org.escafe.buri.compiler.CompilePackage;
import org.escafe.buri.compiler.DataFieldCompiler;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.abst.AbstBuriExePackages;
import org.seasar.framework.container.S2Container;

public class CompilePackageImpl implements CompilePackage {
//    private static Logger logger = Logger.getLogger(CompilePackageImpl.class);
    
    private S2Container container;
    
    private String packageTemplateName ="ftl/wakanagoPackage.ftl";
    private DataFieldCompiler dataFieldCompiler;
    
    public BuriExePackages compile(BuriPackageType buriPackage) {
        BuriExePackages result = new AbstBuriExePackages();
        container.injectDependency(result,AbstBuriExePackages.class);
        result.setup(buriPackage);
        compileDataAccess(result,buriPackage);
        return result;
    }
    
    protected void compileDataAccess(BuriExePackages exePackages,BuriPackageType buriPackage) {
        BuriDataAccessFactory factory = (BuriDataAccessFactory)exePackages;
        dataFieldCompiler.compileAndSetting(factory,buriPackage,null);
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


}
