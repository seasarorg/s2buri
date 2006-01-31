/*
 * çÏê¨ì˙: 2005/08/26
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.invoker.StandardBuriInvoker;
import org.seasar.framework.container.S2Container;

public class StandardBuriInvokerImpl extends AbstractBuriInvoker implements
        StandardBuriInvoker {

    public void invoke(String path, Object data, Object userData) {
        invoke(path, data, userData, null);
    }

    public Object invoke(String path, Object data, Object userData, String context) {
        return invokeAction(path, data, null, userData, context);
    }

    public void invokeAction(String path, Object data, Object action, Object userData) {
        invokeAction(path, data, action, userData, null);
    }

    public Object invokeAction(String path, Object data, Object action, Object userData, String context) {
        return invokeAction(path, getTargetContainer(), data, action, userData, context);
    }

    public void invoke(String path, S2Container container, Object data, Object userData) {
        invoke(path, container, data, userData, null);
    }

    public Object invoke(String path, S2Container container, Object data, Object userData, String context) {
        return invokeAction(path, container, data, null, userData, context);
    }

    public void invokeAction(String path, S2Container container, Object data, Object action, Object userData) {
        invokeAction(path, container, data, action, userData, null);
    }

    public Object invokeAction(String path, S2Container container, Object data, Object action, Object userData, String context) {
        return invoke(path, container, data, userData, action, context,false,null,null);
    }


    public void invokeNoUpdate(String path, Object data, Object userData) {
        invokeNoUpdate(path, data, userData, null);
    }

    public Object invokeNoUpdate(String path, Object data, Object userData, String context) {
        return invokeActionNoUpdate(path, data, null, userData, context);
    }

    public void invokeActionNoUpdate(String path, Object data, Object action, Object userData) {
        invokeActionNoUpdate(path, data, action, userData, null);
    }

    public Object invokeActionNoUpdate(String path, Object data, Object action, Object userData, String context) {
        return invokeActionNoUpdate(path, getTargetContainer(), data, action, userData, context);
    }

    public void invokeNoUpdate(String path, S2Container container, Object data, Object userData) {
        invokeNoUpdate(path, container, data, userData, null);
    }

    public Object invokeNoUpdate(String path, S2Container container, Object data, Object userData, String context) {
        return invokeActionNoUpdate(path, container, data, null, userData, context);
    }

    public void invokeActionNoUpdate(String path, S2Container container, Object data, Object action, Object userData) {
        invokeActionNoUpdate(path, container, data, action, userData, null);
    }

    public Object invokeActionNoUpdate(String path, S2Container container, Object data, Object action, Object userData, String context) {
        return invoke(path, container, data, userData, action, context,true,null,null);
    }
    


    public List getDataListFromPathAndUser(String path, Object userData) {
        return getDataListFromPath(path, userData,getTargetContainer());
    }

    public List getDataIDFromPathAndUser(String path, Object userData) {
        return getDataIDFromPath(path, userData, getTargetContainer());
    }

    public List getDataListFromPath(String path, Object userData) {
        return getDataListFromPath(path, userData,getTargetContainer());
    }

    public List getDataIDFromPath(String path, Object userData) {
        return getDataIDFromPath(path, userData, getTargetContainer());
    }

    public List getPathFromData(String path, Object data, Object userData) {
        return getPathFromData(path, data, userData, getTargetContainer());
    }

    public List getDataListFromPathAndUser(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataListFromPathAndUser(buriPath);
        backup.restore();
        return dataList;
    }

    public List getDataIDFromPathAndUser(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataIDListFromPath(buriPath);
        backup.restore();
        return dataList;
    }

    public List getDataListFromPath(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataListFromPath(buriPath);
        backup.restore();
        return dataList;
    }

    public List getDataIDFromPath(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataIDListFromPath(buriPath);
        backup.restore();
        return dataList;
    }

    public List getPathFromData(String path, Object data, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getStatePathListByData(buriPath);
        backup.restore();
        return dataList;
    }

    
    public List getDataListFromPathAndUserOnly(String path, Object userData) {
        return getDataListFromPath(path, userData,getTargetContainer());
    }

    public List getDataIDFromPathAndUserOnly(String path, Object userData) {
        return getDataIDFromPath(path, userData, getTargetContainer());
    }

    public long countByPathAndDatas(String path, List datas,Object userData) {
        return countByPathAndDatas(path, datas, userData,getTargetContainer());
    }

    public List getDataListFromPathAndUserOnly(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataListFromPathAndUser(buriPath);
        backup.restore();
        return dataList;
    }

    public List getDataIDFromPathAndUserOnly(String path, Object userData, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.getDataIDListFromPathAndUser(buriPath);
        backup.restore();
        return dataList;
    }

    public long countByPathAndDatas(String path, List datas, Object userData, S2Container container) {
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        long count = listUtil.countByPathAndDatas(path,datas);
        backup.restore();
        return count;
    }

    public List findDataList(String path, Object userData, Object dto) {
        return findDataList(path, userData, dto, container);
    }

    public List findDataList(String path, Object userData, Object dto, S2Container container) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData);
        List dataList = listUtil.searchDataListFromPathAndUser(buriPath,dto);
        backup.restore();
        return dataList;
    }
    
}
