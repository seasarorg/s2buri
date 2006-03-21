package org.seasar.buri.examples.gas.action.impl;

import java.util.List;

import org.seasar.buri.examples.gas.bao.GasRateBao;
import org.seasar.buri.examples.gas.dto.PaymentDto;
import org.seasar.buri.examples.gas.entity.MeterCheck;


public class PaymentActionImpl {
    private GasRateBao gasRateBao;
    private PaymentDto paymentDto;
    
    public String pay() {
        MeterCheck criteria = new MeterCheck();
        criteria.setBillCode(this.paymentDto.getBillCode());
        List candidates = this.gasRateBao.findEndBillListByCriteria(criteria);
        if(candidates.isEmpty()) {
            throw new NoSuchBillCodeException(this.paymentDto.getBillCode());
        }
        MeterCheck toProceed = (MeterCheck) candidates.get(0);
        toProceed.setPaymentAmount(this.paymentDto.getPaymentAmount());
        this.gasRateBao.proceed(toProceed);
        return "paymentResult";
    }
    
    public PaymentDto getPaymentDto() {
        return paymentDto;
    }
    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }
    public void setGasRateBao(GasRateBao gasRateBao) {
        this.gasRateBao = gasRateBao;
    }
}

