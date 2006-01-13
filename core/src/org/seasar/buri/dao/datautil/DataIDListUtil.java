/*
 * çÏê¨ì˙: 2005/11/09
 *
 */
package org.seasar.buri.dao.datautil;

import java.util.List;

import org.seasar.buri.engine.BuriPath;

public interface DataIDListUtil {
    
    List getDataListFromPathAndUser(BuriPath path);
    List getDataIDListFromPathAndUser(BuriPath path);
    List getDataListFromPath(BuriPath path);
    List getDataIDListFromPath(BuriPath path);
    
    List searchDataList(BuriPath path,Object findDto);
    List searchDataListFromPathAndUser(BuriPath path,Object findDto);

    List getStatePathListByData(BuriPath path);
    long countByPathAndDatas(String path,List datas);
}
