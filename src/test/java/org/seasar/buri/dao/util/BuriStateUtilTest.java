/*
 * 作成日: 2006/05/10
 *
 */
package org.seasar.buri.dao.util;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.buri.engine.wakanago.WakanagoEngineImpl;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.extension.unit.S2TestCase;

public class BuriStateUtilTest extends S2TestCase {
    private BuriStateUtil stateUtil;
    private WakanagoEngine engine;
    private BuriStateDao stateDao;
    private BuriBranchDao branchDao;

    public BuriStateUtilTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }
    
    public void testBuriStateProceedTx() {
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        stateUtil.saveBranch(walker,dataAccessFactory,sysContext);
        long statusID = stateUtil.saveStatus(dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        assertTrue(walker.getBranchID() != 0);
        
        BuriPath callPath = walker.getNowPath();
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        walker = stateUtil.loadBranchWalker(sysContext);
        assertTrue(walker != null);
        assertTrue(walker.getBranchID() != 0);
        
        BuriStateEntityDto stateDto = stateDao.getBuriState(statusID);
        assertEquals(stateDto.getProcessDate().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertEquals(stateDto.getAbortDate().getTime() / 1000 , DateUtil.getSQLMaxDate().getTime() / 1000 );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        assertTrue(stateDto.getBranchID().longValue() != 0);
        
        stateUtil.saveBranch(walker,dataAccessFactory,sysContext);
        stateUtil.processed(dataAccessFactory,sysContext,walker);
        
        stateDto = stateDao.getBuriState(statusID);
        assertFalse(stateDto.getProcessDate().getTime() == DateUtil.getSQLMaxDate().getTime() );
        assertEquals(stateDto.getAbortDate().getTime() / 1000, DateUtil.getSQLMaxDate().getTime() / 1000 );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        assertTrue(stateDto.getBranchID().longValue() != 0);
        
        walker = stateUtil.loadBranchWalker(sysContext);
        assertTrue(walker != null);
        
    }
    
    public void testBuriStateAbortTx() {
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        walker.setParentPath(sysContext.getCallPath());
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        stateUtil.saveBranch(walker,dataAccessFactory,sysContext);
        long statusID = stateUtil.saveStatus(dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        
        stateUtil.abortStatus(dataAccessFactory,sysContext,walker);
        
        BuriStateEntityDto stateDto = stateDao.getBuriState(statusID);
        assertFalse(stateDto.getProcessDate().getTime() == DateUtil.getSQLMaxDate().getTime() );
        assertFalse((stateDto.getAbortDate().getTime() / 1000) == (DateUtil.getSQLMaxDate().getTime() / 1000) );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        
    }
    
    public void testBuriStateBranchTx() {
        long startSize = branchDao.getAllBuriBranch().size();
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        walker.setParentPath(sysContext.getCallPath());
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        stateUtil.saveBranch(walker,dataAccessFactory,sysContext);
        long statusID = stateUtil.saveStatus(dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+1);
        
        BranchWalker child1 = stateUtil.branchChild(walker,dataAccessFactory,sysContext);
        child1 = child1.moveNext("分岐1","wakanagoTest_wp1_act2");
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+1);
        assertEquals(child1.getParentBranchID(),walker.getBranchID());
        assertEquals(child1.getBranchID(),0);
        stateUtil.saveBranch(child1,dataAccessFactory,sysContext);
        long child1StateID = stateUtil.saveStatus(dataAccessFactory,sysContext,child1);
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+2);
        
        
        BranchWalker child2 = stateUtil.branchChild(walker,dataAccessFactory,sysContext);
        child2 = child2.moveNext("分岐2","wakanagoTest_wp1_act3");
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+2);
        assertEquals(child2.getParentBranchID(),walker.getBranchID());
        assertEquals(child2.getBranchID(),0);
        stateUtil.saveBranch(child2,dataAccessFactory,sysContext);
        long child2StateID = stateUtil.saveStatus(dataAccessFactory,sysContext,child2);
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+3);
        
        long count = stateUtil.countNoProcessedSiblingStatus(dataAccessFactory,sysContext,child2);
        assertEquals(2,count);
        
        sysContext.setStatusID(new Long(child1StateID));// 本当はAbstractで処理する
        stateUtil.processed(dataAccessFactory,sysContext,child1);
        BuriStateEntityDto stateDto = stateDao.getBuriState(child1StateID);
        count = stateUtil.countNoProcessedSiblingStatus(dataAccessFactory,sysContext,child1);
        assertEquals(1,count);
        
        sysContext.setStatusID(new Long(child2StateID));// 本当はAbstractで処理する
        stateUtil.abortStatus(dataAccessFactory,sysContext,child2);
        count = stateUtil.countNoProcessedSiblingStatus(dataAccessFactory,sysContext,child2);
        assertEquals(0,count);
    }
    
    public void testBuriStateBranchAbortTx() {
        long startSize = branchDao.getAllBuriBranch().size();
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
        walker.setParentPath(sysContext.getCallPath());
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        stateUtil.saveBranch(walker,dataAccessFactory,sysContext);
        long statusID = stateUtil.saveStatus(dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+1);
        
        BranchWalker child1 = stateUtil.branchChild(walker,dataAccessFactory,sysContext);
        child1 = child1.moveNext("分岐1","wakanagoTest_wp1_act2");
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+1);
        assertEquals(child1.getParentBranchID(),walker.getBranchID());
        assertEquals(child1.getBranchID(),0);
        stateUtil.saveBranch(child1,dataAccessFactory,sysContext);
        long child1StateID = stateUtil.saveStatus(dataAccessFactory,sysContext,child1);
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+2);
        
        
        BranchWalker child2 = stateUtil.branchChild(walker,dataAccessFactory,sysContext);
        child2 = child2.moveNext("分岐2","wakanagoTest_wp1_act3");
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+2);
        assertEquals(child2.getParentBranchID(),walker.getBranchID());
        assertEquals(child2.getBranchID(),0);
        stateUtil.saveBranch(child2,dataAccessFactory,sysContext);
        long child2StateID = stateUtil.saveStatus(dataAccessFactory,sysContext,child2);
        assertEquals(branchDao.getAllBuriBranch().size(),startSize+3);
        
        long count = stateUtil.countNoProcessedSiblingStatus(dataAccessFactory,sysContext,child2);
        assertEquals(2,count);
        
        stateUtil.abortBranch(dataAccessFactory,sysContext,walker);
        count = stateUtil.countNoProcessedSiblingStatus(dataAccessFactory,sysContext,child2);
        assertEquals(0,count);
    }

}
