package org.seasar.buri.examples.gas.logic.impl;

import java.math.BigDecimal;
import java.util.HashMap;


import org.seasar.buri.examples.gas.logic.GasRateLogic;
import org.seasar.buri.tablepickup.Pickup;
import org.seasar.buri.tablepickup.TablePickup;

public class GasRateLogicImpl implements GasRateLogic {
    private Pickup pickup;

    public GasRateLogicImpl(TablePickup tablePickup) {
        this.pickup = (Pickup)tablePickup.get("rate2");
    }

    public int calculate(int usage) {
        HashMap argContext = new HashMap();
        argContext.put("�g�p��",new Integer(usage));
        BigDecimal basePrice = new BigDecimal(String.valueOf(pickup.lookup(argContext, "��{����")));
        BigDecimal unitPrice = new BigDecimal(String.valueOf(pickup.lookup(argContext, "�P�ʗ���")));
        BigDecimal result = basePrice.add(unitPrice.multiply(BigDecimal.valueOf(usage)));
        return result.setScale(1, BigDecimal.ROUND_DOWN).intValue();
    }
}
