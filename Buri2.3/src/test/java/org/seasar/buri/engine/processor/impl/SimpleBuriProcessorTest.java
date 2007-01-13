/*
 * 作成日: 2005/08/18
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.seasar.buri.dao.BuriDataPathHistoryDao;
import org.seasar.buri.dao.FurnitureItemDao;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.dto.FurnitureItemFindDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.SimpleBuriProcessor;
import org.seasar.extension.unit.S2TestCase;


public class SimpleBuriProcessorTest extends S2TestCase {
    private String PATH = "WakanagoCompile.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriProcessor invoker_;
    private BuriDataPathHistoryDao historyDao;
    private FurnitureItemDao itemDao;
    
    public SimpleBuriProcessorTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        
    }
    
    
    public void testSimpleTestTx() {
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/FurnitureManagement.xpdl","資産管理");
//        for(int i=0 ; i < 3 ; i++) {
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理",buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理",leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理.利用中",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理.利用中".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        FurnitureItemFindDto findDto = new FurnitureItemFindDto();
        findDto.setType("PC");
        List pathNames = new ArrayList();
        pathNames.add("資産管理.備品管理.利用中");
        datas = itemDao.find(findDto, pathNames);
        assertEquals(datas.size(),2);

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理",new Long(buyItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理 ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理",new Long(leaseItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理 ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理.償却期間終了",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理.償却期間終了".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理.リース終了",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理.リース終了".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理.利用中",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理.利用中".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
//        }
        List history = historyDao.getAllBuriDataPathHistory();
        System.out.println(history);
    }
    
    
    public void testSimpleTest2Tx() {
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/FurnitureManagement.xpdl","資産管理");
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理2",buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理2",leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理2.利用中",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2.利用中".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        FurnitureItemFindDto findDto = new FurnitureItemFindDto();
        findDto.setType("PC");
        List pathNames = new ArrayList();
        pathNames.add("資産管理.備品管理2.利用中");
        datas = itemDao.find(findDto, pathNames);
        assertEquals(datas.size(),2);

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理2",new Long(buyItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2 ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("資産管理.備品管理2",new Long(leaseItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2 ="+(end-start)+"ms");
        
        findDto = new FurnitureItemFindDto();
        findDto.setType("PC");
        pathNames = new ArrayList();
        pathNames.add("資産管理.備品管理2.期間終了");
        datas = itemDao.find(findDto, pathNames);
        assertEquals(datas.size(),2);

        start = Calendar.getInstance().getTimeInMillis();
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.put("action2", "buy");
        info.setAction(null);
        info.setContainer(null);
        info.setResultExp(null);
        info.setActNames(null);
        invoker_.toNextStatus("資産管理.備品管理2",new Long(buyItemDto.getFurnitureID()),info);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2 ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        info = new BuriProcessorInfo();
        info.put("action2", "lease");
        info.setAction(null);
        info.setContainer(null);
        info.setResultExp(null);
        info.setActNames(null);        
        invoker_.toNextStatus("資産管理.備品管理2",new Long(leaseItemDto.getFurnitureID()),info);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2 ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理2.償却期間終了",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2.償却期間終了".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理2.リース終了",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2.リース終了".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("資産管理.備品管理2.利用中",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("資産管理.備品管理2.利用中".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        List history = historyDao.getAllBuriDataPathHistory();
        System.out.println(history);
    }
    

}
