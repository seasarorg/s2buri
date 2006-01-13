/*
 * çÏê¨ì˙: 2005/06/12
 *
 */
package org.seasar.buri.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.FlowPickout;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.exception.BuriNotSupportOperationException;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author makotan
 *
 */
public class WakanagoFlowPickoutTest extends S2TestCase {
    private String PATH = "test.dicon";
    private WakanagoWorkFlows workflows;
    private WorkFlowsUtil flowsUtil_;
    private BuriLocalContext context_;

    /**
     * Constructor for WakanagoFlowPickoutTest.
     * @param arg0
     */
    public WakanagoFlowPickoutTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        workflows = (WakanagoWorkFlows)getComponent(WakanagoWorkFlows.class);
        workflows.readWorkFlowFromResource("test","org/seasar/buri/impl/wakanagoPickoutTest.xpdl");
    }
    
    public void testSimple() {
        BuriPath path = new BuriPath("test.simple.Start");
        FlowPickout flowPickout = (FlowPickout)getComponent(FlowPickout.class);
        context_.getAction();
        Map context = new HashMap();

        long start = Calendar.getInstance().getTimeInMillis();

        ActivityTagSelect activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step1[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act2]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step2[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act3]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step3[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act4]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);
        
        path = flowPickout.getNextActivity();
        assertNull(path);

        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("flowPickout 1="+(end-start)+"ms");

        path = new BuriPath("test.simple.Start");
        start = Calendar.getInstance().getTimeInMillis();
        
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step1[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act2]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step2[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act3]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.simple.Step3[test.wakanagoPickoutTest_Wor1.wakanagoPickoutTest_Wor1_Act4]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);
        
        path = flowPickout.getNextActivity();
        assertNull(path);

        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("flowPickout 2="+(end-start)+"ms");
    }
    
    public void testBranch() {
        BuriPath path = new BuriPath("test.branch.start");
        FlowPickout flowPickout = (FlowPickout)getComponent(FlowPickout.class);
        ActivityTagSelect activityTagSelect = flowsUtil_.getActivity(path);
        context_.put("condition",new Integer(1));
        Map context = new HashMap();
        context.put("condition",new Integer(1));

        flowPickout.addProcessedActivity(path,activityTagSelect,context);
        path = flowPickout.getNextActivity();
        assertEquals("test.branch.B1[test.wakanagoPickoutTest_Wor2.wakanagoPickoutTest_Wor2_Act2]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);
        flowPickout.addProcessedActivity(path,activityTagSelect,context);

        path = flowPickout.getNextActivity();
        assertEquals("test.branch.end[test.wakanagoPickoutTest_Wor2.wakanagoPickoutTest_Wor2_Act4]",path.toString());
        activityTagSelect = flowsUtil_.getActivity(path);

        path = new BuriPath("test.branch.start");
        context.put("condition",new Integer(0));
        activityTagSelect = flowsUtil_.getActivity(path);
        try {
            flowPickout.addProcessedActivity(path,activityTagSelect,context);
            fail();
        }catch(BuriNotSupportOperationException ex) {
            assertTrue(true);
        }
    }

    
    
}
