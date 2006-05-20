/*
 * �쐬��: 2006/03/31
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.dao.BuriBranchDao;
import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.extension.unit.S2TestCase;

public class WakanagoSampleTest extends S2TestCase {
    private BuriStateDao stateDao;
    private BuriDataDao dataDao;
    private BuriBranchDao branchDao;
    
    public WakanagoSampleTest(String arg0) {
        super(arg0);
    }

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
        BuriUserContext userContext = engine.createUserContext(dto,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.���J�i�S�e�X�g.�J�n",userContext);
        engine.execute(sysContext);
        
        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+1 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        BuriTestINTDto data = (BuriTestINTDto)sysContext.getUserContext().get("data");
        assertEquals(data.getValue(),"2");
        System.out.println(sysContext.getUserContext());
        sysContext = engine.createSystemContext("wakanagoTest.���J�i�S�e�X�g.Stop1",userContext);
        engine.execute(sysContext);

        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+2 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        sysContext = engine.createSystemContext("wakanagoTest.���J�i�S�e�X�g.Stop2",userContext);
        engine.execute(sysContext);
        
        assertEquals(startStateCount+1 , stateDao.getNoProcessBuriState().size());
        assertEquals(startStateCount+3 , stateDao.getAllBuriState().size());
        assertEquals(startDataCount+1 , dataDao.getAllBuriData().size());
        assertEquals(startBranchCount+1 , branchDao.getAllBuriBranch().size());
        
        long end = System.currentTimeMillis();
        System.out.println("ProcessTime = " + (end-start) + "ms");
    }

}
