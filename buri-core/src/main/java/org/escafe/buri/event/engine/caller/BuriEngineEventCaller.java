package org.escafe.buri.event.engine.caller;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.engine.BuriEngineEventListener;

public interface BuriEngineEventCaller {

	void addEventListener(BuriEngineEventListener listener);
	
	void startExecute(BuriSystemContext sysContext, String resultScript);

	void startFlow(BuriSystemContext sysContext, String resultScript);

	void endFlow(BuriSystemContext sysContext, String resultScript);

	void endExecute(BuriSystemContext sysContext, String resultScript, Object ret);

}
