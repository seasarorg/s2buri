package org.seasar.buri.examples.gas.dto;

import java.io.Serializable;

public class BillReportDto implements Serializable {
    private String billCode;
    private int billAmount;
    private String recipientMailAddress;
    private String customerName;
    private long meterIndication;
    
    public long getMeterIndication() {
        return meterIndication;
    }
    public void setMeterIndication(long meterIndication) {
        this.meterIndication = meterIndication;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getBillAmount() {
        return billAmount;
    }
    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }
    public String getBillCode() {
        return billCode;
    }
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    public String getRecipientMailAddress() {
        return recipientMailAddress;
    }
    public void setRecipientMailAddress(String recipientMailAddress) {
        this.recipientMailAddress = recipientMailAddress;
    }
}
