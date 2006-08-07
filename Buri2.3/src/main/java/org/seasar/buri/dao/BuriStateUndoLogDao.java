package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriStateUndoLogEntityDto;


public interface BuriStateUndoLogDao {
	public Class BEAN = BuriStateUndoLogEntityDto.class;

	public List getAllBuriState();
    
    public String addUndoLog_ARGS = "stateID,branchID,btid";
    public void addUndoLog(long stateID,long branchID,long btid);

    
    public long getNewBuriStateID();
    
	public void insert(BuriStateUndoLogEntityDto dto);
	
	public void update(BuriStateUndoLogEntityDto dto);
	
	public void delete(BuriStateUndoLogEntityDto dto);

}
