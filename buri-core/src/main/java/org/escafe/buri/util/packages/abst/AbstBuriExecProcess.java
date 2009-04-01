/*
 * 作成日: 2006/03/21
 *
 */
package org.escafe.buri.util.packages.abst;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.escafe.buri.aop.impl.BuriMethodInvocation;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.event.flow.caller.BuriFlowEventCaller;
import org.escafe.buri.exception.BuriException;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;

public abstract class AbstBuriExecProcess implements BuriExecProcess {
	protected static Logger logger =
	    Logger.getLogger(AbstBuriExecProcess.class);

	protected ScriptFactory scriptFactory;

	protected S2Container container;

	protected BuriWorkflowProcessType process;

	protected List<MethodInterceptor> actionInterceptors =
	    new ArrayList<MethodInterceptor>();

	protected List<MethodInterceptor> conditionInterceptors =
	    new ArrayList<MethodInterceptor>();

	protected BuriExePackages buriExePackages;

	protected BuriFlowEventCaller buriFlowEventCaller;

	public void destroy() {
		process = null;
	}

	protected Script getConditionScript() {
		return scriptFactory.getScript(buriExePackages
		    .getConditionExpressionType());
	}

	protected Logger getLogger(Object self) {
		logger = Logger.getLogger(self.getClass());
		return logger;
	}

	public void setup(BuriWorkflowProcessType process) {
		this.process = process;
	}

	public BuriWorkflowProcessType getBuriWorkflowProcessType() {
		return process;
	}

	public BranchWalker readBranchWalker(BuriSystemContext sysContext) {
		BranchWalker walker = new BranchWalker();
		walker.setBranchId(0);
		walker.setParentBranchId(0);
		walker.setParentPath(sysContext.getCallPath().moveUpPath());
		walker.setNowPath(null);
		return walker;
	}

	public String selectActivityId(BuriSystemContext sysContext) {
		buriFlowEventCaller.startSelectActivityId(this, sysContext);
		assert sysContext.getCallPath().getActivityName() != null;
		assert sysContext.getCallPath().getActivityName().size() > 0;
		String actName =
		    sysContext.getCallPath().getActivityName().get(0).toString();
		List actList = process.getActivityByName(actName);
		assert actList.size() == 1;
		BuriActivityType actType = (BuriActivityType) actList.get(0);
		buriFlowEventCaller.endSelectActivityId(this, sysContext, actType
		    .getId());
		return actType.getId();
	}

	public String getActivityNameById(String actId) {
		BuriActivityType actType = process.getActivityById(actId);
		return actType.getName();
	}

	public void entryActivity(String actId, BuriSystemContext sysContext,
	        BranchWalker walker) {
		BuriActivityType actType = process.getActivityById(actId);
		String mode = "_start";
		if (actType.isFinishModeManual() && (sysContext.getStatusId() != null)) {
			mode = "_restart";
		}
		sysContext.setStartParticipantName(actType.getParticipantName());
		walker = setupStatus(actId, sysContext, walker);
		buriFlowEventCaller
		    .entryActivity(this, actId, sysContext, walker, mode);
		execActivity(actId, mode, sysContext, walker);
	}

	protected BranchWalker setupStatus(String actId,
	        BuriSystemContext sysContext, BranchWalker walker) {
		return walker;
	}

	protected void startActivity(String actId, BuriSystemContext sysContext,
	        BranchWalker walker) {
		execActivity(actId, "_start", sysContext, walker);
	}

	private void execActivity(String actId, String mode,
	        BuriSystemContext sysContext, BranchWalker walker) {
		MethodInvokeInfo invokeInfo = new MethodInvokeInfo();
		invokeInfo.args = new Object[] { sysContext, walker };
		invokeInfo.methodArgType =
		    new Class[] { BuriSystemContext.class, BranchWalker.class };
		invokeInfo.dummyArgs =
		    new Object[] { sysContext.getUserContext(), walker.getNowPath() };
		invokeInfo.dummyMethodArgType =
		    new Class[] { BuriUserContext.class, BuriPath.class };
		invokeInfo.methodName = actId;
		invokeInfo.mode = mode;
		invokeInfo.interceptors = actionInterceptors;
		runThisMethodName(invokeInfo);
	}

	private Object runThisMethodName(MethodInvokeInfo invokeInfo) {
		Object result = null;
		BuriMethodInvocation invocation =
		    createBuriMethodInvocation(invokeInfo);
		try {
			result = invocation.proceed(); // invokeInfo.interceptorChain.invoke(invocation);
		} catch (Throwable th) {
			if (th instanceof RuntimeException) {
				throw (RuntimeException) th;
			}
			if (th instanceof Error) {
				throw (Error) th;
			}
			throw new java.lang.reflect.UndeclaredThrowableException(th);
		}
		return result;
	}

	protected Boolean conditionCheck(String methodName, String condition,
	        BuriSystemContext sysContext, BranchWalker walker) {
		buriFlowEventCaller.startConditionCheck(
		    this,
		    methodName,
		    condition,
		    sysContext,
		    walker);
		MethodInvokeInfo invokeInfo = new MethodInvokeInfo();
		invokeInfo.args = new Object[] { sysContext, walker };
		invokeInfo.methodArgType =
		    new Class[] { BuriSystemContext.class, BranchWalker.class };
		invokeInfo.dummyArgs =
		    new Object[] { sysContext.getUserContext(), condition };
		invokeInfo.dummyMethodArgType =
		    new Class[] { BuriUserContext.class, String.class };
		invokeInfo.methodName = methodName;
		invokeInfo.mode = "_condition";
		invokeInfo.interceptors = conditionInterceptors;
		Object result = runThisMethodName(invokeInfo);
		buriFlowEventCaller.endConditionCheck(
		    this,
		    methodName,
		    condition,
		    sysContext,
		    walker,
		    result);
		if (!(result instanceof Boolean)) {
			throw new BuriException(methodName
			    + "の式"
			    + condition
			    + "の戻り値がBoolean以外です。式に代入(=)を書いてる場合は比較(==)に変えてください ("
			    + result
			    + ")");
		}
		return (Boolean) result;
	}

	private BuriMethodInvocation createBuriMethodInvocation(
	        MethodInvokeInfo invokeInfo) {
		Method method =
		    ClassUtil.getMethod(this.getClass(), invokeInfo.methodName
		        + invokeInfo.mode, invokeInfo.methodArgType);
		Method dummyMethod =
		    ClassUtil.getMethod(
		        this.getClass(),
		        invokeInfo.methodName,
		        invokeInfo.dummyMethodArgType);
		BuriMethodInvocation invocation =
		    new BuriMethodInvocation(invokeInfo.interceptors);
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

		public Class<?> dummyMethodArgType[];

		public Object dummyArgs[];

		public Class<?> methodArgType[];

		public Object args[];

		public List<MethodInterceptor> interceptors;
	}

	protected void startActivity(BuriSystemContext sysContext,
	        BranchWalker walker) {
		buriFlowEventCaller.startActivity(this, sysContext, walker);
	}

	protected void restartActivity(BuriSystemContext sysContext,
	        BranchWalker walker) {
		buriFlowEventCaller.restartActivity(this, sysContext, walker);
	}

	protected void exitFlow(BuriSystemContext sysContext, BranchWalker walker) {
		buriFlowEventCaller.exitFlow(this, sysContext, walker);
		Iterator<String> ite = sysContext.getAfterCallMethods().iterator();
		while (ite.hasNext()) {
			String actId = ite.next().toString();
			buriFlowEventCaller.callAfterProcess(
			    this,
			    actId,
			    sysContext,
			    walker);
			execActivity(actId, "_afterProcess", sysContext, walker);
		}
	}

	protected List filterNextActivity(BuriSystemContext sysContext,
	        BranchWalker walker, List nextActIDs) {
		return nextActIDs;
	}

	protected void noSelectActivity(BuriSystemContext sysContext,
	        BranchWalker walker) {
	}

	protected void oneSelectActivitySplitAnd(BuriSystemContext sysContext,
	        BranchWalker walker) {
	}

	protected void oneSelectActivitySplitXor(BuriSystemContext sysContext,
	        BranchWalker walker) {
	}

	protected void manySelectActivitySplitAnd(BuriSystemContext sysContext,
	        BranchWalker walker) {
	}

	protected void manySelectActivitySplitXor(BuriSystemContext sysContext,
	        BranchWalker walker) {
	}

	protected BranchWalker splitAndPreprocess(BuriSystemContext sysContext,
	        BranchWalker walker) {
		buriFlowEventCaller.splitAndPreprocess(this, sysContext, walker);
		return walker;
	}

	protected BranchWalker getSplitAndWalker(BuriSystemContext sysContext,
	        BranchWalker walker, BranchWalker parentBranch) {
		return walker;
	}

	protected void joinXorFlow(BuriSystemContext sysContext,
	        BranchWalker walker, String nextName, String nextId) {
		buriFlowEventCaller.joinXorFlow(
		    this,
		    sysContext,
		    walker,
		    nextName,
		    nextId);
	}

	protected void noProcessAndFlow(BuriSystemContext sysContext,
	        BranchWalker walker, String nextName, String nextId) {
		buriFlowEventCaller.noProcessAndFlow(
		    this,
		    sysContext,
		    walker,
		    nextName,
		    nextId);
	}

	protected void joinAndFlow(BuriSystemContext sysContext,
	        BranchWalker walker, String nextName, String nextId) {
		buriFlowEventCaller.joinAndFlow(
		    this,
		    sysContext,
		    walker,
		    nextName,
		    nextId);
	}

	protected boolean canJoinAndFlow(BuriSystemContext sysContext,
	        BranchWalker walker, String nextName, String nextId) {
		return true;
	}

	public void addProcessAOP(MethodInterceptor interceptor) {
		actionInterceptors.add(interceptor);
	}

	public void addConditonAOP(MethodInterceptor interceptor) {
		conditionInterceptors.add(interceptor);
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

	public BuriFlowEventCaller getBuriFlowEventCaller() {
		return buriFlowEventCaller;
	}

	public void setBuriFlowEventCaller(BuriFlowEventCaller buriFlowEventCaller) {
		this.buriFlowEventCaller = buriFlowEventCaller;
	}
}
