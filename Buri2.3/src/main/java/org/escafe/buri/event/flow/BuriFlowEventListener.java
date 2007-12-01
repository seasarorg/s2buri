package org.escafe.buri.event.flow;

public interface BuriFlowEventListener {
	void startSelectActivityId(BuriFlowEvent event);
	void endSelectActivityId(BuriFlowEvent event);
	
	void entryActivity(BuriFlowEvent event);
	void restartActivity(BuriFlowEvent event);
	void startConditionCheck(BuriFlowConditionEvent event);
	void endConditionCheck(BuriFlowConditionEvent event);
	void splitAndPreprocess(BuriFlowJoinSplitEvent event);
	void joinAndFlow(BuriFlowJoinSplitEvent event);
	void noProcessAndFlow(BuriFlowJoinSplitEvent event);
	void joinXorFlow(BuriFlowJoinSplitEvent event);
	void callAfterProcess(BuriFlowEvent event);
	void exitFlow(BuriFlowEvent event);
	void startActivity(BuriFlowEvent event);
}
