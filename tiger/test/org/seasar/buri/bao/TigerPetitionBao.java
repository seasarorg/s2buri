package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriArgs;
import org.seasar.buri.annotation.BuriConvertRule;
import org.seasar.buri.annotation.BuriConverter;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.dto.FurnitureItemDto;

@BuriProcess("資産管理.購入申請")
@BuriConverter(@BuriConvertRule(clazz = Long.class, ognl = "FurnitureItemDao.getFurnitureItem(#data)"))
public interface TigerPetitionBao {

    @BuriActivity("承認待ち")
    @BuriArgs("userData")
    public List getWaitApprove(String userData);

    @BuriActivity("承認済み")
    @BuriArgs("userData")
    public List getEndApprove(String userData);

    @BuriActivity("申請中")
    @BuriArgs("userData")
    public List getNowPetition(String userData);

    @BuriActivity("差し戻し済み")
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
