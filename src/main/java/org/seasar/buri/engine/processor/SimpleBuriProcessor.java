/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.engine.processor;

import java.util.List;

import org.seasar.framework.container.S2Container;

public interface SimpleBuriProcessor {
    void toNextStatus(String path, Object data);
    Object toNextStatus(String path, Object data,String resultExp);
    Object toNextStatusAction(String path, Object data,Object action,String resultExp);

    void toNextStatus(String path, S2Container container,Object data);
    Object toNextStatus(String path, S2Container container,Object data,String resultExp);
    Object toNextStatusAction(String path, S2Container container,Object data,Object action,String resultExp);

//    Object invokeNoUpdate(String path, Object data);
//    Object invokeNoUpdate(String path, Object data,String context);
//    Object invokeNoUpdate(String path, Object data,Object action,String context);
//
//    Object invokeNoUpdate(String path, S2Container container,Object data);
//    Object invokeNoUpdate(String path, S2Container container,Object data,String context);
//    Object invokeNoUpdate(String path, S2Container container,Object data,Object action,String context);

//    List findDataList(String path,Object dto,Class tgtClass);
    List getDataListFromPath(String path,Class tgtClass);
    List getDataIDFromPath(String path,Class tgtClass);
    List getPathFromData(String path, Object data);
    long countByPathAndDatas(String path, List datas);

//    List findDataList(String path,Object dto, S2Container container);
//    List getDataListFromPath(String path, S2Container container);
//    List getDataIDFromPath(String path, S2Container container);
//    List getPathFromData(String path, Object data, S2Container container);
//    long countByPathAndDatas(String path, List datas, S2Container container);

}
