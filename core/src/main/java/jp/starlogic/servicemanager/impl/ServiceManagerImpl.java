/*
 * çÏê¨ì˙: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.ServiceManager;
import jp.starlogic.servicemanager.ServiceMonitor;
import jp.starlogic.servicemanager.ServiceMonitor.ServiceInfo;

public class ServiceManagerImpl implements ServiceManager{
    private List startupService = new ArrayList();
    private ServiceMonitor monitor;
    
    public void addStartupService(String serviceName) {
        startupService.add(serviceName);
    }

    public void startup() {
        Iterator ite = startupService.iterator();
        while(ite.hasNext()) {
            executeService(ite.next().toString());
        }
    }

    public void terminate() {
        Iterator ite = startupService.iterator();
        while(ite.hasNext()) {
            ServiceInfo info = monitor.getServiceInfo(ite.next().toString());
            info.getExecuteService().terminate();
            try {
                info.getThread().join();
            } catch (InterruptedException e) {
                //èIóπèàóùÇ»ÇÃÇ≈âΩÇ‡ÇπÇ∏Ç…éüÇ÷
            }
        }
    }

    public void executeService(String serviceName) {
        Thread thread = monitor.getThread(serviceName);
        thread.setPriority(Thread.MAX_PRIORITY);
        if(thread.isAlive() == false) {
            thread.start();
        }
    }

    public ServiceMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(ServiceMonitor monitor) {
        this.monitor = monitor;
    }

}
