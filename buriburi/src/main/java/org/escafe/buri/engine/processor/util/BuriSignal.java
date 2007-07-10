package org.escafe.buri.engine.processor.util;

import java.util.List;

/**
 * 
 * @author a-conv
 * 
 */
public interface BuriSignal {

	public void signal(String callPath, Object data);

	public void signal(String callPath, Object data, String action);

	public void signal(String callPath, List datas);

	public void signal(String callPath, List datas, String action);

}
