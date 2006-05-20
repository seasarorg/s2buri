/*
 * çÏê¨ì˙: 2006/05/01
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriStateUserEntityDto;

public interface BuriStateUserDao {
    public Class BEAN = BuriStateUserEntityDto.class;

    public List getAllBuriBranch();

    public String getBuriStateUser_ARGS = "stateUserID";
    public BuriStateUserEntityDto getBuriStateUser(long stateUserID);
    
    public long getNewBuriStateUserID();

    public String getBuriStateFromDto_ARGS = "dto";
    public List getBuriStateFromDto(BuriStateUserEntityDto dto);

    public void insert(BuriStateUserEntityDto dto);
    
    public void update(BuriStateUserEntityDto dto);
    
    public void delete(BuriStateUserEntityDto dto);

}
