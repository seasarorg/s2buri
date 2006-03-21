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
        argContext.put("使用量",new Integer(usage));
        BigDecimal basePrice = new BigDecimal(String.valueOf(pickup.lookup(argContext, "基本料金")));
        BigDecimal unitPrice = new BigDecimal(String.valueOf(pickup.lookup(argContext, "単位料金")));
        BigDecimal result = basePrice.add(unitPrice.multiply(BigDecimal.valueOf(usage)));
        return result.setScale(1, BigDecimal.ROUND_DOWN).intValue();
    }
}
