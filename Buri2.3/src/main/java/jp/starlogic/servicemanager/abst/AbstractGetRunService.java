/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.abst;

import jp.starlogic.servicemanager.OneService;
import jp.starlogic.servicemanager.ServiceMonitor;

public abstract class AbstractGetRunService implements OneService {
    private ServiceMonitor monitor;
    private String serviceName;

    private long waitTime = 10000;
    private long runningTime = 0;
    private long stopCheckInterval = 60000;
    private boolean canReExecute = true;

    public void setServiceMonitor(ServiceMonitor monitor) {
        this.monitor = monitor;
    }

    public ServiceMonitor getServiceMonitor() {
        return this.monitor;
    }

    public void initService() {
    }

    protected void updateStatus() {
        updateStatus(ServiceMonitor.RUNNING);
    }

    protected void updateStatus(int status) {
        monitor.updateStatus(serviceName, status);
    }

    protected void updateStatus(Throwable throwable) {
        updateStatus(ServiceMonitor.TERMINATE, throwable);
    }

    protected void updateStatus(int status, Throwable throwable) {
        monitor.updateStatus(serviceName, status, throwable);
    }

    public void destroyService() {
    }

    public void abortProcessed() {

    }

    public boolean canReExecute() {
        return canReExecute;
    }

    public ServiceMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(ServiceMonitor monitor) {
        this.monitor = monitor;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isCanReExecute() {
        return canReExecute;
    }

    public void setCanReExecute(boolean canReExecute) {
        this.canReExecute = canReExecute;
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    public long getStopCheckInterval() {
        return stopCheckInterval;
    }

    public void setStopCheckInterval(long stopCheckInterval) {
        this.stopCheckInterval = stopCheckInterval;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

}
