/*
 * çÏê¨ì˙: 2005/12/27
 *
 */
package jp.starlogic.svm;

import org.seasar.extension.unit.S2TestCase;

import test.sampleservice.CountCronService;

public class CountCronTest extends S2TestCase {
    private String PATH="jp/starlogic/svm/Cron.dicon";

    public CountCronTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testCountUp() throws InterruptedException {
        CountCronService service = (CountCronService)getComponent(CountCronService.class);
        Thread.sleep(120000);
        assertEquals(service.getCount(),2);
    }

}
