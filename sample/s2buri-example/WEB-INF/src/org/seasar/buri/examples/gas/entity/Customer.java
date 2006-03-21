package org.seasar.buri.examples.gas.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    public static final String customerId_ID = "sequence, sequenceName=customerId";

    private long customerId;
    private String customerCode;
    private String customerName;
    private String mailAddress;

    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
