package org.escafe.buri.event.engine;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriActivitySelectEvent {
	private BuriExecProcess wp;
	private BuriSystemContext sysContext;
	private BuriActivitySelector selector;
	private Set<BuriActivityType> acts;
	private BuriActivityType actType;
	private String actId;
	
	public BuriExecProcess getWp() {
		return wp;
	}
	public void setWp(BuriExecProcess wp) {
		this.wp = wp;
	}
	public BuriSystemContext getSysContext() {
		return sysContext;
	}
	public void setSysContext(BuriSystemContext sysContext) {
		this.sysContext = sysContext;
	}
	public BuriActivitySelector getSelector() {
		return selector;
	}
	public void setSelector(BuriActivitySelector selector) {
		this.selector = selector;
	}
	public Set<BuriActivityType> getActs() {
		return acts;
	}
	public void setActs(Set<BuriActivityType> acts) {
		this.acts = acts;
	}
	public BuriActivityType getActType() {
		return actType;
	}
	public void setActType(BuriActivityType actType) {
		this.actType = actType;
	}
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	
}
