/*
 * çÏê¨ì˙: 2005/08/18
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
import org.seasar.framework.container.S2Container;


public class SimpleBuriInvokerTest extends S2TestCase {
    private String PATH = "WakanagoCompile.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriInvoker invoker_;
    
    public SimpleBuriInvokerTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        
    }
    
    
    public void testSimpleTestTx() {
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/FurnitureManagement.xpdl","éëéYä«óù");
//        for(int i=0 ; i < 3 ; i++) {
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("éëéYä«óù.îıïiä«óù",buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("éëéYä«óù.îıïiä«óù",leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("éëéYä«óù.îıïiä«óù.óòópíÜ",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù.óòópíÜ".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("éëéYä«óù.îıïiä«óù",new Long(buyItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        invoker_.invoke("éëéYä«óù.îıïiä«óù",new Long(leaseItemDto.getFurnitureID()));
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("éëéYä«óù.îıïiä«óù.èûãpä˙ä‘èIóπ",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù.èûãpä˙ä‘èIóπ".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("éëéYä«óù.îıïiä«óù.ÉäÅ[ÉXèIóπ",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù.ÉäÅ[ÉXèIóπ".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = invoker_.getDataListFromPath("éëéYä«óù.îıïiä«óù.óòópíÜ",FurnitureItemDto.class);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("éëéYä«óù.îıïiä«óù.óòópíÜ".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
//        }
    }
    

}
