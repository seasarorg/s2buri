/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.engine.invoker;

import java.util.List;

import org.seasar.framework.container.S2Container;

public interface SimpleBuriInvoker {
    Object invoke(String path, Object data);
    Object invoke(String path, Object data,String context);
    Object invoke(String path, Object data,Object action,String context);

    Object invoke(String path, S2Container container,Object data);
    Object invoke(String path, S2Container container,Object data,String context);
    Object invoke(String path, S2Container container,Object data,Object action,String context);

    Object invokeNoUpdate(String path, Object data);
    Object invokeNoUpdate(String path, Object data,String context);
    Object invokeNoUpdate(String path, Object data,Object action,String context);

    Object invokeNoUpdate(String path, S2Container container,Object data);
    Object invokeNoUpdate(String path, S2Container container,Object data,String context);
    Object invokeNoUpdate(String path, S2Container container,Object data,Object action,String context);

    List findDataList(String path,Object dto);
    List getDataListFromPath(String path);
    List getDataIDFromPath(String path);
    List getPathFromData(String path, Object data);
    long countByPathAndDatas(String path, List datas);

    List findDataList(String path,Object dto, S2Container container);
    List getDataListFromPath(String path, S2Container container);
    List getDataIDFromPath(String path, S2Container container);
    List getPathFromData(String path, Object data, S2Container container);
    long countByPathAndDatas(String path, List datas, S2Container container);

}
