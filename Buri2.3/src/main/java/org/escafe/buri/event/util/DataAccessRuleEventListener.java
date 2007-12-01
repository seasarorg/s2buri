package org.escafe.buri.event.util;



public interface DataAccessRuleEventListener {
	void entryProcessor(DataAccessRuleEvent event);
	void returnProcessor(DataAccessRuleEvent event);
	void determinedRule(DataAccessRuleEvent event);
	void endNegotiateDao(DataAccessRuleEvent event);
}
