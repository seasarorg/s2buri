package org.escafe.buri.common.participantprovider.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.seasar.extension.unit.S2TestCase;

/**
 * ExcelBaseParticipantProviderのテスト。
 */
public class ExcelBaseParticipantProvider_getAuthorizedUserIds_nameなし_Test
        extends S2TestCase {
	private ExcelBaseParticipantProvider participantProvider;

	private UserDto ユーザ1;

	private UserDto ユーザ2;

	private UserDto ユーザ3;

	private UserDto ユーザ4;

	private UserDto ユーザ5;

	private UserDto ゲスト;

	private ParticipantContext context;

	@Override
	protected void setUp() throws Exception {
		include("ExcelBaseParticipantProviderTest_nameなし.dicon");
		ユーザ1 = new UserDto(1, "ユーザ1@ロールA");
		ユーザ2 = new UserDto(2, "ユーザ2@ロールB");
		ユーザ3 = new UserDto(3, "ユーザ3@ロールA");
		ユーザ4 = new UserDto(4, "ユーザ4@ロールB");
		ユーザ5 = new UserDto(5, "ユーザ5@ロールB");
		ゲスト = new UserDto(99, "ゲスト");
		context = new ParticipantContext();
	}

	public void testGetAuthorizedUserIds_ユーザ1_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ユーザ1));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(1L), ids.get(0));
		assertEquals(1, ids.size()); // 本人も含まれる
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ2_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ユーザ2));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(1L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ3_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ユーザ3));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(3L), ids.get(0));
		assertEquals(1, ids.size()); // 本人も含まれる
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ4_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ユーザ4));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(3L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ5_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ユーザ5));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(3L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ゲスト_ロールA() throws Exception {
		context.setParticipantName("ロールA");
		context.setUserId(participantProvider.getUserId(ゲスト));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(0, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ1_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ユーザ1));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(2L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ2_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ユーザ2));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(2L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ3_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ユーザ3));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		Set<IdentityInfo> expected = new HashSet<IdentityInfo>();
		expected.add(new IdentityInfo(4L));
		expected.add(new IdentityInfo(5L));
		for (IdentityInfo id : ids) {
			assertTrue(expected.contains(id));
		}
		assertEquals(2, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ4_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ユーザ4));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(4L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ユーザ5_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ユーザ5));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(new IdentityInfo(5L), ids.get(0));
		assertEquals(1, ids.size());
		System.out.println(ids);
	}

	public void testGetAuthorizedUserIds_ゲスト_ロールB() throws Exception {
		context.setParticipantName("ロールB");
		context.setUserId(participantProvider.getUserId(ゲスト));
		List<IdentityInfo> ids =
		    participantProvider.getAuthorizedUserIds(context);
		assertEquals(0, ids.size());
		System.out.println(ids);
	}
}
