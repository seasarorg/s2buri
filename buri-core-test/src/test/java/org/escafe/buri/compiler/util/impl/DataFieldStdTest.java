/*
 * 作成日: 2006/07/05
 *
 */
package org.escafe.buri.compiler.util.impl;

import org.escafe.buri.common.util.BuriConfiguration;
import org.escafe.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.escafe.buri.exception.BuriDataFieldErrorException;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;

public class DataFieldStdTest extends S2TestCase {
	private final String PATH = "buri/dicon/buri-share.dicon";

	private BuriDataFieldCompilePreprocessor bdfcp;

	private BuriConfiguration configuration;

	public DataFieldStdTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(PATH);
		include("org/escafe/buri/dicon/allTestService.dicon");
	}

	public void testDataFieldType01() {
		BuriDataFieldType dft = new BuriDataFieldType();
		dft.setId("org.escafe.buri.entity.BuriTestINT");
		setBuriDataFieldType(dft, "pkey", "testId,testId!=0");
		setBuriDataFieldType(dft, "insert", "service.insert(#data)");
		setBuriDataFieldType(dft, "update", "service.update(#data)");
		setBuriDataFieldType(dft, "select", "service.select(#data)");
		setBuriDataFieldType(dft, "delete", "service.delete(#data)");
		setBuriDataFieldType(dft, "tableName", "BuriTestINT");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		assertEquals(dst.getKeys().toString(), "{testId=testId!=0}");
		assertEquals(dst.getInsert(), "service.insert(#data)");
		assertEquals(dst.getUpdate(), "service.update(#data)");
		assertEquals(dst.getSelect(), "service.select(#data)");
		assertEquals(dst.getDelete(), "service.delete(#data)");
		assertEquals(dst.getTableName(), "BuriTestINT");
	}

	public void testDataFieldType04() {
		BuriDataFieldType dft = new BuriDataFieldType();
		dft.setId("org.escafe.buri.entity.BuriTestINT");
		setBuriDataFieldType(dft, "pkey", "id,id!=0");
		setBuriDataFieldType(dft, "insert", "service.insert(#data)");
		setBuriDataFieldType(dft, "update", "service.update(#data)");
		setBuriDataFieldType(dft, "select", "service.select(#data)");
		setBuriDataFieldType(dft, "delete", "service.delete(#data)");
		setBuriDataFieldType(dft, "tableName", "BuriTestINT");
		try {
			bdfcp.preprocess(dft);
			fail();
		} catch (BuriDataFieldErrorException ex) {
			assertTrue(true);
		} catch (PropertyNotFoundRuntimeException ex) {
			assertTrue(true);
		}
	}

	public void testDataFieldType05() {
		BuriDataFieldType dft = new BuriDataFieldType();
		dft.setId("org.escafe.buri.entity.BuriTestINT");
		setBuriDataFieldType(dft, "preprocess", "service.select(#data)");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		assertEquals(dst.getPreprocess(), "service.select(#data)");
	}

	public void testDataFieldType06() {
		BuriDataFieldType dft = new BuriDataFieldType();
		dft.setId("org.escafe.buri.entity.BuriTestINT");
		setBuriDataFieldType(dft, "preprocess", "service.select(#data)");
		setBuriDataFieldType(dft, "pkey", "testId,testId!=0");
		setBuriDataFieldType(dft, "insert", "service.insert(#data)");
		setBuriDataFieldType(dft, "update", "service.update(#data)");
		setBuriDataFieldType(dft, "select", "service.select(#data)");
		setBuriDataFieldType(dft, "delete", "service.delete(#data)");
		setBuriDataFieldType(dft, "tableName", "BuriTestINT");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		assertEquals(dst.getKeys().toString(), "{testId=testId!=0}");
		assertEquals(dst.getInsert(), "service.insert(#data)");
		assertEquals(dst.getUpdate(), "service.update(#data)");
		assertEquals(dst.getSelect(), "service.select(#data)");
		assertEquals(dst.getDelete(), "service.delete(#data)");
		assertEquals(dst.getPreprocess(), "service.select(#data)");
	}

	public void testDataFieldType07() {
		BuriDataFieldType dft = new BuriDataFieldType();
		configuration.addValue("DtoPackageName", "org.escafe.buri.entity");
		dft.setId("BuriTestINT");
		setBuriDataFieldType(dft, "pkey", "testId,testId!=0");
		setBuriDataFieldType(dft, "insert", "service.insert(#data)");
		setBuriDataFieldType(dft, "update", "service.update(#data)");
		setBuriDataFieldType(dft, "select", "service.select(#data)");
		setBuriDataFieldType(dft, "delete", "service.delete(#data)");
		setBuriDataFieldType(dft, "tableName", "BuriTestINT");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		assertEquals(dst.getKeys().toString(), "{testId=testId!=0}");
		assertEquals(dst.getInsert(), "service.insert(#data)");
		assertEquals(dst.getUpdate(), "service.update(#data)");
		assertEquals(dst.getSelect(), "service.select(#data)");
		assertEquals(dst.getDelete(), "service.delete(#data)");
	}

	public void testDataFieldType08() {
		BuriDataFieldType dft = new BuriDataFieldType();
		configuration.addValue("DtoPackageName", "org.escafe.buri.entity");
		configuration.addValue("ServicePackageName", "org.escafe.buri.service");
		dft.setId("BuriTestINT");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		System.out.println(dst.getKeys().toString());
		assertEquals(
		    dst.getKeys().toString(),
		    "{testId=testId != null && testId != 0}");
		assertEquals(dst.getInsert(), "buriTestINTService.insert(#data)");
		assertEquals(dst.getUpdate(), "buriTestINTService.update(#data)");
		assertEquals(
		    dst.getSelect(),
		    "buriTestINTService.getBuriTestINT(#data.testId)");
		assertEquals(dst.getDelete(), "buriTestINTService.delete(#data)");
		assertEquals(
		    dst.getSelectMany(),
		    "buriTestINTService.getBuriTestINTByIds(#data)");
	}

	public void testDataFieldType09() {
		BuriDataFieldType dft = new BuriDataFieldType();
		dft.setId("org.escafe.buri.entity.BuriTestINT");
		BuriDataFieldType dst = bdfcp.preprocess(dft);
		System.out.println(dst.getKeys().toString());
		assertEquals(
		    dst.getKeys().toString(),
		    "{testId=testId != null && testId != 0}");
		assertEquals(dst.getInsert(), "buriTestINTService.insert(#data)");
		assertEquals(dst.getUpdate(), "buriTestINTService.update(#data)");
		assertEquals(
		    dst.getSelect(),
		    "buriTestINTService.getBuriTestINT(#data.testId)");
		assertEquals(dst.getDelete(), "buriTestINTService.delete(#data)");
		assertEquals(
		    dst.getSelectMany(),
		    "buriTestINTService.getBuriTestINTByIds(#data)");
	}

	private void setBuriDataFieldType(BuriDataFieldType dft, String name,
	        String val) {
		BuriExtendedAttributeType beat = new BuriExtendedAttributeType();
		beat.setName(name);
		beat.setValue(val);
		dft.addExtendedAttribute(beat);
	}
}
