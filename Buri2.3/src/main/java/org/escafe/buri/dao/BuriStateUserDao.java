/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriStateUserEntityDto;

public interface BuriStateUserDao {
    public Class BEAN = BuriStateUserEntityDto.class;

    public List getAllBuriBranch();

    public String getBuriStateUser_ARGS = "stateUserID";

    public BuriStateUserEntityDto getBuriStateUser(long stateUserID);

    public long getNewBuriStateUserID();

    public void insertEntityList(List dtos);

    public void insert(BuriStateUserEntityDto dto);

    public void update(BuriStateUserEntityDto dto);

    public void delete(BuriStateUserEntityDto dto);

}
