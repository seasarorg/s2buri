package org.escafe.buri.event.state;

import org.escafe.buri.event.BuriEventListener;

public interface BuriStatusEventListener extends BuriEventListener {
	void saveState(BuriStatusEvent event);
	void processed(BuriStatusEvent event);
	void abortState(BuriStatusEvent event);
	void abortBranch(BuriStatusEvent event);
}
