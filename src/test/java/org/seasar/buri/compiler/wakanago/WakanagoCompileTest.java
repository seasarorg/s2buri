/*
 * çÏê¨ì˙: 2006/04/04
 *
 */
package org.seasar.buri.compiler.wakanago;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.extension.unit.S2TestCase;

public class WakanagoCompileTest extends S2TestCase {
    private String PATH = "WakanagoCompile.dicon";
    private BuriCompiler compiler_;
    public WakanagoCompileTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testCompile() {
        BuriExePackages exePackages = compiler_.CompileResource("wakanagoxpdl/wakanagoTest.xpdl",null);
        assertNotNull(exePackages);
    }

}
