package org.escafe.buri.event.util.caller;

import org.escafe.buri.compiler.util.BuriDataFieldProcRule;
import org.escafe.buri.event.util.DataAccessRuleEventListener;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;

public interface DataAccessRuleEventCaller {
	void addDataAccessRuleEventListener(DataAccessRuleEventListener listener);
	void entryProcessor(BuriDataFieldType bdft);
	void returnProcessor(BuriDataFieldType bdft);
	void determinedRule(BuriDataFieldProcRule rule,BuriDataFieldType bdft);
	void endNegotiateDao(BuriDataFieldProcRule rule,BuriDataFieldType bdft);
}
