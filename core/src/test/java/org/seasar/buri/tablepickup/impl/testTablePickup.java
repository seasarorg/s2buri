/*
 * 作成日: 2004/08/09
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
package org.seasar.buri.tablepickup.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.seasar.buri.tablepickup.Pickup;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author makotan
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
public class testTablePickup extends S2TestCase {
    private TablePickupImpl tableLoader;
    
	/**
	 * Constructor for testTablePickup.
	 * @param arg0
	 */
	public testTablePickup(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        include("buri2.dicon");
        String fileName = "org/seasar/buri/tablepickup/impl/test01.xls";
        tableLoader = new TablePickupImpl();
        tableLoader.setContainer(getContainer());
        List sheetNames = new ArrayList();
        sheetNames.add("test1");
        sheetNames.add("test2");
        sheetNames.add("test3");
        sheetNames.add("test4");
        sheetNames.add("test5");
        tableLoader.readFile(fileName,sheetNames);
	}

	public void testTableLoader1() {
		assertTrue(tableLoader.containsKey("test1"));
		assertFalse(tableLoader.containsKey("Sheet1"));
	}

	public void testTablePickup1() {
        Pickup pickup = (Pickup)tableLoader.get("test1");

		HashMap argContext = new HashMap();
		argContext.put("a",new Integer(1));
		argContext.put("b",new Integer(1));

		Object obj = pickup.pickup(argContext);
		assertEquals(obj,new Integer(1));

		argContext.put("a",new Integer(3));
		argContext.put("b",new Integer(3));
		obj = pickup.pickup(argContext);
		assertEquals(obj,new Integer(9));

		argContext.put("a",new Integer(3));
		argContext.put("b",new Integer(1));
		obj = pickup.pickup(argContext);
		assertEquals(obj,new Integer(3));
	}

	public void testTablePickup2() {

        Pickup pickup = (Pickup)tableLoader.get("test2");
		HashMap argContext = new HashMap();
		argContext.put("a","1");
		argContext.put("b","1");

		Object obj = pickup.pickup(argContext);
		assertEquals(obj,"a");

		argContext.put("a","3");
		argContext.put("b","3");
		obj = pickup.pickup(argContext);
		assertEquals(obj,"I");

		argContext.put("a","3");
		argContext.put("b","1");
		obj = pickup.pickup(argContext);
		assertEquals(obj,"c");
		
	}

	public void testTablePickupLite1() {
        Pickup pickup = (Pickup)tableLoader.get("test3");
		Object obj = pickup.pickupLite("a",new Integer(1));
		assertEquals(obj,"A");

		obj = pickup.pickupLite("a",new Integer(2));
		assertEquals(obj,"B");

		obj = pickup.pickupLite("b",new Integer(1));
		assertEquals(obj,"D");

		obj = pickup.pickupLite("c",new Integer(1));
		assertEquals(obj,new Integer(100));	
	
	}

	public void testLookup1() {
        Pickup pickup = (Pickup)tableLoader.get("test4");
		HashMap argContext = new HashMap();
		argContext.put("key",new Integer(1));

		Object obj = pickup.lookup(argContext);
		assertEquals(obj,new Integer(10));


		argContext.put("key",new Integer(2));
		obj = pickup.lookup(argContext);
		assertEquals(obj,new Integer(20));

		argContext.put("key",new Integer(6));
		obj = pickup.lookup(argContext);
		assertEquals(obj,new Integer(60));
	}

	public void testLookupLite1() {
        Pickup pickup = (Pickup)tableLoader.get("test5");
		Object obj = pickup.lookupLite(new Integer(1));
		assertEquals(obj,new Integer(10));

		obj = pickup.lookupLite(new Integer(2));
		assertEquals(obj,new Integer(20));

		obj = pickup.lookupLite(new Integer(6));
		assertEquals(obj,new Integer(60));

		obj = pickup.lookupLite(new Integer(6),"Name");
		assertEquals(obj,"オオウオ");

		obj = pickup.lookupLite(new Integer(4));
		assertEquals(obj,new Double(40.56));
	}
	
	
	
	
}
