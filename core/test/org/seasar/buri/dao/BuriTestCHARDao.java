/*
 * çÏê¨ì˙: 2005/07/02
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestCHARDto;


public interface BuriTestCHARDao {
    public Class BEAN = BuriTestCHARDto.class;

    public List getAllBuriTestCHAR();

    public String getBuriTestCHAR_ARGS = "testID";
    public BuriTestCHARDto getBuriTestCHAR(String testID);
    
    public void insert(BuriTestCHARDto dto);
    
    public void update(BuriTestCHARDto dto);
    
    public void delete(BuriTestCHARDto dto);

}
