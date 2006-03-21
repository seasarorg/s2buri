package org.seasar.buri.examples.gas.action.impl;

public class NoSuchBillCodeException extends RuntimeException {
    private String billCode;

    public String getBillCode() {
        return billCode;
    }

    public NoSuchBillCodeException(String billCode) {
        super("�Y�����鐿���ԍ������݂��܂��� : [" + billCode + "]");
        this.billCode = billCode;
    }
}
