/*
 * 作成日: 2006/03/31
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.entity.BuriTestINT;
import org.escafe.buri.service.BuriBranchEntityService;
import org.escafe.buri.service.BuriDataEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.extension.unit.S2TestCase;

public class WakanagoSampleTest extends S2TestCase {
	private BuriStateEntityService buriStateEntityService;

	private BuriDataEntityService buriDataEntityService;

	private BuriBranchEntityService buriBranchEntityService;

	private BuriDataUtil dataUtil;

	public WakanagoSampleTest(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("WakanagoCompile.dicon");
	}

	public void testWakanagoTestTx() {
		WakanagoEngine engine =
		    (WakanagoEngine) getComponent(WakanagoEngine.class);
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		long startStateCount = buriStateEntityService.getAllBuriState().size();
		long startDataCount = buriDataEntityService.getAllBuriData().size();
		long startBranchCount =
		    buriBranchEntityService.getAllBuriBranch().size();
		long start = System.currentTimeMillis();
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		engine.execute(sysContext, null);
		assertEquals(startStateCount + 1, buriStateEntityService
		    .getNoProcessBuriState()
		    .size());
		assertEquals(startStateCount + 1, buriStateEntityService
		    .getAllBuriState()
		    .size());
		assertEquals(startDataCount + 1, buriDataEntityService
		    .getAllBuriData()
		    .size());
		assertEquals(startBranchCount + 1, buriBranchEntityService
		    .getAllBuriBranch()
		    .size());
		BuriTestINT data =
		    (BuriTestINT) sysContext.getUserContext().get("data");
		assertEquals(data.value, "2");
		System.out.println(sysContext.getUserContext());
		sysContext =
		    engine.createSystemContext(
		        "wakanagoTest.ワカナゴテスト.Stop1",
		        userContext);
		engine.execute(sysContext, null);
		assertEquals(startStateCount + 1, buriStateEntityService
		    .getNoProcessBuriState()
		    .size());
		assertEquals(startStateCount + 2, buriStateEntityService
		    .getAllBuriState()
		    .size());
		assertEquals(startDataCount + 1, buriDataEntityService
		    .getAllBuriData()
		    .size());
		assertEquals(startBranchCount + 1, buriBranchEntityService
		    .getAllBuriBranch()
		    .size());
		sysContext =
		    engine.createSystemContext(
		        "wakanagoTest.ワカナゴテスト.Stop2",
		        userContext);
		engine.execute(sysContext, null);
		assertEquals(startStateCount + 1, buriStateEntityService
		    .getNoProcessBuriState()
		    .size());
		assertEquals(startStateCount + 3, buriStateEntityService
		    .getAllBuriState()
		    .size());
		assertEquals(startDataCount + 1, buriDataEntityService
		    .getAllBuriData()
		    .size());
		assertEquals(startBranchCount + 1, buriBranchEntityService
		    .getAllBuriBranch()
		    .size());
		long end = System.currentTimeMillis();
		System.out.println("ProcessTime = " + (end - start) + "ms");
	}

	public void testDataTestTx() {
		WakanagoEngine engine =
		    (WakanagoEngine) getComponent(WakanagoEngine.class);
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriTestINT dto = new BuriTestINT();
		dto.value = "hoge";
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		sysContext.setTargetDtoClass(BuriTestINT.class);
		BuriExePackages packages = engine.selectPackage(sysContext);
		BuriExecProcess process = packages.getProcess(sysContext.getCallPath());
		List<?> dataList =
		    dataUtil.getDtoListByPathName(
		        "wakanagoTest.ワカナゴテスト.Stop1",
		        (DataAccessFactory) process,
		        sysContext);
		long startCount = dataList.size();
		engine.execute(sysContext, null);
		dataList =
		    dataUtil.getDtoListByPathName(
		        "wakanagoTest.ワカナゴテスト.Stop1",
		        (DataAccessFactory) process,
		        sysContext);
		assertEquals(startCount + 1, dataList.size());
		sysContext =
		    engine.createSystemContext(
		        "wakanagoTest.ワカナゴテスト.Stop1",
		        userContext);
		sysContext.setTargetDtoClass(BuriTestINT.class);
		engine.execute(sysContext, null);
		dataList =
		    dataUtil.getDtoListByPathName(
		        "wakanagoTest.ワカナゴテスト.Stop1",
		        (DataAccessFactory) process,
		        sysContext);
		assertEquals(startCount, dataList.size());
		dataList =
		    dataUtil.getDtoListByPathName(
		        "wakanagoTest.ワカナゴテスト.Stop2",
		        (DataAccessFactory) process,
		        sysContext);
		assertEquals(startCount + 1, dataList.size());
	}
}
