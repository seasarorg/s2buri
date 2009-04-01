package org.escafe.buri.event.engine.caller.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.event.engine.BuriProcessSelectEvent;
import org.escafe.buri.event.engine.BuriProcessSelectEventListener;
import org.escafe.buri.event.engine.caller.BuriProcessSelectEventCaller;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriProcessSelectEventCallerImpl implements BuriProcessSelectEventCaller {
	private List<BuriProcessSelectEventListener> listeners = new ArrayList<BuriProcessSelectEventListener>();
	
	public void addEventListener(BuriProcessSelectEventListener listener) {
		listeners.add(listener);
	}

	public void startSelectProcess(BuriExePackages packageObj,BuriSystemContext sysContext) {
		BuriProcessSelectEvent event = new BuriProcessSelectEvent();
		event.setWPackageObj(packageObj);
		event.setSysContext(sysContext);
		for (BuriProcessSelectEventListener listener : listeners) {
			listener.startSelectProcess(event);
		}
	}

	public void startSelector(BuriExePackages packageObj,BuriSystemContext sysContext, BuriProcessSelector selector,List pros) {
		BuriProcessSelectEvent event = new BuriProcessSelectEvent();
		event.setWPackageObj(packageObj);
		event.setSysContext(sysContext);
		event.setSelector(selector);
		event.setPros(pros);
		for (BuriProcessSelectEventListener listener : listeners) {
			listener.startSelector(event);
		}
	}

	public void endSelector(BuriExePackages packageObj,BuriSystemContext sysContext, BuriProcessSelector selector,List pros) {
		BuriProcessSelectEvent event = new BuriProcessSelectEvent();
		event.setWPackageObj(packageObj);
		event.setSysContext(sysContext);
		event.setSelector(selector);
		event.setPros(pros);
		for (BuriProcessSelectEventListener listener : listeners) {
			listener.endSelector(event);
		}
	}

	public void endSelectProcess(BuriExePackages packageObj,BuriSystemContext sysContext, BuriExecProcess process, List pros) {
		BuriProcessSelectEvent event = new BuriProcessSelectEvent();
		event.setWPackageObj(packageObj);
		event.setSysContext(sysContext);
		event.setPros(pros);
		event.setProcess(process);
		for (BuriProcessSelectEventListener listener : listeners) {
			listener.endSelectProcess(event);
		}
	}

	public void errorSelectProcess(BuriExePackages packageObj,BuriSystemContext systemContext, BuriPath callPath, List proces) {
		BuriProcessSelectEvent event = new BuriProcessSelectEvent();
		event.setWPackageObj(packageObj);
		event.setSysContext(systemContext);
		event.setPros(proces);
		event.setCallPath(callPath);
		for (BuriProcessSelectEventListener listener : listeners) {
			listener.errorSelectProcess(event);
		}
	}

}
