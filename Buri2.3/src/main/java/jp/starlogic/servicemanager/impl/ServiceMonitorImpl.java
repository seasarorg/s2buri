/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.starlogic.servicemanager.ExecuteService;
import jp.starlogic.servicemanager.OneService;
import jp.starlogic.servicemanager.ServiceMonitor;

import org.seasar.framework.container.S2Container;

public class ServiceMonitorImpl implements ServiceMonitor {
   
    private Map services = new HashMap();
    private List keyList = new ArrayList();
    private S2Container container;

    public void updateStatus(String serviceName, int status) {
        updateStatus(serviceName, status, null);
    }

    public void updateStatus(String serviceName, int status, Throwable throwable) {
        ServiceInfo info;
        synchronized (this) {
            if(services.containsKey(serviceName)) {
                info =(ServiceInfo)services.get(serviceName);
                info.setStatus(status);
                info.setLastThrowable(throwable);
                info.setStatusUpdateTime(GregorianCalendar.getInstance().getTimeInMillis());
            }
        }
    }

    public Throwable getServiceLastThrowable(String serviceName) {
        return getServiceInfo(serviceName).getLastThrowable();
    }

    public int getServiceStatus(String serviceName) {
        return getServiceInfo(serviceName).getStatus();
    }

    public OneService getOneService(String serviceName) {
        return getServiceInfo(serviceName).getOneService();
    }

    public ServiceInfo getServiceInfo(String serviceName) {
        ServiceInfo info=null;
        synchronized (this) {
            if( ! services.containsKey(serviceName)) {
                addService(serviceName);
            }
            if(services.containsKey(serviceName)) {
                info =(ServiceInfo)((ServiceInfo)services.get(serviceName)).clone();
            }
        }
        return info;
    }

    public Thread getThread(String serviceName) {
        ServiceInfo info = getServiceInfo(serviceName);
        Thread thread = null;
        thread = new Thread(info.getExecuteService());
        info.getExecuteService().clear();
        synchronized (this) {
            info = (ServiceInfo)services.get(serviceName);
            info.setThread(thread);
        }
        return thread;
    }

    public void addService(String serviceName, OneService oneService) {
        if(oneService==null) {
            return;
        }
        ServiceInfo info = new ServiceInfo();
        info.setServiceName(serviceName);
        ExecuteService executeService = getExecuteService(serviceName, oneService);
        executeService.setServiceName(serviceName);
        Thread thread = new Thread(executeService);
        info.setExecuteService(executeService);
        info.setOneService(oneService);
        info.setThread(thread);
        synchronized (this) {
            services.put(serviceName,info);
            keyList.add(serviceName);
        }
    }
    
    protected ExecuteService getExecuteService(String serviceName, OneService oneService) {
        return (ExecuteService)container.getComponent(ExecuteService.class);
    }

    public void addService(String serviceName) {
        OneService oneService = (OneService)container.getComponent(serviceName);
        addService(serviceName, oneService);
    }

    public List getServiceNames() {
        List keys = new ArrayList();
        synchronized (this) {
            keys.addAll(keyList);
        }
        return keys;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public void setTerminate(String serviceName, boolean isTerminate) {
        ServiceInfo info;
        synchronized (this) {
            if(services.containsKey(serviceName)) {
                info =(ServiceInfo)services.get(serviceName);
                info.setTerminate(isTerminate);
            }
        }
    }

    public void setTerminate(String serviceName) {
        setTerminate(serviceName, true);
    }

    public boolean getIsTerminate(String serviceName) {
        return getServiceInfo(serviceName).isTerminate();
    }

}
