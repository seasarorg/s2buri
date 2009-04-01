package org.escafe.buri.event;

import java.util.Date;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.escafe.buri.event.flow.BuriFlowConditionEvent;
import org.escafe.buri.event.flow.BuriFlowEvent;
import org.escafe.buri.event.flow.BuriFlowEventListener;
import org.escafe.buri.event.flow.BuriFlowJoinSplitEvent;
import org.escafe.buri.event.flow.caller.BuriFlowEventCaller;
import org.escafe.buri.service.BuriDataPathHistoryEntityService;
import org.escafe.buri.service.FurnitureItemService;
import org.seasar.extension.unit.S2TestCase;

public class BuriFlowEventTest extends S2TestCase {
	private final String PATH = "WakanagoCompile.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	private BuriDataPathHistoryEntityService buriDataPathHistoryEntityService;

	private FurnitureItemService furnitureItemService;

	private BuriFlowEventCaller flowEventCaller;

	public BuriFlowEventTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testEventTx() {
		flowEventCaller.addBuriFlowEventListener(new BuriFlowEventListener() {
			public void callAfterProcess(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void endConditionCheck(BuriFlowConditionEvent event) {
				System.out.println(event);
			}

			public void endSelectActivityId(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void entryActivity(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void exitFlow(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void joinAndFlow(BuriFlowJoinSplitEvent event) {
				System.out.println(event);
			}

			public void joinXorFlow(BuriFlowJoinSplitEvent event) {
				System.out.println(event);
			}

			public void noProcessAndFlow(BuriFlowJoinSplitEvent event) {
				System.out.println(event);
			}

			public void restartActivity(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void splitAndPreprocess(BuriFlowJoinSplitEvent event) {
				System.out.println(event);
			}

			public void startActivity(BuriFlowEvent event) {
				System.out.println(event);
			}

			public void startConditionCheck(BuriFlowConditionEvent event) {
				System.out.println(event);
			}

			public void startSelectActivityId(BuriFlowEvent event) {
				System.out.println(event);
			}
		});
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/basicTest.xpdl",
		    "basicTest");
		FurnitureItem buyItemDto = new FurnitureItem();
		buyItemDto.name = "T45_001";
		buyItemDto.type = "PC";
		buyItemDto.acquisitionType = AcquisitionType.BUYING;
		buyItemDto.acquisition = new Date();
		invoker_.toNextStatus("basicTest.test02", buyItemDto);
		invoker_.toNextStatus("basicTest.test02", buyItemDto);
	}
}
