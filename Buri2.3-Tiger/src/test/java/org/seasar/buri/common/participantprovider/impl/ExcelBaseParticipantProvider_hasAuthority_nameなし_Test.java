package org.seasar.buri.common.participantprovider.impl;

import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.dao.unit.S2DaoTestCase;

/**
 * ExcelBaseParticipantProviderのテスト。
 */
public class ExcelBaseParticipantProvider_hasAuthority_nameなし_Test extends S2DaoTestCase {

    private ExcelBaseParticipantProvider participantProvider;
    private UserDto ユーザ1;
    private UserDto ユーザ2;
    private UserDto ユーザ3;
    private UserDto ゲスト;
    private ParticipantContext context;

    @Override
    protected void setUp() throws Exception {
        include("ExcelBaseParticipantProviderTest_nameなし.dicon");

        ユーザ1 = new UserDto(1, "ユーザ1@ロールA");
        ユーザ2 = new UserDto(2, "ユーザ2@ロールB");
        ユーザ3 = new UserDto(3, "ユーザ3@ロールA");
        ゲスト = new UserDto(99, "ゲスト");

        context = new ParticipantContext();
    }

    public void testロールAが必要とされている場合() throws Exception {
        context.setParticipantName("ロールA");

        context.setCurrentUserId(participantProvider.getUserId(ユーザ1));
        assertTrue(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ユーザ2));
        assertFalse(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ユーザ3));
        assertTrue(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ゲスト));
        assertFalse(participantProvider.hasAuthority(context)); // Excel未登録のユーザの場合はfalse
    }

    public void testロールBが必要とされている場合() throws Exception {
        context.setParticipantName("ロールB");

        context.setCurrentUserId(participantProvider.getUserId(ユーザ1));
        assertFalse(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ユーザ2));
        assertTrue(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ユーザ3));
        assertFalse(participantProvider.hasAuthority(context));

        context.setCurrentUserId(participantProvider.getUserId(ゲスト));
        assertFalse(participantProvider.hasAuthority(context)); // Excel未登録のユーザの場合はfalse
    }

    public void test_nameを指定しないExcelを使うときはname指定を省略してOK() throws Exception {
        context.setParticipantName("ロールA");

        context.setCurrentUserId(new IdentityInfo(1L));
        assertTrue(participantProvider.hasAuthority(context));
    }
}
