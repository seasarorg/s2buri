/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.util.packages.abst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;

public abstract class AbstDataAccessUtil implements DataAccessUtil {
    protected ScriptFactory scriptFactory;
    
    protected Script getScriptEngine() {
        return scriptFactory.getDefaultScript();
    }
    
    protected List getDataList(List keyVals,String execScript) {
        if(execScript.length() == 0) {
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
            Object oneResult = getDataFromDto(oneKey);
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
        Script scriptEngine = getScriptEngine();
        Object result = scriptEngine.run(execScript,context);
        return result;
        
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }
    
}
