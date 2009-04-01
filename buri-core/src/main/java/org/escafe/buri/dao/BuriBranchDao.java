package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriBranchEntityDto;

public interface BuriBranchDao {
    public Class BEAN = BuriBranchEntityDto.class;

    public List getAllBuriBranch();

    public String select_ARGS = "BranchID";

    public BuriBranchEntityDto select(long branchID);

    public String getBranchByParentID_ARGS = "parentBranchID";

    public List getBranchByParentID(long parentBranchID);

    //    public String selectFromDto_ARGS = "dto";
    //    public List selectFromDto(BuriBranchEntityDto dto);

    public void insert(BuriBranchEntityDto dto);

    public void update(BuriBranchEntityDto dto);

    public void delete(BuriBranchEntityDto dto);

}
