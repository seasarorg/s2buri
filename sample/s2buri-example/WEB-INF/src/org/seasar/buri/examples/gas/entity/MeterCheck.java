package org.seasar.buri.examples.gas.entity;

import java.io.Serializable;
import java.util.Date;


public class MeterCheck implements Serializable {
    public static final String meterCheckId_ID = "sequence, sequenceName=meterCheckId";

    private long meterCheckId;
    private Date meterCheckDate;
    private int meterIndication;
    
    private String billCode;
    private int billAmount;
    private int paymentAmount;
    
    public static final int customer_RELNO = 0;
    public static final String customer_RELKEYS = "customerId:customerId";
    private long customerId;
    private Customer customer;
    
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public Date getMeterCheckDate() {
        return meterCheckDate;
    }
    public void setMeterCheckDate(Date meterCheckDate) {
        this.meterCheckDate = meterCheckDate;
    }
    public long getMeterCheckId() {
        return meterCheckId;
    }
    public void setMeterCheckId(long meterCheckId) {
        this.meterCheckId = meterCheckId;
    }
    public int getMeterIndication() {
        return meterIndication;
    }
    public void setMeterIndication(int meterIndication) {
        this.meterIndication = meterIndication;
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
    public int getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
