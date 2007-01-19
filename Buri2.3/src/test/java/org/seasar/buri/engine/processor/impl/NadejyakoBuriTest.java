package org.seasar.buri.engine.processor.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.dao.BuriTestUserDao;
import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.SimpleBuriProcessor;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;

public class NadejyakoBuriTest extends S2TestCase {
    private String PATH = "buri/dicon/buriSimple.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriProcessor invoker_;
    private BuriTestUserDao dao;

	public NadejyakoBuriTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
        include(PATH);
	}
	
	// BuriTestUserDtoを使うとDerbyでエラーになるのでちょっとテスト延期
	public void te1stなでじゃこをぶりから呼んでみるTx() {
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/なでじゃこ.xpdl","なでなで");
        
        BuriTestUserDto userDto = new BuriTestUserDto();
        
        userDto.setUserName("えすかふぇ");
//        userDto.setParentUserID(10);
        
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.put("利用者", userDto);
        info.put("上司無し", new Integer(0));
        
        invoker_.toNextStatus("なでなで.なでじゃこてすと.開始", userDto,info);
		
        System.out.println(userDto);
        
	}

}
