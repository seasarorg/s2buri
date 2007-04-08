/*
 * 作成日: 2006/07/10
 *
 */
package org.seasar.buri.common.delayloader.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.common.delayloader.DelayLoader;
import org.seasar.buri.common.delayloader.DelayLoaderInfo;
import org.seasar.framework.util.ResourceUtil;

public class DelayLoaderImpl implements DelayLoader{
    private Map resource = new HashMap();
    private Map file = new HashMap();
    
    public void addResourceLoader(Object obj,DelayLoaderInfo loaderInfo) throws Throwable {
        addLoader(resource,obj,loaderInfo);
    }
    
    public void addFileLoader(Object obj,DelayLoaderInfo loaderInfo) throws Throwable {
        addLoader(file,obj,loaderInfo);
    }
    
    protected void addLoader(Map content,Object obj,DelayLoaderInfo loaderInfo) throws Throwable {
        Map keyMap = new HashMap();
        if(content.containsKey(obj)) {
            keyMap = (Map)content.get(obj);
            if(keyMap.containsKey(loaderInfo.getTgtKey())) {
                loaderInfo.getInvoke().proceed();
            }
        }
        keyMap.put(loaderInfo.getTgtKey(),loaderInfo);
        content.put(obj,keyMap);
    }
    
    public void read(Object obj,String key) throws Throwable{
        DelayLoaderInfo loaderInfo = null;
        File readFile = null;
        if(resource.containsKey(obj)) {
            Map keyMap = (Map)resource.get(obj);
            loaderInfo = (DelayLoaderInfo)keyMap.get(key);
            readFile = ResourceUtil.getResourceAsFile(loaderInfo.getName());
        } else if(file.containsKey(obj)) {
            Map keyMap = (Map)file.get(obj);
            loaderInfo = (DelayLoaderInfo)keyMap.get(key);
            readFile = new File(loaderInfo.getName());
        }
        read(readFile,loaderInfo);
    }
    
    protected void read(File readFile,DelayLoaderInfo loaderInfo) throws Throwable{
        if(loaderInfo == null || readFile == null) {
            return;
        }
        if(readFile.lastModified() != loaderInfo.getLastModified()) {
            loaderInfo.setLastModified(readFile.lastModified());
            loaderInfo.getInvoke().proceed();
        }
    }
    
}
