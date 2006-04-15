package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;

public interface PurePetitionBao {

    public List getWaitApprove(String userData);

    public List getEndApprove(String userData);

    public List getNowPetition(String userData);

    public List getReturning(String userData);

    public void petition(FurnitureItemDto dto, String userData);

    public void approve(FurnitureItemDto dto, String userData);

    public void returning(FurnitureItemDto dto, String userData);
}
