/*
 * ì¬“ú: 2006/03/21
 *
 */
package org.seasar.buri.util.packages.abst;

import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.buri.aop.impl.BuriMethodInvocation;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.aop.interceptors.InterceptorChain;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;

public abstract class AbstBuriExecProcess implements BuriExecProcess ,BuriDataAccessFactory{
//    private static Logger logger = Logger.getLogger(AbstBuriExecProcess.class);
    protected ScriptFactory scriptFactory;
    protected Script conditionScript;
    protected S2Container container;
    protected BuriWorkflowProcessType process;
    protected InterceptorChain actionInterceptor = new InterceptorChain();
    protected InterceptorChain conditionInterceptor = new InterceptorChain();
    
    protected BuriExePackages buriExePackages;
    protected BuriDataAccessFactory dataAccessFactory;
    
    public void setup(BuriWorkflowProcessType process) {
        this.process = process;
        DataAccessFactory rootFactory = (DataAccessFactory)container.getComponent("rootDataAccessFactory");
        rootFactory.addChildFactory(process.getId(),this);
    }
    
    public BuriWorkflowProcessType getBuriWorkflowProcessType() {
        return process;
    }
    

    public String selectActivityId(BuriSystemContext sysContext) {
        assert sysContext.getCallPath().getActivityName() != null;
        assert sysContext.getCallPath().getActivityName().size() > 0;
        String actName = sysContext.getCallPath().getActivityName().get(0).toString();
        List actList = process.getActivityByName(actName);
        assert actList.size() == 1;
        BuriActivityType actType = (BuriActivityType)actList.get(0);
        return actType.getId();
    }
    
    public String getActivityNameById(String actId) {
        BuriActivityType actType = process.getActivityById(actId);
        return actType.getName();
    }
    
    public void entryActivity(String actId,BuriSystemContext sysContext,BranchWalker walker) {
        BuriActivityType actType = process.getActivityById(actId);
        String mode = "_start";
        if(actType.isFinishModeManual()) {
            mode = "_restart";
        }
        walker = setupStatus(actId,sysContext,walker);
        execActivity(actId,mode,sysContext,walker);
    }
    
    protected BranchWalker setupStatus(String actId,BuriSystemContext sysContext,BranchWalker walker) {
        return walker;
    }
    
    protected void startActivity(String actId,BuriSystemContext sysContext,BranchWalker walker) {
        execActivity(actId,"_start",sysContext,walker);
    }
    
    private void execActivity(String actId,String mode,BuriSystemContext sysContext,BranchWalker walker) {
        MethodInvokeInfo invokeInfo = new MethodInvokeInfo();
        invokeInfo.args = new Object[]{sysContext,walker};
        invokeInfo.methodArgType = new Class[]{BuriSystemContext.class,BranchWalker.class};
        invokeInfo.dummyArgs = new Object[]{sysContext.getUserContext(),walker.getNowPath()};
        invokeInfo.dummyMethodArgType = new Class[]{BuriUserContext.class,BuriPath.class};
        invokeInfo.methodName = actId;
        invokeInfo.mode = mode;
        invokeInfo.interceptorChain = actionInterceptor;
        runThisMethodName(invokeInfo);
    }
    
    private Object runThisMethodName(MethodInvokeInfo invokeInfo) {
        Object result = null;
        BuriMethodInvocation invocation = createBuriMethodInvocation(invokeInfo);
        try{
            result = invokeInfo.interceptorChain.invoke(invocation);
        } catch(Throwable th) {
            if(th instanceof RuntimeException) {
                throw (RuntimeException)th;
            }
            if(th instanceof Error) {
                throw (Error)th;
            }
            throw new java.lang.reflect.UndeclaredThrowableException(th);
        }
        return result;
    }
    
    protected Boolean conditionCheck(String methodName,String condition,BuriSystemContext sysContext,BranchWalker walker) {
        MethodInvokeInfo invokeInfo = new MethodInvokeInfo();
        invokeInfo.args = new Object[]{sysContext,walker};
        invokeInfo.methodArgType = new Class[]{BuriSystemContext.class,BranchWalker.class};
        
        invokeInfo.dummyArgs = new Object[]{sysContext.getUserContext(),condition};
        invokeInfo.dummyMethodArgType = new Class[]{BuriUserContext.class,String.class};
        
        invokeInfo.methodName = methodName;
        invokeInfo.mode = "_condition";
        invokeInfo.interceptorChain = conditionInterceptor;
        Object result = runThisMethodName(invokeInfo);
        assert result instanceof Boolean: methodName + "‚Ì–ß‚è’l‚ªBooleanˆÈŠO‚Å‚·(" + result + ")";
        return (Boolean)result;
    }
    
    private BuriMethodInvocation createBuriMethodInvocation(MethodInvokeInfo invokeInfo) {
        Method method = ClassUtil.getMethod(this.getClass(),invokeInfo.methodName+invokeInfo.mode,invokeInfo.methodArgType);
        Method dummyMethod = ClassUtil.getMethod(this.getClass(),invokeInfo.methodName,invokeInfo.dummyMethodArgType);
        BuriMethodInvocation invocation = new BuriMethodInvocation();
        invocation.setArguments(invokeInfo.dummyArgs);
        invocation.setMethod(dummyMethod);
        invocation.setThisObject(this);
        invocation.setTargetClass(this.getClass());
        invocation.setCallMethod(method);
        invocation.setCallArguments(invokeInfo.args);
        return invocation;
    }
    
    private class MethodInvokeInfo {
        public String methodName;
        public String mode;
        public BuriSystemContext sysContext;
        public BranchWalker walker;
        public Class dummyMethodArgType[];
        public Object dummyArgs[];
        public Class methodArgType[];
        public Object args[];
        public InterceptorChain interceptorChain;
    }
    
    protected void startActivity(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void restartActivity(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void exitFlow(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected List filterNextActivity(BuriSystemContext sysContext,BranchWalker walker,List nextActIDs) {
        return nextActIDs;
    }
    
    protected void noSelectActivity(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void oneSelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void oneSelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitAnd(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected void manySelectActivitySplitXor(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    protected BranchWalker splitAndPreprocess(BuriSystemContext sysContext,BranchWalker walker) {
        return walker;
    }
    
    protected BranchWalker getSplitAndWalker(BuriSystemContext sysContext,BranchWalker walker,BranchWalker parentBranch) {
        return walker;
    }
    
    protected void joinXorFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        
    }
    
    protected boolean joinAndFlow(BuriSystemContext sysContext,BranchWalker walker,String nextName,String nextId) {
        return true;
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

    
    
    public void addProcessAOP(MethodInterceptor interceptor) {
        actionInterceptor.add(interceptor);
    }
    
    public void addConditonAOP(MethodInterceptor interceptor) {
        conditionInterceptor.add(interceptor);
    }

    
    public S2Container getContainer() {
        return container;
    }


    public void setContainer(S2Container container) {
        this.container = container;
    }


    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }


    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

    public BuriExePackages getBuriExePackages() {
        return buriExePackages;
    }

    public void setBuriExePackages(BuriExePackages buriExePackages) {
        this.buriExePackages = buriExePackages;
    }

    public BuriDataAccessFactory getDataAccessFactory() {
        return dataAccessFactory;
    }

    public void setDataAccessFactory(BuriDataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    

}
