/*
 * ì¬“ú: 2006/01/02
 *
 */
package org.seasar.buri.bao.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.bao.BaoFunctionMetadata;
import org.seasar.buri.bao.BaoInvokeMetadata;
import org.seasar.buri.bao.BaoInvoker;
import org.seasar.buri.bao.BaoStatusMetadata;
import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.IsNumberUtil;
import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.invoker.impl.AbstractBuriInvoker;
import org.seasar.buri.exception.BuriBaoException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;

public class BaoInvokerImpl extends AbstractBuriInvoker implements BaoInvoker {
    private S2Container container;
    private ClassDefUtil classDefUtil;
    private static Logger logger = Logger.getLogger(BaoInvokerImpl.class);

    public Object invoke(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        String path = getBuriPath(invokeMetadata);
        String action = invokeMetadata.getAction();
        String resultOgnl = invokeMetadata.getResult();
        Object data = getInvokeData(invokeMetadata,invoke);
        Object userData = getUserData(invokeMetadata,invoke);
        Map appContext = getAppContext(invokeMetadata,invoke,data,"data");
        getContextUtil().getLocalContext().setContainer(container.getRoot());
        Object result = innerInvoke(path,container.getRoot(),data,userData,action,resultOgnl,false,appContext);
        return result;
    }
    
    protected Object getUserData(BaoFunctionMetadata metadata,MethodInvocation invoke) {
        Object userData = getNameData(metadata,invoke,"userData");
        if(userData==null) {
            String userKey = metadata.getBaoMetadata().getUserInfo();
            if( ! StringUtil.isEmpty(userKey) && container.getRoot().hasComponentDef(userKey)){
                userData = container.getRoot().getComponent(userKey);
            }
        }
        return userData;
    }
    
    public Object getInvokeData(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke) {
        Object data = getNameData(invokeMetadata,invoke,"data");
        if(data==null) {
            createBaoExcep("EBRI0027",invoke);
        }
        Class targetDto = invokeMetadata.getBaoMetadata().getTargetDto();
        if(targetDto != null && classDefUtil.getClazz(data).isAssignableFrom(targetDto)) {
            // NoCode
        } else {
            data = convertUseBuriConvert(invokeMetadata,data);
        }
        return data;
    }
    
    protected Object convertUseBuriConvert(BaoInvokeMetadata invokeMetadata,Object data) {
        if(invokeMetadata.getBuriConvert() != null) {
            BuriConvert convert = invokeMetadata.getBuriConvert();
            data = convBuriConvertInfo(data,convert);
        } else {
            Map converters = invokeMetadata.getBaoMetadata().getConverter();
            String className = classDefUtil.getClassName(data);
            if(converters.containsKey(className)) {
                BuriConvert convert = (BuriConvert)converters.get(className);
                data = convBuriConvertInfo(data,convert);
            }
        }
        return data;
    }
    
    protected Object convBuriConvertInfo(Object data,BuriConvert convert) {
        ScriptProcessor processor = new ScriptProcessor();
        HashMap context = new HashMap();
        context.put("data",data);
        String convertOgnl = convert.getConvertOgnl();
        data = processor.getValue(convertOgnl,container.getRoot(),context);
        return data;
    }
    
    public Object getDataFromStatus(BaoStatusMetadata statusMetadata,MethodInvocation invoke) {
        Class retType = invoke.getMethod().getReturnType();
        if(retType.equals(List.class)) {
            return getDataList(statusMetadata,invoke);
        } else if(IsNumberUtil.isNumberType(retType)) {
            return countDataList(statusMetadata,invoke);
        }
        return null;
    }
    
    protected Long countDataList(BaoStatusMetadata statusMetadata, MethodInvocation invoke) {
        String path = getBuriPath(statusMetadata);
        List datas = (List)getNameData(statusMetadata,invoke,"datas");
        Object userData = getUserData(statusMetadata,invoke);
        Map appContext = getAppContext(statusMetadata,invoke,datas,"datas");
        return countByPathAndDatas(path, datas, userData, container.getRoot(),appContext);
    }

    protected Long countByPathAndDatas(String path, List datas, Object userData, S2Container container,Map appContext) {
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData,appContext);
        long count = listUtil.countByPathAndDatas(path,datas);
        backup.restore();
        return new Long(count);
    }

    protected List getDataList(BaoStatusMetadata statusMetadata, MethodInvocation invoke) {
        String path = getBuriPath(statusMetadata);
        Object data = getNameData(statusMetadata,invoke,"data");
        Object userData = getUserData(statusMetadata,invoke);
        Map appContext = getAppContext(statusMetadata,invoke,data,"data");
        return getDataList(path,data,userData, container.getRoot(),appContext);
    }
    
    protected List getDataList(String path,Object dto,Object userData,S2Container container,Map appContext) {
        BuriPath buriPath = new BuriPath(path);
        ContextBackup backup = new ContextBackup();
        backup.backup();
        setupBuriLocalContext(container,userData,appContext);
        List dataList = null;
        if(dto==null) {
            dataList = listUtil.getDataListFromPath(buriPath);
        } else {
            dataList = listUtil.searchDataList(buriPath,dto);
        }
        backup.restore();
        return dataList;
    }

    protected Map getAppContext(BaoFunctionMetadata invokeMetadata,MethodInvocation invoke,Object argData,String dataName) {
        Map appContext = new HashMap();
        int i = 0;
        Object data = null;
        Iterator ite = invokeMetadata.getArgName().iterator();
        while(ite.hasNext()) {
            String name = ite.next().toString();
            if(name.equals(dataName)) {
                data = argData;
            } else {
                data = invoke.getArguments()[i];
            }
            appContext.put(name,data);
            i++;
        }
        return appContext;
    }
    
    protected Object getNameData(BaoFunctionMetadata invokeMetadata,MethodInvocation invoke,String findName) {
        int i = 0;
        Object data = null;
        Object args[] = invoke.getArguments();
        Iterator ite = invokeMetadata.getArgName().iterator();
        while(ite.hasNext()) {
            String name = ite.next().toString();
            if(name.equals(findName)) {
                data = args[i];
            }
            i++;
        }
        return data;
    }
    
    protected String getBuriPath(BaoFunctionMetadata metadata) {
        String act = metadata.getActivityName();
        String process = metadata.getBaoMetadata().getProcess();
        String path = process;
        if(act.length() > 1) {
            path = path + "." + act;
        }
        return path;
    }
    
    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }
    protected BuriBaoException createBaoExcep(String messageCode,MethodInvocation invoke) {
        return new BuriBaoException(messageCode,new Object[]{classDefUtil.getClassName(invoke.getThis()),invoke.getMethod().getName()});
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }
    
}
