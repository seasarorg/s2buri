package org.escafe.buri.event.engine.caller;

import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.selector.BuriProcessSelector;
import org.escafe.buri.event.engine.BuriProcessSelectEventListener;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface BuriProcessSelectEventCaller {

	void addEventListener(BuriProcessSelectEventListener listener);
	
	void startSelectProcess(BuriExePackages packageObj,	BuriSystemContext sysContext);

	void startSelector(BuriExePackages packageObj,BuriSystemContext sysContext, BuriProcessSelector selector,List pros);

	void endSelector(BuriExePackages packageObj, BuriSystemContext sysContext,BuriProcessSelector selector, List pros);

	void endSelectProcess(BuriExePackages packageObj,BuriSystemContext sysContext, BuriExecProcess process, List pros);

	void errorSelectProcess(BuriExePackages packageObj,BuriSystemContext systemContext, BuriPath callPath, List proces);

}
