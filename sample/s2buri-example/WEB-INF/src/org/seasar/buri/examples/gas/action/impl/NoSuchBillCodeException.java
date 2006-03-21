package org.seasar.buri.examples.gas.action.impl;

public class NoSuchBillCodeException extends RuntimeException {
    private String billCode;

    public String getBillCode() {
        return billCode;
    }

    public NoSuchBillCodeException(String billCode) {
        super("ŠY“–‚·‚é¿‹”Ô†‚ª‘¶İ‚µ‚Ü‚¹‚ñ : [" + billCode + "]");
        this.billCode = billCode;
    }
}
