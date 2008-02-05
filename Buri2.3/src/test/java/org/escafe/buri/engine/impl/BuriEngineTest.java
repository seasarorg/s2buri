/*
 * 作成日: 2006/05/22
 *
 */
package org.escafe.buri.engine.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import jp.starlogic.util.datetime.UtilCalendar;

import org.escafe.buri.dao.BuriJoinWaitingDao;
import org.escafe.buri.dao.BuriPathDao;
import org.escafe.buri.dao.BuriStateDao;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dto.BuriPathEntityDto;
import org.escafe.buri.dto.BuriStateEntityDto;
import org.escafe.buri.dto.BuriTestINTDto;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.engine.service.impl.BuriTimerService;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.extension.unit.S2TestCase;

public class BuriEngineTest extends S2TestCase {
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao;
    private BuriDataUtil dataUtil;
    private BuriTimerService timerService;
    private BuriJoinWaitingDao waitingDao;

    public BuriEngineTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("buri/dicon/buriTimer.dicon");
    }

    public void test01Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test01.start",userContext);

        int stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() == 0);
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    }
    
    public void test02Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test02.start",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
        BuriExePackages packages = engine.selectPackage(sysContext);
        BuriExecProcess process =  packages.getProcess(sysContext.getCallPath());

        int stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());
        
        System.out.println("buriEngine.getStatePathListByData");
        
        Collection pathList = dataUtil.getBuriPathByDto(testDto,(DataAccessFactory)process,sysContext);
        assertEquals(pathList.size(),1);
        BuriPath tgtPath = (BuriPath)pathList.iterator().next();
        assertEquals(tgtPath.toString(),"basicTest.test02.stopThis[basicTest.basicTest_Wor2.basicTest_Wor2_Act2]");
        
        System.out.println("buriEngine.getDataListFromPath");
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test02.start",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
        Collection dataList = dataUtil.getDtoListByPathName("basicTest.test02.stopThis",(DataAccessFactory)process,sysContext);
        
        assertEquals(dataList.size(),1);
        BuriTestINTDto reloadDto = (BuriTestINTDto)dataList.iterator().next();
        assertEquals(reloadDto.getValue(),testDto.getValue());

        System.out.println("buriEngine.execute");
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test02.stopThis",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
        stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getAllBuriState().size());

    }
    
    public void test03Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test03.start",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
//        BuriExePackages packages = engine.selectPackage(sysContext);
//        BuriExecProcess process =  packages.getProcess(sysContext.getCallPath());
        
        int stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
    }
    
    
    public void test04Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test04.start",userContext);
        
        int stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test04.continue1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-2,stateDao_.getNoProcessBuriState().size());
        assertEquals(0,stateDao_.getNoProcessBuriState().size());
    
    }
    
    
    public void test05Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test05.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertTrue(testDto.getTestID() != 0);

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.のーど１",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        stateDao_.getAllBuriState();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐終了",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        stateDao_.getAllBuriState();
        assertEquals(0,stateDao_.getNoProcessBuriState().size());
    
    }
    
    
    public void test05_1Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test05.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-3,stateDao_.getNoProcessBuriState().size());
    }
    
    public void test06_01Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test06.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.のーど１",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.のーど2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.分岐1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.分岐終了",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

    }
    
    public void test07Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test07.SAND",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+4,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM3",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM4",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

    }
    
    
    public void test07_2Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test07.SAND",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+4,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07",userContext);
        List<String> actNames = new ArrayList<String>();
        actNames.add("FM2");
        actNames.add("FM3");
        sysContext.setActivityNames(actNames);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM4",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

    }
    
    
    public void test08Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        //最初の実行
        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        //ループに行く
        userContext = engine.createUserContext(testDto,null,"loop",null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //ループから脱出する
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //ループに行く もう一回！！
        userContext = engine.createUserContext(testDto,null,"loop",null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //ループから脱出する もう一回！！
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
        
        
        //次のループへ
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());


        //2番目のループに行く、でも即戻ってくる
        userContext = engine.createUserContext(testDto,null,"ENTERLOOP",null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //2番目のループに行く、でも即戻ってくる　もう一回！！
        userContext = engine.createUserContext(testDto,null,"ENTERLOOP",null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    

        //2番目のループも終わり
        userContext = engine.createUserContext(testDto,null,"EXITLOOP",null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    
    }

    
    public void test09Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,"A",null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test09.Start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"A");
        
        
        testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        userContext = engine.createUserContext(testDto,null,"D",null);
        sysContext = engine.createSystemContext("basicTest.test09.Start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"no hit");
        
    }
    
    
    public void test10Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,"A",null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test10.start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"notNull");
        
        
        testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test10.start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"null");
        
    }
    
    
    public void test11Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test11.start",userContext);

        engine.execute(sysContext,null);
        
        List preState = stateDao_.getNoProcessBuriState();
        Thread.sleep(3000);
        timerService.execute();
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(preState.size(),postState.size());
        System.out.println(postState);
        BuriStateEntityDto stateDto = (BuriStateEntityDto)postState.get(0);
        BuriPathEntityDto dto = pathDao.getBuriPath(stateDto.getPathID().longValue());
        assertEquals(dto.getPathName(),"basicTest.test11.timeOrver");
    }

    
    
    public void test12_1Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test12.start",userContext);

        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.ANDbranch");
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test12.State1");
        paths.add("basicTest.test12.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.State1",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.State2");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.State2",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.end");
        
        
    }
    
    public void test12_2Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test12.start",userContext);

        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.ANDbranch");
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test12.State1");
        paths.add("basicTest.test12.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.State1",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.State2");
        
        
        userContext = engine.createUserContext(testDto,null,"loop",null);
        sysContext = engine.createSystemContext("basicTest.test12.State2",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.ReEntry");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.ReEntry",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.ANDbranch");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        paths.add("basicTest.test12.State1");
        paths.add("basicTest.test12.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.State1",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.State2");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test12.State2",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test12.end");
        
        
    }
    
    public void test13_1Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test13.start",userContext);

        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.ANDbranch");
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        System.out.println(postState);
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test13.State1");
        paths.add("basicTest.test13.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.State1",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.end");
        
        
    }
    
    
    public void test13_2Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test13.start",userContext);

        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.ANDbranch");
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test13.State1");
        paths.add("basicTest.test13.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        userContext = engine.createUserContext(testDto,null,"loop",null);
        sysContext = engine.createSystemContext("basicTest.test13.State2",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.ReEntry");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.ReEntry",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.ANDbranch");
        
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.ANDbranch",userContext);

        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        paths = new HashSet();
        paths.add("basicTest.test13.State1");
        paths.add("basicTest.test13.State2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        userContext = engine.createUserContext(testDto,null,null,null);
        sysContext = engine.createSystemContext("basicTest.test13.State1",userContext);

        engine.execute(sysContext,null);
                
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test13.end");
        
        
    }
    
    public void test14_1Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test14.start",userContext);
        
        BuriExePackages packages = engine.selectPackage(sysContext);
        BuriWorkflowProcessType process = packages.getBuriPackageType().getProcessById("basicTest_wp5");
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance(); 
        calendar.addSecond(-5);
        process.setValidTo(calendar.getTime());

        process = packages.getBuriPackageType().getProcessById("basicTest_wp51");
        process.setValidFrom(calendar.getTime());
        
        Object result = engine.execute(sysContext,"#flow");
        Integer flowID = (Integer)result;
        assertEquals(new Integer(1),flowID);
        
        
    }
    
    public void test14_2Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test14.start",userContext);
        
        BuriExePackages packages = engine.selectPackage(sysContext);
        BuriWorkflowProcessType process = packages.getBuriPackageType().getProcessById("basicTest_wp5");
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance(); 
        calendar.addSecond(5);
        process.setValidTo(calendar.getTime());

        process = packages.getBuriPackageType().getProcessById("basicTest_wp51");
        process.setValidFrom(calendar.getTime());
        
        Object result = engine.execute(sysContext,"#flow");
        Integer flowID = (Integer)result;
        assertEquals(new Integer(1),flowID);
        
        
    }
    
    public void test15_1Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test15.start",userContext);
        
        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.branch");

        
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.branch",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test15.proc1");
        paths.add("basicTest.test15.proc2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        
        userContext = engine.createUserContext(testDto,null,"xor",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc2",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.end");
        
//        List waitings = waitingDao.getNowWaiting();
//        assertEquals(0,waitings.size());
        
        
    }
    
    public void test15_2Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test15.start",userContext);
        
        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.branch");

        
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.branch",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test15.proc1");
        paths.add("basicTest.test15.proc2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc2",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.proc1");
        
//        List waitings = waitingDao.getNowWaiting();
//        assertEquals(1,waitings.size());
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc1",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.end");
        
//        waitingDao.getAllBuriState();
//        waitings = waitingDao.getNowWaiting();
//        assertEquals(0,waitings.size());
    }
    
    public void test15_3Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test15.start",userContext);
        
        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.branch");

        
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.branch",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test15.proc1");
        paths.add("basicTest.test15.proc2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc2",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.proc1");
        
        
        testDto.setValue("retry");
        userContext = engine.createUserContext(testDto,null,"xor",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc1",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.toRetry");
        
    }
    
    public void test15_4Tx() throws InterruptedException {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test15.start",userContext);
        
        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.branch");

        
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.branch",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        HashSet paths = new HashSet();
        paths.add("basicTest.test15.proc1");
        paths.add("basicTest.test15.proc2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc2",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.proc1");
        
        
        testDto.setValue("retry");
        userContext = engine.createUserContext(testDto,null,"xor",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc1",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.toRetry");
        
        
        
        testDto.setValue("testValue");
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.toRetry",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.branch");
        
        
        userContext = engine.createUserContext(testDto,null,"",null);
        sysContext = engine.createSystemContext("basicTest.test15.branch",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(2,postState.size());
        paths = new HashSet();
        paths.add("basicTest.test15.proc1");
        paths.add("basicTest.test15.proc2");
        assertTrue(paths.contains(getPathName(postState.get(0))));
        assertTrue(paths.contains(getPathName(postState.get(1))));
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc1",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.proc2");
        
        
        userContext = engine.createUserContext(testDto,null,"and",null);
        sysContext = engine.createSystemContext("basicTest.test15.proc2",userContext);
        
        engine.execute(sysContext,null);
        
        postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test15.end");
        
        
        
    }


    public void test16Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,"A",null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test16.Start",userContext);
        
        engine.execute(sysContext,null);
        
        List postState = stateDao_.getNoProcessBuriState();
        assertEquals(1,postState.size());
        assertEquals(getPathName(postState.get(0)),"basicTest.test16.end1");

    }

    protected String getPathName(Object postData) {
        System.out.println(postData);
        BuriStateEntityDto stateDto = (BuriStateEntityDto)postData;
        BuriPathEntityDto dto = pathDao.getBuriPath(stateDto.getPathID().longValue());
        return dto.getPathName();
    }
    
}
