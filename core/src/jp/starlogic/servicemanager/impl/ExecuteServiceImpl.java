/*
 * ì¬“ú: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.impl;

import java.util.GregorianCalendar;

import jp.starlogic.servicemanager.ExecuteService;
import jp.starlogic.servicemanager.OneService;
import jp.starlogic.servicemanager.ServiceMonitor;
import jp.starlogic.servicemanager.exception.ConfigureRuntimeException;

import org.seasar.framework.log.Logger;

public class ExecuteServiceImpl implements ExecuteService {
    private static Logger logger = Logger.getLogger(ExecuteServiceImpl.class);
    private ServiceMonitor monitor;
    private String serviceName;
    private boolean isTerminate = true;

    public void clear() {
        isTerminate = true;
    }

    public void terminate() {
        monitor.setTerminate(serviceName);
        isTerminate = false;
        if(logger.isDebugEnabled()) {
            logger.debug("terminate "+serviceName);
        }
    }

    public void run() {
        serviceStartUp();
        OneService service = monitor.getOneService(serviceName);
        long waitTime = service.getWaitTime();
        long runningTime = service.getRunningTime();
        if(waitTime != 0) {
            runWaitTime(service,waitTime);
        } else if(runningTime != 0){
            runRunningTime(service,runningTime);
        } else {
            throw new ConfigureRuntimeException("ESVM002");
        }
        serviceTerminate();
    }
    
    protected void serviceStartUp() {
        if(logger.isDebugEnabled()) {
            logger.debug("START "+serviceName);
        }
        monitor.updateStatus(serviceName,ServiceMonitor.RUNNING);
    }
    
    protected void serviceTerminate() {
        if(logger.isDebugEnabled()) {
            logger.debug("END "+serviceName);
        }
    }
    
    protected void sleep(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            // Š„‚èž‚Ý‚É‚æ‚ésleep‚Ì’†Ž~‚ðl—¶‚µ‚Ä—áŠO‚ð–³Ž‹
        }
    }
    
    protected long getTimeInMillis() {
        return GregorianCalendar.getInstance().getTimeInMillis();
    }
    
    protected void runWaitTime(OneService service,long waitTime) {
        boolean isNotThrowEx = true;
        init(service);
        while(isTerminate) {
            isNotThrowEx = processed(service);
            if(isNotThrowEx==false) {break;}
            sleep(waitTime);
        }
        destroy(service,isNotThrowEx);
    }
    
    protected void runRunningTime(OneService service,long runningTime) {
        boolean isNotThrowEx = true;
        init(service);
        while(isTerminate) {
            long start = getTimeInMillis();
            isNotThrowEx = processed(service);
            if(isNotThrowEx==false) {break;}
            long processTime = getTimeInMillis() - start;
            long waitTime = runningTime - processTime;
            if(logger.isDebugEnabled() && ! serviceName.equals("StartupService")) {
                logger.debug("WaitTime("+serviceName+")="+waitTime);
            }
            if(waitTime>0) {
                sleep(waitTime);
            }
        }
        destroy(service,isNotThrowEx);
    }

    public void execute(OneService service) {
        if(service.canService()) {
            service.execute();
        }
    }
    
    protected boolean processed(OneService service) {
        boolean isNotThrowEx = true;
        try {
            startExecute();
            execute(service);
            endExecute();
            monitor.updateStatus(serviceName,ServiceMonitor.RUNNING);
        } catch(Exception ex) {
            logger.fatal(serviceName,ex);
            monitor.updateStatus(serviceName,ServiceMonitor.TERMINATE,ex);
            terminate();
            isNotThrowEx = false;
            service.abortProcessed();
            abortExecute();
        }
        return isNotThrowEx;
    }
    
    protected void startExecute() {
        
    }
    
    protected void endExecute() {
        
    }
    
    protected void abortExecute() {
        
    }
    
    protected void init(OneService service) {
        service.initService();
    }
    
    protected void destroy(OneService service,boolean isNotThrowEx) {
        if(isNotThrowEx) {
            if(service.canReExecute()) {
                monitor.updateStatus(serviceName,ServiceMonitor.WAIT);
                if(logger.isDebugEnabled()) {
                    logger.debug("destroy "+serviceName + "/Next Status=WAIT");
                }
            } else {
                monitor.updateStatus(serviceName,ServiceMonitor.TERMINATE);
                if(logger.isDebugEnabled()) {
                    logger.debug("destroy "+serviceName + "/Next Status=TERMINATE");
                }
            }
        }
        service.destroyService();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceMonitor getMonitor() {
        return monitor;
    }

    public void setMonitor(ServiceMonitor monitor) {
        this.monitor = monitor;
    }

    public boolean isTerminate() {
        return isTerminate;
    }

}
