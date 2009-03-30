/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dto;

public class BuriTestCHARDto {
	public static final String TABLE = "BURI_TEST_CHAR";

	private String testId;

	private String value;

	private int versionNo;

	public BuriTestCHARDto() {
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
}
