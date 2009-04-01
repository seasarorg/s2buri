package org.escafe.buri.event.flow;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriFlowEvent {
	private String actId;
	private BuriSystemContext sysContext;
	private BranchWalker walker;
	private BuriExecProcess buriExecProcess;
	private String mode;
	private String methodName;
	
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
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
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		builder.append("actId=").append(actId);
		builder.append("/CallPath=").append(sysContext.getCallPath());
		builder.append("/NowPath=").append(walker.getNowPath());
		builder.append("/UserContext=").append(sysContext.getUserContext());
		builder.append("/buriExecProcess=").append(buriExecProcess);
		builder.append("/mode=").append(mode);
		builder.append("/methodName=").append(methodName);
		return builder.append("]").toString();
	}

}
