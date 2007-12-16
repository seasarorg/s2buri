package org.escafe.buri.event.util;

import org.escafe.buri.event.BuriEventListener;



public interface DataAccessRuleEventListener extends BuriEventListener {
	void entryProcessor(DataAccessRuleEvent event);
	void returnProcessor(DataAccessRuleEvent event);
	void determinedRule(DataAccessRuleEvent event);
	void endNegotiateDao(DataAccessRuleEvent event);
}
