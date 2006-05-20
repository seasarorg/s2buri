/*
 * çÏê¨ì˙: 2006/05/01
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriUserEntityDto;

public interface BuriUserDao {
    public Class BEAN = BuriUserEntityDto.class;

    public List getAllBuriBranch();

    public String getBuriUser_ARGS = "buriUserID";
    public BuriUserEntityDto getBuriUser(long buriUserID);
    
    public long getNewBuriStateUserID();

    public String getBuriUserFromDto_ARGS = "dto";
    public List getBuriUserFromDto(BuriUserEntityDto dto);

    public void insert(BuriUserEntityDto dto);
    
    public void update(BuriUserEntityDto dto);
    
    public void delete(BuriUserEntityDto dto);

}
