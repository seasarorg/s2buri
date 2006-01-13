/*
 * çÏê¨ì˙: 2005/07/02
 *
 */
package org.seasar.buri.dao.util;


import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.datautil.BuriDataDaoUtil;
import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dao.datautil.DataIDListUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.dto.BuriTestCHARDto;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.dto.BuriTestManyDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriPath;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;


public class BuriDataDaoUtilTest extends S2TestCase {
    private String PATH = "buri2.dicon";
    private String DAOPATH = "../../dicon/allTestDao.dicon";
    private BuriDataDaoUtil buriDataDaoUtil;
    private S2Container container;
    private BuriEngine buriEngine;
    private BuriDataDao dataDao_;
    private DataIDListUtil listUtil_;
    private DataAccessUtil accessUtil_;

    public BuriDataDaoUtilTest(String arg0) {
        super(arg0);
    }
    

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        include(DAOPATH);
        buriDataDaoUtil = (BuriDataDaoUtil)getComponent(BuriDataDaoUtil.class);
        container = getContainer();

        buriEngine = (BuriEngine)getComponent(BuriEngine.class);
        buriEngine.getWorkflows().readWorkFlowFromResource("test","org/seasar/buri/dao/util/BuriData.xpdl");
    }
    
    public void testEasyAccessTx() {
        BuriPath path = new BuriPath("test.BuriDataInt.Generic");
        BuriTestINTDto testINTDto = new BuriTestINTDto();
        BuriEngine engine = (BuriEngine)getComponent(BuriEngine.class);
        BuriLocalContext context = engine.createContext(testINTDto);
        context.setCallBuriPath(path);

        int dataCount = dataDao_.getAllBuriData().size();
        
        testINTDto.setValue("123");
        long buriID = buriDataDaoUtil.createOnlyBuriDataID(path);
        dataDao_.getAllBuriData();
        assertTrue(buriID!=0);
//        sequenceÇÃÇ†ÇÈDBÇÃèÍçá
//        assertEquals(dataCount,dataDao_.getAllBuriData().size());
        assertEquals(dataCount+1,dataDao_.getAllBuriData().size());
        
        buriDataDaoUtil.storeBuriData(path);
        
        assertEquals(dataCount+1,dataDao_.getAllBuriData().size());
        
        
    }
    
    public void testIntAccessTx() {
        BuriPath path = new BuriPath("test.BuriDataInt.Generic");
        BuriTestINTDto testINTDto = new BuriTestINTDto();
        BuriEngine engine = (BuriEngine)getComponent(BuriEngine.class);

        BuriLocalContext context = engine.createContext(testINTDto);
        context.setCallBuriPath(path);
        
        BuriDataEntityDto buriDataEntityDto;
        testINTDto.setValue("123");
        context.setData(testINTDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertTrue(testINTDto.getTestID()!=0);
        assertTrue(buriDataEntityDto.getDataID() !=0);
        assertEquals(buriDataEntityDto.getPkeyNum().longValue() , testINTDto.getTestID());
        
        testINTDto.setValue("456");
        context.setData(testINTDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        BuriDataEntityDto buriDataEntityDtoReload= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertEquals(buriDataEntityDto.getDataID() , buriDataEntityDtoReload.getDataID());
        assertEquals(buriDataEntityDto.getPkeyNum() , buriDataEntityDtoReload.getPkeyNum());
        
        BuriTestINTDto test2INTDto = new BuriTestINTDto();
        test2INTDto.setValue("ABC");
        context.setData(test2INTDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertEquals(buriDataEntityDto.getPkeyNum().longValue() , test2INTDto.getTestID());
        assertTrue(buriDataEntityDto.getDataID() != buriDataEntityDtoReload.getDataID());
        
        Object readData = accessUtil_.getObjectFromDataID(path,buriDataEntityDto.getDataID());
        
        BuriTestINTDto readINTDto = (BuriTestINTDto)readData;
        assertEquals(readINTDto.getTestID(),test2INTDto.getTestID());
        assertEquals(readINTDto.getValue(),test2INTDto.getValue());
        assertEquals(readINTDto.getVersionNo(),test2INTDto.getVersionNo());
        
    }
    
    public void testCHARAccessTx() {
        BuriPath path = new BuriPath("test.BuriDataChar.Generic");
        BuriTestCHARDto testCHARDto = new BuriTestCHARDto();
        BuriEngine engine = (BuriEngine)getComponent(BuriEngine.class);

        BuriLocalContext context = (BuriLocalContext)getComponent(BuriLocalContext.class);
        context.setCallBuriPath(path);
        context.setContainer(getContainer());
        
        BuriDataEntityDto buriDataEntityDto;
        testCHARDto.setTestID("AAAA");
        testCHARDto.setValue("123");
        context.setData(testCHARDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertTrue(buriDataEntityDto.getDataID() !=0);
        
        testCHARDto.setTestID("AAAA");
        testCHARDto.setValue("456");
        context.setData(testCHARDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        BuriDataEntityDto buriDataEntityDtoReload= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertEquals(buriDataEntityDto.getDataID() , buriDataEntityDtoReload.getDataID());
        assertEquals(buriDataEntityDto.getPkeyNum() , buriDataEntityDtoReload.getPkeyNum());
        
        BuriTestCHARDto test2CHARDto = new BuriTestCHARDto();
        test2CHARDto.setTestID("BBBB");
        test2CHARDto.setValue("ABC");
        context.setData(test2CHARDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);
        
        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertTrue(buriDataEntityDto.getDataID() != buriDataEntityDtoReload.getDataID());
        
        Object readData = accessUtil_.getObjectFromDataID(path,buriDataEntityDto.getDataID());
        
        BuriTestCHARDto readCHARDto = (BuriTestCHARDto)readData;
        assertEquals(readCHARDto.getTestID(),test2CHARDto.getTestID());
        assertEquals(readCHARDto.getValue(),test2CHARDto.getValue());
        assertEquals(readCHARDto.getVersionNo(),test2CHARDto.getVersionNo());
    }
    
    public void testManyAccessTx() {
        BuriPath path = new BuriPath("test.BuriDataMany.Generic");
        BuriTestManyDto testManyDto = new BuriTestManyDto();
        BuriLocalContext context = (BuriLocalContext)getComponent(BuriLocalContext.class);
        context.setContainer(container);
        context.setCallBuriPath(path);
        BuriEngine engine = (BuriEngine)getComponent(BuriEngine.class);
//        context.setBuriEngine(engine);
        
        BuriDataEntityDto buriDataEntityDto;
        testManyDto.setTestID01(1);
        testManyDto.setTestID02(2);
        testManyDto.setValue("123");
        context.setData(testManyDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertTrue(buriDataEntityDto.getDataID() !=0);
        
        testManyDto.setValue("456");
        context.setData(testManyDto);
        BuriDataEntityDto buriDataEntityDtoReload= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertEquals(buriDataEntityDto.getDataID() , buriDataEntityDtoReload.getDataID());
        assertEquals(buriDataEntityDto.getPkeyNum() , buriDataEntityDtoReload.getPkeyNum());
        
        BuriTestManyDto test2ManyDto = new BuriTestManyDto();
        test2ManyDto.setTestID01(2);
        test2ManyDto.setTestID02(2);
        test2ManyDto.setValue("ABC");
        context.setData(test2ManyDto);
        buriDataDaoUtil.createOnlyBuriDataID(path);
        buriDataDaoUtil.storeBuriData(path);

        buriDataEntityDto= buriDataDaoUtil.getBuriDataEntityDto(path);
        assertTrue(buriDataEntityDto.getDataID() != buriDataEntityDtoReload.getDataID());

        
        Object readData = accessUtil_.getObjectFromDataID(path,buriDataEntityDto.getDataID());
        
        BuriTestManyDto readManyDto = (BuriTestManyDto)readData;
        assertEquals(readManyDto.getTestID01(),test2ManyDto.getTestID01());
        assertEquals(readManyDto.getTestID02(),test2ManyDto.getTestID02());
        assertEquals(readManyDto.getValue(),test2ManyDto.getValue());
        assertEquals(readManyDto.getVersionNo(),test2ManyDto.getVersionNo());
        
    }
    
    

}
