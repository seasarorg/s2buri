/*
 * çÏê¨ì˙: 2005/06/27
 *
 */
package org.seasar.buri.engine;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.engine.util.BuriTraceElement;
import org.seasar.framework.container.S2Container;


/**
 * @author makotan
 *
 */
public interface BuriEngine {
    void execute(BuriPath path);
    
    WakanagoWorkFlows getWorkflows();
 
    BuriTraceElement createBuriTraceElement(BuriPath path);
    void traceBuriPath(BuriPath path);
    
    BuriLocalContext createContext(Object data);
    BuriLocalContext createContext(Object data,S2Container container);
}
