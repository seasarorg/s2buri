package example.org.escafe.buri.dto;

public class CustomerDto {
	public static final String TABLE = "CUSTOMER";

	public static final String customerId_Id =
	    "sequence, sequenceName=customerId";

	private Long customerId;

	private String customerName = "";

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/customerId=").append(customerId);
		buff.append("/customerName=").append(customerName);
		buff.append("]");
		return buff.toString();
	}
}
