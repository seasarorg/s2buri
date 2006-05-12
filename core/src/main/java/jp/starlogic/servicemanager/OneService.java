/*
 * çÏê¨ì˙: 2005/12/20
 *
 */
package jp.starlogic.servicemanager;

public interface OneService {
    
    void setServiceMonitor(ServiceMonitor monitor);
    void setServiceName(String serviceName);
    
    void initService();
    boolean canService();
    void execute();
    void destroyService();
    
    void abortProcessed();

    boolean canReExecute();
    
    long getWaitTime();
    long getRunningTime();
    long getStopCheckInterval();
}
