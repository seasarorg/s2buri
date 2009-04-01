package org.escafe.buri.event;

import java.util.Date;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.escafe.buri.event.state.caller.BuriStatusEventCaller;
import org.escafe.buri.event.util.AbstractDataAccessRuleEventListener;
import org.escafe.buri.event.util.DataAccessRuleEvent;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.escafe.buri.service.BuriDataPathHistoryEntityService;
import org.escafe.buri.service.FurnitureItemService;
import org.seasar.extension.unit.S2TestCase;

public class BuriDataAccessEventTest extends S2TestCase {
	private final String PATH = "WakanagoCompile.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	private BuriDataPathHistoryEntityService buriDataPathHistoryEntityService;

	private FurnitureItemService furnitureItemService;

	private DataAccessRuleEventCaller dataAccessRuleEventCaller;

	private BuriStatusEventCaller statusCaller;

	public BuriDataAccessEventTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testSimpleEventTx() {
		dataAccessRuleEventCaller
		    .addDataAccessRuleEventListener(new AbstractDataAccessRuleEventListener() {
			    @Override
			    public void determinedRule(DataAccessRuleEvent event) {
				    System.out.println("determinedRule:"
				        + event.getBuriDataFieldType().getId());
				    System.out.println("determinedRule:" + event.getRule());
			    }

			    @Override
			    public void endNegotiateDao(DataAccessRuleEvent event) {
				    System.out.println("endNegotiateDao:"
				        + event.getBuriDataFieldType().toString());
			    }

			    @Override
			    public void entryProcessor(DataAccessRuleEvent event) {
				    System.out.println("entryProcessor:"
				        + event.getBuriDataFieldType().getId());
			    }

			    @Override
			    public void returnProcessor(DataAccessRuleEvent event) {
				    System.out.println("returnProcessor:"
				        + event.getBuriDataFieldType());
			    }
		    });
		buriEngine.readWorkFlowFromResource("wakanagoxpdl/BTS.xpdl", "BTS");
		FurnitureItem buyItemDto = new FurnitureItem();
		buyItemDto.name = "T45_001";
		buyItemDto.type = "PC";
		buyItemDto.acquisitionType = AcquisitionType.BUYING;
		buyItemDto.acquisition = new Date();
		invoker_.toNextStatus("BTS.プロジェクト管理", buyItemDto);
	}
}
