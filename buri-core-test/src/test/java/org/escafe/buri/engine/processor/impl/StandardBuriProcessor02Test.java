/*
 * 作成日: 2006/06/19
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.List;

import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.escafe.buri.entity.BuriTestUser;
import org.escafe.buri.exception.select.BuriNotSelectedActivityException;
import org.escafe.buri.service.BuriTestUserService;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.entity.Item;
import example.org.escafe.buri.service.ItemService;

public class StandardBuriProcessor02Test extends S2TestCase {
	private final String PATH = "buri/dicon/buriStandard.dicon";

	private BuriEngine buriEngine;

	private StandardBuriProcessor processor;

	private BuriTestUserService buriTestUserService;

	private ItemService itemService;

	private ParticipantProvider participantProvider;

	public StandardBuriProcessor02Test(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("org/escafe/buri/dicon/allTestService.dicon");
		include(PATH);
	}

	public void test01Tx() {
		readXlsWriteDb("StdTestData.xls");
		participantProvider =
		    (ParticipantProvider) getComponent("StdTestParticipantProvider");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/stdTest.xpdl",
		    "stdTest",
		    participantProvider);
		Item itemDto1 = itemService.findById(1L);
		BuriTestUser user1Dto = buriTestUserService.getBuriTestUser(1L);
		BuriTestUser user2Dto = buriTestUserService.getBuriTestUser(2L);
		BuriTestUser user3Dto = buriTestUserService.getBuriTestUser(3L);
		processor.toNextStatus("stdTest.Test02.指示", itemDto1, user1Dto);
		List dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.指示未処理",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatus("stdTest.Test02.指示未処理", itemDto1, user2Dto);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.指示未処理",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってる振り",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.未処理",
		        user3Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatus("stdTest.Test02.未処理", itemDto1, user3Dto);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってるところ",
		        user3Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.未処理",
		        user3Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってる振り",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatus("stdTest.Test02.頑張ってるところ", itemDto1, user3Dto);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってるところ",
		        user3Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.確認待ち",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってる振り",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatusAction(
		    "stdTest.Test02.確認待ち",
		    itemDto1,
		    user2Dto,
		    "OK");
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.確認待ち",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってる振り",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.報告確認前",
		        user1Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatusAction(
		    "stdTest.Test02.報告確認前",
		    itemDto1,
		    user1Dto,
		    "OK");
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.報告確認前",
		        user1Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.報告確認",
		        user1Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
	}

	public void test02Tx() {
		readXlsWriteDb("StdTestData.xls");
		participantProvider =
		    (ParticipantProvider) getComponent("StdTestParticipantProvider");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/stdTest.xpdl",
		    "stdTest",
		    participantProvider);
		Item itemDto1 = itemService.findById(1L);
		BuriTestUser user1Dto = buriTestUserService.getBuriTestUser(1L);
		BuriTestUser user2Dto = buriTestUserService.getBuriTestUser(2L);
		BuriTestUser user3Dto = buriTestUserService.getBuriTestUser(3L);
		processor.toNextStatus("stdTest.Test02.指示", itemDto1, user1Dto);
		List dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.指示未処理",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		processor.toNextStatus("stdTest.Test02.指示未処理", itemDto1, user2Dto);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.指示未処理",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 0);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.頑張ってる振り",
		        user2Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		dataList =
		    processor.getDataListFromPath(
		        "stdTest.Test02.未処理",
		        user3Dto,
		        Item.class);
		System.out.println(dataList);
		assertEquals(dataList.size(), 1);
		try {
			processor.toNextStatus(
			    "stdTest.Test02.頑張ってるところ",
			    itemDto1,
			    user3Dto);
			fail();
		} catch (BuriNotSelectedActivityException ex) {
			assertTrue(true);
		}
	}
}
