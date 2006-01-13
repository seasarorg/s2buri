/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.engine;

import org.seasar.framework.container.S2Container;

/**
 * @author makotan
 *
 */
public interface WakanagoEngine {
    Object execute(String buriPath,Object root,String context);
    void execute(String buriPath,Object root);
    Object execute(S2Container container,String buriPath,Object root,String context);
    void execute(S2Container container,String buriPath,Object root);
    
    WakanagoWorkFlows getWorkflows();
}
