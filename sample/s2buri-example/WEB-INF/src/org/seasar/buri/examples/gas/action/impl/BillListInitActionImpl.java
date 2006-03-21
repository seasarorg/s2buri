package org.seasar.buri.examples.gas.action.impl;

import java.util.List;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.seasar.buri.examples.gas.bao.GasRateBao;
import org.seasar.buri.examples.gas.dto.BillReportDto;
import org.seasar.buri.examples.gas.entity.MeterCheck;
import org.seasar.buri.examples.gas.utils.DtoUtils;

public class BillListInitActionImpl {
    private List billDtoList;
    private GasRateBao gasRateBao;

    public String initialize() {
        List entities = this.gasRateBao.getEndBillList();
        this.billDtoList = (List) CollectionUtils.collect(entities, new Transformer() {
            public Object transform(Object each) {
                return convertToDto((MeterCheck) each);
            }
        });
        return null;
    }

    private BillReportDto convertToDto(MeterCheck entity) {
        BillReportDto dto = new BillReportDto();
        DtoUtils.copy(entity, dto);
        dto.setCustomerName(entity.getCustomer().getCustomerName());
        dto.setRecipientMailAddress(entity.getCustomer().getMailAddress());
        return dto;
    }

    public List getBillDtoList() {
        return billDtoList;
    }
    
    public void setGasRateBao(GasRateBao gasRateBao) {
        this.gasRateBao = gasRateBao;
    }
}
