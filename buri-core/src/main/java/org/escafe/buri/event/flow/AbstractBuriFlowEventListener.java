package org.escafe.buri.event.flow;

public class AbstractBuriFlowEventListener implements BuriFlowEventListener {

	public void callAfterProcess(BuriFlowEvent event) {
	}

	public void endConditionCheck(BuriFlowConditionEvent event) {
	}

	public void endSelectActivityId(BuriFlowEvent event) {
	}

	public void entryActivity(BuriFlowEvent event) {
	}

	public void exitFlow(BuriFlowEvent event) {
	}

	public void joinAndFlow(BuriFlowJoinSplitEvent event) {
	}

	public void joinXorFlow(BuriFlowJoinSplitEvent event) {
	}

	public void noProcessAndFlow(BuriFlowJoinSplitEvent event) {
	}

	public void restartActivity(BuriFlowEvent event) {
	}

	public void splitAndPreprocess(BuriFlowJoinSplitEvent event) {
	}

	public void startActivity(BuriFlowEvent event) {
	}

	public void startConditionCheck(BuriFlowConditionEvent event) {
	}

	public void startSelectActivityId(BuriFlowEvent event) {
	}

}
