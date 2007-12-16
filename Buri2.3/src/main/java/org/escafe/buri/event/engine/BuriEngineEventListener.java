package org.escafe.buri.event.engine;

import org.escafe.buri.event.BuriEventListener;

public interface BuriEngineEventListener extends BuriEventListener {

	void startExecute(BuriEngineEvent event);

	void startFlow(BuriEngineEvent event);

	void endFlow(BuriEngineEvent event);

	void endExecute(BuriEngineEvent event);

}
