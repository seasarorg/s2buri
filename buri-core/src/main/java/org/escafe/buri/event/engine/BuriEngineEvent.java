package org.escafe.buri.event.engine;

import org.escafe.buri.engine.BuriSystemContext;

public class BuriEngineEvent {
	private BuriSystemContext sysContext;
	private String resultScript;
	private Object result;
	
	public BuriSystemContext getSysContext() {
		return sysContext;
	}
	public void setSysContext(BuriSystemContext sysContext) {
		this.sysContext = sysContext;
	}
	public String getResultScript() {
		return resultScript;
	}
	public void setResultScript(String resultScript) {
		this.resultScript = resultScript;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
