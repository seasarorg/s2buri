/*
 * 作成日: 2006/07/17
 *
 */
package example.org.escafe.buri.test;

import java.util.List;

import org.escafe.buri.bao.PrivacyInfoManagementBao;
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
		include("baotest.dicon");
	}

	public void test01Tx() {
		readXlsWriteDb("org/escafe/buri/engine/processor/impl/StdTestData.xls");
		Item dto = itemService.findById(1L);
		setupBuriTestUser(3);
		bao.indicationRequest(dto);
		List<Item> datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 0);
		setupBuriTestUser(2);
		datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 1);
		bao.waitingIndicationRecognition(dto);
		datas = bao.getWaitingIndicationRecognition();
		assertEquals(datas.size(), 0);
		setupBuriTestUser(3);
		datas = bao.getIndicationRecognition();
		assertEquals(datas.size(), 1);
		setupBuriTestUser(4);
		datas = bao.getIndicationRecognition();
		assertEquals(datas.size(), 1);
	}

	private void setupBuriTestUser(long userID) {
		BuriTestUser testUserDto = buriTestUserService.getBuriTestUser(userID);
		buriTestUser.userId = testUserDto.userId;
		buriTestUser.userName = testUserDto.userName;
	}
}
