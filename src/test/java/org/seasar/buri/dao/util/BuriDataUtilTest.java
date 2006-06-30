/*
 * 作成日: 2006/05/09
 *
 */
package org.seasar.buri.dao.util;

import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriTestINTDao;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.extension.unit.S2TestCase;

public class BuriDataUtilTest extends S2TestCase {
    private BuriDataUtil dataUtil;
    private BuriPathUtil pathUtil;
    private WakanagoEngine engine;
    private BuriTestINTDao buriTestINTDao;

    public BuriDataUtilTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }
    
    public void testDataUtilTx() {
        BuriTestINTDto dto = new BuriTestINTDto();
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        BuriDataAccessFactory dataAccessFactory = (BuriDataAccessFactory)getComponent("rootDataAccessFactory");
        BuriUserContext userContext = engine.createUserContext(dto,null,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);

        
        long dataId = dataUtil.getBuriDataId(dataAccessFactory,sysContext);
        assertTrue(dataId != 0);
        
        dto.setValue("hoge");
        buriTestINTDao.insert(dto);
        
        dataUtil.updateBuriData(dataAccessFactory,sysContext);
        
        userContext = engine.createUserContext(dto,null,null,null);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        
        long saveId = dataUtil.getBuriDataId(dataAccessFactory,sysContext);
        assertEquals(dataId,saveId);
        
    }
    
    public void testPathUtilTx() {
        BuriPath path = new BuriPath("pkg.prces.act","rpkg.rproces.ract");
        
        BuriPath realPath = pathUtil.getBuriPathFromRealPath(path);
        assertEquals(path.getPlainName(),realPath.getPlainName());
        assertEquals(path.getRealPath().getPlainName(),realPath.getRealPath().getPlainName());
        assertTrue(realPath.getBuriPathID() != 0);
        long fstID = realPath.getBuriPathID();
        
        path = new BuriPath("pkg.prces.act","rpkg.rproces.ract2");
        pathUtil.getBuriPathFromRealPath(path);
        
        path = new BuriPath("pkg.prces2.act","rpkg.rproces2.ract");
        pathUtil.getBuriPathFromRealPath(path);
        
        path = new BuriPath("pkg.prces.act");
        List pathList = pathUtil.getBuriPathFromPathName(path);
        
        Iterator ite = pathList.iterator();
        path = (BuriPath)ite.next();
        assertEquals(path.getPlainName(),"pkg.prces.act");
        assertEquals(path.getRealPath().getPlainName(),"rpkg.rproces.ract");
        assertTrue(realPath.getBuriPathID() != 0);
            
        path = (BuriPath)ite.next();
        assertEquals(path.getPlainName(),"pkg.prces.act");
        assertEquals(path.getRealPath().getPlainName(),"rpkg.rproces.ract2");
        assertTrue(realPath.getBuriPathID() != 0);
        
        path = new BuriPath("pkg.prces.act","rpkg.rproces.ract");
        realPath = pathUtil.getBuriPathFromRealPath(path);
        assertEquals(fstID,realPath.getBuriPathID());
        
    }

}
