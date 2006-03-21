package org.seasar.buri.examples.gas.action.impl;

import java.util.List;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.seasar.buri.examples.gas.bao.GasRateBao;
import org.seasar.buri.examples.gas.dto.RemindedPaymentDto;
import org.seasar.buri.examples.gas.entity.MeterCheck;
import org.seasar.buri.examples.gas.utils.DtoUtils;

public class RemindedPaymentListInitActionImpl {
    private GasRateBao gasRateBao;
    private List remindedPaymentDtoList;

    public String initialize() {
        List entities = gasRateBao.getRemindedPaymentList();
        this.remindedPaymentDtoList = (List) CollectionUtils.collect(entities, new Transformer() {
            public Object transform(Object each) {
                return convertToDto((MeterCheck) each);
            }
        });
        return null;
    }

    private RemindedPaymentDto convertToDto(MeterCheck entity) {
        RemindedPaymentDto dto = new RemindedPaymentDto();
        DtoUtils.copy(entity, dto);
        dto.setCustomerName(entity.getCustomer().getCustomerName());
        return dto;
    }
    
    public List getRemindedPaymentDtoList() {
        return remindedPaymentDtoList;
    }
    public void setRemindedPaymentDtoList(List remindedPaymentDtoList) {
        this.remindedPaymentDtoList = remindedPaymentDtoList;
    }

    public void setGasRateBao(GasRateBao gasRateBao) {
        this.gasRateBao = gasRateBao;
    }
}
