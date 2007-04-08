/*
 * 作成日: 2006/07/07
 *
 */
package org.seasar.buri.common.util;

import java.util.ArrayList;
import java.util.List;

public class BuriConfigurationImpl implements BuriConfiguration{
    private MultiValueMap conf = new MultiValueMap();
    
    public void addValue(String name,Object val) {
        conf.put(name,val);
    }
    
    public List getValList(String name) {
        if(conf.containsKey(name)) {
            return conf.get(name);
        }
        return new ArrayList();
    }
    
    public Object getVal(String name) {
        List valList = getValList(name);
        if(valList == null || valList.size() == 0 ) {
            return null;
        }
        return valList.get(0);
    }
    
}
