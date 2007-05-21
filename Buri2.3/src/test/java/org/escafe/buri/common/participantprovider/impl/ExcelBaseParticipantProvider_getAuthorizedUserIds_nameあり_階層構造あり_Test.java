package org.escafe.buri.common.participantprovider.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.escafe.buri.common.participantprovider.impl.ExcelBaseParticipantProvider;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.seasar.dao.unit.S2DaoTestCase;

/**
 * ExcelBaseParticipantProviderのテスト。
 */
public class ExcelBaseParticipantProvider_getAuthorizedUserIds_nameあり_階層構造あり_Test
        extends S2DaoTestCase {

    private ExcelBaseParticipantProvider participantProvider;
    private UserDto ああ部長;
    private UserDto いい課長;
    private UserDto うう君;
    private UserDto るるちゃん;
    private ParticipantContext context;

    @Override
    protected void setUp() throws Exception {
        include("ExcelBaseParticipantProviderTest_nameあり_階層構造あり.dicon");

        ああ部長 = new UserDto(1, "ああ部長＠開発部");
        いい課長 = new UserDto(2, "いい課長＠一課");
        うう君 = new UserDto(3, "うう君");
        るるちゃん = new UserDto(12, "るるちゃん");

        context = new ParticipantContext();
    }

    public void test直上の部長はだれか_部長本人の場合() throws Exception {
        context.setParticipantName("部長");
        context.setUserId(participantProvider.getUserId(ああ部長));
        List<IdentityInfo> ids = participantProvider.getAuthorizedUserIds(context);
        assertEquals(new IdentityInfo(1L, "ああ部長＠開発部"), ids.get(0));
        assertEquals(1, ids.size()); // 本人も含まれるように*修正*
        System.out.println(ids);
    }

    public void test直上の部長はだれか_課長の場合() throws Exception {
        context.setParticipantName("部長");
        context.setUserId(participantProvider.getUserId(いい課長));
        List<IdentityInfo> ids = participantProvider.getAuthorizedUserIds(context);
        assertEquals(new IdentityInfo(1L, "ああ部長＠開発部"), ids.get(0));
        assertEquals(1, ids.size());
        System.out.println(ids);
    }

    public void test直上の部長はだれか_平社員の場合() throws Exception {
        context.setParticipantName("部長");
        context.setUserId(participantProvider.getUserId(うう君));
        List<IdentityInfo> ids = participantProvider.getAuthorizedUserIds(context);
        Set<IdentityInfo> expected = new HashSet<IdentityInfo>();
        expected.add(new IdentityInfo(1L, "ああ部長＠開発部"));
        expected.add(new IdentityInfo(7L, "きき部長＠営業部"));
        assertTrue(expected.containsAll(ids));
        assertEquals(expected.size(), ids.size()); // 兼務の平社員の場合は両方の部長が結果として返る(順番は保証されない)
        System.out.println(ids);
    }

    public void test直上の部長はだれか_横一列に書いてもOK() throws Exception {
        context.setParticipantName("部長");
        context.setUserId(participantProvider.getUserId(るるちゃん));
        List<IdentityInfo> ids = participantProvider.getAuthorizedUserIds(context);
        assertEquals(new IdentityInfo(10L, "らら部長"), ids.get(0));
        assertEquals(1, ids.size());
        System.out.println(ids);
    }

}
