/*
 * çÏê¨ì˙: 2005/06/13
 *
 */
package org.seasar.buri.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author makotan
 *
 */
public class WakanagoEngineTest extends S2TestCase {
    private String PATH = "test.dicon";
    private WakanagoEngine buriEngine;
    
    
    /**
     * Constructor for WakanagoEngineTest.
     * @param arg0
     */
    public WakanagoEngineTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        buriEngine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        buriEngine.getWorkflows().readWorkFlowFromResource("test","org/seasar/buri/impl/wakanagoPickoutTest.xpdl");
    }
    
    public void testNomal() {
        Map rootObj = new HashMap();
        long start = Calendar.getInstance().getTimeInMillis();
        Integer result = (Integer)buriEngine.execute("test.wakanago.nomal",rootObj,"#result");
        assertEquals(1,result.intValue());
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("WakanagoEngine 1="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
//        for(int i=0; i< 1000; i++){
        result = (Integer)buriEngine.execute("test.wakanago.nomal",rootObj,"#result");
        assertEquals(1,result.intValue());
//        }
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("WakanagoEngine 2="+(end-start)+"ms");
    
    }

}
