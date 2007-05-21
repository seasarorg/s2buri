/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.util.packages.abst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.StringUtil;

public abstract class AbstDataAccessUtilLongKey extends AbstDataAccessUtil implements DataAccessUtilLongKey {
    
    protected Long getLongPkey(Object target,String prop) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        PropertyDesc pkeyPropertyDesc = beanDesc.getPropertyDesc(prop);
        Object result = pkeyPropertyDesc.getValue(target);
        assert result instanceof Long;
        return (Long)result;
    }

    protected List getDataList(List keyVals,String execScript) {
        if(StringUtil.isEmpty(execScript)) {
            return getDataListFromKeys(keyVals);
        }
        Object result = runScript(keyVals,execScript);
        assert result instanceof List;
        return (List)result;
    }
    
    protected List getDataListFromKeys(List keyVals) {
        List result = new ArrayList();
        Iterator ite = keyVals.iterator();
        while (ite.hasNext()) {
            Object oneKey = (Object) ite.next();
            Object oneResult = getObjectFromKey((Long)oneKey);
            result.add(oneResult);
        }
        return result;
    }

    
    protected int deleteData(Object data,String execScript) {
        Object result = runScript(data,execScript);
        if (result instanceof Integer) {
            Integer delCount = (Integer) result;
            return delCount.intValue();
        }
        return 0;
    }
    
    protected Object runScript(Object data,String execScript) {
        Map context = new HashMap();
        context.put("data",data);
        Object result = getDataAccessScript().run(execScript,null,context);
        return result;
        
    }
    
}
