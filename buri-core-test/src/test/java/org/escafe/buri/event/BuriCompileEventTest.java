package org.escafe.buri.event;

import java.util.Date;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.escafe.buri.event.boot.AbstractBuriComplieEventListener;
import org.escafe.buri.event.boot.BuriCompileEvent;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;
import org.escafe.buri.event.state.BuriStatusEvent;
import org.escafe.buri.event.state.BuriStatusEventListener;
import org.escafe.buri.event.state.caller.BuriStatusEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.service.BuriDataPathHistoryEntityService;
import org.escafe.buri.service.FurnitureItemService;
import org.seasar.extension.unit.S2TestCase;

public class BuriCompileEventTest extends S2TestCase {
	private final String PATH = "WakanagoCompile.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	private BuriDataPathHistoryEntityService buriDataPathHistoryEntityService;

	private FurnitureItemService furnitureItemService;

	private BuriComplieEventCaller compileCaller;

	private BuriStatusEventCaller statusCaller;

	public BuriCompileEventTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testSimpleEventTx() {
		compileCaller
		    .addComplieEventListener(new AbstractBuriComplieEventListener() {
			    @Override
			    public void startReadXpdl(BuriCompileEvent event) {
				    assertEquals(
				        event.getCompileTargetName(),
				        "wakanagoxpdl/basicTest.xpdl");
				    assertEquals(
				        event.getCompileTargetType(),
				        BuriCompileEvent.CompileTargetType.XPDL);
				    assertNotNull(event.getCompiler());
				    assertNull(event.getResult());
				    System.out.println("startReadXpdl:"
				        + event.getCompileTargetName());
			    }

			    @Override
			    public void endReadXpdl(BuriCompileEvent event) {
				    assertEquals(
				        event.getCompileTargetName(),
				        "wakanagoxpdl/basicTest.xpdl");
				    assertEquals(
				        event.getCompileTargetType(),
				        BuriCompileEvent.CompileTargetType.XPDL);
				    assertNotNull(event.getCompiler());
				    assertNotNull(event.getResult());
				    assertTrue(event.getResult() instanceof BuriPackageType);
				    System.out.println("endReadXpdl:"
				        + event.getCompileTargetName());
			    }

			    @Override
			    public void startCompile(BuriCompileEvent event) {
				    System.out.println("startCompile:" + event);
			    }

			    @Override
			    public void endCompile(BuriCompileEvent event) {
				    System.out.println("endCompile:" + event);
			    }

			    @Override
			    public void startObjectInit(BuriCompileEvent event) {
				    System.out.println("startObjectInit:" + event);
			    }

			    @Override
			    public void endObjectInit(BuriCompileEvent event) {
				    System.out.println("endObjectInit:" + event);
			    }
		    });
		statusCaller.addStatusEventListener(new BuriStatusEventListener() {
			public void abortBranch(BuriStatusEvent event) {
				System.out.println("abortBranch:" + event);
			}

			public void abortState(BuriStatusEvent event) {
				System.out.println("abortState:" + event);
			}

			public void processed(BuriStatusEvent event) {
				System.out.println("processed:" + event);
			}

			public void saveState(BuriStatusEvent event) {
				System.out.println("saveState:" + event);
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
