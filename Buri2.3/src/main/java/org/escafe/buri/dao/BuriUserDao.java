/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriUserEntityDto;

public interface BuriUserDao {
    public Class BEAN = BuriUserEntityDto.class;

    public List getAllBuriBranch();

    public String getBuriUser_ARGS = "buriUserID";

    public BuriUserEntityDto getBuriUser(long buriUserID);

    public long getNewBuriStateUserID();

    public String getBuriUserFromIds_ARGS = "userIDNum,userIDVal";

    public BuriUserEntityDto getBuriUserFromIds(Long userIDNum, String userIDVal);
    
    public String getBuriUserFromPathAndPkey_ARGS = "path,pkeyNum,pkeyVal";
    
    public List getBuriUserFromPathAndPkey(String path, long pkeyNum,String pkeyVal);

    //
    //    public String getBuriUserFromDto_ARGS = "dto";
    //    public List getBuriUserFromDto(BuriUserEntityDto dto);

    public void insert(BuriUserEntityDto dto);

    public void update(BuriUserEntityDto dto);

    public void delete(BuriUserEntityDto dto);

}