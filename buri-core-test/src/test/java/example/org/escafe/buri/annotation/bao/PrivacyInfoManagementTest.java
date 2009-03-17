/*
 * 作成日: 2006/07/17
 *
 */
package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.bao.PrivacyInfoManagementBao;
import org.escafe.buri.entity.BuriTestUser;
import org.escafe.buri.service.BuriTestUserService;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.entity.Item;
import example.org.escafe.buri.service.ItemService;

public class PrivacyInfoManagementTest extends S2TestCase {
	private PrivacyInfoManagementBao bao;

	private ItemService itemService;

	private BuriTestUserService buriTestUserService;

	private BuriTestUser buriTestUser;

	public PrivacyInfoManagementTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("baoAnnotationTest.dicon");
	}

	public void test01Tx() {
		readXlsWriteDb("org/escafe/buri/engine/processor/impl/StdTestData.xls");
		Item dto = itemService.findById(1L);
		setupBuriTestUser(3L);
		bao.indicationRequest(dto);
		List<Item> datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 0);
		setupBuriTestUser(2L);
		datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 1);
		bao.waitingIndicationRecognition(dto);
		datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 0);
		setupBuriTestUser(3L);
		datas = bao.getIndicationRecognition();
		assertEquals(datas.size(), 1);
		setupBuriTestUser(4L);
		datas = bao.getIndicationRecognition();
		assertEquals(datas.size(), 1);
	}

	private void setupBuriTestUser(Long userId) {
		BuriTestUser buriTestUser = buriTestUserService.getBuriTestUser(userId);
		this.buriTestUser.userId = buriTestUser.userId;
		this.buriTestUser.userName = buriTestUser.userName;
	}
}
