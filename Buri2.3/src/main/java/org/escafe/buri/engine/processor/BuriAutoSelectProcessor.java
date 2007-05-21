/*
 * 作成日: 2006/06/20
 *
 */
package org.escafe.buri.engine.processor;

import java.util.List;

import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

public interface BuriAutoSelectProcessor {
    boolean isStdProcessor(String buriPath);
    boolean isSimpleProcessor(String buriPath);
    DataAccessFactory getDataAccessFactory(String buriPath);
    
    void toNextStatus(String path, Object data, Object userData);
    Object toNextStatus(String path, Object data, Object userData,String resultExp);
    void toNextStatusAction(String path, Object data, Object userData,Object action);
    
    Object toNextStatus(String path, Object data, Object userData,BuriProcessorInfo info);

    List getDataListFromPath(String path, Object userData,Class tgtClass);
    List getDataIDFromPath(String path, Object userData,Class tgtClass);
    List getPathFromData(String path, Object data, Object userData,Class tgtClass);
    long countByPathAndDatas(String path, List datas,Object userData);

    List getDataListFromPath(String path, Object userData,Class tgtClass,S2Container container);
    List getDataIDFromPath(String path, Object userData,Class tgtClass,S2Container container);
    List getPathFromData(String path, Object data, Object userData,Class tgtClass,S2Container container);
    long countByPathAndDatas(String path, List datas,Object userData,S2Container container);

}
