package org.escafe.buri.event.boot;

import org.escafe.buri.event.BuriEventListener;

public interface BuriComplieEventListener extends BuriEventListener {
	void startReadXpdl(BuriCompileEvent event);
	void endReadXpdl(BuriCompileEvent event);
	void startCompile(BuriCompileEvent event);
	void endCompile(BuriCompileEvent event);
	void startObjectInit(BuriCompileEvent event);
	void endObjectInit(BuriCompileEvent event);
	
}
