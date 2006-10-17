/*
 * �쐬��: 2005/08/18
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
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/FurnitureManagement.xpdl","���Y�Ǘ�");
//        for(int i=0 ; i < 3 ; i++) {
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("���Y�Ǘ�.���i�Ǘ�",buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("���Y�Ǘ�.���i�Ǘ�",leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p��",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        FurnitureItemFindDto findDto = new FurnitureItemFindDto();
        findDto.setType("PC");
        List pathNames = new ArrayList();
        pathNames.add("���Y�Ǘ�.���i�Ǘ�.���p��");
        datas = itemDao.find(findDto, pathNames);
        assertEquals(datas.size(),2);

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("���Y�Ǘ�.���i�Ǘ�",new Long(buyItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ� ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.toNextStatus("���Y�Ǘ�.���i�Ǘ�",new Long(leaseItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ� ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p���ԏI��",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p���ԏI��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���[�X�I��",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���[�X�I��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p��",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
//        }
        List history = historyDao.getAllBuriDataPathHistory();
        System.out.println(history);
    }
    

}
