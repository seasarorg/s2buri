package org.escafe.buri.event.flow.caller.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.flow.BuriFlowConditionEvent;
import org.escafe.buri.event.flow.BuriFlowEvent;
import org.escafe.buri.event.flow.BuriFlowEventListener;
import org.escafe.buri.event.flow.BuriFlowJoinSplitEvent;
import org.escafe.buri.event.flow.caller.BuriFlowEventCaller;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriFlowEventCallerImpl implements BuriFlowEventCaller {
	private List<BuriFlowEventListener> listeners = new ArrayList<BuriFlowEventListener>();
	
	public void addBuriFlowEventListener(BuriFlowEventListener listener) {
		listeners.add(listener);
	}
	
	public void startSelectActivityId(BuriExecProcess buriExecProcess,BuriSystemContext sysContext) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		for(BuriFlowEventListener listener : listeners) {
			listener.startSelectActivityId(event);
		}
	}
	public void endSelectActivityId(BuriExecProcess buriExecProcess,BuriSystemContext sysContext,String actId) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setActId(actId);
		for(BuriFlowEventListener listener : listeners) {
			listener.endSelectActivityId(event);
		}
	}
	
	public void entryActivity(BuriExecProcess buriExecProcess,String actId, BuriSystemContext sysContext, BranchWalker walker,String mode) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setActId(actId);
		event.setWalker(walker);
		event.setMode(mode);
		for(BuriFlowEventListener listener : listeners) {
			listener.entryActivity(event);
		}
	}
	public void startActivity(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for(BuriFlowEventListener listener : listeners) {
			listener.startActivity(event);
		}
	}
	public void restartActivity(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for(BuriFlowEventListener listener : listeners) {
			listener.restartActivity(event);
		}
	}
	
	public void startConditionCheck(BuriExecProcess buriExecProcess,String methodName, String condition, BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowConditionEvent event = new BuriFlowConditionEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setMethodName(methodName);
		event.setCondition(condition);
		for(BuriFlowEventListener listener : listeners) {
			listener.startConditionCheck(event);
		}
	}
	
	public void endConditionCheck(BuriExecProcess buriExecProcess,String methodName, String condition, BuriSystemContext sysContext, BranchWalker walker,Object result) {
		BuriFlowConditionEvent event = new BuriFlowConditionEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setMethodName(methodName);
		event.setCondition(condition);
		event.setResult(result);
		for(BuriFlowEventListener listener : listeners) {
			listener.endConditionCheck(event);
		}
	}
	
	public void splitAndPreprocess(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowJoinSplitEvent event = new BuriFlowJoinSplitEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for(BuriFlowEventListener listener : listeners) {
			listener.splitAndPreprocess(event);
		}
	}
	public void joinAndFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		BuriFlowJoinSplitEvent event = new BuriFlowJoinSplitEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setNextId(nextId);
		event.setNextName(nextName);
		for(BuriFlowEventListener listener : listeners) {
			listener.joinAndFlow(event);
		}
	}
	public void noProcessAndFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		BuriFlowJoinSplitEvent event = new BuriFlowJoinSplitEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setNextId(nextId);
		event.setNextName(nextName);
		for(BuriFlowEventListener listener : listeners) {
			listener.noProcessAndFlow(event);
		}
	}
	public void joinXorFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker, String nextName, String nextId) {
		BuriFlowJoinSplitEvent event = new BuriFlowJoinSplitEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		event.setNextId(nextId);
		event.setNextName(nextName);
		for(BuriFlowEventListener listener : listeners) {
			listener.joinXorFlow(event);
		}
	}
	
	public void callAfterProcess(BuriExecProcess buriExecProcess,String actId,BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setActId(actId);
		event.setWalker(walker);
		for(BuriFlowEventListener listener : listeners) {
			listener.callAfterProcess(event);
		}
	}
	
	public void exitFlow(BuriExecProcess buriExecProcess,BuriSystemContext sysContext, BranchWalker walker) {
		BuriFlowEvent event = new BuriFlowEvent();
		event.setBuriExecProcess(buriExecProcess);
		event.setSysContext(sysContext);
		event.setWalker(walker);
		for(BuriFlowEventListener listener : listeners) {
			listener.exitFlow(event);
		}
	}
	
}
