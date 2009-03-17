/*
 * 作成日: 2006/03/26
 *
 */
package org.escafe.buri.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiValueMap {
    private Map inMap = new HashMap();

    public int size() {
        return inMap.size();
    }

    public void clear() {
        inMap.clear();
    }

    public boolean isEmpty() {
        return inMap.isEmpty();
    }

    public boolean containsKey(Object arg0) {
        return inMap.containsKey(arg0);
    }

    public boolean containsValue(Object arg0) {
        return false;
    }

    public Collection values() {
        return null;
    }

    public void putAll(MultiValueMap mvMap) {
        inMap.putAll(mvMap.inMap);
    }

    public Set entrySet() {
        return inMap.entrySet();
    }

    public Set keySet() {
        return inMap.keySet();
    }

    public List get(Object arg0) {
        return (List) inMap.get(arg0);
    }

    public List remove(Object arg0) {
        return (List) inMap.remove(arg0);
    }

    public void put(Object arg0, Object arg1) {
        List valList;
        if (containsKey(arg0)) {
            valList = (List) inMap.get(arg0);
        } else {
            valList = new ArrayList();
            inMap.put(arg0, valList);
        }
        valList.add(arg1);
    }

    @Override
    public String toString() {
        return inMap.toString();
    }

    public Map convertMap() {
        return inMap;
    }

}
