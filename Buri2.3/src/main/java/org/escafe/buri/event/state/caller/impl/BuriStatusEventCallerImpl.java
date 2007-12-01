package org.escafe.buri.event.state.caller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.state.BuriStatusEvent;
import org.escafe.buri.event.state.BuriStatusEventListener;
import org.escafe.buri.event.state.caller.BuriStatusEventCaller;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStatusEventCallerImpl implements BuriStatusEventCaller{
	
	private List<BuriStatusEventListener> listeners = new ArrayList<BuriStatusEventListener>();
	
	public void addStatusEventListener(BuriStatusEventListener listener) {
		listeners.add(listener);
	}
	public void saveState(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setFactory(factory);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for (BuriStatusEventListener listener : listeners) {
			listener.saveState(event);
		}
	}
	public void processed(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setFactory(factory);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for (BuriStatusEventListener listener : listeners) {
			listener.processed(event);
		}
	}
	public void abortState(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setFactory(factory);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for (BuriStatusEventListener listener : listeners) {
			listener.abortState(event);
		}
	}
	public void abortBranch(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setFactory(factory);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for (BuriStatusEventListener listener : listeners) {
			listener.abortBranch(event);
		}
	}
	public void createBranch(BuriSystemContext sysContext, BranchWalker walker) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for (BuriStatusEventListener listener : listeners) {
			listener.abortBranch(event);
		}
	}
	public void setAutorun(DataAccessFactory factory,BuriSystemContext sysContext, BranchWalker walker, Date autorun) {
		BuriStatusEvent event = new BuriStatusEvent();
		event.setFactory(factory);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setAutorun(autorun);
		for (BuriStatusEventListener listener : listeners) {
			listener.abortBranch(event);
		}
	}
	
}
