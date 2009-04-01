package example.org.escafe.buri.dto;


public class CustomerDto {
	public static final String TABLE = "Customer";

	public static final String customerID_ID = "sequence, sequenceName=customerID";
	private long customerID;
	private String customerName = "";
	
	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
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
		buff.append("/customerID=").append(customerID);
		buff.append("/customerName=").append(customerName);
		buff.append("]");
		return buff.toString();
	}
	
}
