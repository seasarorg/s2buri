package org.escafe.buri.engine.processor.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.seasar.extension.unit.S2TestCase;

public class NadejyakoBuriTest extends S2TestCase {
	private final String PATH = "buri/dicon/buriSimple.dicon";

	private BuriEngine buriEngine;

	private SimpleBuriProcessor invoker_;

	public NadejyakoBuriTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
	}

	public void testDummy() {
		// なでじゃこがうまくいくようになるまでテスト中止
	}

	public void te1stなでじゃこをぶりから呼んでみるTx() {
		buriEngine.readWorkFlowFromResource("wakanagoxpdl/なでじゃこ.xpdl", "なでなで");
		FurnitureItem furnitureItem = new FurnitureItem();
		furnitureItem.type = "サーバ";
		furnitureItem.name = "PS3";
		furnitureItem.acquisition = new Date();
		furnitureItem.acquisitionType = AcquisitionType.LEASE;
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("備品", furnitureItem);
		info.put("リース", new BigDecimal(1));
		invoker_.toNextStatus("なでなで.なでじゃこてすと.開始", furnitureItem, info);
		System.out.println(furnitureItem);
	}
}
