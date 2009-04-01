package org.escafe.buri.event.engine.caller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.event.engine.BuriActivitySelectEvent;
import org.escafe.buri.event.engine.BuriActivitySelectEventListener;
import org.escafe.buri.event.engine.caller.BuriActivitySelectEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriActivitySelectEventCallerImpl implements BuriActivitySelectEventCaller {
	private List<BuriActivitySelectEventListener> listeners = new ArrayList<BuriActivitySelectEventListener>();
	
	public void addEventListener(BuriActivitySelectEventListener listener) {
		listeners.add(listener);
	}

	public void startSelectActivityId(BuriExecProcess wp, BuriSystemContext sysContext) {
		BuriActivitySelectEvent event = new BuriActivitySelectEvent();
		event.setWp(wp);
		event.setSysContext(sysContext);
		for (BuriActivitySelectEventListener listener : listeners) {
			listener.startSelectActivityId(event);
		}
	}

	public void startActivitySelector(BuriExecProcess wp, BuriSystemContext sysContext, BuriActivitySelector selector,Set<BuriActivityType> acts) {
		BuriActivitySelectEvent event = new BuriActivitySelectEvent();
		event.setWp(wp);
		event.setSysContext(sysContext);
		event.setSelector(selector);
		event.setActs(acts);
		for (BuriActivitySelectEventListener listener : listeners) {
			listener.startActivitySelector(event);
		}
	}
	
	public void endActivitySelector(BuriExecProcess wp, BuriSystemContext sysContext, BuriActivitySelector selector,Set<BuriActivityType> acts) {
		BuriActivitySelectEvent event = new BuriActivitySelectEvent();
		event.setWp(wp);
		event.setSysContext(sysContext);
		event.setSelector(selector);
		event.setActs(acts);
		for (BuriActivitySelectEventListener listener : listeners) {
			listener.endActivitySelector(event);
		}
	}

	public void endSelectActivityId(BuriExecProcess wp, BuriSystemContext sysContext, String actId,Set<BuriActivityType> acts) {
		BuriActivitySelectEvent event = new BuriActivitySelectEvent();
		event.setWp(wp);
		event.setSysContext(sysContext);
		event.setActId(actId);
		event.setActs(acts);
		for (BuriActivitySelectEventListener listener : listeners) {
			listener.endSelectActivityId(event);
		}
	}

	public void errorActivitySelect(Set<BuriActivityType> acts, BuriSystemContext systemContext, BuriExecProcess wp) {
		BuriActivitySelectEvent event = new BuriActivitySelectEvent();
		event.setWp(wp);
		event.setActs(acts);
		for (BuriActivitySelectEventListener listener : listeners) {
			listener.errorActivitySelect(event);
		}
	}

}
