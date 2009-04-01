package org.escafe.buri.mail.util;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

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
	
	public static InternetAddress createInternetAddress(String address, String personal){
		try {
			return new InternetAddress(address, personal);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
