/*
 * 作成日: 2006/05/10
 *
 */
package org.seasar.buri.dao.util;

import jp.starlogic.util.datetime.DateUtil;

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

    public BuriStateUtilTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }
    
    public void testBuriStateTx() {
        BuriTestINTDto dto = new BuriTestINTDto();
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        BranchWalker walker = ((WakanagoEngineImpl)engine).createBranchWalker(sysContext);
        walker.setParentPath(sysContext.getCallPath());
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        long statusID = stateUtil.saveStatus(dto,dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        
        BuriPath callPath = walker.getNowPath();
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        walker = stateUtil.getNowPathBranchWalker(dto,dataAccessFactory,sysContext,callPath);
        assertTrue(walker != null);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        
        BuriStateEntityDto stateDto = stateDao.getBuriState(statusID);
        assertEquals(stateDto.getProcessDate().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertEquals(stateDto.getAbortDate().getTime() / 1000 , DateUtil.getSQLMaxDate().getTime() / 1000 );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        
        stateUtil.processed(dto,dataAccessFactory,sysContext,walker);
        
        stateDto = stateDao.getBuriState(statusID);
        assertFalse(stateDto.getProcessDate().getTime() == DateUtil.getSQLMaxDate().getTime() );
        assertEquals(stateDto.getAbortDate().getTime() / 1000, DateUtil.getSQLMaxDate().getTime() / 1000 );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        
        walker = stateUtil.getNowPathBranchWalker(dto,dataAccessFactory,sysContext,callPath);
        assertTrue(walker == null);
        
        
        
        userContext = engine.createUserContext(dto,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        walker = ((WakanagoEngineImpl)engine).createBranchWalker(sysContext);
        walker.setParentPath(sysContext.getCallPath());
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        statusID = stateUtil.saveStatus(dto,dataAccessFactory,sysContext,walker);
        assertTrue(statusID != 0);
        sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
        
        stateUtil.abortStatus(dto,dataAccessFactory,sysContext,walker);
        
        stateDto = stateDao.getBuriState(statusID);
        assertFalse(stateDto.getProcessDate().getTime() == DateUtil.getSQLMaxDate().getTime() );
        assertFalse((stateDto.getAbortDate().getTime() / 1000) == (DateUtil.getSQLMaxDate().getTime() / 1000) );
        assertEquals(stateDto.getAutoRunTime().getTime(), DateUtil.getSQLMaxDate().getTime() );
        assertTrue(stateDto.getDataID().longValue() != 0);
        assertTrue(stateDto.getPathID().longValue() != 0);
        
    }

}
