package org.seasar.buri.examples.gas.action.impl;

import org.seasar.buri.examples.gas.dto.PaymentDto;

public class BillListActionImpl {
    private String billCode;
    private int billAmount;
    private String customerName;
    private PaymentDto paymentDto;
    
    public String goPayment() {
        this.paymentDto.setBillCode(this.billCode);
        this.paymentDto.setBillAmount(this.billAmount);
        this.paymentDto.setCustomerName(this.customerName);
        return "paymentForm";
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }
    public PaymentDto getPaymentDto() {
        return paymentDto;
    }
    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }
    public int getBillAmount() {
        return billAmount;
    }
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    public String getBillCode() {
        return billCode;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
}
