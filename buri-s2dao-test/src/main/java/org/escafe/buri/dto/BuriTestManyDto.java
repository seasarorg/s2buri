/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dto;

public class BuriTestManyDto {
	public static final String TABLE = "BURI_TEST_MANY";

	private Long testId01;

	private Long testId02;

	private String value;

	private Long versionNo;

	public BuriTestManyDto() {
	}

	public Long getTestId01() {
		return testId01;
	}

	public void setTestId01(Long testId01) {
		this.testId01 = testId01;
	}

	public Long getTestId02() {
		return testId02;
	}

	public void setTestId02(Long testId02) {
		this.testId02 = testId02;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
}
