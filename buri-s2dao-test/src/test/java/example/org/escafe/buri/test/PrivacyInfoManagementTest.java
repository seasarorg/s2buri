/*
 * 作成日: 2006/07/17
 *
 */
package example.org.escafe.buri.test;

import java.util.List;

import org.escafe.buri.dto.BuriTestUserDto;
import org.escafe.buri.s2dao.bao.PrivacyInfoManagementBao;
import org.escafe.buri.s2dao.dao.BuriTestUserDao;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.dao.ItemDao;
import example.org.escafe.buri.dto.ItemDto;

public class PrivacyInfoManagementTest extends S2TestCase {
	private PrivacyInfoManagementBao bao;

	private ItemDao dao;

	private BuriTestUserDao userDao;

	private BuriTestUserDto userDto;

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
		ItemDto dto = dao.getItem(1L);
		setupBuriTestUser(3L);
		bao.indicationRequest(dto);
		List datas = bao.getWaitingIndicationRecognition();
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
		BuriTestUserDto testUserDto = userDao.getBuriTestUser(userId);
		userDto.setUserId(testUserDto.getUserId());
		userDto.setUserName(testUserDto.getUserName());
	}
}
