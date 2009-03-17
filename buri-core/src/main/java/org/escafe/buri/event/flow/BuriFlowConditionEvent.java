package org.escafe.buri.event.flow;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriFlowConditionEvent {
	private BuriSystemContext sysContext;
	private BranchWalker walker;
	private BuriExecProcess buriExecProcess;
	private String methodName;
	private String condition;
	private Object result;
	
	public BuriSystemContext getSysContext() {
		return sysContext;
	}
	public void setSysContext(BuriSystemContext sysContext) {
		this.sysContext = sysContext;
	}
	public BranchWalker getWalker() {
		return walker;
	}
	public void setWalker(BranchWalker walker) {
		this.walker = walker;
	}
	public BuriExecProcess getBuriExecProcess() {
		return buriExecProcess;
	}
	public void setBuriExecProcess(BuriExecProcess buriExecProcess) {
		this.buriExecProcess = buriExecProcess;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		builder.append("/CallPath=").append(sysContext.getCallPath());
		builder.append("/NowPath=").append(walker.getNowPath());
		builder.append("/UserContext=").append(sysContext.getUserContext());
		builder.append("/buriExecProcess=").append(buriExecProcess);
		builder.append("/condition=").append(condition);
		builder.append("/result=").append(result);
		return builder.append("]").toString();
	}

}
