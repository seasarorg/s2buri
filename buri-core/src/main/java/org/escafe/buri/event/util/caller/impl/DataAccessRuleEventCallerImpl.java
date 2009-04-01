package org.escafe.buri.event.util.caller.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.compiler.util.BuriDataFieldProcRule;
import org.escafe.buri.event.util.DataAccessRuleEvent;
import org.escafe.buri.event.util.DataAccessRuleEventListener;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;

public class DataAccessRuleEventCallerImpl implements DataAccessRuleEventCaller {
	private List<DataAccessRuleEventListener> listeners = new ArrayList<DataAccessRuleEventListener>();
	
	public void addDataAccessRuleEventListener(DataAccessRuleEventListener listener) {
		listeners.add(listener);
	}
	
	public void entryProcessor(BuriDataFieldType bdft) {
		DataAccessRuleEvent event = new DataAccessRuleEvent();
		event.setBuriDataFieldType(bdft);
		for(DataAccessRuleEventListener listener : listeners) {
			listener.entryProcessor(event);
		}
	}
	
	public void returnProcessor(BuriDataFieldType bdft) {
		DataAccessRuleEvent event = new DataAccessRuleEvent();
		event.setBuriDataFieldType(bdft);
		for(DataAccessRuleEventListener listener : listeners) {
			listener.returnProcessor(event);
		}
	}
	
	public void determinedRule(BuriDataFieldProcRule rule,BuriDataFieldType bdft) {
		DataAccessRuleEvent event = new DataAccessRuleEvent();
		event.setRule(rule);
		event.setBuriDataFieldType(bdft);
		for(DataAccessRuleEventListener listener : listeners) {
			listener.determinedRule(event);
		}
	}
	
	public void endNegotiateDao(BuriDataFieldProcRule rule,BuriDataFieldType bdft) {
		DataAccessRuleEvent event = new DataAccessRuleEvent();
		event.setRule(rule);
		event.setBuriDataFieldType(bdft);
		for(DataAccessRuleEventListener listener : listeners) {
			listener.endNegotiateDao(event);
		}
	}
}
