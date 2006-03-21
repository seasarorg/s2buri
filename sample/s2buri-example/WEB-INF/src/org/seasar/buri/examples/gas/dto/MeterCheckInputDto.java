package org.seasar.buri.examples.gas.dto;

import java.io.Serializable;

public class MeterCheckInputDto implements Serializable {
    private String customerCode;
    private int meterIndication;
    
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public int getMeterIndication() {
        return meterIndication;
    }
    public void setMeterIndication(int meterIndication) {
        this.meterIndication = meterIndication;
    }
}
