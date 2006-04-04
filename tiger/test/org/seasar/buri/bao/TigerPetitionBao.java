package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriArgs;
import org.seasar.buri.annotation.BuriConvertRule;
import org.seasar.buri.annotation.BuriConverter;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.dto.FurnitureItemDto;

@BuriProcess("���Y�Ǘ�.�w���\��")
@BuriConverter(@BuriConvertRule(clazz = Long.class, ognl = "FurnitureItemDao.getFurnitureItem(#data)"))
public interface TigerPetitionBao {

    @BuriActivity("���F�҂�")
    @BuriArgs("userData")
    public List getWaitApprove(String userData);

    @BuriActivity("���F�ς�")
    @BuriArgs("userData")
    public List getEndApprove(String userData);

    @BuriActivity("�\����")
    @BuriArgs("userData")
    public List getNowPetition(String userData);

    @BuriActivity("�����߂��ς�")
    @BuriArgs("userData")
    public List getReturning(String userData);

    @BuriArgs({"data", "userData"})
    public void petition(FurnitureItemDto dto, String userData);

    @BuriAction("approve")
    @BuriArgs({"data", "userData"})
    public void approve(FurnitureItemDto dto, String userData);

    @BuriAction("returning")
    @BuriArgs({"data", "userData"})
    public void returning(FurnitureItemDto dto, String userData);
}
