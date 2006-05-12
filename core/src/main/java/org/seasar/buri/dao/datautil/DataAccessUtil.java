/*
 * çÏê¨ì˙: 2005/11/09
 *
 */
package org.seasar.buri.dao.datautil;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.BuriDataMetaData;

public interface DataAccessUtil {

    void sotreData(Object data);
    void sotreDataNoPkey(Object data);
    
    Object getObjectFromDataID(BuriPath path, long dataID,String className);
    Object getObjectFromDataID(BuriPath path, long dataID);

    Object callOgnls(BuriDataMetaData metaData,String processOgnl,Object data);
    Object callFindOgnls(BuriDataMetaData metaData,String processOgnl,Object ids,Object findDto);
    Object getObjectFromDataID(BuriDataMetaData metaData,long dataID);
}
