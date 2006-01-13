/*
 * çÏê¨ì˙: 2005/06/02
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.Calendar;
import java.util.List;
import java.util.Map;


import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriManySelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectProcessException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.exception.select.BuriNotSelecteedPackageException;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ApplicationUutil;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.ParticipantUtil;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.buri.xpdl.util.ToolUtil;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.extension.unit.S2TestCase;
import org.wfmc.x2002.xpdl10.ApplicationDocument.Application;
import org.wfmc.x2002.xpdl10.ParticipantDocument.Participant;

/**
 * @author makotan
 *
 */
public class SimpleTagSelectTest extends S2TestCase {
    private String PATH = "test.dicon";
    private WakanagoWorkFlows workflows;
    private WorkFlowsUtil flowsUtil_;
    private ApplicationUutil applicationUutil_;
    private ParticipantUtil participantUtil_;
    private ToolUtil toolUtil_;
    private ExtendedAttributeUtil attributeUtil_;
    /**
     * Constructor for SimpleTagSelectTest.
     * @param arg0
     */
    public SimpleTagSelectTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        workflows = (WakanagoWorkFlows)getComponent(WakanagoWorkFlows.class);
        workflows.readWorkFlowFromResource("test","org/seasar/buri/xpdl/util/wakanagoTest.xpdl");
    }
    
    public void testNomal() {
        new BuriParticipant();
        BuriPath path = new BuriPath("test.Nomal.start");
        BuriPath testPath = new BuriPath("test.Nomal.start");
        WorkFlowPackageTagSelect packageTagSelect = flowsUtil_.getWorkFlowPackage(path);
        testPath.setWorkflowPackage(packageTagSelect);
        WorkflowProcessTagSelect workflowTagSelect = flowsUtil_.getWorkflowProcess(path);
        testPath.setWorkflowProcess(workflowTagSelect);

        long start = Calendar.getInstance().getTimeInMillis();
        ActivityTagSelect activityTagSelect = flowsUtil_.getActivity(path);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("ActivityTagSelectFromBuriPath 1="+(end-start)+"ms");
        assertNotNull(activityTagSelect);

        testPath.changePath(activityTagSelect);
        assertEquals("test.Nomal.start[wakanagoTest.wakanagoTest_Wor4.wakanagoTest_Wor4_Act1]",testPath.toString());
        BuriPath test2Path = (BuriPath)testPath.clone();
        assertEquals("test.Nomal.start[wakanagoTest.wakanagoTest_Wor4.wakanagoTest_Wor4_Act1]",test2Path.toString());

        start = Calendar.getInstance().getTimeInMillis();
        activityTagSelect = flowsUtil_.getActivity(path);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("ActivityTagSelectFromBuriPath 2="+(end-start)+"ms");
        test2Path.moveChildPath(activityTagSelect);
        assertEquals("test.Nomal.start.start[wakanagoTest.wakanagoTest_Wor4.wakanagoTest_Wor4_Act1.wakanagoTest_Wor4_Act1]",test2Path.toString());
        test2Path.moveUpPath();
        assertEquals("test.Nomal.start[wakanagoTest.wakanagoTest_Wor4.wakanagoTest_Wor4_Act1]",test2Path.toString());
        
        List transitions = activityTagSelect.getXorSplitTransitions();
        assertEquals(transitions.size(),2);

        packageTagSelect = workflows.getPackageTagSelect(path);
        workflowTagSelect = flowsUtil_.getWorkflowProcess(path);
        start = Calendar.getInstance().getTimeInMillis();
        activityTagSelect = flowsUtil_.getActivity(path);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("ActivityTagSelectFromBuriPath 3="+(end-start)+"ms");
        
        transitions = activityTagSelect.getXorSplitTransitions();
        assertEquals(transitions.size(),2);
        
        Application[] appArray = applicationUutil_.getApplicationArray(path,null);
        assertEquals(appArray.length,2);
        applicationUutil_.getApplication(workflowTagSelect.getBuriPath(),"OgnlInvoker");
        applicationUutil_.getApplication(workflowTagSelect.getBuriPath(),"appNameLocal");
        Application app = applicationUutil_.getApplication(workflowTagSelect.getBuriPath(),"appNotName");
        assertNull(app);
        
        Participant[] participantList = participantUtil_.getParticipantArray(path,null);
        assertEquals(participantList.length,2);
        participantUtil_.getParticipant(path,"Role1");
        participantUtil_.getParticipant(path,"LocalRole");
        Participant partiy = participantUtil_.getParticipant(path,"appNotName");
        assertNull(partiy);
        
        List toolList = toolUtil_.getTools(activityTagSelect);
        assertEquals(toolList.size(),1);
        
        ToolTagSelect ognlInvoker = (ToolTagSelect)toolList.get(0);
        Map attri = attributeUtil_.get(ognlInvoker.getTool());
        System.out.println(attri);
        
        workflows.readWorkFlowFromResource("test","org/seasar/buri/xpdl/util/wakanagoTest.xpdl");
    }

    public void testNotSelecteedPackage() {
        BuriPath path = new BuriPath("testNotName.Nomal.start");
        try{
            workflows.getPackageTagSelect(path);
            fail();
        } catch(BuriNotSelecteedPackageException ex) {
            assertTrue(true);
        }

    }
    
    public void testMultiSelectProcess() {
        BuriPath path = new BuriPath("test.Multi.Generic");
        try{
            flowsUtil_.getWorkflowProcess(path);
            fail();
        } catch(BuriManySelectProcessException ex) {
            assertTrue(true);
        }
    }
    
    public void testMultiSelectActivity() {
        BuriPath path = new BuriPath("test.MultiActivity.Generic");
        try{
            flowsUtil_.getActivity(path);
            fail();
        } catch(BuriManySelectActivityException ex) {
            assertTrue(true);
        }
    }
    
    public void testNotSelectProcess() {
        BuriPath path = new BuriPath("test.Not.Generic");
        try{
            flowsUtil_.getWorkflowProcess(path);
            fail();
        } catch(BuriNotSelectProcessException ex) {
            assertTrue(true);
        }
    }
    
    public void testNotSelectActivity() {
        BuriPath path = new BuriPath("test.MultiActivity.NotGeneric");
        try{
            flowsUtil_.getActivity(path);
            fail();
        } catch(BuriNotSelectedActivityException ex) {
            assertTrue(true);
        }
    }
    
    
}
