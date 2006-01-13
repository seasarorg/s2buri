/*
 * ì¬ú: 2006/01/04
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

    private final String roleSato = "²¡,Û·,\¿Ò";
    private final String roleSuzuki = "éØ,Û·ã,\¿Ò";
    private final String roleTakahashi = "´,W·,\¿Ò";
    private final String roleTanaka = "c,åC,\¿Ò";
    private final String roleWatanabe = "nÓ,êÊÐõ,\¿Ò";
    private final String roleIto = "É¡,êÊÐõ,\¿Ò";
    private final String roleYamamoto = "R{,êÊÐõ,\¿Ò";

    public PetitionUserBaoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("org/seasar/buri/bao/test/dicon/PetitionUser.dicon");
    }
    
    public void testFMBaoTx() {
        // {Íburi2.diconÉ­àÌ
        ParticipantProvider provider = (ParticipantProvider)getComponent(ParticipantProvider.class);
        buriEngine_.getWorkflows().readWorkFlowFromResource("YÇ","petition.xpdl",provider);
        UserData userData = (UserData)getComponent("user");
        
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // êÊÐõF\¿è±«Jn
        logger.debug("******************************");
        logger.debug("invoke YÇ.wü\¿");
        userData.setUser(roleYamamoto);
        petitionBao_.petition(buyItemDto);
        
        // \¿
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser YÇ.wü\¿.\¿ "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // åCF³FÒ¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleTanaka);
        userData.setUser(roleTanaka);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // åCF³F
        logger.debug("******************************");
        logger.debug("invokeAction YÇ.wü\¿ "+roleTanaka);
        userData.setUser(roleTanaka);
        petitionBao_.approve(buyItemDto);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // åCF³FÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleTanaka);
        userData.setUser(roleTanaka);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);

        // W·F³FÒ¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleTakahashi);
        userData.setUser(roleTakahashi);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // \¿
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser YÇ.wü\¿.\¿ "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);


        // W·F³F
        logger.debug("******************************");
        logger.debug("invokeAction YÇ.wü\¿ "+roleTakahashi);
        userData.setUser(roleTakahashi);
        petitionBao_.approve(buyItemDto);
        
        // W·F³FÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleTakahashi);
        userData.setUser(roleTakahashi);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        
        // Û·ãF³FÒ¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleSuzuki);
        userData.setUser(roleSuzuki);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // Û·F³FÒ¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleSato);
        userData.setUser(roleSato);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // \¿
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser YÇ.wü\¿.\¿ "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // Û·F³F
        logger.debug("******************************");
        logger.debug("invokeAction YÇ.wü\¿ "+roleSato);
        userData.setUser(roleSato);
        petitionBao_.approve(buyItemDto);

        
        // Û·ãF³FÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleSuzuki);
        userData.setUser(roleSuzuki);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        
        // Û·F³FÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPath YÇ.wü\¿.³FÒ¿ "+roleSato);
        userData.setUser(roleSato);
        datas = petitionBao_.getWaitApprove();
        assertEquals(datas.size(),0);
        

        // \¿ÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser YÇ.wü\¿.\¿ "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getNowPetition();
        assertEquals(datas.size(),0);
   

        // ³FÏÝ
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser YÇ.wü\¿.³FÏÝ "+roleYamamoto);
        userData.setUser(roleYamamoto);
        datas = petitionBao_.getEndApprove();
        assertEquals(datas.size(),1);
        logger.debug(datas);
    
        
    }

}
