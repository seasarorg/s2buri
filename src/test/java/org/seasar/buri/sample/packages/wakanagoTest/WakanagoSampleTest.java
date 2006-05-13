/*
 * 作成日: 2006/03/31
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import java.util.HashMap;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.WakanagoEngine;
import org.seasar.extension.unit.S2TestCase;

public class WakanagoSampleTest extends S2TestCase {

    public WakanagoSampleTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("WakanagoCompile.dicon");
    }
    
    public void testWakanagoTest() {
        WakanagoEngine engine = (WakanagoEngine)getComponent(WakanagoEngine.class);
        
        engine.readWorkFlowFromResource("wakanagoxpdl/wakanagoTest.xpdl","wakanagoTest");
        
        HashMap userData = new HashMap();
        userData.put("val",new Long(1));
        long start = System.currentTimeMillis();
        BuriUserContext userContext = engine.createUserContext(userData,null,null);
        BuriSystemContext sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.開始",userContext);
        engine.execute(sysContext);
        HashMap data = (HashMap)sysContext.getUserContext().get("data");
        assertEquals(data.get("abc"),"abc");
        System.out.println(sysContext.getUserContext());
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.Stop1",userContext);
        engine.execute(sysContext);
        sysContext = engine.createSystemContext("wakanagoTest.ワカナゴテスト.Stop2",userContext);
        engine.execute(sysContext);
        long end = System.currentTimeMillis();
        System.out.println("ProcessTime = " + (end-start) + "ms");
    }

}
