/*
 * 作成日: 2006/05/08
 *
 */
package org.escafe.buri.dao.util;

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriDataUtil {
    List getIDListByPathName(String pathName,DataAccessFactory factory,BuriSystemContext sysContext);
    List getDtoListByPathName(String pathName,DataAccessFactory factory,BuriSystemContext sysContext);
    List getBuriPathByDto(Object dto,DataAccessFactory factory,BuriSystemContext sysContext);
    
    void storeData(DataAccessFactory factory,BuriSystemContext sysContext);
    long getBuriDataId(DataAccessFactory factory,BuriSystemContext sysContext);
    void updateBuriData(DataAccessFactory factory,BuriSystemContext sysContext);
    Object getBuriData(long dataId,DataAccessFactory factory);
    long countByPathAndDatas(String pathName,List dataList,DataAccessFactory factory,BuriSystemContext sysContext);
}
