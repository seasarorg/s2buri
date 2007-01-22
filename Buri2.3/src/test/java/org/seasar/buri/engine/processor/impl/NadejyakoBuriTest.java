package org.seasar.buri.engine.processor.impl;

import java.util.Date;

import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.SimpleBuriProcessor;
import org.seasar.extension.unit.S2TestCase;

public class NadejyakoBuriTest extends S2TestCase {
    private String PATH = "buri/dicon/buriSimple.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriProcessor invoker_;

	public NadejyakoBuriTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
        include(PATH);
	}
	public void testなでじゃこをぶりから呼んでみるTx() {
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/なでじゃこ.xpdl","なでなで");
        
        FurnitureItemDto itemDto = new FurnitureItemDto();
        itemDto.setType("サーバ");
        itemDto.setName("PS3");
        itemDto.setAcquisition(new Date());
        itemDto.setAcquisitionType(1);
        
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.put("備品", itemDto);
        info.put("リース", new Integer(1));
        
        invoker_.toNextStatus("なでなで.なでじゃこてすと.開始", itemDto,info);
		
        System.out.println(itemDto);
        
	}

}
