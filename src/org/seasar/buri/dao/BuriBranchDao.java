package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriBranchEntityDto;


public interface BuriBranchDao {
	public Class BEAN = BuriBranchEntityDto.class;

	public List getAllBuriBranch();

	public String getBuriBranch_ARGS = "BranchID";
	public BuriBranchEntityDto getBuriBranch(long BranchID);
    
    public String getBuriParentBranch_ARGS = "parentBranchID";
    public List getBuriParentBranch(long parentBranchID);
	
    public long getNewBuriBranchID();

    public void insert(BuriBranchEntityDto dto);
	
	public void update(BuriBranchEntityDto dto);
	
	public void delete(BuriBranchEntityDto dto);

}
