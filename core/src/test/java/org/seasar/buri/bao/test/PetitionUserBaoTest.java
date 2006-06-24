/*
 * �쐬��: 2006/01/04
 *
 */
package org.seasar.buri.bao.test;

import java.util.Date;
import java.util.List;

import org.seasar.buri.bao.PetitionUserBao;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.invoker.impl.StandardBuriInvokerTest;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.log.Logger;

public class PetitionUserBaoTest extends S2TestCase {
    private static Logger logger = Logger.getLogger(StandardBuriInvokerTest.class);
    private BuriEngine buriEngine_;
    private PetitionUserBao petitionBao_;
    
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;

    private final String roleSato = "����,�ے�,�\����";
    private final String roleSuzuki = "���,�ے��㗝,�\����";
    private final String roleTakahashi = "����,�W��,�\����";
    private final String roleTanaka = "�c��,��C,�\����";
    private final String roleWatanabe = "�n��,��ʎЈ�,�\����";
    private final String roleIto = "�ɓ�,��ʎЈ�,�\����";
    private final String roleYamamoto = "�R�{,��ʎЈ�,�\����";

    public PetitionUserBaoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("org/seasar/buri/bao/test/dicon/PetitionUser.dicon");
    }
    
    public void testFMBaoTx() {
        // �{����buri2.dicon�ɏ�������
        ParticipantProvider provider = (ParticipantProvider)getComponent(ParticipantProvider.class);
        buriEngine_.getWorkflows().readWorkFlowFromResource("���Y�Ǘ�","petition.xpdl",provider);
        UserData userData = (UserData)getComponent("user");
        
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // ��ʎЈ��F�\���葱���J�n
        logger.debug("******************************");
        logger.debug("invoke ���Y�Ǘ�.�w���\��");
        userData.setUser(roleYamamoto);
        petitionBao_.petition(buyItemDto);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ��C�F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTanaka);
        userData.setUser(roleTanaka);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // ��C�F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleTanaka);
        userData.setUser(roleTanaka);
        petitionBao_.approve(buyItemDto);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // ��C�F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTanaka);
        userData.setUser(roleTanaka);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);

        // �W���F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTakahashi);
        userData.setUser(roleTakahashi);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);


        // �W���F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleTakahashi);
        userData.setUser(roleTakahashi);
        petitionBao_.approve(buyItemDto);
        
        // �W���F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTakahashi);
        userData.setUser(roleTakahashi);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        
        // �ے��㗝�F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSuzuki);
        userData.setUser(roleSuzuki);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �ے��F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSato);
        userData.setUser(roleSato);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �ے��F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleSato);
        userData.setUser(roleSato);
        petitionBao_.approve(buyItemDto);

        
        // �ے��㗝�F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSuzuki);
        userData.setUser(roleSuzuki);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        
        // �ے��F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSato);
        userData.setUser(roleSato);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        

        // �\���ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),0);
   

        // ���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.���F�ς� "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getEndApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
    
        
    }

}
