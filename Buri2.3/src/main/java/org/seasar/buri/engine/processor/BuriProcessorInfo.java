/*
 * çÏê¨ì˙: 2006/06/14
 *
 */
package org.seasar.buri.engine.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;

public class BuriProcessorInfo {
    private S2Container container;
    private Object action;
    private Map context = new HashMap();
    private String resultExp;
    private List actNames;
    
    public Object getAction() {
        return action;
    }
    public void setAction(Object action) {
        this.action = action;
    }
    public S2Container getContainer() {
        return container;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public String getResultExp() {
        return resultExp;
    }
    public void setResultExp(String resultExp) {
        this.resultExp = resultExp;
    }
    public void put(String key,Object val) {
        context.put(key,val);
    }
    public void putAll(Map datas) {
        context.putAll(datas);
    }
    public Object get(String key) {
        return context.get(key);
    }
    public Map getContext() {
        return context;
    }
    public List getActNames() {
        return actNames;
    }
    public void setActNames(List actNames) {
        this.actNames = actNames;
    }
    
}
