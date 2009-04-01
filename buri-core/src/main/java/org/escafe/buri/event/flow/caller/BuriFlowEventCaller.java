package org.escafe.buri.event.flow.caller;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.flow.BuriFlowEventListener;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface BuriFlowEventCaller {
	void addBuriFlowEventListener(BuriFlowEventListener listener);
	void startSelectActivityId(BuriExecProcess buriExecProcess,BuriSystemContext sysContext);
	void startActivity(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker);
	void restartActivity(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker);
	void endSelectActivityId(BuriExecProcess buriExecProcess,BuriSystemContext sysContext,String actId);
	void entryActivity(BuriExecProcess buriExecProcess,String actId, BuriSystemContext sysContext, BranchWalker walker,String mode);
	void startConditionCheck(BuriExecProcess buriExecProcess,String methodName, String condition, BuriSystemContext sysContext, BranchWalker walker);
	void endConditionCheck(BuriExecProcess buriExecProcess,String methodName, String condition, BuriSystemContext sysContext, BranchWalker walker,Object result);
	void callAfterProcess(BuriExecProcess buriExecProcess,String actId,BuriSystemContext sysContext, BranchWalker walker);
	void splitAndPreprocess(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker);
	void noProcessAndFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
	void joinXorFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
	void joinAndFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId);
	void exitFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker);
	
}
