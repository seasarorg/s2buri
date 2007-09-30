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

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.script.Script;
import org.seasar.framework.util.StringUtil;

public abstract class AbstDataAccessUtil implements DataAccessUtil {
    protected Script dataAccessScript;
    protected Script pkeyExpressionScript;
    protected ClassDefUtil classDefUtil = new ClassDefUtilImpl();;

    protected List getDataList(List keyVals, String execScript) {
        if (StringUtil.isEmpty(execScript)) {
            return getDataListFromKeys(keyVals);
        }
        Object result = runScript(keyVals, execScript);
        assert result instanceof List;
        return (List) result;
    }

    protected List getDataListFromKeys(List keyVals) {
        List result = new ArrayList();
        Iterator ite = keyVals.iterator();
        while (ite.hasNext()) {
            Object oneKey = ite.next();
            Object oneResult = getDataFromDto(oneKey);
            result.add(oneResult);
        }
        return result;
    }

    protected int deleteData(Object data, String execScript) {
        Object result = runScript(data, execScript);
        if (result instanceof Integer) {
            Integer delCount = (Integer) result;
            return delCount.intValue();
        }
        return 0;
    }

    protected Object runScript(Object data, String execScript) {
        Map context = new HashMap();
        context.put("data", data);
        Object result = dataAccessScript.run(execScript, null, context);
        return result;

    }
    
    public String getClassName(Object data) {
    	return classDefUtil.getClassName(data);
    }
    
    public String getString(Object data) {
    	return data.toString();
    }

    public Script getDataAccessScript() {
        return dataAccessScript;
    }

    public void setDataAccessScript(Script dataAccessScript) {
        this.dataAccessScript = dataAccessScript;
    }

    public Script getPkeyExpressionScript() {
        return pkeyExpressionScript;
    }

    public void setPkeyExpressionScript(Script pkeyExpressionScript) {
        this.pkeyExpressionScript = pkeyExpressionScript;
    }

}
