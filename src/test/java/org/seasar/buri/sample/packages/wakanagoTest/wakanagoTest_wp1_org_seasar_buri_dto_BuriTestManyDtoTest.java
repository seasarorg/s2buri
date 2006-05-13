/*
 * çÏê¨ì˙: 2006/05/05
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.dto.BuriTestManyDto;
import org.seasar.extension.unit.S2TestCase;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestManyDtoTest extends
        S2TestCase {
    private wakanagoTest_wp1_org_seasar_buri_dto_BuriTestManyDto dataAccess;
    
    public wakanagoTest_wp1_org_seasar_buri_dto_BuriTestManyDtoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        include("wakanagoSample.dicon");
        super.setUp();
    }

    public void testDataAccessTx() {
        BuriTestManyDto dto = new BuriTestManyDto();
        dto.setTestID01(100);
        dto.setTestID02(101);
        dto.setValue("hoge01");
        dataAccess.Store(dto);
        
        String key = dataAccess.getKey(dto);
        assertEquals(key,"testID01=100\ntestID02=101\n");
        
        dto.setTestID01(102);
        dto.setValue("HOGE01");
        dataAccess.Store(dto);
        
        dto = (BuriTestManyDto)dataAccess.getObjectFromKey("testID01=100\ntestID02=101\n");
        assertEquals(dto.getValue(),"hoge01");
        
        dto = new BuriTestManyDto();
        dto.setTestID01(100);
        dto.setTestID02(101);
        
        dto = (BuriTestManyDto)dataAccess.getDataFromDto(dto);
        assertEquals(dto.getValue(),"hoge01");
        
        dto.setValue("hoge01v2");
        dataAccess.Store(dto);
        
        dto = (BuriTestManyDto)dataAccess.getDataFromDto(dto);
        assertEquals(dto.getValue(),"hoge01v2");
    }
}
