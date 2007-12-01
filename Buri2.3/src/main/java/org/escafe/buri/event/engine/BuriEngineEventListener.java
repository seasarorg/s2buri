package org.escafe.buri.event.engine;

public interface BuriEngineEventListener {

	void startExecute(BuriEngineEvent event);

	void startFlow(BuriEngineEvent event);

	void endFlow(BuriEngineEvent event);

	void endExecute(BuriEngineEvent event);

}
