/*
 * 作成日: 2005/07/02
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestManyDto;


public interface BuriTestManyDao {
    public Class BEAN = BuriTestManyDto.class;

    public List getAllBuriTestMany();

    public String getBuriTestMany_ARGS = "testID01,testID02";
    public BuriTestManyDto getBuriTestMany(long testID01,long testID02);
    
    public String insert_ARGS = "dto";
    public void insert(BuriTestManyDto dto);
    
    public void update(BuriTestManyDto dto);
    
    public void delete(BuriTestManyDto dto);

}
