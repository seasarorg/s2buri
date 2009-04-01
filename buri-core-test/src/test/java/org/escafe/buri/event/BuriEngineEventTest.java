package org.escafe.buri.event;

import java.util.Date;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.escafe.buri.event.engine.BuriActivitySelectEvent;
import org.escafe.buri.event.engine.BuriActivitySelectEventListener;
import org.escafe.buri.event.engine.BuriEngineEvent;
import org.escafe.buri.event.engine.BuriEngineEventListener;
import org.escafe.buri.event.engine.BuriProcessSelectEvent;
import org.escafe.buri.event.engine.BuriProcessSelectEventListener;
import org.escafe.buri.event.engine.caller.BuriActivitySelectEventCaller;
import org.escafe.buri.event.engine.caller.BuriEngineEventCaller;
import org.escafe.buri.event.engine.caller.BuriProcessSelectEventCaller;
import org.seasar.extension.unit.S2TestCase;

public class BuriEngineEventTest extends S2TestCase {
	private final String PATH = "WakanagoCompile.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	private BuriEngineEventCaller engineEventCaller;

	private BuriActivitySelectEventCaller activitySelectEventCaller;

	private BuriProcessSelectEventCaller processSelectEventCaller;

	public BuriEngineEventTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testEventTx() {
		engineEventCaller.addEventListener(new BuriEngineEventListener() {
			public void endExecute(BuriEngineEvent event) {
				System.out.println(event);
			}

			public void endFlow(BuriEngineEvent event) {
				System.out.println(event);
			}

			public void startExecute(BuriEngineEvent event) {
				System.out.println(event);
			}

			public void startFlow(BuriEngineEvent event) {
				System.out.println(event);
			}
		});
		activitySelectEventCaller
		    .addEventListener(new BuriActivitySelectEventListener() {
			    public void endActivitySelector(BuriActivitySelectEvent event) {
				    System.out.println(event);
			    }

			    public void endSelectActivityId(BuriActivitySelectEvent event) {
				    System.out.println(event);
			    }

			    public void errorActivitySelect(BuriActivitySelectEvent event) {
				    System.out.println(event);
			    }

			    public void startActivitySelector(BuriActivitySelectEvent event) {
				    System.out.println(event);
			    }

			    public void startSelectActivityId(BuriActivitySelectEvent event) {
				    System.out.println(event);
			    }
		    });
		processSelectEventCaller
		    .addEventListener(new BuriProcessSelectEventListener() {
			    public void endSelectProcess(BuriProcessSelectEvent event) {
				    System.out.println(event);
			    }

			    public void endSelector(BuriProcessSelectEvent event) {
				    System.out.println(event);
			    }

			    public void errorSelectProcess(BuriProcessSelectEvent event) {
				    System.out.println(event);
			    }

			    public void startSelectProcess(BuriProcessSelectEvent event) {
				    System.out.println(event);
			    }

			    public void startSelector(BuriProcessSelectEvent event) {
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
