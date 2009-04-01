package org.escafe.buri.dto;

public class BuriTestINTDto {
	public static final String TABLE = "BURI_TEST_INT";

	public static final String testId_ID =
	    "sequence, sequenceName=BURI_TEST_INT_SEQ";

	private Long testId;

	private String value = "";

	private Long versionNo;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
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

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/testId=").append(testId);
		buff.append("/value=").append(value);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
}
