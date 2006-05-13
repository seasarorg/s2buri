/*
 * çÏê¨ì˙: 2006/05/08
 *
 */
package org.seasar.buri.dao.util;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriDataUtil {
    long getBuriDataId(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext);
    void updateBuriData(Object argDto,DataAccessFactory factory,BuriSystemContext sysContext);
    Object getBuriData(long dataId,DataAccessFactory factory);
}
