package org.escafe.buri.event;

import java.util.Date;

import org.escafe.buri.dao.BuriDataPathHistoryDao;
import org.escafe.buri.dao.FurnitureItemDao;
import org.escafe.buri.dto.FurnitureItemDto;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;
import org.escafe.buri.event.state.caller.BuriStatusEventCaller;
import org.escafe.buri.event.util.AbstractDataAccessRuleEventListener;
import org.escafe.buri.event.util.DataAccessRuleEvent;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.seasar.extension.unit.S2TestCase;

public class BuriDataAccessEventTest extends S2TestCase {
    private String PATH = "WakanagoCompile.dicon";
    private BuriEngine buriEngine;
    private SimpleBuriProcessor invoker_;
    private BuriDataPathHistoryDao historyDao;
    private FurnitureItemDao itemDao;
    private DataAccessRuleEventCaller dataAccessRuleEventCaller;
    private BuriStatusEventCaller statusCaller;

	public BuriDataAccessEventTest(String name) {
		super(name);
	}
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    public void testSimpleEventTx() {
    	dataAccessRuleEventCaller.addDataAccessRuleEventListener(new AbstractDataAccessRuleEventListener(){
    		public void determinedRule(DataAccessRuleEvent event) {
    			System.out.println("determinedRule:" + event.getBuriDataFieldType().getId());
    			System.out.println("determinedRule:" + event.getRule());
    		}

    		public void endNegotiateDao(DataAccessRuleEvent event) {
    			System.out.println("endNegotiateDao:" + event.getBuriDataFieldType().toString());
    		}

    		public void entryProcessor(DataAccessRuleEvent event) {
    			System.out.println("entryProcessor:" + event.getBuriDataFieldType().getId());
    		}

    		public void returnProcessor(DataAccessRuleEvent event) {
    			System.out.println("returnProcessor:" + event.getBuriDataFieldType());
    		}
    	});
        
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/BTS.xpdl","BTS");
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        invoker_.toNextStatus("BTS.プロジェクト管理",buyItemDto);
    }

}
