/*
 * çÏê¨ì˙: 2006/07/12
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.Iterator;
import java.util.List;

import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.RoleInfo;
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
        assertTrue(hasUserIdNum(userList,2));
        assertTrue(hasUserIdNum(userList,5));
        
        context = new ParticipantContext();
        context.setParticipantName("éxìX");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        userList = provider.getUser(context);
        assertEquals(userList.size(),3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,4));
        assertTrue(hasUserIdNum(userList,3));
        assertTrue(hasUserIdNum(userList,6));
        
        context = new ParticipantContext();
        context.setParticipantName("ñ{é–ïîèê");
        context.setActionUserIdNum(new Long(9));
        context.setActionUserIdVal("I");
        userList = provider.getUser(context);
        assertEquals(userList.size(),1);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,7));
    }
    
    
    public void test02() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test02.xls");
        Object userData = provider.getUserData(new Long(1),"A");
        assertNotNull(userData);
        
        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("éxé–");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        List userList = provider.getUser(context);
        System.out.println(userList);
        assertEquals(userList.size(),3);
        assertTrue(hasUserIdNum(userList,8));
        assertTrue(hasUserIdNum(userList,2));
        assertTrue(hasUserIdNum(userList,5));
        
        context = new ParticipantContext();
        context.setParticipantName("éxìX");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        userList = provider.getUser(context);
        assertEquals(userList.size(),6);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,10));
        assertTrue(hasUserIdNum(userList,9));
        assertTrue(hasUserIdNum(userList,11));
        assertTrue(hasUserIdNum(userList,4));
        assertTrue(hasUserIdNum(userList,3));
        assertTrue(hasUserIdNum(userList,6));
        
        context = new ParticipantContext();
        context.setParticipantName("ñ{é–ïîèê");
        context.setActionUserIdNum(new Long(9));
        context.setActionUserIdVal("I");
        userList = provider.getUser(context);
        assertEquals(userList.size(),2);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,7));
        assertTrue(hasUserIdNum(userList,1));
    }
    
    public void test03() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test03.xls");
        
        Object userData = provider.getUserData(new Long(1),null);
        assertNotNull(userData);
        Long id = provider.getUserIDNum(userData,"");
        assertNotNull(id);
        assertEquals(id,new Long(1));
        String val = provider.getUserIDString(userData,"");
        assertNull(val);
        
        ParticipantContext context = new ParticipantContext();
        context.setParticipantName("éxé–");
        context.setActionUserIdNum(new Long(1));
        List userList = provider.getUser(context);
        assertEquals(userList.size(),2);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,2));
        assertTrue(hasUserIdNum(userList,5));
        
        context = new ParticipantContext();
        context.setParticipantName("éxìX");
        context.setActionUserIdNum(new Long(1));
        userList = provider.getUser(context);
        assertEquals(userList.size(),3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,4));
        assertTrue(hasUserIdNum(userList,3));
        assertTrue(hasUserIdNum(userList,6));
        
        context = new ParticipantContext();
        context.setParticipantName("ñ{é–ïîèê");
        context.setActionUserIdNum(new Long(9));
        userList = provider.getUser(context);
        assertEquals(userList.size(),1);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,7));
        
    }
    public void test04() {
        provider.loadFromResource("ExcelBaseParticipantProvider/test04.xls");
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
        assertEquals(userList.size(),1);
        assertTrue(hasUserIdNum(userList,5));
        
        context = new ParticipantContext();
        context.setParticipantName("éxìX");
        context.setActionUserIdNum(new Long(1));
        context.setActionUserIdVal("A");
        userList = provider.getUser(context);
        assertEquals(userList.size(),3);
        System.out.println(userList);
        assertTrue(hasUserIdNum(userList,4));
        assertTrue(hasUserIdNum(userList,3));
        assertTrue(hasUserIdNum(userList,6));
        
    }
    
    
    
    private boolean hasUserIdNum(List userList,long idNum) {
        Iterator ite = userList.iterator();
        while(ite.hasNext()) {
            RoleInfo itemDto = (RoleInfo)ite.next();
            if(itemDto.getIdNum().longValue() == idNum) {
                return true;
            }
        }
        return false;
    }
    
}
