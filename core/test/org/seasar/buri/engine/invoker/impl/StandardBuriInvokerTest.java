/*
 * ì¬“ú: 2005/08/26
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
    
    private final String roleSato = "²“¡,‰Û’·,\¿Ò";
    private final String roleSuzuki = "—é–Ø,‰Û’·‘ã—,\¿Ò";
    private final String roleTakahashi = "‚‹´,ŒW’·,\¿Ò";
    private final String roleTanaka = "“c’†,å”C,\¿Ò";
    private final String roleWatanabe = "“n•Ó,ˆê”ÊĞˆõ,\¿Ò";
    private final String roleIto = "ˆÉ“¡,ˆê”ÊĞˆõ,\¿Ò";
    private final String roleYamamoto = "R–{,ˆê”ÊĞˆõ,\¿Ò";
    
    public StandardBuriInvokerTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        ParticipantProvider provider = (ParticipantProvider)getComponent(ParticipantProvider.class);
        buriEngine = (BuriEngine)getComponent(BuriEngine.class);
//        buriEngine.getWorkflows().readWorkFlowFromResource("‘YŠÇ—","petition.xpdl");
        buriEngine.getWorkflows().readWorkFlowFromResource("‘YŠÇ—","petition.xpdl",provider);
        
    }
    
    public void testStandardTestTx() {
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        List datas;
        
        // ˆê”ÊĞˆõF\¿è‘±‚«ŠJn
        logger.debug("******************************");
        logger.debug("invoke ‘YŠÇ—.w“ü\¿");
        invoker_.invoke("‘YŠÇ—.w“ü\¿",buyItemDto,roleYamamoto);
        
        // \¿’†
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’† "+roleYamamoto);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // å”CF³”F‘Ò‚¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleTanaka);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleTanaka);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // å”CF³”F
        logger.debug("******************************");
        logger.debug("invokeAction ‘YŠÇ—.w“ü\¿ "+roleTanaka);
        invoker_.invokeAction("‘YŠÇ—.w“ü\¿",buyItemDto ,"approve",roleTanaka);
        
        logger.debug(pathDao_.getAllBuriPath());
        logger.debug(stateDao_.getAllBuriState());
        logger.debug(stateDao_.getNoProcessBuriState());
        
        // å”CF³”FÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleTanaka);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleTanaka);
        assertEquals(datas.size(),0);

        // ŒW’·F³”F‘Ò‚¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleTakahashi);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleTakahashi);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // \¿’†
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’† "+roleYamamoto);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),1);


        // ŒW’·F³”F
        logger.debug("******************************");
        logger.debug("invokeAction ‘YŠÇ—.w“ü\¿ "+roleTakahashi);
        invoker_.invokeAction("‘YŠÇ—.w“ü\¿",buyItemDto ,"approve",roleTakahashi);
        
        // ŒW’·F³”FÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleTakahashi);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleTakahashi);
        assertEquals(datas.size(),0);
        
        // ‰Û’·‘ã—F³”F‘Ò‚¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleSuzuki);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleSuzuki);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ‰Û’·F³”F‘Ò‚¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleSato);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleSato);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // \¿’†
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’† "+roleYamamoto);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // ‰Û’·F³”F
        logger.debug("******************************");
        logger.debug("invokeAction ‘YŠÇ—.w“ü\¿ "+roleSato);
        invoker_.invokeAction("‘YŠÇ—.w“ü\¿",buyItemDto ,"approve",roleSato);

        
        // ‰Û’·‘ã—F³”FÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleSuzuki);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleSuzuki);
        assertEquals(datas.size(),0);
        
        // ‰Û’·F³”FÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿ "+roleSato);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleSato);
        assertEquals(datas.size(),0);
        

        // \¿Ï‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’† "+roleYamamoto);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),0);
   

        // ³”FÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.³”FÏ‚İ "+roleYamamoto);
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”FÏ‚İ",roleYamamoto);
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
        
        // ˆê”ÊĞˆõF\¿è‘±‚«ŠJn
        logger.debug("******************************");
        logger.debug("invoke ‘YŠÇ—.w“ü\¿");
        invoker_.invoke("‘YŠÇ—.w“ü\¿",buyItemDto,roleYamamoto);
        
        // \¿’†
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’†");
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // å”CF³”F‘Ò‚¿
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿");
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.³”F‘Ò‚¿",roleTanaka);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // å”CF·‚µ–ß‚µ
        logger.debug("******************************");
        logger.debug("invokeAction ‘YŠÇ—.w“ü\¿");
        invoker_.invokeAction("‘YŠÇ—.w“ü\¿",buyItemDto ,"returning",roleTanaka);
        
        // ˆê”ÊĞˆõF·‚µ–ß‚µÏ‚İ
        logger.debug("******************************");
        logger.debug("getDataListFromPath ‘YŠÇ—.w“ü\¿.·‚µ–ß‚µÏ‚İ");
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.·‚µ–ß‚µÏ‚İ",roleYamamoto);
        assertEquals(datas.size(),1);
        logger.debug(datas);
        
        // \¿’†
        logger.debug("******************************");
        logger.debug("getDataListFromPathAndUser ‘YŠÇ—.w“ü\¿.\¿’†");
        datas = invoker_.getDataListFromPath("‘YŠÇ—.w“ü\¿.\¿’†",roleYamamoto);
        assertEquals(datas.size(),0);

    
    }
    
    
    
}
