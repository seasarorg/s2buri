/*
 * 作成日: 2006/05/05
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.entity.BuriTestMany;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilManyKey;

public class wakanagoTest_wp1_org_seasar_buri_entity_BuriTestMany extends
        AbstDataAccessUtilManyKey {
	private static String params[] = new String[] { "testId01", "testId02" };

	private static String condition[] =
	    new String[] {
	        "testId01 != null && testId01 != 0",
	        "testId02 != null && testId02 != 0" };

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

	protected BuriTestMany convKeyStringToObj(String keyObj) {
		BuriTestMany dto = new BuriTestMany();
		setStringKeyToObj(dto, keyObj);
		return dto;
	}

	public Object getObjectFromKey(String keyObj) {
		BuriTestMany dto = convKeyStringToObj(keyObj);
		return getDataFromDto(dto);
	}

	public Object Store(Object data) {
		String execScript = "";
		if (hasPkey(data, condition)) {
			execScript = "buriTestManyService.update(#data)";
		} else {
			execScript = "buriTestManyService.insert(#data)";
		}
		runScript(data, execScript);
		return data;
	}

	public int delete(Object data) {
		String execScript = "buriTestManyService.delete(#data)";
		return deleteData(data, execScript);
	}

	public Object getDataFromDto(Object keyVal) {
		String execScript =
		    "buriTestManyService.getBuriTestMany(#data.testId01 , #data.testId02)";
		return runScript(keyVal, execScript);
	}

	public String getTableName(Object data) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
