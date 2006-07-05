/*
 * çÏê¨ì˙: 2006/07/05
 *
 */
package org.seasar.buri.compiler.util.impl;

import org.seasar.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.seasar.buri.exception.BuriDataFieldErrorException;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.seasar.extension.unit.S2TestCase;

public class DataFieldStdTest extends S2TestCase {
    private String PATH = "buri/dicon/buri-share.dicon";
    private BuriDataFieldCompilePreprocessor bdfcp;
    
    public DataFieldStdTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testDataFieldType01() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
        setBuriDataFieldType(dft,"pkey","testID,testID!=0");
        setBuriDataFieldType(dft,"insert","dao.insert(#data)");
        setBuriDataFieldType(dft,"update","dao.update(#data)");
        setBuriDataFieldType(dft,"select","dao.select(#data)");
        setBuriDataFieldType(dft,"delete","dao.delete(#data)");
        
        BuriDataFieldType dst = bdfcp.preprocess(dft);

        assertEquals(dst.getKeys().toString(),"{testID=testID!=0}");
        assertEquals(dst.getInsert(),"dao.insert(#data)");
        assertEquals(dst.getUpdate(),"dao.update(#data)");
        assertEquals(dst.getSelect(),"dao.select(#data)");
        assertEquals(dst.getDelete(),"dao.delete(#data)");
    }
    
    public void testDataFieldType02() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
        setBuriDataFieldType(dft,"pkey","testID,testID!=0");
//        setBuriDataFieldType(dft,"insert","dao.insert(#data)");
        setBuriDataFieldType(dft,"update","dao.update(#data)");
        setBuriDataFieldType(dft,"select","dao.select(#data)");
        setBuriDataFieldType(dft,"delete","dao.delete(#data)");
        
        try{
            bdfcp.preprocess(dft);
            fail();
        } catch(BuriDataFieldErrorException ex) {
            assertTrue(true);
        }
    }
    
    public void testDataFieldType03() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
//        setBuriDataFieldType(dft,"pkey","id,id!=0");
        setBuriDataFieldType(dft,"insert","dao.insert(#data)");
        setBuriDataFieldType(dft,"update","dao.update(#data)");
        setBuriDataFieldType(dft,"select","dao.select(#data)");
        setBuriDataFieldType(dft,"delete","dao.delete(#data)");
        
        try{
            bdfcp.preprocess(dft);
            fail();
        } catch(BuriDataFieldErrorException ex) {
            assertTrue(true);
        }
    }
    
    public void testDataFieldType04() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
        setBuriDataFieldType(dft,"pkey","id,id!=0");
        setBuriDataFieldType(dft,"insert","dao.insert(#data)");
        setBuriDataFieldType(dft,"update","dao.update(#data)");
        setBuriDataFieldType(dft,"select","dao.select(#data)");
        setBuriDataFieldType(dft,"delete","dao.delete(#data)");
        
        try{
            bdfcp.preprocess(dft);
            fail();
        } catch(BuriDataFieldErrorException ex) {
            assertTrue(true);
        }
    }
    
    
    public void testDataFieldType05() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
        setBuriDataFieldType(dft,"preprocess","dao.select(#data)");
        
        BuriDataFieldType dst = bdfcp.preprocess(dft);

        assertEquals(dst.getPreprocess(),"dao.select(#data)");
    }
    
    public void testDataFieldType06() {
        BuriDataFieldType dft = new BuriDataFieldType();
        dft.setId("org.seasar.buri.dto.BuriTestINTDto");
        setBuriDataFieldType(dft,"preprocess","dao.select(#data)");
        setBuriDataFieldType(dft,"pkey","testID,testID!=0");
        setBuriDataFieldType(dft,"insert","dao.insert(#data)");
        setBuriDataFieldType(dft,"update","dao.update(#data)");
        setBuriDataFieldType(dft,"select","dao.select(#data)");
        setBuriDataFieldType(dft,"delete","dao.delete(#data)");
        
        BuriDataFieldType dst = bdfcp.preprocess(dft);

        assertEquals(dst.getKeys().toString(),"{testID=testID!=0}");
        assertEquals(dst.getInsert(),"dao.insert(#data)");
        assertEquals(dst.getUpdate(),"dao.update(#data)");
        assertEquals(dst.getSelect(),"dao.select(#data)");
        assertEquals(dst.getDelete(),"dao.delete(#data)");
        assertEquals(dst.getPreprocess(),"dao.select(#data)");
    }
    
    
    private void setBuriDataFieldType(BuriDataFieldType dft,String name,String val) {
        BuriExtendedAttributeType beat = new BuriExtendedAttributeType();
        beat.setName(name);
        beat.setValue(val);
        dft.addExtendedAttribute(beat);
    }

}
