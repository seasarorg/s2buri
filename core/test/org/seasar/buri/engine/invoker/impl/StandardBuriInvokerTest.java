/*
 * �쐬��: 2005/08/26
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.Date;
import java.util.List;

import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.buri.engine.invoker.StandardBuriInvoker;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.log.Logger;


public class StandardBuriInvokerTest extends S2TestCase {
    private String PATH = "org/seasar/buri/engine/invoker/impl/StandardBuriInvoker.dicon";
    private static Logger logger = Logger.getLogger(StandardBuriInvokerTest.class);
    private BuriEngine buriEngine;
    private StandardBuriInvoker invoker_;
    private SimpleBuriInvoker simpleInvoker_;
    
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;
    
    private final String roleSato = "����,�ے�,�\����";
    private final String roleSuzuki = "���,�ے��㗝,�\����";
    private final String roleTakahashi = "����,�W��,�\����";
    private final String roleTanaka = "�c��,��C,�\����";
    private final String roleWatanabe = "�n��,��ʎЈ�,�\����";
    private final String roleIto = "�ɓ�,��ʎЈ�,�\����";
    private final String roleYamamoto = "�R�{,��ʎЈ�,�\����";
    
    public StandardBuriInvokerTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        ParticipantProvider provider = (ParticipantProvider)getComponent(ParticipantProvider.class);
        buriEngine = (BuriEngine)getComponent(BuriEngine.class);
//        buriEngine.getWorkflows().readWorkFlowFromResource("���Y�Ǘ�","petition.xpdl");
        buriEngine.getWorkflows().readWorkFlowFromResource("���Y�Ǘ�","petition.xpdl",provider);
        
    }
    
    public void testStandardTestTx() {
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // ��ʎЈ��F�\���葱���J�n
        logger.debug("******************************");
        logger.debug("invoke ���Y�Ǘ�.�w���\��");
        invoker_.invoke("���Y�Ǘ�.�w���\��",buyItemDto,roleYamamoto);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ��C�F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTanaka);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleTanaka);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // ��C�F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleTanaka);
        invoker_.invokeAction("���Y�Ǘ�.�w���\��",buyItemDto ,"approve",roleTanaka);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // ��C�F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTanaka);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleTanaka);
        assertEquals(datas.size(),0);

        // �W���F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTakahashi);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleTakahashi);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),1);


        // �W���F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleTakahashi);
        invoker_.invokeAction("���Y�Ǘ�.�w���\��",buyItemDto ,"approve",roleTakahashi);
        
        // �W���F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleTakahashi);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleTakahashi);
        assertEquals(datas.size(),0);
        
        // �ے��㗝�F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSuzuki);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleSuzuki);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �ے��F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSato);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleSato);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �ے��F���F
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\�� "+roleSato);
        invoker_.invokeAction("���Y�Ǘ�.�w���\��",buyItemDto ,"approve",roleSato);

        
        // �ے��㗝�F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSuzuki);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleSuzuki);
        assertEquals(datas.size(),0);
        
        // �ے��F���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂� "+roleSato);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleSato);
        assertEquals(datas.size(),0);
        

        // �\���ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\���� "+roleYamamoto);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),0);
   

        // ���F�ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.���F�ς� "+roleYamamoto);
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�ς�",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
    
    
    }

    public void testReturningTestTx() {
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // ��ʎЈ��F�\���葱���J�n
        logger.debug("******************************");
        logger.debug("invoke ���Y�Ǘ�.�w���\��");
        invoker_.invoke("���Y�Ǘ�.�w���\��",buyItemDto,roleYamamoto);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\����");
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ��C�F���F�҂�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.���F�҂�");
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.���F�҂�",roleTanaka);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ��C�F�����߂�
        logger.debug("******************************");
        logger.debug("invokeAction ���Y�Ǘ�.�w���\��");
        invoker_.invokeAction("���Y�Ǘ�.�w���\��",buyItemDto ,"returning",roleTanaka);
        
        // ��ʎЈ��F�����߂��ς�
        logger.debug("******************************");
        logger.debug("getDataListFromPath ���Y�Ǘ�.�w���\��.�����߂��ς�");
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�����߂��ς�",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // �\����
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.�\����");
        datas = invoker_.getDataListFromPath("���Y�Ǘ�.�w���\��.�\����",roleYamamoto);
        assertEquals(datas.size(),0);

    
    }
    
    
    
}
