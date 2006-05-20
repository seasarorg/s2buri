/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import java.util.ArrayList;
import java.util.List;

import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.extension.unit.S2TestCase;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestINTDtoTest extends
        S2TestCase {
    
    private wakanagoTest_wp1_org_seasar_buri_dto_BuriTestINTDto utilLongKey;

    public wakanagoTest_wp1_org_seasar_buri_dto_BuriTestINTDtoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("wakanagoSample.dicon");
    }
    
    public void testDataAccessTx() {
        BuriTestINTDto dto = new BuriTestINTDto();
        dto.setValue("test01");
        dto = (BuriTestINTDto)utilLongKey.Store(dto);
        System.out.println(dto);
        long testID1 = dto.getTestID();
        
        dto = new BuriTestINTDto();
        dto.setValue("test02");
        dto = (BuriTestINTDto)utilLongKey.Store(dto);
        System.out.println(dto);
        long testID2 = dto.getTestID();
        
        dto.setTestID(testID1);
        dto = (BuriTestINTDto)utilLongKey.getDataFromDto(dto);
        assertEquals("test01",dto.getValue());
        
        dto.setValue("UpdateVal01");
        utilLongKey.Store(dto);
        
        assertEquals(dto.getTestID(),testID1);
        
        List idList = new ArrayList();
        idList.add(new Long(testID1));
        idList.add(new Long(testID2));
        
        List objList = utilLongKey.get(idList);
        assertEquals(objList.size(),2);
        
    }

}
