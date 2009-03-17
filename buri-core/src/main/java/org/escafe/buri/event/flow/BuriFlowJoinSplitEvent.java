package org.escafe.buri.event.flow;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriFlowJoinSplitEvent {
	private BuriSystemContext sysContext;
	private BranchWalker walker;
	private BuriExecProcess buriExecProcess;
	private String mode;
	private String methodName;
	private String nextName;
	private String nextId;
	
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
	public String getNextName() {
		return nextName;
	}
	public void setNextName(String nextName) {
		this.nextName = nextName;
	}
	public String getNextId() {
		return nextId;
	}
	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

}
