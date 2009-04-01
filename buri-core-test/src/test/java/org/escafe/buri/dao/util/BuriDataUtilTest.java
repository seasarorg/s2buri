/*
 * 作成日: 2006/05/09
 *
 */
package org.escafe.buri.dao.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.WakanagoEngine;
import org.escafe.buri.entity.BuriTestINT;
import org.escafe.buri.service.BuriTestINTService;
import org.seasar.extension.unit.S2TestCase;

public class BuriDataUtilTest extends S2TestCase {
	private BuriDataUtil dataUtil;

	private BuriPathUtil pathUtil;

	private WakanagoEngine engine;

	private BuriTestINTService buriTestINTService;

	public BuriDataUtilTest(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("WakanagoCompile.dicon");
	}

	public void testDataUtilTx() {
		BuriTestINT dto = new BuriTestINT();
		engine.readWorkFlowFromResource(
		    "wakanagoxpdl/wakanagoTest.xpdl",
		    "wakanagoTest");
		BuriDataAccessFactory dataAccessFactory =
		    (BuriDataAccessFactory) getComponent("rootDataAccessFactory");
		BuriUserContext userContext =
		    engine.createUserContext(dto, null, null, null);
		BuriSystemContext sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		long dataId = dataUtil.getBuriDataId(dataAccessFactory, sysContext);
		assertTrue(dataId != 0);
		dto.value = "hoge";
		buriTestINTService.insert(dto);
		dataUtil.updateBuriData(dataAccessFactory, sysContext);
		userContext = engine.createUserContext(dto, null, null, null);
		sysContext =
		    engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始", userContext);
		long saveId = dataUtil.getBuriDataId(dataAccessFactory, sysContext);
		assertEquals(dataId, saveId);
	}

	public void testPathUtilTx() {
		BuriPath path = new BuriPath("pkg.prces.act", "rpkg.rproces.ract");
		BuriPath realPath = pathUtil.getBuriPathFromRealPath(path);
		assertEquals(path.getPlainName(), realPath.getPlainName());
		assertEquals(path.getRealPath().getPlainName(), realPath
		    .getRealPath()
		    .getPlainName());
		assertTrue(realPath.getBuriPathId() != 0);
		long fstID = realPath.getBuriPathId();
		path = new BuriPath("pkg.prces.act", "rpkg.rproces.ract2");
		pathUtil.getBuriPathFromRealPath(path);
		path = new BuriPath("pkg.prces2.act", "rpkg.rproces2.ract");
		pathUtil.getBuriPathFromRealPath(path);
		path = new BuriPath("pkg.prces.act");
		List pathList = pathUtil.getBuriPathFromPathName(path);
		HashSet plains = new HashSet();
		plains.add("rpkg.rproces.ract");
		plains.add("rpkg.rproces.ract2");
		Iterator ite = pathList.iterator();
		path = (BuriPath) ite.next();
		assertEquals(path.getPlainName(), "pkg.prces.act");
		// assertEquals(path.getRealPath().getPlainName(),"rpkg.rproces.ract");
		assertTrue(plains.contains(path.getRealPath().getPlainName()));
		assertTrue(realPath.getBuriPathId() != 0);
		plains.remove(path.getRealPath().getPlainName());
		path = (BuriPath) ite.next();
		assertEquals(path.getPlainName(), "pkg.prces.act");
		// assertEquals(path.getRealPath().getPlainName(),"rpkg.rproces.ract2");
		assertTrue(plains.contains(path.getRealPath().getPlainName()));
		assertTrue(realPath.getBuriPathId() != 0);
		plains.remove(path.getRealPath().getPlainName());
		assertTrue(plains.size() == 0);
		path = new BuriPath("pkg.prces.act", "rpkg.rproces.ract");
		realPath = pathUtil.getBuriPathFromRealPath(path);
		assertEquals(fstID, realPath.getBuriPathId());
	}
}
