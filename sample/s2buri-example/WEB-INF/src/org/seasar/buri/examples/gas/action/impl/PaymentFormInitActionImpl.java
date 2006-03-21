package org.seasar.buri.examples.gas.action.impl;

import org.seasar.buri.examples.gas.dto.PaymentDto;

public class PaymentFormInitActionImpl {
    private PaymentDto paymentDto;
    
    public String initialize() {
        return null;
    }

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }
    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }
}
