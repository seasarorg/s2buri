package org.escafe.buri.event.state;

import java.util.Date;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStatusEvent {
	private DataAccessFactory factory;
	private BuriSystemContext sysContext;
	private BranchWalker walker;
	private Date autorun;
	public DataAccessFactory getFactory() {
		return factory;
	}
	public void setFactory(DataAccessFactory factory) {
		this.factory = factory;
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
	public Date getAutorun() {
		return autorun;
	}
	public void setAutorun(Date autorun) {
		this.autorun = autorun;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		builder.append("CallPath=").append(sysContext.getCallPath());
		builder.append("/NowPath=").append(walker.getNowPath());
		builder.append("/UserContext=").append(sysContext.getUserContext());
		builder.append("/factory=").append(factory);
		builder.append("/autorun=").append(autorun);
		return builder.append("]").toString();
	}
}
