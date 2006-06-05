/*
 * çÏê¨ì˙: 2006/06/05
 *
 */
package org.seasar.buri.util.packages.abst;

import java.util.HashMap;
import java.util.Map;

import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;

public abstract class AbstPreprocessAccessUtil implements PreprocessAccessUtil {
    protected ScriptFactory scriptFactory;
    
    protected Script getScriptEngine() {
        return scriptFactory.getDefaultScript();
    }

    protected Object runScript(Object data,String execScript) {
        Map context = new HashMap();
        context.put("data",data);
        Script scriptEngine = getScriptEngine();
        Object result = scriptEngine.run(execScript,null,context);
        return result;
        
    }

    public ScriptFactory getScriptFactory() {
        return scriptFactory;
    }

    public void setScriptFactory(ScriptFactory scriptFactory) {
        this.scriptFactory = scriptFactory;
    }

}
