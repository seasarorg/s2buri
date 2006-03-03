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
        buriEngine_ = (BuriEngine)getComponent(BuriEngine.class);
        buriEngine_.getWorkflows().readWorkFlowFromResource("component","org/seasar/buri/component/impl/component.xpdl");
    }
    
    public void testSendMailTx() {
        FurnitureItemDto buyItemDto = new FurnitureItemDto();
        buyItemDto.setName("T45_001");
        buyItemDto.setType("PC");
        buyItemDto.setAcquisitionTypeBuy();
        buyItemDto.setAcquisition(new Date());
        invoker_.invoke("component.mail",getContainer().getRoot(), buyItemDto);
    }

}
