/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.service;

import java.util.Iterator;
import java.util.List;

import jp.starlogic.servicemanager.ServiceManager;
import jp.starlogic.servicemanager.ServiceMonitor;
import jp.starlogic.servicemanager.ServiceMonitor.ServiceInfo;
import jp.starlogic.servicemanager.abst.AbstractGetRunService;

import org.seasar.framework.log.Logger;

public class StartupService extends AbstractGetRunService {
    private static Logger logger = Logger.getLogger(StartupService.class);
    private ServiceMonitor monitor;
    private ServiceManager manager;
    
    public boolean canService() {
        return true;
    }

    public void execute() {
        List smNames = monitor.getServiceNames();
        Iterator ite = smNames.iterator();
        while(ite.hasNext()) {
            String smName = ite.next().toString();
            ServiceInfo info = monitor.getServiceInfo(smName);
            statusUpdate(info,smName);
        }
        
    }

    private void statusUpdate(ServiceInfo info,String smName) {
        if(info.getStatus() == ServiceMonitor.WAIT) {
            statusIsWait(info,smName);
        }else if(info.getStatus() == ServiceMonitor.RUNNING) {
            statusIsRunning(info);
        }else if(info.getStatus() == ServiceMonitor.TERMINATE) {
            statusIsTerminate(info,smName);
        } else {
            stopCheckAndTerminate(info);
            // 他のパターンではすべて待機
        }
    }
    
    protected void statusIsTerminate(ServiceInfo info,String smName) {
        if(info.getThread().isAlive() == false && info.getOneService().canReExecute() == true) {
            manager.executeService(smName);
            if(logger.isDebugEnabled()) {
                logger.debug("statusUpdate reRun" + info);
            }
        }
    }
    
    protected void statusIsRunning(ServiceInfo info) {
        if(info.getThread().isAlive() == false) {
            if(logger.isDebugEnabled()) {
                logger.debug("statusUpdate Thread is dead" + info);
            }
            info.getExecuteService().terminate();
        }
    }
    
    protected void statusIsWait(ServiceInfo info,String smName) {
        if(info.getThread().isAlive()) {
            logger.fatal("statusUpdate getThread().isAlive()!! "+info);
        } else {
            manager.executeService(smName);
        }
    }
    
    protected void stopCheckAndTerminate(ServiceInfo info) {
        if(info.getOneService().getStopCheckInterval() <= 0) {
            return;
        }
        long aliveCheck = getTimeInMillis() - info.getStatusUpdateTime();
        if(aliveCheck >= info.getOneService().getStopCheckInterval() ) {
            info.getExecuteService().terminate();
            if(logger.isDebugEnabled()) {
                logger.debug("statusUpdate TimeOrver" + info);
            }
        }
    }

    public void destroyService() {
        if(logger.isDebugEnabled()) {
            logger.debug("destroyService");
        }
        List smNames = monitor.getServiceNames();
        Iterator ite = smNames.iterator();
        while(ite.hasNext()) {
            String smName = ite.next().toString();
            ServiceInfo info = monitor.getServiceInfo(smName);
            info.getExecuteService().terminate();
        }
    }

    protected long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    public ServiceManager getManager() {
        return manager;
    }

    public void setManager(ServiceManager manager) {
        this.manager = manager;
    }

    public ServiceMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(ServiceMonitor monitor) {
        this.monitor = monitor;
    }

}
