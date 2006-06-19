/*
 * 作成日: 2006/06/14
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.List;

import org.seasar.buri.dao.BuriTestUserDao;
import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.processor.StandardBuriProcessor;
import org.seasar.extension.unit.S2TestCase;

import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dto.ItemDto;

public class StandardBuriProcessor01Test extends S2TestCase {
    private String PATH = "buri/dicon/buriStandard.dicon";
    private BuriEngine buriEngine;
    private StandardBuriProcessor processor;
    private BuriTestUserDao userDao;
    private ItemDao itemDao;
    private ParticipantProvider participantProvider;
    
    public StandardBuriProcessor01Test(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        
    }
    
    public void test01Tx() {
        readXlsWriteDb("StdTestData.xls");
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/stdTest.xpdl","stdTest",participantProvider);
        
        ItemDto itemDto1 = itemDao.getItem(1);
        BuriTestUserDto userDto = null;
        
        processor.toNextStatus("stdTest.Test01",itemDto1,userDto);
        BuriTestUserDto user3Dto = userDao.getBuriTestUser(3);
        List dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        BuriTestUserDto user2Dto = userDao.getBuriTestUser(2);
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        BuriTestUserDto user1Dto = userDao.getBuriTestUser(1);
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        
        ItemDto itemDto2 = itemDao.getItem(2);
        userDto = null;
        
        processor.toNextStatus("stdTest.Test01",itemDto2,userDto);
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        
        ItemDto itemDto3 = itemDao.getItem(3);
        userDto = null;
        
        processor.toNextStatus("stdTest.Test01",itemDto3,userDto);
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        processor.toNextStatus("stdTest.Test01.受付済み",itemDto1,user3Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        processor.toNextStatus("stdTest.Test01.受付済み",itemDto2,user2Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);

        
        processor.toNextStatus("stdTest.Test01.受付済み",itemDto3,user1Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        
        
        processor.toNextStatus("stdTest.Test01.処理中",itemDto1,user3Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        processor.toNextStatus("stdTest.Test01.処理中",itemDto2,user2Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),2);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),1);
        
        
        processor.toNextStatus("stdTest.Test01.処理中",itemDto3,user1Dto);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),3);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user2Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中",user1Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),0);
        
        processor.toNextStatus("stdTest.Test01.返信待ち",itemDto1,user1Dto);
        
        
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち",user3Dto,ItemDto.class);
        System.out.println(dataList);
        assertEquals(dataList.size(),2);
        
    }

}
