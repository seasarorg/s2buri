package org.escafe.buri.common.participantprovider.impl;

import java.util.Map;

import org.escafe.buri.engine.IdentityInfo;
import org.seasar.extension.unit.S2TestCase;

/**
 * ExcelBaseParticipantProviderのテスト。
 */
public class ExcelBaseParticipantProvider_getUserXxx_Test extends S2TestCase {
	private ExcelBaseParticipantProvider participantProvider;

	private UserDto ロールA;

	@Override
	protected void setUp() throws Exception {
		include("ExcelBaseParticipantProviderTest_nameあり.dicon"); // nameあり・なしに無関係
		ロールA = new UserDto(1, "ユーザ@ロールA");
	}

	public void testGetUserId_数字と文字列両方あり() throws Exception {
		IdentityInfo userId = participantProvider.getUserId(ロールA);
		assertEquals(new Long(1), userId.getIdNumber());
		assertEquals("ユーザ@ロールA", userId.getIdString());
		System.out.println(userId);
	}

	public void testGetUserId_数字のみ() throws Exception {
		ロールA.setLoginName(null);
		IdentityInfo userId = participantProvider.getUserId(ロールA);
		assertEquals(new Long(1), userId.getIdNumber());
		assertNull(userId.getIdString());
		System.out.println(userId);
	}

	public void testGetUserData() throws Exception {
		// MEMO: 本来はUserDto型のインスタンスが返るようにしなければならないが、
		// ここではDaoを用意しなくても簡単にOGNLで生成できて確認できるようにMapを使った。
		Object userDto =
		    participantProvider.getUserData(new IdentityInfo(
		        new Long(1),
		        "ユーザ@ロールA"));
		assertNotNull(userDto);
		assertEquals(new Long(1), ((Map) userDto).get("userIdNumber"));
		assertEquals("ユーザ@ロールA", ((Map) userDto).get("userIdString"));
	}
}
