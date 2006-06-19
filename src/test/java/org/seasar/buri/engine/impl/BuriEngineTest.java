/*
 * 作成日: 2006/05/22
 *
 */
package org.seasar.buri.engine.impl;

import java.util.Collection;

import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.extension.unit.S2TestCase;

public class BuriEngineTest extends S2TestCase {
    private BuriStateDao stateDao_;
    private BuriDataUtil dataUtil;

    public BuriEngineTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }

    public void test01Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test02.start",userContext);
        sysContext.setTgtClass(BuriTestINTDto.class);
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
        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test02.start",userContext);
        sysContext.setTgtClass(BuriTestINTDto.class);
        Collection dataList = dataUtil.getDtoListByPathName("basicTest.test02.stopThis",(DataAccessFactory)process,sysContext);
        
        assertEquals(dataList.size(),1);
        BuriTestINTDto reloadDto = (BuriTestINTDto)dataList.iterator().next();
        assertEquals(reloadDto.getValue(),testDto.getValue());

        System.out.println("buriEngine.execute");
        
        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test02.stopThis",userContext);
        sysContext.setTgtClass(BuriTestINTDto.class);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test03.start",userContext);
        sysContext.setTgtClass(BuriTestINTDto.class);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test04.start",userContext);
        
        int stateSize = stateDao_.getAllBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test05.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertTrue(testDto.getTestID() != 0);

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.のーど１",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        stateDao_.getAllBuriState();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐終了",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        stateDao_.getAllBuriState();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    
    }
    
    
    public void test05_1Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test05.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test05.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test06.開始",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.分岐2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.のーど１",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.のーど2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test06.分岐1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    

        userContext = engine.createUserContext(testDto,null,null);
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test07.SAND",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+4,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM1",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM2",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test07.FM3",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());

        userContext = engine.createUserContext(testDto,null,null);
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
        BuriUserContext userContext = engine.createUserContext(testDto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        int stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        //ループに行く
        userContext = engine.createUserContext(testDto,null,"loop");
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //ループから脱出する
        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //ループに行く もう一回！！
        userContext = engine.createUserContext(testDto,null,"loop");
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //ループから脱出する もう一回！！
        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
        
        
        //次のループへ
        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());


        //2番目のループに行く、でも即戻ってくる
        userContext = engine.createUserContext(testDto,null,"ENTERLOOP");
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //2番目のループに行く、でも即戻ってくる　もう一回！！
        userContext = engine.createUserContext(testDto,null,"ENTERLOOP");
        sysContext = engine.createSystemContext("basicTest.test08",userContext);
        
        stateSize = stateDao_.getNoProcessBuriState().size();
        engine.execute(sysContext,null);
        
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    

        //2番目のループも終わり
        userContext = engine.createUserContext(testDto,null,"EXITLOOP");
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

        BuriUserContext userContext = engine.createUserContext(testDto,null,"A");
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test09.Start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"A");
        
        
        testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        userContext = engine.createUserContext(testDto,null,"D");
        sysContext = engine.createSystemContext("basicTest.test09.Start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"no hit");
        
    }
    
    
    public void test10Tx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        engine.readWorkFlowFromResource("wakanagoxpdl/basicTest.xpdl","basicTest");

        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriUserContext userContext = engine.createUserContext(testDto,null,"A");
        BuriSystemContext sysContext = engine.createSystemContext("basicTest.test10.start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"notNull");
        
        
        testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        userContext = engine.createUserContext(testDto,null,null);
        sysContext = engine.createSystemContext("basicTest.test10.start",userContext);

        engine.execute(sysContext,null);
        
        assertEquals(userContext.get("result"),"null");
        
    }
}
