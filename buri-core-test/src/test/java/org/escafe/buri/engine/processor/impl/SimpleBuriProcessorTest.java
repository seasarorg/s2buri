/*
 * 作成日: 2005/08/18
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.escafe.buri.dto.FurnitureItemFindDto;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.escafe.buri.service.BuriDataPathHistoryEntityService;
import org.escafe.buri.service.FurnitureItemService;
import org.seasar.extension.unit.S2TestCase;

public class SimpleBuriProcessorTest extends S2TestCase {
	private final String PATH = "WakanagoCompile.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	private BuriDataPathHistoryEntityService buriDataPathHistoryEntityService;

	private FurnitureItemService furnitureItemService;

	public SimpleBuriProcessorTest(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testSimpleTestTx() {
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/FurnitureManagement.xpdl",
		    "資産管理");
		// for(int i=0 ; i < 3 ; i++) {
		List datas;
		FurnitureItem buyItemDto = new FurnitureItem();
		buyItemDto.name = "T45_001";
		buyItemDto.type = "PC";
		buyItemDto.acquisitionType = AcquisitionType.BUYING;
		buyItemDto.acquisition = new Date();
		long start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理", buyItemDto);
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(buyItemDto.toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		FurnitureItem leaseItemDto = new FurnitureItem();
		leaseItemDto.name = "T45_002";
		leaseItemDto.type = "PC";
		leaseItemDto.acquisitionType = AcquisitionType.LEASE;
		leaseItemDto.acquisition = new Date();
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理", leaseItemDto);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println(leaseItemDto.toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath("資産管理.備品管理.利用中", FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理.利用中".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 2);
		FurnitureItemFindDto findDto = new FurnitureItemFindDto();
		findDto.setType("PC");
		List pathNames = new ArrayList();
		pathNames.add("資産管理.備品管理.利用中");
		datas = furnitureItemService.find(findDto, pathNames);
		assertEquals(datas.size(), 2);
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理", new Long(buyItemDto.furnitureId));
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理 =" + (end - start) + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理", new Long(leaseItemDto.furnitureId));
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理 =" + (end - start) + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath(
		        "資産管理.備品管理.償却期間終了",
		        FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理.償却期間終了".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_
		        .getDataListFromPath("資産管理.備品管理.リース終了", FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理.リース終了".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath("資産管理.備品管理.利用中", FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理.利用中".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		// }
		List history =
		    buriDataPathHistoryEntityService.getAllBuriDataPathHistory();
		System.out.println(history);
	}

	public void testSimpleTest2Tx() {
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/FurnitureManagement.xpdl",
		    "資産管理");
		List datas;
		FurnitureItem buyItemDto = new FurnitureItem();
		buyItemDto.name = "T45_001";
		buyItemDto.type = "PC";
		buyItemDto.acquisitionType = AcquisitionType.BUYING;
		buyItemDto.acquisition = new Date();
		long start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理2", buyItemDto);
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(buyItemDto.toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		FurnitureItem leaseItemDto = new FurnitureItem();
		leaseItemDto.name = "T45_002";
		leaseItemDto.type = "PC";
		leaseItemDto.acquisitionType = AcquisitionType.LEASE;
		leaseItemDto.acquisition = new Date();
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理2", leaseItemDto);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println(leaseItemDto.toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath("資産管理.備品管理2.利用中", FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2.利用中".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 2);
		FurnitureItemFindDto findDto = new FurnitureItemFindDto();
		findDto.setType("PC");
		List pathNames = new ArrayList();
		pathNames.add("資産管理.備品管理2.利用中");
		datas = furnitureItemService.find(findDto, pathNames);
		assertEquals(datas.size(), 2);
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理2", new Long(buyItemDto.furnitureId));
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2 =" + (end - start) + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		invoker_.toNextStatus("資産管理.備品管理2", new Long(leaseItemDto.furnitureId));
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2 =" + (end - start) + "ms");
		findDto = new FurnitureItemFindDto();
		findDto.setType("PC");
		pathNames = new ArrayList();
		pathNames.add("資産管理.備品管理2.期間終了");
		datas = furnitureItemService.find(findDto, pathNames);
		assertEquals(datas.size(), 2);
		start = Calendar.getInstance().getTimeInMillis();
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("action2", "buy");
		info.setAction(null);
		info.setContainer(null);
		info.setResultExp(null);
		info.setActNames(null);
		invoker_.toNextStatus(
		    "資産管理.備品管理2",
		    new Long(buyItemDto.furnitureId),
		    info);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2 =" + (end - start) + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		info = new BuriProcessorInfo();
		info.put("action2", "lease");
		info.setAction(null);
		info.setContainer(null);
		info.setResultExp(null);
		info.setActNames(null);
		invoker_.toNextStatus(
		    "資産管理.備品管理2",
		    new Long(leaseItemDto.furnitureId),
		    info);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2 =" + (end - start) + "ms");
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath(
		        "資産管理.備品管理2.償却期間終了",
		        FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2.償却期間終了".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath(
		        "資産管理.備品管理2.リース終了",
		        FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2.リース終了".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		start = Calendar.getInstance().getTimeInMillis();
		datas =
		    invoker_.getDataListFromPath("資産管理.備品管理2.利用中", FurnitureItem.class);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("資産管理.備品管理2.利用中".toString()
		    + " time ="
		    + (end - start)
		    + "ms");
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		List history =
		    buriDataPathHistoryEntityService.getAllBuriDataPathHistory();
		System.out.println(history);
	}
}
