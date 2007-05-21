package org.escafe.buri.common.participantprovider.impl;

import org.escafe.buri.common.participantprovider.impl.ExcelBaseParticipantProvider;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.seasar.dao.unit.S2DaoTestCase;

/**
 * ExcelBaseParticipantProviderのテスト。
 */
public class ExcelBaseParticipantProvider_hasAuthority_nameあり_Test extends S2DaoTestCase {

    private ExcelBaseParticipantProvider participantProvider;
    private UserDto ユーザ1;
    private UserDto ユーザ2;
    private UserDto ユーザ3;
    private UserDto ゲスト;
    private ParticipantContext context;

    @Override
    protected void setUp() throws Exception {
        include("ExcelBaseParticipantProviderTest_nameあり.dicon");

        ユーザ1 = new UserDto(1, "ユーザ1@ロールA");
        ユーザ2 = new UserDto(2, "ユーザ2@ロールB");
        ユーザ3 = new UserDto(3, "ユーザ3@ロールA");
        ゲスト = new UserDto(99, "ゲスト");

        context = new ParticipantContext();
    }

    public void testロールAが必要とされている場合() throws Exception {
        context.setParticipantName("ロールA");

        context.setUserId(participantProvider.getUserId(ユーザ1));
        assertTrue(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ユーザ2));
        assertFalse(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ユーザ3));
        assertTrue(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ゲスト));
        assertFalse(participantProvider.hasAuthority(context)); // Excel未登録のユーザの場合はfalse
    }

    public void testロールBが必要とされている場合() throws Exception {
        context.setParticipantName("ロールB");

        context.setUserId(participantProvider.getUserId(ユーザ1));
        assertFalse(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ユーザ2));
        assertTrue(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ユーザ3));
        assertFalse(participantProvider.hasAuthority(context));

        context.setUserId(participantProvider.getUserId(ゲスト));
        assertFalse(participantProvider.hasAuthority(context)); // Excel未登録のユーザの場合はfalse
    }

    public void test_nameを指定したExcelを使うときはname指定を省略するとNG() throws Exception {
        context.setParticipantName("ロールA");

        context.setUserId(new IdentityInfo(1L));
        assertFalse(participantProvider.hasAuthority(context));
    }

    public void test_nameを指定したExcelを使うときはIDNumberとIDStringが矛盾しているとNG() throws Exception {
        context.setParticipantName("ロールA");

        context.setUserId(new IdentityInfo(1L, "ユーザ2@ロールB"));
        assertFalse(participantProvider.hasAuthority(context));
    }

}
