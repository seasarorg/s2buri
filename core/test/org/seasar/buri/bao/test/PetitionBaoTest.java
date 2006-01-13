/*
 * 作成日: 2006/01/04
 *
 */
package org.seasar.buri.bao.test;

import java.util.Date;
import java.util.List;

import org.seasar.buri.bao.PetitionBao;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.invoker.impl.StandardBuriInvokerTest;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.log.Logger;

public class PetitionBaoTest extends S2TestCase {
    private static Logger logger = Logger.getLogger(StandardBuriInvokerTest.class);
    private BuriEngine buriEngine_;
    private PetitionBao petitionBao_;
    
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;

    private final String roleSato = "佐藤,課長,申請者";
    private final String roleSuzuki = "鈴木,課長代理,申請者";
    private final String roleTakahashi = "高橋,係長,申請者";
    private final String roleTanaka = "田中,主任,申請者";
    private final String roleWatanabe = "渡辺,一般社員,申請者";
    private final String roleIto = "伊藤,一般社員,申請者";
    private final String roleYamamoto = "山本,一般社員,申請者";

    public PetitionBaoTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("org/seasar/buri/bao/test/dicon/Petition.dicon");
    }
    
    public void testFMBaoTx() {
        // 本当はburi2.diconに書くもの
        ParticipantProvider provider = (ParticipantProvider)getComponent(ParticipantProvider.class);
        buriEngine_.getWorkflows().readWorkFlowFromResource("資産管理","petition.xpdl",provider);
        
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // 一般社員：申請手続き開始
        logger.debug("******************************");
        logger.debug("invoke 資産管理.購入申請");
        petitionBao_.petition(buyItemDto,roleYamamoto);
        
        // 申請中
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser 資産管理.購入申請.申請中 "+roleYamamoto);
        datas = petitionBao_.getNowPetition(roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // 主任：承認待ち
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleTanaka);
        datas = petitionBao_.getWaitApprove(roleTanaka);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // 主任：承認
        logger.debug("******************************");
        logger.debug("invokeAction 資産管理.購入申請 "+roleTanaka);
        petitionBao_.approve(buyItemDto,roleTanaka);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // 主任：承認済み
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleTanaka);
        datas = petitionBao_.getWaitApprove(roleTanaka);
        assertEquals(datas.size(),0);

        // 係長：承認待ち
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleTakahashi);
        datas = petitionBao_.getWaitApprove(roleTakahashi);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // 申請中
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser 資産管理.購入申請.申請中 "+roleYamamoto);
        datas = petitionBao_.getNowPetition(roleYamamoto);
        assertEquals(datas.size(),1);


        // 係長：承認
        logger.debug("******************************");
        logger.debug("invokeAction 資産管理.購入申請 "+roleTakahashi);
        petitionBao_.approve(buyItemDto,roleTakahashi);
        
        // 係長：承認済み
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleTakahashi);
        datas = petitionBao_.getWaitApprove(roleTakahashi);
        assertEquals(datas.size(),0);
        
        // 課長代理：承認待ち
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleSuzuki);
        datas = petitionBao_.getWaitApprove(roleSuzuki);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // 課長：承認待ち
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleSato);
        datas = petitionBao_.getWaitApprove(roleSato);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // 申請中
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser 資産管理.購入申請.申請中 "+roleYamamoto);
        datas = petitionBao_.getNowPetition(roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // 課長：承認
        logger.debug("******************************");
        logger.debug("invokeAction 資産管理.購入申請 "+roleSato);
        petitionBao_.approve(buyItemDto,roleSato);

        
        // 課長代理：承認済み
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleSuzuki);
        datas = petitionBao_.getWaitApprove(roleSuzuki);
        assertEquals(datas.size(),0);
        
        // 課長：承認済み
        logger.debug("******************************");
        logger.debug("getDataListFromPath 資産管理.購入申請.承認待ち "+roleSato);
        datas = petitionBao_.getWaitApprove(roleSato);
        assertEquals(datas.size(),0);
        

        // 申請済み
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser 資産管理.購入申請.申請中 "+roleYamamoto);
        datas = petitionBao_.getNowPetition(roleYamamoto);
        assertEquals(datas.size(),0);
   

        // 承認済み
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser 資産管理.購入申請.承認済み "+roleYamamoto);
        datas = petitionBao_.getEndApprove(roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
    
        
    }

}
