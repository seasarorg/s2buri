package org.escafe.buri.event.engine;

import org.escafe.buri.event.BuriEventListener;

public interface BuriActivitySelectEventListener extends BuriEventListener {

	void startSelectActivityId(BuriActivitySelectEvent event);

	void startActivitySelector(BuriActivitySelectEvent event);

	void endActivitySelector(BuriActivitySelectEvent event);

	void endSelectActivityId(BuriActivitySelectEvent event);

	void errorActivitySelect(BuriActivitySelectEvent event);

}
