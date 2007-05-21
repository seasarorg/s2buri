/*
 * 作成日: 2006/07/10
 *
 */
package org.escafe.buri.common.delayloader;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.seasar.extension.unit.S2TestCase;

public class DelayLoaderTest extends S2TestCase {
    private String PATH = "DelayLoaderTest.dicon";

    public DelayLoaderTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testNomalReader() throws Exception {
        FileReader fileReader = (FileReader)getComponent("NomalReader");
        File tgt = File.createTempFile("buri",".test");
        tgt.deleteOnExit();
        PrintWriter writer = new PrintWriter(new FileWriter(tgt));
        writer.print("test01");
        writer.close();
        
        fileReader.loadFromFile(tgt.getPath(),"test");
        String data = fileReader.getFile("test");
        
        assertEquals("test01",data);
        writer = new PrintWriter(new FileWriter(tgt));
        writer.print("test02");
        writer.close();
        
        data = fileReader.getFile("test");
        assertEquals("test01",data);
    }
    
    
    public void testDelayReader() throws Exception {
        FileReader fileReader = (FileReader)getComponent("DelayReader");
        File tgt = File.createTempFile("buri",".test");
        tgt.deleteOnExit();
        PrintWriter writer = new PrintWriter(new FileWriter(tgt));
        writer.print("test01");
        writer.close();
        
        fileReader.loadFromFile(tgt.getPath(),"test");
        String data = fileReader.getFile("test");
        
        assertEquals("test01",data);
        writer = new PrintWriter(new FileWriter(tgt));
        writer.print("test02");
        writer.close();
        
        data = fileReader.getFile("test");
//        assertEquals("test02",data);
    }
    
    

}
