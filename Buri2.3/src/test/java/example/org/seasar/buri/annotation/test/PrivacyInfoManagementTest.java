/*
 * 作成日: 2006/07/17
 *
 */
package example.org.seasar.buri.annotation.test;

import java.util.List;

import org.seasar.buri.annotation.bao.PrivacyInfoManagementBao;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriPathDataUserDao;
import org.seasar.buri.dao.BuriTestUserDao;
import org.seasar.buri.dao.BuriUserDao;
import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.extension.unit.S2TestCase;

import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dto.ItemDto;

public class PrivacyInfoManagementTest extends S2TestCase {
    private PrivacyInfoManagementBao bao;
    private ItemDao dao;
    private BuriTestUserDao userDao;
    private BuriTestUserDto userDto;
    private BuriPathDataUserDao dataUserDao;
    private BuriUserDao buriUserDao;
    
    public PrivacyInfoManagementTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("baoAnnotationTest.dicon");
    }
    
    public void test01Tx() {
        readXlsWriteDb("org/seasar/buri/engine/processor/impl/StdTestData.xls");
        ItemDto dto = dao.getItem(1);
        
        setupBuriTestUser(3);
        bao.indicationRequest(dto);
        
        List datas = bao.getWaitingIndicationRecognition();
        assertEquals(datas.size(),0);
        
        setupBuriTestUser(2);
        datas = bao.getWaitingIndicationRecognition();
        assertEquals(datas.size(),1);
        
        
        bao.waitingIndicationRecognition(dto);
        
        datas = bao.getWaitingIndicationRecognition();
        assertEquals(datas.size(),0);
        
        setupBuriTestUser(3);
        datas = bao.getIndicationRecognition();
        assertEquals(datas.size(),1);
        
        setupBuriTestUser(4);
        datas = bao.getIndicationRecognition();
        assertEquals(datas.size(),1);
        
    }
    
    private void setupBuriTestUser(long userID) {
        BuriTestUserDto testUserDto = userDao.getBuriTestUser((int)userID);
        userDto.setUserID(testUserDto.getUserID());
        userDto.setUserName(testUserDto.getUserName());
    }

}
