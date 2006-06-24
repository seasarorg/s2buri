/*
 * �쐬��: 2005/08/18
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.extension.unit.S2TestCase;


public class SimpleBuriInvokerTest extends S2TestCase {
    private String PATH = "SimpleBuriInvoker.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriInvoker invoker_;
    
    public SimpleBuriInvokerTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        buriEngine = (BuriEngine)getComponent(BuriEngine.class);
        buriEngine.getWorkflows().readWorkFlowFromResource("���Y�Ǘ�","FurnitureManagement.xpdl");
        
    }
    
    public void testSimpleTestTx() {
//        for(int i=0 ; i < 3 ; i++) {
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("���Y�Ǘ�.���i�Ǘ�",buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("���Y�Ǘ�.���i�Ǘ�",leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p��");
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        FurnitureItemDto findDto = new FurnitureItemDto();
        findDto.setName("T45_002");
        datas = invoker_.findDataList("���Y�Ǘ�.���i�Ǘ�.���p��",findDto);
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        findDto = new FurnitureItemDto();
        findDto.setType("PC");
        datas = invoker_.findDataList("���Y�Ǘ�.���i�Ǘ�.���p��",findDto);
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invokeNoUpdate("���Y�Ǘ�.���i�Ǘ�",new Long(buyItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ� ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invokeNoUpdate("���Y�Ǘ�.���i�Ǘ�",new Long(leaseItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ� ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p���ԏI��");
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p���ԏI��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���[�X�I��");
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���[�X�I��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.���i�Ǘ�.���p��");
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("���Y�Ǘ�.���i�Ǘ�.���p��".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
//        }
    }
    

}
