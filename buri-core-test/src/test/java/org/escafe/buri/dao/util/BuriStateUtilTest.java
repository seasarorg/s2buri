/*
 * 作成日: 2006/05/10
 *
 */
package org.escafe.buri.dao.util;

import jp.starlogic.util.datetime.DateUtil;

import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.entity.BuriStateEntity;
import org.escafe.buri.entity.BuriTestINT;
import org.escafe.buri.service.BuriBranchEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.extension.unit.S2TestCase;

public class BuriStateUtilTest extends S2TestCase {
	private BuriStateUtil stateUtil;

	private WakanagoEngine engine;

	private BuriStateEntityService buriStateEntityService;

	private BuriBranchEntityService buriBranchEntityService;

	public BuriStateUtilTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("WakanagoCompile.dicon");
	}

	public void testBuriStateProceedTx() {
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriDataAccessFactory dataAccessFactory =
		    (BuriDataAccessFactory) getComponent("rootDataAccessFactory");
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
		walker = walker.moveNext("開始", "wakanagoTest_wp1_act1");
		stateUtil.saveBranch(walker, dataAccessFactory, sysContext);
		long statusId =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, walker);
		assertTrue(statusId != 0);
		assertTrue(walker.getBranchID() != 0);
		userContext = engine.createUserContext(dto, null, null, null);
		sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		sysContext.setStatusID(new Long(statusId));// 本当はAbstractで処理する
		walker = stateUtil.loadBranchWalker(sysContext);
		assertNotNull(walker);
		assertTrue(walker.getBranchID() != 0);
		BuriStateEntity stateDto =
		    buriStateEntityService.getBuriState(statusId);
		assertEquals(stateDto.processDate.getTime() / 10, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10);
		assertEquals(stateDto.abortDate.getTime() / 1000, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 1000);
		assertEquals(stateDto.autoRunTime.getTime() / 10, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10);
		assertTrue(stateDto.dataId.longValue() != 0);
		assertTrue(stateDto.pathId.longValue() != 0);
		assertTrue(stateDto.branchId.longValue() != 0);
		stateUtil.saveBranch(walker, dataAccessFactory, sysContext);
		stateUtil.processed(dataAccessFactory, sysContext, walker);
		stateDto = buriStateEntityService.getBuriState(statusId);
		assertFalse((stateDto.processDate.getTime() / 10) == (DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10));
		assertEquals(stateDto.abortDate.getTime() / 1000, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 1000);
		assertEquals(stateDto.autoRunTime.getTime() / 10, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10);
		assertTrue(stateDto.dataId.longValue() != 0);
		assertTrue(stateDto.pathId.longValue() != 0);
		assertTrue(stateDto.branchId.longValue() != 0);
		walker = stateUtil.loadBranchWalker(sysContext);
		assertNotNull(walker);
	}

	public void testBuriStateAbortTx() {
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriDataAccessFactory dataAccessFactory =
		    (BuriDataAccessFactory) getComponent("rootDataAccessFactory");
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		userContext = engine.createUserContext(dto, null, null, null);
		sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
		walker.setParentPath(sysContext.getCallPath());
		walker = walker.moveNext("開始", "wakanagoTest_wp1_act1");
		stateUtil.saveBranch(walker, dataAccessFactory, sysContext);
		long statusID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, walker);
		assertTrue(statusID != 0);
		sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
		stateUtil.abortStatus(dataAccessFactory, sysContext, walker);
		BuriStateEntity stateDto =
		    buriStateEntityService.getBuriState(statusID);
		assertFalse((stateDto.processDate.getTime() / 10) == (DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10));
		assertFalse((stateDto.abortDate.getTime() / 1000) == (DateUtil
		    .getSQLMaxDate()
		    .getTime() / 1000));
		assertEquals(stateDto.autoRunTime.getTime() / 10, DateUtil
		    .getSQLMaxDate()
		    .getTime() / 10);
		assertTrue(stateDto.dataId.longValue() != 0);
		assertTrue(stateDto.pathId.longValue() != 0);
	}

	public void testBuriStateBranchTx() {
		long startSize = buriBranchEntityService.getAllBuriBranch().size();
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriDataAccessFactory dataAccessFactory =
		    (BuriDataAccessFactory) getComponent("rootDataAccessFactory");
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		userContext = engine.createUserContext(dto, null, null, null);
		sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
		walker.setParentPath(sysContext.getCallPath());
		walker = walker.moveNext("開始", "wakanagoTest_wp1_act1");
		stateUtil.saveBranch(walker, dataAccessFactory, sysContext);
		long statusID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, walker);
		assertTrue(statusID != 0);
		sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 1);
		BranchWalker child1 =
		    stateUtil.branchChild(walker, dataAccessFactory, sysContext);
		child1 = child1.moveNext("分岐1", "wakanagoTest_wp1_act2");
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 1);
		assertEquals(child1.getParentBranchID(), walker.getBranchID());
		assertEquals(child1.getBranchID(), 0);
		stateUtil.saveBranch(child1, dataAccessFactory, sysContext);
		long child1StateID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, child1);
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 2);
		BranchWalker child2 =
		    stateUtil.branchChild(walker, dataAccessFactory, sysContext);
		child2 = child2.moveNext("分岐2", "wakanagoTest_wp1_act3");
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 2);
		assertEquals(child2.getParentBranchID(), walker.getBranchID());
		assertEquals(child2.getBranchID(), 0);
		stateUtil.saveBranch(child2, dataAccessFactory, sysContext);
		long child2StateID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, child2);
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 3);
		long count =
		    stateUtil.countNoProcessedSiblingStatus(
		        dataAccessFactory,
		        sysContext,
		        child2);
		assertEquals(2, count);
		sysContext.setStatusID(new Long(child1StateID));// 本当はAbstractで処理する
		stateUtil.processed(dataAccessFactory, sysContext, child1);
		count =
		    stateUtil.countNoProcessedSiblingStatus(
		        dataAccessFactory,
		        sysContext,
		        child1);
		assertEquals(1, count);
		sysContext.setStatusID(new Long(child2StateID));// 本当はAbstractで処理する
		stateUtil.abortStatus(dataAccessFactory, sysContext, child2);
		count =
		    stateUtil.countNoProcessedSiblingStatus(
		        dataAccessFactory,
		        sysContext,
		        child2);
		assertEquals(0, count);
	}

	public void testBuriStateBranchAbortTx() {
		long startSize = buriBranchEntityService.getAllBuriBranch().size();
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriDataAccessFactory dataAccessFactory =
		    (BuriDataAccessFactory) getComponent("rootDataAccessFactory");
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		userContext = engine.createUserContext(dto, null, null, null);
		sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		BranchWalker walker = stateUtil.loadBranchWalker(sysContext);
		walker.setParentPath(sysContext.getCallPath());
		walker = walker.moveNext("開始", "wakanagoTest_wp1_act1");
		stateUtil.saveBranch(walker, dataAccessFactory, sysContext);
		long statusID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, walker);
		assertTrue(statusID != 0);
		sysContext.setStatusID(new Long(statusID));// 本当はAbstractで処理する
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 1);
		BranchWalker child1 =
		    stateUtil.branchChild(walker, dataAccessFactory, sysContext);
		child1 = child1.moveNext("分岐1", "wakanagoTest_wp1_act2");
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 1);
		assertEquals(child1.getParentBranchID(), walker.getBranchID());
		assertEquals(child1.getBranchID(), 0);
		stateUtil.saveBranch(child1, dataAccessFactory, sysContext);
		long child1StateID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, child1);
		assertTrue(child1StateID != 0);
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 2);
		BranchWalker child2 =
		    stateUtil.branchChild(walker, dataAccessFactory, sysContext);
		child2 = child2.moveNext("分岐2", "wakanagoTest_wp1_act3");
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 2);
		assertEquals(child2.getParentBranchID(), walker.getBranchID());
		assertEquals(child2.getBranchID(), 0);
		stateUtil.saveBranch(child2, dataAccessFactory, sysContext);
		long child2StateID =
		    stateUtil.saveStatus(dataAccessFactory, sysContext, child2);
		assertTrue(child2StateID != 0);
		assertEquals(
		    buriBranchEntityService.getAllBuriBranch().size(),
		    startSize + 3);
		long count =
		    stateUtil.countNoProcessedSiblingStatus(
		        dataAccessFactory,
		        sysContext,
		        child2);
		assertEquals(2, count); // ★
		stateUtil.abortBranch(dataAccessFactory, sysContext, child2);
		count =
		    stateUtil.countNoProcessedSiblingStatus(
		        dataAccessFactory,
		        sysContext,
		        child1);
		assertEquals(0, count);
	}
}
