/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.entity.BuriTestINT;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;

public class wakanagoTest_wp1_org_seasar_buri_entity_BuriTestINT extends
        AbstDataAccessUtilLongKey implements DataAccessUtilLongKey {
	public List get(List keyVals) {
		String execScript = "buriTestINTService.getBuriTestINTByIds(#data)";
		return getDataList(keyVals, execScript);
	}

	public Long getKey(Object key) {
		return getLongPkey(key, "testID");
	}

	public Object getObjectFromKey(Long keyObj) {
		BuriTestINT dto = new BuriTestINT();
		pkeyExpressionScript.setVal(dto, "testId", keyObj, null);
		return getDataFromDto(dto);
	}

	public Object Store(Object data) {
		String execScript = "";
		if (hasAvailableKey(data)) {
			execScript = "buriTestINTService.update(#data)";
		} else {
			execScript = "buriTestINTService.insert(#data)";
		}
		runScript(data, execScript);
		return data;
	}

	public boolean hasAvailableKey(Object keyVal) {
		Object evalResult =
		    pkeyExpressionScript.eval(
		        keyVal,
		        "testId != null && testId != 0",
		        null);
		return ((Boolean) evalResult).booleanValue();
	}

	public int delete(Object data) {
		String execScript = "BuriTestINTDao.delete(#data)";
		return deleteData(data, execScript);
	}

	public Object getDataFromDto(Object keyVal) {
		BuriTestINT dto = (BuriTestINT) keyVal;
		String execScript = "buriTestINTService.getBuriTestINT(#data.testId)";
		return runScript(dto, execScript);
	}

	public String getTableName(Object data) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
