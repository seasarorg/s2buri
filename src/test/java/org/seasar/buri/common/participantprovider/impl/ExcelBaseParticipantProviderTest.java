/*
 * çÏê¨ì˙: 2006/07/12
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.List;

import org.seasar.buri.engine.ParticipantContext;
import org.seasar.extension.unit.S2TestCase;

public class ExcelBaseParticipantProviderTest extends S2TestCase {
    private ExcelBaseParticipantProvider provider;
    
    public ExcelBaseParticipantProviderTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("ExcelBaseParticipantProvider/ExcelBase.dicon");
    }

    public void test01() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test01.xls");
        Object userData = provider.getUserData(new Long(1),"A");
        assertNotNull(userData);
        Long id = provider.getUserIDNum(userData,"");
        assertNotNull(id);
        assertEquals(id,new Long(1));
        String val = provider.getUserIDString(userData,"");
        assertNotNull(val);
        assertEquals(val,"A");
        
        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("éxé–");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        List userList = provider.getUser(context);
        assertEquals(userList.size(),2);
        assertEquals(userList.toString(),"[[idVar=B/idNum=2], [idVar=E/idNum=5]]");
        
        context = new ParticipantContext();
        context.setParticipantName("éxìX");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        userList = provider.getUser(context);
        assertEquals(userList.size(),3);
        System.out.println(userList);
        assertEquals(userList.toString(),"[[idVar=D/idNum=4], [idVar=C/idNum=3], [idVar=F/idNum=6]]");
        
        context = new ParticipantContext();
        context.setParticipantName("ñ{é–ïîèê");
        context.setActionUserIdNum(new Long(9));
        context.setActionUserIdVal("I");
        userList = provider.getUser(context);
        assertEquals(userList.size(),1);
        System.out.println(userList);
        assertEquals(userList.toString(),"[[idVar=G/idNum=7]]");
    }
    
}
