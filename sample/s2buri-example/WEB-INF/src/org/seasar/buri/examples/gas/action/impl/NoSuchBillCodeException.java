package org.seasar.buri.examples.gas.action.impl;

public class NoSuchBillCodeException extends RuntimeException {
    private String billCode;

    public String getBillCode() {
        return billCode;
    }

    public NoSuchBillCodeException(String billCode) {
        super("該当する請求番号が存在しません : [" + billCode + "]");
        this.billCode = billCode;
    }
}
