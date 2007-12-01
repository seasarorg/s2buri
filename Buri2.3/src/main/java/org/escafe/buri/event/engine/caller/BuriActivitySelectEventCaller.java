package org.escafe.buri.event.engine.caller;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriActivitySelector;
import org.escafe.buri.event.engine.BuriActivitySelectEventListener;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface BuriActivitySelectEventCaller {

	void addEventListener(BuriActivitySelectEventListener listener);
	
	void startSelectActivityId(BuriExecProcess wp, BuriSystemContext sysContext);

	void startActivitySelector(BuriExecProcess wp, BuriSystemContext sysContext, BuriActivitySelector selector,Set<BuriActivityType> acts);

	void endActivitySelector(BuriExecProcess wp, BuriSystemContext sysContext, BuriActivitySelector selector,Set<BuriActivityType> acts);

	void endSelectActivityId(BuriExecProcess wp, BuriSystemContext sysContext, String actId,Set<BuriActivityType> acts);

	void errorActivitySelect(Set<BuriActivityType> acts, BuriSystemContext systemContext, BuriExecProcess wp);

}
