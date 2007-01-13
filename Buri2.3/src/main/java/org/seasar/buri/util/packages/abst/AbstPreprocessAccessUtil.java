/*
 * 作成日: 2006/06/05
 *
 */
package org.seasar.buri.util.packages.abst;

import java.util.HashMap;
import java.util.Map;

import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.coffee.script.Script;

public abstract class AbstPreprocessAccessUtil implements PreprocessAccessUtil {
    protected Script preprocessScript;
    
    protected Object runScript(Object data,String execScript) {
        Map context = new HashMap();
        context.put("data",data);
        Object result = preprocessScript.run(execScript,null,context);
        return result;
        
    }

    public Script getPreprocessScript() {
        return preprocessScript;
    }

    public void setPreprocessScript(Script preprocessScript) {
        this.preprocessScript = preprocessScript;
    }

}
