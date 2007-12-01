package org.escafe.buri.event.engine;

import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriProcessSelectEvent {
	private BuriExePackages wPackageObj;
	private BuriSystemContext sysContext;
	private BuriProcessSelector selector;
	private List pros;
	private BuriExecProcess process;
	private BuriPath callPath;
	
	public BuriExePackages getWPackageObj() {
		return wPackageObj;
	}
	public void setWPackageObj(BuriExePackages packageObj) {
		wPackageObj = packageObj;
	}
	public BuriSystemContext getSysContext() {
		return sysContext;
	}
	public void setSysContext(BuriSystemContext sysContext) {
		this.sysContext = sysContext;
	}
	public BuriProcessSelector getSelector() {
		return selector;
	}
	public void setSelector(BuriProcessSelector selector) {
		this.selector = selector;
	}
	public List getPros() {
		return pros;
	}
	public void setPros(List pros) {
		this.pros = pros;
	}
	public BuriExecProcess getProcess() {
		return process;
	}
	public void setProcess(BuriExecProcess process) {
		this.process = process;
	}
	public BuriPath getCallPath() {
		return callPath;
	}
	public void setCallPath(BuriPath callPath) {
		this.callPath = callPath;
	}
	
}
