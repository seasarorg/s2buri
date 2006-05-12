/*
 * ì¬“ú: 2005/03/29
 *
 */
package org.seasar.buri.component.impl;

import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.component.OgnlInvoker;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;


/**
 * @author makotan
 *
 */
public class OgnlInvokerImpl implements OgnlInvoker {
	private static Logger logger = Logger.getLogger(OgnlInvokerImpl.class);
    private ExtendedAttributeUtil attributeUtil;
    private ContextUtil contextUtil;

    /* (”ñ Javadoc)
     * @see jp.starlogic.makotan.buridicon.BuriComponent2#buriExecute(jp.starlogic.makotan.buridicon.util.ScriptProcessor, java.lang.Object, jp.starlogic.makotan.buridicon.internal.Tool)
     */
    public void buriExecute(ScriptProcessor processor, ToolTagSelect tool,Map contextData) {
        boolean isDebug = getLogger().isDebugEnabled();
        if(contextData.containsKey("WakanagoEngineImpl.isDebugEnabled()")) {
            isDebug = ((Boolean)contextData.get("WakanagoEngineImpl.isDebugEnabled()")).booleanValue();
        }
        BuriPath path = tool.getActivityTagSelect().getBuriPath();
        if(isDebug) {
            getLogger().debug("BEGIN ExecutePath = [" + path + "]");
        }
        Map context = attributeUtil.get(tool);
        String ognls = (String)context.get("ognl");
        processOgnls(ognls,processor,contextData,isDebug);
        String exception = (String)context.get("exception");
        processException(exception,processor,contextData,isDebug);
        if(isDebug) {
            getLogger().debug("END ExecutePath = [" + path + "]");
        }
    }
    
    protected S2Container getContainer() {
        return contextUtil.getLocalContext().getContainer();
    }
    
    private void processException(String exception,ScriptProcessor processor,Map contextData,boolean isDebug) {
        if(StringUtil.isEmpty(exception)) {
            return;
        }
        if(isDebug) {
            getLogger().debug("Call OgnlTraceInvoker Exception = [" + exception + "]");
        }
        RuntimeException runtimeException = null;
        if( isProcessedOgnl(exception,isDebug) ) { //ognlLine.length()>0) {
            runtimeException = (RuntimeException)processor.getValue(exception,getContainer(),contextData);
        }
        throw runtimeException;
    }
    
    protected Logger getLogger() {
        return logger;
    }
    
    public Object processOgnls(String ognls,ScriptProcessor processor,Map contextData) {
        return processOgnls(ognls,processor,contextData,getLogger().isDebugEnabled());
    }

    private Object processOgnls(String ognls,ScriptProcessor processor,Map contextData,boolean isDebug) {
        if(StringUtil.isEmpty(ognls)) {
            return null;
        }
        String ognlLines[] = ognls.split("\n");
        Object result = null;
        for(int i=0; i < ognlLines.length ; i++) {
            String ognlLine = ognlLines[i];
            if( isProcessedOgnl(ognlLine,isDebug) ) { //ognlLine.length()>0) {
                result = processOgnl(ognlLine,processor,i,contextData,isDebug);
            }
        }
        return result;
    }
    
    private boolean isProcessedOgnl(String ognlLine,boolean isDebug) {
        String line = ognlLine.trim();
        if(line.trim().length()==0) {
            return false;
        }
        String commentCheck = "";
        if(line.length() >= 2) {
            commentCheck = line.substring(0,2);
        }
        if(commentCheck.compareTo("//") == 0) {
            if(isDebug) {
                getLogger().debug(ognlLine);
            }
            return false;
        }
        return true;
    }
    
    private Object processOgnl(String ognlLine,ScriptProcessor processor,int line,Map contextData,boolean isDebug) {
        String ognls[] = ognlLine.split("=");
        String setContext = null;
        String getValue = "";
        if(ognls.length==1) {
            getValue = ognlLine;
        }else if(ognls.length==2) {
            setContext = ognls[0].trim();
            getValue = ognls[1].trim();
        }
        Object data = getValue(processor,getValue,line,contextData,isDebug);
        setValue(processor,setContext,data,contextData,isDebug);
        return data;
    }
    private Object getValue(ScriptProcessor processor,String getValue,int line,Map contextData,boolean isDebug) {
        String getValStr = "";
        if(isDebug) {
            getValStr = "Line:" + line + "//Ž®=["+getValue+"]";
        }
        Object data = null;
        try {
            data = processor.getValue(getValue,getContainer(),contextData);
        }finally {
            if(isDebug) {
                getLogger().debug(getValStr + " :" + data);
            }
        }
        return data;
    }
    
    private void setValue(ScriptProcessor processor,String setContext,Object data,Map contextData,boolean isDebug) {
        if(setContext != null) {
            if(isDebug) {
                getLogger().debug(setContext + " = " + data);
            }
            processor.setValue(setContext,getContainer(),data,contextData);
        }
    }

    public ExtendedAttributeUtil getAttributeUtil() {
        return attributeUtil;
    }

    public void setAttributeUtil(ExtendedAttributeUtil attributeUtil) {
        this.attributeUtil = attributeUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

}
