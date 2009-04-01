package org.escafe.buri.event.engine;

import org.escafe.buri.event.BuriEventListener;

public interface BuriProcessSelectEventListener extends BuriEventListener {

	void startSelectProcess(BuriProcessSelectEvent event);

	void startSelector(BuriProcessSelectEvent event);

	void endSelector(BuriProcessSelectEvent event);

	void endSelectProcess(BuriProcessSelectEvent event);

	void errorSelectProcess(BuriProcessSelectEvent event);

}
