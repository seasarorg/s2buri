/*
 * 作成日: 2005/08/18
 *
 */
package org.seasar.buri.engine.processor;

import java.util.List;

import org.seasar.framework.container.S2Container;

public interface SimpleBuriProcessor {
    void toNextStatus(String path, Object data);
    Object toNextStatus(String path, Object data,String resultExp);
    void toNextStatusAction(String path, Object data,String action);
    Object toNextStatus(String path, Object data,BuriProcessorInfo info);

    List getDataListFromPath(String path,Class tgtClass);
    List getDataIDFromPath(String path,Class tgtClass);
    List getPathFromData(String path, Object data);
    long countByPathAndDatas(String path, List datas);

    List getDataListFromPath(String path,Class tgtClass, S2Container container);
    List getDataIDFromPath(String path,Class tgtClass, S2Container container);
    List getPathFromData(String path, Object data,S2Container container);
    long countByPathAndDatas(String path, List datas,S2Container container);

}
