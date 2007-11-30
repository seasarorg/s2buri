package org.escafe.buri.mail.util;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.mail.BuriMailSendProcessor;
import org.seasar.framework.container.S2Container;

public class BuriMailUtil {
	private BuriMailUtil(){
		
	}
	
	public static BuriMailSendProcessor getSendProcessor(BuriSystemContext sysContext){
		S2Container container = sysContext.getContainer();
		return (BuriMailSendProcessor) container.getComponent(BuriMailSendProcessor.class);
	}

}
