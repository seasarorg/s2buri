package org.seasar.buri.examples.gas.service.impl;

import java.util.Random;

import org.seasar.buri.examples.gas.entity.MeterCheck;
import org.seasar.buri.examples.gas.logic.GasRateLogic;
import org.seasar.buri.examples.gas.service.BillingService;


public class BillingServiceImpl implements BillingService {
    private GasRateLogic rule;
    private Random random = new Random();

    public MeterCheck createBill(MeterCheck meterCheck) {
        meterCheck.setBillCode(String.valueOf(random.nextInt(10000)));
        meterCheck.setBillAmount(rule.calculate(meterCheck.getMeterIndication()));
        return meterCheck;
    }

    public void setRule(GasRateLogic rule) {
        this.rule = rule;
    }
}
