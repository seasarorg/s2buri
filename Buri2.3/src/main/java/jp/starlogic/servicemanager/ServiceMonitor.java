/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager;

import java.util.List;

public interface ServiceMonitor {
    public static final int WAIT = 0;
    public static final int RUNNING = 1;
    public static final int TERMINATE = 2;
    public static final int NOT_KNOW = -1;
    
    void updateStatus(String serviceName, int status);
    void updateStatus(String serviceName, int status,Throwable throwable);
    Throwable getServiceLastThrowable(String serviceName);
    
    int getServiceStatus(String serviceName);
    OneService getOneService(String serviceName);
    ServiceInfo getServiceInfo(String serviceName);
    Thread getThread(String serviceName);
    
    void setTerminate(String serviceName,boolean isTerminate);
    void setTerminate(String serviceName);
    boolean getIsTerminate(String serviceName);
    
    void addService(String serviceName,OneService oneService);
    void addService(String serviceName);
    List getServiceNames();
    
    public class ServiceInfo {
        private String serviceName;
        private long statusUpdateTime = 0;
        private int status = WAIT;
        private Throwable lastThrowable = null;
        private boolean isTerminate = false;
        private Thread thread = null;
        private OneService oneService = null;
        private ExecuteService executeService = null;
        
        public Throwable getLastThrowable() {
            return lastThrowable;
        }
        public void setLastThrowable(Throwable lastThrowable) {
            this.lastThrowable = lastThrowable;
        }
        public String getServiceName() {
            return serviceName;
        }
        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
        public int getStatus() {
            return status;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        public long getStatusUpdateTime() {
            return statusUpdateTime;
        }
        public void setStatusUpdateTime(long statusUpdateTime) {
            this.statusUpdateTime = statusUpdateTime;
        }
        public boolean isTerminate() {
            return isTerminate;
        }
        public void setTerminate(boolean isTerminate) {
            this.isTerminate = isTerminate;
        }
        public Thread getThread() {
            return thread;
        }
        public void setThread(Thread thread) {
            this.thread = thread;
        }
        public OneService getOneService() {
            return oneService;
        }
        public void setOneService(OneService oneService) {
            this.oneService = oneService;
        }
        public ExecuteService getExecuteService() {
            return executeService;
        }
        public void setExecuteService(ExecuteService executeService) {
            this.executeService = executeService;
        }
        
        public Object clone() {
            ServiceInfo info = new ServiceInfo();
            info.serviceName = serviceName;
            info.statusUpdateTime = statusUpdateTime;
            info.status = status;
            info.lastThrowable = lastThrowable;
            info.isTerminate = isTerminate;
            info.thread = thread;
            info.oneService = oneService;
            info.executeService = executeService;
            return info;
        }
        
        public String toString() {
            StringBuffer buff = new StringBuffer("[");
            buff.append("serviceName=").append(serviceName);
            buff.append("/statusUpdateTime=").append(statusUpdateTime);
            buff.append("/status=").append(status);
            buff.append("/lastThrowable=").append(lastThrowable);
            buff.append("/isTerminate=").append(isTerminate);
            buff.append("/thread=").append(thread);
            buff.append("/oneService=").append(oneService);
            buff.append("/executeService=").append(executeService);
            buff.append("]");
            return buff.toString();
        }
    }
}
