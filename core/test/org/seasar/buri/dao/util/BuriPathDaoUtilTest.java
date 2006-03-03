/*
 * 作成日: 2005/07/01
 *
 */
package org.seasar.buri.dao.util;


import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.extension.unit.S2TestCase;

public class BuriPathDaoUtilTest extends S2TestCase {
    private String PATH = "buri2.dicon";
    private BuriPathDaoUtil buriPathDaoUtil;

    public BuriPathDaoUtilTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        buriPathDaoUtil = (BuriPathDaoUtil)getComponent(BuriPathDaoUtil.class);
    }
    
    public void testBuriPathDaoUtilTx() {
//        BuriPath path = new BuriPath("test.123.456","test.proc1.act1");
//        BuriPath newPath = buriPathDaoUtil.getBuriPath(path);
//        assertEquals(newPath.getPlainName(),path.getPlainName());
//        assertEquals(newPath.getRealPath().getPlainName(),path.getRealPath().getPlainName());
//        assertTrue(newPath.getBuriPathID() != 0);
//
//        BuriPath newPath2 = buriPathDaoUtil.getBuriPath(path);
//        assertEquals(newPath.getBuriPathID(),newPath2.getBuriPathID());
        
        /* BuriPathはエラーチェック対象外にしたので外す
        try{
            BuriPath errorPath = new BuriPath("test.123.456","test.proc1.act1");
            errorPath.setWorkflowProcess("","wf01");
            buriPathDaoUtil.getBuriPath(errorPath);
            fail();
        } catch(BuriPathNotCorrectedException ex) {
            assertTrue(true);
        }
        
        BuriPath dataPath = buriPathDaoUtil.getBuriPath(newPath.getBuriPathID());
        assertEquals(newPath.getPlainName(),dataPath.getPlainName());
        assertEquals(newPath.getRealPath().getPlainName(),dataPath.getRealPath().getPlainName());
        */
    }


}
