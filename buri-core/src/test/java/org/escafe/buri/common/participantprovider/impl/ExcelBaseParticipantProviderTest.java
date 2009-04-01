/*
 * 作成日: 2006/07/12
 *
 */
package org.escafe.buri.common.participantprovider.impl;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.participantprovider.impl.ExcelBaseParticipantProvider;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.seasar.extension.unit.S2TestCase;

public class ExcelBaseParticipantProviderTest extends S2TestCase {
    private ExcelBaseParticipantProvider provider;

    public ExcelBaseParticipantProviderTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("ExcelBaseParticipantProvider/ExcelBase.dicon");
    }

    public void test01Tx() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test01.xls");
        Object userData = provider.getUserData(new IdentityInfo(new Long(1), "A"));
        assertNotNull(userData);
        IdentityInfo appUserId = provider.getUserId(userData);
        assertEquals(new Long(1), appUserId.getIdNumber());
        assertEquals("A", appUserId.getIdString());

        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("支社");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        List userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 2);
        assertTrue(hasUserIdNum(userList, 2));
        assertTrue(hasUserIdNum(userList, 5));

        context = new ParticipantContext();
        context.setParticipantName("支店");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 4));
        assertTrue(hasUserIdNum(userList, 3));
        assertTrue(hasUserIdNum(userList, 6));

        context = new ParticipantContext();
        context.setParticipantName("本社部署");
        context.setUserId(new IdentityInfo(new Long(9), "I"));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 1);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 7));
    }

    public void test02Tx() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test02.xls");
        Object userData = provider.getUserData(new IdentityInfo(new Long(1), "A"));
        assertNotNull(userData);

        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("支社");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        List userList = provider.getAuthorizedUserIds(context);
        System.out.println(userList);
        assertEquals(userList.size(), 3);
        assertTrue(hasUserIdNum(userList, 8));
        assertTrue(hasUserIdNum(userList, 2));
        assertTrue(hasUserIdNum(userList, 5));

        context = new ParticipantContext();
        context.setParticipantName("支店");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 6);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 10));
        assertTrue(hasUserIdNum(userList, 9));
        assertTrue(hasUserIdNum(userList, 11));
        assertTrue(hasUserIdNum(userList, 4));
        assertTrue(hasUserIdNum(userList, 3));
        assertTrue(hasUserIdNum(userList, 6));

        context = new ParticipantContext();
        context.setParticipantName("本社部署");
        context.setUserId(new IdentityInfo(new Long(9), "I"));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 2);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 7));
        assertTrue(hasUserIdNum(userList, 1));
    }

    public void test03Tx() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test03.xls");

        Object userData = provider.getUserData(new IdentityInfo(new Long(1)));
        assertNotNull(userData);
        IdentityInfo appUserId = provider.getUserId(userData);
        assertEquals(new Long(1), appUserId.getIdNumber());
        assertNull(appUserId.getIdString());

        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("支社");
        context.setUserId(new IdentityInfo(new Long(1)));
        List userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 2);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 2));
        assertTrue(hasUserIdNum(userList, 5));

        context = new ParticipantContext();
        context.setParticipantName("支店");
        context.setUserId(new IdentityInfo(new Long(1)));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 4));
        assertTrue(hasUserIdNum(userList, 3));
        assertTrue(hasUserIdNum(userList, 6));

        context = new ParticipantContext();
        context.setParticipantName("本社部署");
        context.setUserId(new IdentityInfo(new Long(9)));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 1);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 7));

    }

    public void test04Tx() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test04.xls");
        Object userData = provider.getUserData(new IdentityInfo(new Long(1), "A"));
        assertNotNull(userData);
        IdentityInfo appUserId = provider.getUserId(userData);
        assertEquals(new Long(1), appUserId.getIdNumber());
        assertEquals("A", appUserId.getIdString());

        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("支社");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        List userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 1);
        assertTrue(hasUserIdNum(userList, 5));

        context = new ParticipantContext();
        context.setParticipantName("支店");
        context.setUserId(new IdentityInfo(new Long(1), "A"));
        userList = provider.getAuthorizedUserIds(context);
        assertEquals(userList.size(), 3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList, 4));
        assertTrue(hasUserIdNum(userList, 3));
        assertTrue(hasUserIdNum(userList, 6));

    }

    private boolean hasUserIdNum(List userList, long idNum) {
        Iterator ite = userList.iterator();
        while (ite.hasNext()) {
            IdentityInfo itemDto = (IdentityInfo) ite.next();
            if (itemDto.getIdNumber().longValue() == idNum) {
                return true;
            }
        }
        return false;
    }

}
