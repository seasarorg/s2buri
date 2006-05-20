package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriStateEntityDto;


public interface BuriStateDao {
	public Class BEAN = BuriStateEntityDto.class;

	public List getAllBuriState();

    public String getNoProcessBuriState_QUERY = "processDate > CURRENT_TIMESTAMP";
    public List getNoProcessBuriState();

	public String getBuriState_ARGS = "StateID";
	public BuriStateEntityDto getBuriState(long StateID);
    
    
    
    public String getTimeOrverState_QUERY = "processDate > CURRENT_TIMESTAMP and autoRunTime < CURRENT_TIMESTAMP";
    public List getTimeOrverState();
    
    
    
    public String updateProceesByStateID_ARGS = "StateID";
    public void updateProceesByStateID(long stateID);
    
    
    
    public String updateAbortByStateID_ARGS = "StateID";
    public void updateAbortByStateID(long stateID);
    
    public String updateAbortByBranchID_ARGS = "branchID";
    public void updateAbortByBranchID(long branchID);
    
    public String countByBranchIDAndNotProcessed_ARGS = "branchID";
    public long countByBranchIDAndNotProcessed(long branchID);
    
	
    public String getBuriStateByPathAndData_QUERY = "pathID = /*pathID*/0 and dataID = /*dataID*/0 and processDate > CURRENT_TIMESTAMP ";
    public String getBuriStateByPathAndData_ARGS = "pathID,dataID";
    public BuriStateEntityDto getBuriStateByPathAndData(long pathID,long dataID);

//    public String getAllBuriStateByPathAndData_QUERY = "pathID = /*pathID*/0 and dataID = /*dataID*/0 order by processDate DESC";
//    public String getAllBuriStateByPathAndData_ARGS = "pathID,dataID";
//    public BuriStateEntityDto getAllBuriStateByPathAndData(long pathID,long dataID);
//    
//    public String searchPathIDs_ARGS = "pathIDs,dataID";
//    public int searchPathIDs(List pathIDs,long dataID);
//    
//    public String updateAbortByBranchID_ARGS = "branchID,savingStateID";
//    public void updateAbortByBranchID(long branchID,long savingStateID);
//    
//    public String updateProcessedByBranchID_ARGS = "long StateID";
//    public void updateProcessedByBranchID(long StateID);
//    
//    public String countByBranchIDAndNotProcessed_ARGS = "branchID";
//    public long countByBranchIDAndNotProcessed(long branchID);
//    
//    public String countByPathAndDatas_ARGS = "pathID,pathList";
//    public long countByPathAndDatas(long pathID,List pathList);
    
    public long getNewBuriStateID();
    
	public void insert(BuriStateEntityDto dto);
	
	public void update(BuriStateEntityDto dto);
	
	public void delete(BuriStateEntityDto dto);

}
