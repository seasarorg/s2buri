/*
 * çÏê¨ì˙: 2005/12/20
 *
 */
package jp.starlogic.svm;

import jp.starlogic.servicemanager.ServiceMonitor;
import jp.starlogic.servicemanager.ServiceMonitor.ServiceInfo;

import org.seasar.extension.unit.S2TestCase;

import test.sampleservice.CounterService;

public class ServiceTest extends S2TestCase {
    private String PATH="jp/starlogic/svm/serviceManager.dicon";
    
    public ServiceTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }

    public void testCounter() throws Exception {
        CounterService service = (CounterService)getComponent(CounterService.class);
        Thread.sleep(10000);
        assertEquals(service.getCounter(),21);
    }

    public void testCounterRestart() throws Exception {
        CounterService service = (CounterService)getComponent(CounterService.class);
        ServiceMonitor  monitor = (ServiceMonitor)getComponent(ServiceMonitor.class);
        Thread.sleep(5000);
        long count1 = service.getCounter();
        ServiceInfo info = monitor.getServiceInfo("CounterService");
        info.getExecuteService().terminate();
        System.out.println("terminate");
        Thread.sleep(5000);
        assertFalse(service.getCounter()==count1);
    
    }
}
