package org.escafe.buri.event.state;

public interface BuriStatusEventListener {
	void saveState(BuriStatusEvent event);
	void processed(BuriStatusEvent event);
	void abortState(BuriStatusEvent event);
	void abortBranch(BuriStatusEvent event);
}
