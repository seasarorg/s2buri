package org.escafe.buri.event.engine;

public interface BuriProcessSelectEventListener {

	void startSelectProcess(BuriProcessSelectEvent event);

	void startSelector(BuriProcessSelectEvent event);

	void endSelector(BuriProcessSelectEvent event);

	void endSelectProcess(BuriProcessSelectEvent event);

	void errorSelectProcess(BuriProcessSelectEvent event);

}
