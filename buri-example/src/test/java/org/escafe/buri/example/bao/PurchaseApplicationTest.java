package org.escafe.buri.example.bao;

import java.util.Calendar;

import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.example.entity.PurchaseApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

@RunWith(Seasar2.class)
public class PurchaseApplicationTest {

	public TestContext ctx;

	@Test
	public void test() {
		Calendar ca = Calendar.getInstance();
		BuriAutoSelectProcessor processor = ctx
				.getComponent(BuriAutoSelectProcessor.class);
		PurchaseApplication pa = new PurchaseApplication();
		pa.applicantName = "加藤";
		pa.productName = "ノート";
		pa.amount = 1;
		pa.createDate = ca.getTime();
		pa.applicationDate = ca.getTime();
		pa.updateDate = ca.getTime();
		processor.toNextStatus("購入管理.購入申請.申請開始", pa, null);
		processor.toNextStatus("購入管理.購入申請.承認済み", pa, null);
		processor.toNextStatus("購入管理.購入申請.決済済み", pa, null);
		processor.toNextStatus("購入管理.購入申請.確認済み", pa, null);
	}
}
