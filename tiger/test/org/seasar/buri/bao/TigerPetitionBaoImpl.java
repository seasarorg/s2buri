package org.seasar.buri.bao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriArgs;
import org.seasar.buri.annotation.BuriConvertRule;
import org.seasar.buri.annotation.BuriConverter;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.engine.invoker.StandardBuriInvoker;

@BuriProcess("���Y�Ǘ�.�w���\��")
@BuriConverter(@BuriConvertRule(clazz = Long.class, ognl = "FurnitureItemDao.getFurnitureItem(#data)"))
public abstract class TigerPetitionBaoImpl implements PurePetitionBao {

    private static final Log logger = LogFactory
        .getLog(TigerPetitionBaoImpl.class);

    private StandardBuriInvoker invoker;

    public void setStandardBuriInvoker(StandardBuriInvoker invoker) {
        this.invoker = invoker;
    }

    public List getWaitApprove(String userData) {
        logger.debug("IMPLEMENTATION: getDataListFromPathAndUser ���Y�Ǘ�.�w���\��.���F�҂�");
        return invoker.getDataListFromPathAndUser("���Y�Ǘ�.�w���\��.���F�҂�", userData);
    }

    @BuriActivity("���F�ς�")
    @BuriArgs("userData")
    public abstract List getEndApprove(String userData);

    @BuriActivity("�\����")
    @BuriArgs("userData")
    public abstract List getNowPetition(String userData);

    @BuriActivity("�����߂��ς�")
    @BuriArgs("userData")
    public abstract List getReturning(String userData);

    @BuriArgs( { "data", "userData" })
    public abstract void petition(FurnitureItemDto dto, String userData);

    @BuriAction("approve")
    @BuriArgs( { "data", "userData" })
    public abstract void approve(FurnitureItemDto dto, String userData);

    @BuriAction("returning")
    @BuriArgs( { "data", "userData" })
    public abstract void returning(FurnitureItemDto dto, String userData);
}
