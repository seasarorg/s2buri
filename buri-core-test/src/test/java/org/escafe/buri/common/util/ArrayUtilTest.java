package org.escafe.buri.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.extension.unit.S2TestCase;

public class ArrayUtilTest extends S2TestCase {
	public ArrayUtilTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testProcOgnl() {
		List<Map<String, Long>> list = new ArrayList<Map<String, Long>>();
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("data", new Long(1));
		data.put("data", new Long(5));
		list.add(data);
		Object obj =
		    ArrayUtil.procOgnl(
		        list,
		        null,
		        "#arg.result.add(#data.data+1),#arg.sum = #arg.sum+#data.data",
		        "#arg",
		        "#arg = #{}, #arg.sum = 0,#arg.result={}",
		        getContainer());
		assertTrue(obj instanceof Map);
		System.out.println(obj);
		assertEquals("{sum=5, result=[6]}", obj.toString());
	}
}
