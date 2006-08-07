/*
 * çÏê¨ì˙: 2006/05/13
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTransactionEntityDto;

public interface BuriTransactionDao {
    public Class BEAN = BuriTransactionEntityDto.class;

    public List getAllBuriBranch();
    
    public String select_ARGS = "BranchID";
    public BuriTransactionEntityDto select(long branchID);
    
    public String selectFromDto_ARGS = "dto";
    public List selectFromDto(BuriTransactionEntityDto dto);
    
    public void insert(BuriTransactionEntityDto dto);
    
    public void update(BuriTransactionEntityDto dto);
    
    public void delete(BuriTransactionEntityDto dto);

}
