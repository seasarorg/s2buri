/*
 * 作成日: 2004/06/22
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
package org.seasar.buri.common.util;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import org.seasar.buri.exception.BuriException;
import org.seasar.buri.exception.BuriOGNLRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * @author makoto
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
public class ScriptProcessor {
	private static Logger logger = Logger.getLogger(ScriptProcessor.class);
	
	private Map context;
	private static HashMap expressionCache = new HashMap();

	public ScriptProcessor() {
		context = Ognl.createDefaultContext(null);
	}
	
	public Object getContext(String name) {
		return context.get(name);
	}
	
	public Map getContext() {
		return context;
	}

	public void removeContext(String contextName) {
		context.remove(contextName);
	}

	public void putInContext(String name, Object value) {
		context.put(name, value);
	}
	
	public void putAllContext(Map context) {
		if(context == null) return;
		this.context.putAll(context);
	}

	public Object parseExpression(String expression) {
		Object ret=null;
		try {
			ret = Ognl.parseExpression(expression);
		} catch (OgnlException e) {
		    processException(e,"EBRI0001",new Object[]{expression,e},expression,null,null);
		}
		return ret;
	}

	public Object getValueExpression(Object expression,Object root,Map context) {
		Object ret=null;
		try {
			ret = Ognl.getValue(expression, context, root);
		} catch (OgnlException t) {
		    processException(t,"EBRI0002",new Object[]{expression,root,t,context},expression,root,context);
		} catch (Exception re) {
		    if(re instanceof BuriException) {
		        throw (BuriException)re;
            }
            new BuriOGNLRuntimeException("EBRI0002",new Object[]{expression,root,re,context},re);
        }
		return ret;
	}

    public Object getValueExpression(Object expression,Object root) {
        return getValueExpression(expression,root,context);
    }

    public Object getValue(String expression,Object root,Map context) {
        Object expresObj = getExpression(expression);
        return getValueExpression(expresObj,root,context);
    }

	public Object getValue(String expression,Object root) {
	    Object expresObj = getExpression(expression);
	    return getValueExpression(expresObj,root);
	}
    
    public void setValueExpression(Object expression,Object root,Object value,Map context) {
        try {
            Map ognlContext = Ognl.createDefaultContext(null);
            ognlContext.putAll(context);
            Ognl.setValue(expression, ognlContext, root,value);
            context.putAll(ognlContext);
    
        } catch (OgnlException t) {
            processException(t,"EBRI0003",new Object[]{expression,root,value,t},expression,root,context);
        } catch (RuntimeException re) {
            if(re instanceof BuriException) {
                throw re;
            }
            new BuriOGNLRuntimeException("EBRI0003",new Object[]{expression,root,value,re,context},re);
        }
    }
	
	public void setValueExpression(Object expression,Object root,Object value) {
        setValueExpression(expression,root,value,context);
	}

    public void setValue(String expression,Object root,Object value,Map context) {
        Object expresObj = getExpression(expression);
        setValueExpression(expresObj,root,value,context);
    }

	public void setValue(String expression,Object root,Object value) {
	    Object expresObj = getExpression(expression);
	    setValueExpression(expresObj,root,value);
	}
	
	protected synchronized Object getExpression(String expression) {
	    Object expresObj = expressionCache.get(expression);
	    if(expresObj==null){
	        expresObj = parseExpression(expression);
	        expressionCache.put(expression,expresObj);
	    }
	    return expresObj;
	}
//	
//	public Object[] convertArgNameToArgObj(Object root,String[] argName) {
//		Object[] args = new Object[0];;
//		if(argName != null && argName[0].length() > 0){
//			args = new Object[argName.length];
//			for(int i=0 ; i < argName.length ; i++) {
//			    String val = argName[i];
//		        args[i] = getValue(val,root);
//			}
//		}
//		return args;
//	}
//	
//	public Object callMethod(Object root,Object target,String methodName , String[] argName) {
//		Object[] args = convertArgNameToArgObj(root,argName);
//		Object ret = null;
//
//		MethodAccessor accessor;
//		try {
//			accessor = OgnlRuntime.getMethodAccessor(target.getClass());
//			ret = accessor.callMethod(context,target,methodName,args);
//		} catch (OgnlException e) {
//		    processException(e,"EBRI0004",new Object[]{root,target,methodName , argName,e});
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentRuntimeException("EBRI0005",new Object[]{root,target,methodName , argName,e},e);
//        }
//		return ret;
//	}
	
	protected void processException(OgnlException e,String message,Object[] objs,Object expression,Object root,Map context) {
        String errInfo = ((e.getReason()!=null) ? "exception=" + e.getReason().toString() : "") + "\nexpression=" + expression + "\ncontext=" + context;
        logger.error(errInfo);
	    if(e.getReason() instanceof RuntimeException) {
            throw (RuntimeException)e.getReason();
	    }
	    throw new BuriOGNLRuntimeException(message,objs,e);
	}

}
