/*
 * çÏê¨ì˙: 2005/06/09
 *
 */
package org.seasar.buri.rule.impl;


import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.rule.ConditionProcessingRule;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;


/**
 * @author makotan
 *
 */
public class OgnlConditionProcessingRuleImpl implements ConditionProcessingRule{
    private static Logger logger = Logger.getLogger(OgnlConditionProcessingRuleImpl.class);
    private S2Container container;
    private ContextUtil contextUtil;
    private boolean isDebugMode = true;

    /* (îÒ Javadoc)
     * @see org.seasar.buri.rule.ConditionProcessingRule#judgesCondition(java.lang.String, java.util.Map)
     */
    public boolean judgesCondition(String condition,Map context) {
        ScriptProcessor processor = new ScriptProcessor();
        String getValStr = "";
        if(logger.isDebugEnabled() && isDebugMode) {
            getValStr = "//ConditionéÆ=["+condition+"]";
        }
        Boolean bool = Boolean.FALSE;
        try {
            bool = (Boolean)processor.getValue(condition,contextUtil.getLocalContext().getContainer(),context);
        } catch (Throwable th) {
            logger.error(th.toString() + "\n/context=" + context);
        }finally {
            if(logger.isDebugEnabled() && isDebugMode) {
                logger.debug(getValStr + " :" + bool);
            }
        }
        return bool.booleanValue();
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public void setDebugMode(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

}
