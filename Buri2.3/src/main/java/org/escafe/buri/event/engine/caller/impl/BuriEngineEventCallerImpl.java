package org.escafe.buri.event.engine.caller.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.engine.BuriEngineEvent;
import org.escafe.buri.event.engine.BuriEngineEventListener;
import org.escafe.buri.event.engine.caller.BuriEngineEventCaller;

public class BuriEngineEventCallerImpl implements BuriEngineEventCaller {
	private List<BuriEngineEventListener> listeners = new ArrayList<BuriEngineEventListener>();
	
	public void addEventListener(BuriEngineEventListener listener) {
		listeners.add(listener);
	}
	
	public void startExecute(BuriSystemContext sysContext, String resultScript) {
		BuriEngineEvent event = new BuriEngineEvent();
		event.setSysContext(sysContext);
		event.setResultScript(resultScript);
		for (BuriEngineEventListener listener : listeners) {
			listener.startExecute(event);
		}
	}

	public void startFlow(BuriSystemContext sysContext, String resultScript) {
		BuriEngineEvent event = new BuriEngineEvent();
		event.setSysContext(sysContext);
		event.setResultScript(resultScript);
		for (BuriEngineEventListener listener : listeners) {
			listener.startFlow(event);
		}
	}

	public void endFlow(BuriSystemContext sysContext, String resultScript) {
		BuriEngineEvent event = new BuriEngineEvent();
		event.setSysContext(sysContext);
		event.setResultScript(resultScript);
		for (BuriEngineEventListener listener : listeners) {
			listener.endFlow(event);
		}
	}
	
	public void endExecute(BuriSystemContext sysContext, String resultScript, Object ret) {
		BuriEngineEvent event = new BuriEngineEvent();
		event.setSysContext(sysContext);
		event.setResultScript(resultScript);
		event.setResult(ret);
		for (BuriEngineEventListener listener : listeners) {
			listener.endExecute(event);
		}
	}

}
