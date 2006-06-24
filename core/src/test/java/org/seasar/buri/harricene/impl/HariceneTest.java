package org.seasar.buri.harricene.impl;

import java.util.HashMap;

import org.seasar.buri.harricene.DecisionTable;
import org.seasar.buri.harricene.Harricene;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author makoto
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
public class HariceneTest extends S2TestCase {

	/**
	 * Constructor for Haricene.
	 * @param arg0
	 */
	public HariceneTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testDecision() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test01.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Boolean(true));
		vals.put("val2",new Boolean(true));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
		String ret = table.decision(vals,context);
		assertEquals(ret,"A");
	}

	public void testDecision2() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test01.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Boolean(true));
		vals.put("val2",new Boolean(false));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"B");
	}

	public void testDecision3() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test01.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Boolean(false));
		vals.put("val2",new Boolean(true));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"C");
	}

	public void testDecision4() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test01.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Boolean(false));
		vals.put("val2",new Boolean(false));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"D");
	}

	public void testDecision5() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test03.xls";
        harisen.readFile(fileName);
		vals.put("val1","1");
		vals.put("val2","2");
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"A");
	}

	public void testDecision6() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test03.xls";
        harisen.readFile(fileName);
		vals.put("val1","1");
		vals.put("val2","3");
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"B");
	}

	public void testDecision7() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test03.xls";
        harisen.readFile(fileName);
		vals.put("val1","3");
		vals.put("val2","2");
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"C");
	}

	public void testDecision8() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test03.xls";
        harisen.readFile(fileName);
		vals.put("val1","3");
		vals.put("val2","3");
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"D");
	}

	public void testDecision9() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test04.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Integer(1));
		vals.put("val2",new Integer(1));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"A");
	}

	public void testDecision10() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test04.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Integer(1));
		vals.put("val2",new Integer(2));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"B");
	}

	public void testDecision11() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test04.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Integer(2));
		vals.put("val2",new Integer(1));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"C");
	}

	public void testDecision12() {
		Harricene harisen = new HarriceneImpl();
		HashMap context = new HashMap();
		HashMap vals = new HashMap();
		String fileName = "org/seasar/buri/harricene/impl/test04.xls";
        harisen.readFile(fileName);
		vals.put("val1",new Integer(2));
		vals.put("val2",new Integer(2));
        DecisionTable table = (DecisionTable)harisen.get("Sheet1");
        String ret = table.decision(vals,context);
		assertEquals(ret,"D");
	}

}

