/*
 * 作成日: 2006/06/19
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.List;

import org.escafe.buri.dao.BuriTestUserDao;
import org.escafe.buri.dto.BuriTestUserDto;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.escafe.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.dao.ItemDao;
import example.org.escafe.buri.dto.ItemDto;

public class StandardBuriProcessor02Test extends S2TestCase {
    private String PATH = "buri/dicon/buriStandard.dicon";
    private BuriEngine buriEngine;
    private StandardBuriProcessor processor;
    private BuriTestUserDao userDao;
    private ItemDao itemDao;
    private ParticipantProvider participantProvider;

    public StandardBuriProcessor02Test(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void test01Tx() {
        readXlsWriteDb("StdTestData.xls");
        participantProvider = (ParticipantProvider)getComponent("StdTestParticipantProvider");
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/stdTest.xpdl","stdTest",participantProvider);
        
        ItemDto itemDto1 = itemDao.getItem(1);
        
        BuriTestUserDto user1Dto = userDao.getBuriTestUser(1);
        BuriTestUserDto user2Dto = userDao.getBuriTestUser(2);
        BuriTestUserDto user3Dto = userDao.getBuriTestUser(3);
        processor.toNextStatus("stdTest.Test02.指示",itemDto1,user1Dto);
        List dataList = processor.getDataListFromPath("stdTest.Test02.指示未処理",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        processor.toNextStatus("stdTest.Test02.指示未処理",itemDto1,user2Dto);
        dataList = processor.getDataListFromPath("stdTest.Test02.指示未処理",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってる振り",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        dataList = processor.getDataListFromPath("stdTest.Test02.未処理",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        processor.toNextStatus("stdTest.Test02.未処理",itemDto1,user3Dto);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってるところ",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        dataList = processor.getDataListFromPath("stdTest.Test02.未処理",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってる振り",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        processor.toNextStatus("stdTest.Test02.頑張ってるところ",itemDto1,user3Dto);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってるところ",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.確認待ち",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってる振り",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        processor.toNextStatusAction("stdTest.Test02.確認待ち",itemDto1,user2Dto,"OK");
        dataList = processor.getDataListFromPath("stdTest.Test02.確認待ち",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってる振り",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.報告確認前",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        processor.toNextStatusAction("stdTest.Test02.報告確認前",itemDto1,user1Dto,"OK");
        dataList = processor.getDataListFromPath("stdTest.Test02.報告確認前",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.報告確認",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
    }
    
    
    public void test02Tx() {
        readXlsWriteDb("StdTestData.xls");
        participantProvider = (ParticipantProvider)getComponent("StdTestParticipantProvider");
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/stdTest.xpdl","stdTest",participantProvider);
        
        ItemDto itemDto1 = itemDao.getItem(1);
        
        BuriTestUserDto user1Dto = userDao.getBuriTestUser(1);
        BuriTestUserDto user2Dto = userDao.getBuriTestUser(2);
        BuriTestUserDto user3Dto = userDao.getBuriTestUser(3);
        processor.toNextStatus("stdTest.Test02.指示",itemDto1,user1Dto);
        List dataList = processor.getDataListFromPath("stdTest.Test02.指示未処理",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        processor.toNextStatus("stdTest.Test02.指示未処理",itemDto1,user2Dto);
        dataList = processor.getDataListFromPath("stdTest.Test02.指示未処理",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        dataList = processor.getDataListFromPath("stdTest.Test02.頑張ってる振り",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        dataList = processor.getDataListFromPath("stdTest.Test02.未処理",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        try {
            processor.toNextStatus("stdTest.Test02.頑張ってるところ",itemDto1,user3Dto);
            fail();
        } catch(BuriNotSelectedActivityException ex) {
            assertTrue(true);
        }
    }

}