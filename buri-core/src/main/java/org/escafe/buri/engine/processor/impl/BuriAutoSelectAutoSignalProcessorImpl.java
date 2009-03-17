package org.escafe.buri.engine.processor.impl;

import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriSignal;
import org.escafe.buri.exception.BuriNotSupportOperationException;

public class BuriAutoSelectAutoSignalProcessorImpl extends BuriAutoSelectProcessorImpl implements BuriAutoSelectProcessor {
	private BuriSignal buriSignal;
	
    public void toNextStatus(String path, Object data, Object userData) {
    	buriSignal.signal(path, data);
    }
    
    public void toNextStatusAction(String path, Object data, Object userData, Object action) {
    	buriSignal.signal(path, data,action.toString());
    }

    public Object toNextStatus(String path, Object data, Object userData, String resultExp) {
    	throw new BuriNotSupportOperationException("EBRI0030");
    }

    public Object toNextStatus(String path, Object data, Object userData, BuriProcessorInfo info) {
    	throw new BuriNotSupportOperationException("EBRI0030");
    }
	

}
