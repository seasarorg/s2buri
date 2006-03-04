/*
 * çÏê¨ì˙: 2005/09/16
 *
 */
package org.seasar.buri.component.impl;

import java.util.Date;

import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.extension.unit.S2TestCase;

import com.ozacc.mail.MailSendException;


public class MailSenderImplTest extends S2TestCase {
    private String PATH = "org/seasar/buri/component/impl/mailSender.dicon";
    private SimpleBuriInvoker invoker_;
    private BuriEngine buriEngine_;

    public MailSenderImplTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testSendMailTx() {
        buriEngine_.getWorkflows().readWorkFlowFromResource("component","org/seasar/buri/component/impl/component.xpdl");
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        try{
        invoker_.invoke("component.mail", buyItemDto);
        } catch (MailSendException ex) {
            //ä¬ã´éüëÊÇ»ÇÃÇ≈Ç‡Ç›è¡Çµ
        }
    }

}
