/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriWaitingUserEntityDto;

public interface BuriWaitingUserDao {
    public Class BEAN = BuriWaitingUserEntityDto.class;

    public List getAllWaitingUser();

    public String getBuriWaitingUser_ARGS = "waitingUserID";

    public BuriWaitingUserEntityDto getBuriWaitingUser(long waitingUserID);

    public void insertEntityList(List dtos);

    public void insert(BuriWaitingUserEntityDto dto);

    public void update(BuriWaitingUserEntityDto dto);

    public void delete(BuriWaitingUserEntityDto dto);

}
