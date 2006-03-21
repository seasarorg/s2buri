package org.seasar.buri.examples.gas.dto;

import java.io.Serializable;

public class PaymentDto implements Serializable {
    private String customerName;
    private String billCode;
    private int paymentAmount;
    private int billAmount;
    private int meterIndication;
    
    public int getBillAmount() {
        return billAmount;
    }
    public String getBillCode() {
        return billCode;
    }
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    public int getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setMeterIndication(int meterIndication) {
        this.meterIndication = meterIndication;
    }
    public int getMeterIndication() {
        return meterIndication;
    }
}
