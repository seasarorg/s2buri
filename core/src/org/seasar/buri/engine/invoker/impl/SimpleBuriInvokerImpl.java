/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.framework.container.S2Container;

public class SimpleBuriInvokerImpl extends AbstractBuriInvoker 
            implements SimpleBuriInvoker {

    public Object invoke(String path, Object data) {
        return invoke( path, getTargetContainer().getRoot(), data);
    }

    public Object invoke(String path, Object data, String context) {
        return invoke( path, getTargetContainer(), data, context);
    }

    public Object invoke(String path, Object data, Object action, String context) {
        return invoke(path, getTargetContainer(), data, action, context);
    }

    public Object invoke(String path, S2Container container, Object data) {
        return invoke(path, container, data, null);
    }

    public Object invoke(String path, S2Container container, Object data, String context) {
        return invoke(path, container, data, null, context);
    }

    public Object invoke(String path, S2Container container, Object data, Object action, String context) {
        return invoke(path, container, data, null ,action, context, false,null);
    }

    
    public Object invokeNoUpdate(String path, Object data) {
        return invokeNoUpdate( path, getTargetContainer().getRoot(), data);
    }

    public Object invokeNoUpdate(String path, Object data, String context) {
        return invokeNoUpdate( path, getTargetContainer(), data, context);
    }

    public Object invokeNoUpdate(String path, Object data, Object action, String context) {
        return invokeNoUpdate(path, getTargetContainer(), data, action, context);
    }

    public Object invokeNoUpdate(String path, S2Container container, Object data) {
        return invokeNoUpdate(path, container, data, null);
    }

    public Object invokeNoUpdate(String path, S2Container container, Object data, String context) {
        return invokeNoUpdate(path, container, data, null, context);
    }

    public Object invokeNoUpdate(String path, S2Container container, Object data, Object action, String context) {
        return invoke(path, container, data, null ,action, context, true,null);
    }


    public List getDataListFromPath(String path) {
        return getDataListFromPath(path, getTargetContainer());
    }

    public List getDataIDFromPath(String path) {
        return getDataIDFromPath(path, getTargetContainer());
    }

    public List getPathFromData(String path, Object data) {
        return getPathFromData(path, data, getTargetContainer());
    }

    public long countByPathAndDatas(String path, List datas) {
        return countByPathAndDatas(path, datas, getTargetContainer());
    }

    public List getDataListFromPath(String path, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,null);
        List dataList = listUtil.getDataListFromPath(buriPath);
        backup.restore();
        return dataList;
    }

    public List getDataIDFromPath(String path, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,null);
        List dataList = listUtil.getDataIDListFromPath(buriPath);
        backup.restore();
        return dataList;
    }

    public List getPathFromData(String path, Object data, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,null);
        List dataList = listUtil.getStatePathListByData(buriPath);
        backup.restore();
        return dataList;
    }

    public long countByPathAndDatas(String path, List datas, S2Container container) {
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,null);
        long count = listUtil.countByPathAndDatas(path,datas);
        backup.restore();
        return count;
    }

    public List findDataList(String path, Object dto) {
        return findDataList(path, dto, container);
    }

    public List findDataList(String path, Object dto, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,null);
        List dataList = listUtil.searchDataList(buriPath,dto);
        backup.restore();
        return dataList;
    }

}
