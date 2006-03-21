package org.seasar.buri.examples.gas.service;

import org.seasar.buri.examples.gas.entity.MeterCheck;

public interface BillingService {
    MeterCheck createBill(MeterCheck meterCheck);
}
