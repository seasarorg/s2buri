/*
 * çÏê¨ì˙: 2005/06/08
 *
 */
package org.seasar.buri.engine;

import java.util.Map;
import java.util.Stack;

import org.seasar.buri.engine.util.BuriTraceElement;
import org.seasar.buri.xpdl.util.ActivityTagSelect;



/**
 * @author makotan
 *
 */
public interface FlowPickout {
    void addProcessedActivity(BuriPath path,ActivityTagSelect tagSelect,Map context);
    BuriPath getNextActivity();
    Stack setupWaitStack(BuriPath basePath,Stack waitStack,BuriTraceElement traceEle,Map context);
    
}
