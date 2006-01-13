/*
 * ì¬“ú: 2006/01/04
 *
 */
package org.seasar.buri.bao.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.seasar.buri.bao.FurnitureManagementBao;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.extension.unit.S2TestCase;

public class FurnitureManagementBaoTest extends S2TestCase {
    private BuriEngine buriEngine_;
    private FurnitureManagementBao fmBao_;

    public FurnitureManagementBaoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("org/seasar/buri/bao/test/dicon/FurnitureManagement.dicon");
    }
    
    public void testFMBaoTx() {
        // –{“–‚Íburi2.dicon‚É‘‚­‚à‚Ì
        buriEngine_.getWorkflows().readWorkFlowFromResource("‘YŠÇ—","FurnitureManagement.xpdl");
        
        List datas;
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        
        long start = Calendar.getInstance().getTimeInMillis();
        fmBao_.enterItem(buyItemDto);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(buyItemDto.toString() + " time =" + (end-start)+"ms");

        FurnitureItemDto leaseItemDto = new FurnitureItemDto();
        leaseItemDto.setName("T45_002");
        leaseItemDto.setType("PC");
        leaseItemDto.setAcquisitionTypeLease();
        leaseItemDto.setAcquisition(new Date());        
        
        start = Calendar.getInstance().getTimeInMillis();
        fmBao_.enterItem(leaseItemDto);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(leaseItemDto.toString() + " time =" + (end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = fmBao_.getNowUse();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ—.—˜—p’†".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        FurnitureItemDto findDto = new FurnitureItemDto();
        findDto.setName("T45_002");
        datas = fmBao_.findNowUse(findDto);
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        findDto = new FurnitureItemDto();
        findDto.setType("PC");
        datas = fmBao_.findNowUse(findDto);
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        

        start = Calendar.getInstance().getTimeInMillis();
        fmBao_.timeorverItem(buyItemDto.getFurnitureID());
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ— ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        fmBao_.timeorverItem(leaseItemDto.getFurnitureID());
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ— ="+(end-start)+"ms");

        start = Calendar.getInstance().getTimeInMillis();
        datas = fmBao_.getEndDepreciation();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ—.‹pŠúŠÔI—¹".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = fmBao_.getEndLease();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ—.ƒŠ[ƒXI—¹".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),1);

        start = Calendar.getInstance().getTimeInMillis();
        datas = fmBao_.getNowUse();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("‘YŠÇ—.”õ•iŠÇ—.—˜—p’†".toString() + " time =" + (end-start)+"ms");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
    }

}
