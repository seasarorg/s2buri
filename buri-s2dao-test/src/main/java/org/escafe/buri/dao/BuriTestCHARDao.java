/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriTestCHARDto;


public interface BuriTestCHARDao {
    public Class BEAN = BuriTestCHARDto.class;

    public List getAllBuriTestCHAR();

    public String getBuriTestCHAR_ARGS = "testID";
    public BuriTestCHARDto getBuriTestCHAR(String testID);
    
    public String insert_ARGS = "dto";
    public void insert(BuriTestCHARDto dto);
    
    public void update(BuriTestCHARDto dto);
    
    public void delete(BuriTestCHARDto dto);

}
