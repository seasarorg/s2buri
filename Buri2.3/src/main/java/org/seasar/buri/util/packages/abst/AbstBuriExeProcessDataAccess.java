/*
 * 作成日: 2006/05/06
 *
 */
package org.seasar.buri.util.packages.abst;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.compiler.DataFieldCompiler;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public class AbstBuriExeProcessDataAccess extends AbstBuriExecProcess implements BuriDataAccessFactory{
    private BuriStateUtil stateUtil;
    protected BuriDataAccessFactory dataAccessFactory;
    private DataFieldCompiler dataFieldCompiler;
    private ClassDefUtil classDefUtil;

    public void setup(BuriWorkflowProcessType process) {
        super.setup(process);
        DataAccessFactory rootFactory = (DataAccessFactory)container.getComponent("rootDataAccessFactory");
        rootFactory.addChildFactory(process.getId(),this);
    }

    public BranchWalker readBranchWalker(BuriSystemContext sysContext) {
        BranchWalker walker = getStateUtil().loadBranchWalker(sysContext);
        return walker;
    }
    
    protected BranchWalker setupStatus(String actId,BuriSystemContext sysContext,BranchWalker walker) {
        BranchWalker startWalker = walker.moveNext(getActivityNameById(actId),actId);
        long statusID = getStateUtil().loadStatus(this,sysContext,startWalker);
        sysContext.setStatusID(new Long(statusID));
        BranchWalker loadWalker = getStateUtil().loadBranchWalker(sysContext);
        return loadWalker;
    }
    
    protected void exitFlow(BuriSystemContext sysContext,BranchWalker walker) {
        getStateUtil().saveBranch(walker,this,sysContext);
        long statusId = getStateUtil().saveStatus(this,sysContext,walker);
        sysContext.setStatusID(new Long(statusId));
        super.exitFlow(sysContext,walker);
    }
    
    protected void restartActivity(BuriSystemContext sysContext,BranchWalker walker) {
        getStateUtil().processed(this,sysContext,walker);
    }
    
    protected void noSelectActivity(BuriSystemContext sysContext,BranchWalker walker) {
        throw new BuriNotSelectedActivityException(walker.getNowPath(),null);
    }
    
    protected void oneSelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void oneSelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        throw new BuriManySelectActivityException(walker.getNowPath(),null);
    }
    
    
    protected BranchWalker splitAndPreprocess(BuriSystemContext sysContext,BranchWalker walker) {
        getStateUtil().saveBranch(walker,this,sysContext);
        return walker;
    }
    
    protected BranchWalker getSplitAndWalker(BuriSystemContext sysContext,BranchWalker walker,BranchWalker parentBranch) {
        BranchWalker child = getStateUtil().branchChild(walker,this,sysContext);
        return child;
    }
    
    protected void joinXorFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        if(walker.getParentBranchID()!=0) {
            getStateUtil().abortBranch(this,sysContext,walker);
        }
    }
    
    protected boolean joinAndFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        long count = getStateUtil().countNoProcessedSiblingStatus(this,sysContext,walker);
        if(count==0) {
            return true;
        }
        return false;
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
        DataAccessUtil util = dataAccessFactory.getDataAccessUtil(tgtClass);
        return util;
//        if(util == null) {
//            BuriDataFieldType fieldType = new BuriDataFieldType();
//            fieldType.setId(classDefUtil.getClassName(tgtClass));
//            dataFieldCompiler.compileAndSettingOne(fieldType,(BuriDataAccessFactory)this,getBuriExePackages().getBuriPackageType(),this.getBuriWorkflowProcessType());
//        }
//        return dataAccessFactory.getDataAccessUtil(tgtClass);
    }

    public FilterAccessUtil getFilterAccessUtil(Class tgtClass) {
        return dataAccessFactory.getFilterAccessUtil(tgtClass);
    }

    public PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass) {
        return dataAccessFactory.getPreprocessAccessUtil(tgtClass);
    }


    
    
    public BuriStateUtil getStateUtil() {
        return stateUtil;
    }

    public void setStateUtil(BuriStateUtil stateUtil) {
        this.stateUtil = stateUtil;
    }

    public BuriDataAccessFactory getDataAccessFactory() {
        return dataAccessFactory;
    }

    public void setDataAccessFactory(BuriDataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    public DataFieldCompiler getDataFieldCompiler() {
        return dataFieldCompiler;
    }

    public void setDataFieldCompiler(DataFieldCompiler dataFieldCompiler) {
        this.dataFieldCompiler = dataFieldCompiler;
    }

	public ClassDefUtil getClassDefUtil() {
		return classDefUtil;
	}

	public void setClassDefUtil(ClassDefUtil classDefUtil) {
		this.classDefUtil = classDefUtil;
	}

    
}
