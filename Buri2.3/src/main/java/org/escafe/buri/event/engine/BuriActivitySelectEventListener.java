package org.escafe.buri.event.engine;

public interface BuriActivitySelectEventListener {

	void startSelectActivityId(BuriActivitySelectEvent event);

	void startActivitySelector(BuriActivitySelectEvent event);

	void endActivitySelector(BuriActivitySelectEvent event);

	void endSelectActivityId(BuriActivitySelectEvent event);

	void errorActivitySelect(BuriActivitySelectEvent event);

}
