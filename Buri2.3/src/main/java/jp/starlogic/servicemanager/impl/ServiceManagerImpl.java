/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.ServiceManager;
import jp.starlogic.servicemanager.ServiceMonitor;
import jp.starlogic.servicemanager.ServiceMonitor.ServiceInfo;

import org.seasar.framework.log.Logger;

public class ServiceManagerImpl implements ServiceManager {
    private List<String> startupService = new ArrayList<String>();
    private ServiceMonitor monitor;
    private static Logger log = Logger.getLogger(ServiceManagerImpl.class); 

    public void addStartupService(String serviceName) {
        startupService.add(serviceName);
    }

    public void startup() {
    	log.info("ServiceManager Start");
        Iterator<String> ite = startupService.iterator();
        while (ite.hasNext()) {
            executeService(ite.next().toString());
        }
    }

    public void terminate() {
    	log.info("ServiceManager End");
        Iterator<String> ite = startupService.iterator();
        while (ite.hasNext()) {
            ServiceInfo info = monitor.getServiceInfo(ite.next().toString());
            info.getExecuteService().terminate();
            try {
                info.getThread().join();
            } catch (InterruptedException e) {
                //終了処理なので何もせずに次へ
            }
        }
    }

    public void executeService(String serviceName) {
        Thread thread = monitor.getThread(serviceName);
        thread.setPriority(Thread.MAX_PRIORITY);
        if (thread.isAlive() == false) {
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
