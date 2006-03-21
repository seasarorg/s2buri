package org.seasar.buri.examples.gas.action.impl;

import java.util.List;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.seasar.buri.examples.gas.bao.GasRateBao;
import org.seasar.buri.examples.gas.dto.ApprovedPaymentDto;
import org.seasar.buri.examples.gas.entity.MeterCheck;
import org.seasar.buri.examples.gas.utils.DtoUtils;

public class ApprovedPaymentListInitActionImpl {
    private GasRateBao gasRateBao;
    private List approvedPaymentDtoList;
    
    public String initialize() {
        List entities = gasRateBao.getApprovedPaymentList();
        this.approvedPaymentDtoList = (List) CollectionUtils.collect(entities, new Transformer() {
            public Object transform(Object each) {
                return convertToDto((MeterCheck) each);
            }
        });
        return null;
    }
    
    private ApprovedPaymentDto convertToDto(MeterCheck entity) {
        ApprovedPaymentDto dto = new ApprovedPaymentDto();
        DtoUtils.copy(entity, dto);
        dto.setCustomerName(entity.getCustomer().getCustomerName());
        return dto;
    }

    public List getApprovedPaymentDtoList() {
        return approvedPaymentDtoList;
    }
    public void setApprovedPaymentDtoList(List approvedPaymentDtoList) {
        this.approvedPaymentDtoList = approvedPaymentDtoList;
    }
    public void setGasRateBao(GasRateBao gasRateBao) {
        this.gasRateBao = gasRateBao;
    }
}
