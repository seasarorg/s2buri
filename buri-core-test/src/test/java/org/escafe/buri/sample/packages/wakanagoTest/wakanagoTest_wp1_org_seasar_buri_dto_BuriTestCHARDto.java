/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.entity.BuriTestCHAR;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestCHARDto extends
        AbstDataAccessUtilManyKey implements DataAccessUtilManyKey {
	private static String params[] = new String[] { "testId" };

	private static String condition[] =
	    new String[] { "testId != null && testId != \"\"" };

	@Override
	protected String[] getConditions() {
		return condition;
	}

	public List get(List keyStr) {
		String execScript = "";
		return getDataList(keyStr, execScript);
	}

	public String getKey(Object key) {
		return getKey(key, params);
	}

	protected BuriTestCHAR convKeyStringToObj(String keyObj) {
		BuriTestCHAR dto = new BuriTestCHAR();
		setStringKeyToObj(dto, keyObj);
		return dto;
	}

	public Object getObjectFromKey(String keyObj) {
		BuriTestCHAR dto = convKeyStringToObj(keyObj);
		return getDataFromDto(dto);
	}

	public Object Store(Object data) {
		String execScript = "";
		if (hasPkey(data, condition)) {
			execScript = "buriTestCHARService.update(#data)";
		} else {
			execScript = "buriTestCHARService.insert(#data)";
		}
		runScript(data, execScript);
		return data;
	}

	public int delete(Object data) {
		String execScript = "buriTestCHARService.delete(#data)";
		return deleteData(data, execScript);
	}

	public Object getDataFromDto(Object keyVal) {
		String execScript = "buriTestCHARService.getBuriTestCHAR(#data.testId)";
		return runScript(keyVal, execScript);
	}

	public String getTableName(Object data) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
