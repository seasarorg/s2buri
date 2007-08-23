package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriStateEntityDto;

public interface BuriStateDao {
    public Class BEAN = BuriStateEntityDto.class;

    public List getAllBuriState();

    public String getNoProcessBuriState_QUERY = "processDate > CURRENT_TIMESTAMP";

    public List getNoProcessBuriState();

    public String getBuriState_ARGS = "StateID";

    public BuriStateEntityDto getBuriState(long StateID);

    public String updateProceesByStateID_ARGS = "StateID";

    public void updateProceesByStateID(long stateID);

    public String updateAbortByStateID_ARGS = "StateID";

    public void updateAbortByStateID(long stateID);

    public String updateAbortByDataId_ARGS = "dataId";

    public void updateAbortByDataId(long dataId);

    public String updateAbortByBranchID_ARGS = "branchID";

    public void updateAbortByBranchID(long branchID);

    public String countByBranchIDAndNotProcessed_ARGS = "branchID";

    public long countByBranchIDAndNotProcessed(long branchID);

    public String getBuriStateByPathAndData_QUERY = "pathID = /*pathID*/0 and dataID = /*dataID*/0 and processDate > CURRENT_TIMESTAMP ";
    public String getBuriStateByPathAndData_ARGS = "pathID,dataID";

    public BuriStateEntityDto getBuriStateByPathAndData(long pathID, long dataID);
    
    public String countBuriStateByPathNameAndPkey_ARGS = "pathName,longKey,manyKey";

    public int countBuriStateByPathNameAndPkey(String pathName,Long longKey,String manyKey);

    public long getNewBuriStateID();

    public void insert(BuriStateEntityDto dto);

    public void update(BuriStateEntityDto dto);

    public void delete(BuriStateEntityDto dto);

}
