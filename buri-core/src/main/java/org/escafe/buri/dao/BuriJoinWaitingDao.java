package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriJoinWaitingEntityDto;

public interface BuriJoinWaitingDao {
    public Class BEAN = BuriJoinWaitingEntityDto.class;

    public List getAllBuriState();
    
    public String getBuriJoinWaiting_ARGS = "waitingID";

    public BuriJoinWaitingEntityDto getBuriJoinWaiting(long waitingID);

    public String getNowWaiting_QUERY = "processDate > CURRENT_TIMESTAMP";
    public List<BuriJoinWaitingEntityDto> getNowWaiting();
    
    public String updateClearWaitingInfo_ARGS = "branchID";
    public void updateClearWaitingInfo(long branchID);

    public void insert(BuriJoinWaitingEntityDto dto);

    public void update(BuriJoinWaitingEntityDto dto);

    public void delete(BuriJoinWaitingEntityDto dto);

}
