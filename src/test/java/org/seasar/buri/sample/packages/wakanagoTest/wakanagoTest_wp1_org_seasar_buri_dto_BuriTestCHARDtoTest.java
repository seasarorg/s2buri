/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.dto.BuriTestCHARDto;
import org.seasar.extension.unit.S2TestCase;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestCHARDtoTest extends
        S2TestCase {
    
    private wakanagoTest_wp1_org_seasar_buri_dto_BuriTestCHARDto manyKey;
    
    public wakanagoTest_wp1_org_seasar_buri_dto_BuriTestCHARDtoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("wakanagoSample.dicon");
    }
    public void testDataAccessTx() {
        BuriTestCHARDto dto = new BuriTestCHARDto();
        dto.setTestID("Hoge01");
        dto.setValue("TestData01");
        manyKey.Store(dto);
        assertEquals(dto.getTestID(),"Hoge01");
        
        dto = (BuriTestCHARDto)manyKey.getDataFromDto(dto);
        assertEquals(dto.getValue(),"TestData01");
    }

}
