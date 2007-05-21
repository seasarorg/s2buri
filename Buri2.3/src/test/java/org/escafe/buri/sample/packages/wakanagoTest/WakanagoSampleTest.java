/*
 * 作成日: 2006/03/31
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.dao.BuriBranchDao;
import org.escafe.buri.dao.BuriDataDao;
import org.escafe.buri.dao.BuriStateDao;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dto.BuriTestINTDto;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.extension.unit.S2TestCase;

public class WakanagoSampleTest extends S2TestCase {
    private BuriStateDao stateDao;
    private BuriDataDao dataDao;
    private BuriBranchDao branchDao;
    private BuriDataUtil dataUtil;
    
    public WakanagoSampleTest(String arg0) {
        super(arg0);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }
    
    public void testWakanagoTestTx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        
        long startStateCount = stateDao.getAllBuriState().size();
        long startDataCount = dataDao.getAllBuriData().size();
        long startBranchCount = branchDao.getAllBuriBranch().size();

        long start = System.currentTimeMillis();
        BuriUserContext userContext = engine.createUserContext(dto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        engine.execute(sysContext,null);
        
        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+1 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        BuriTestINTDto data = (BuriTestINTDto)sysContext.getUserContext().get("data");
        assertEquals(data.getValue(),"2");
        System.out.println(sysContext.getUserContext());
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.Stop1",userContext);
        engine.execute(sysContext,null);

        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+2 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.Stop2",userContext);
        engine.execute(sysContext,null);
        
        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+3 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        long end = System.currentTimeMillis();
        System.out.println("ProcessTime = " + (end-start) + "ms");
    }
    
    public void testDataTestTx() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("hoge");
        
        BuriUserContext userContext = engine.createUserContext(dto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
        
        BuriExePackages packages = engine.selectPackage(sysContext);
        BuriExecProcess process =  packages.getProcess(sysContext.getCallPath());
        List dataList = dataUtil.getDtoListByPathName("wakanagoTest.ワカナゴテスト.Stop1",(DataAccessFactory)process,sysContext);
        long startCount = dataList.size();
        
        engine.execute(sysContext,null);
        
        dataList = dataUtil.getDtoListByPathName("wakanagoTest.ワカナゴテスト.Stop1",(DataAccessFactory)process,sysContext);
        assertEquals( startCount+1,dataList.size());
        
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.Stop1",userContext);
        sysContext.setTargetDtoClass(BuriTestINTDto.class);
        engine.execute(sysContext,null);
        
        dataList = dataUtil.getDtoListByPathName("wakanagoTest.ワカナゴテスト.Stop1",(DataAccessFactory)process,sysContext);
        assertEquals( startCount,dataList.size());
        
        dataList = dataUtil.getDtoListByPathName("wakanagoTest.ワカナゴテスト.Stop2",(DataAccessFactory)process,sysContext);
        assertEquals( startCount+1,dataList.size());
        
    }

}
