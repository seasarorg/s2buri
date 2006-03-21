package org.seasar.buri.examples.gas.action.impl;

import java.util.Date;

import org.seasar.buri.examples.gas.bao.GasRateBao;
import org.seasar.buri.examples.gas.dao.CustomerDao;
import org.seasar.buri.examples.gas.dto.BillReportDto;
import org.seasar.buri.examples.gas.dto.MeterCheckInputDto;
import org.seasar.buri.examples.gas.entity.Customer;
import org.seasar.buri.examples.gas.entity.MeterCheck;


public class MeterCheckInputActionImpl {
    private MeterCheckInputDto meterCheckInputDto;
    private BillReportDto billReportDto;
    private GasRateBao gasRateBao;
    private CustomerDao customerDao;

    public String entry() {
        MeterCheck meterCheck = new MeterCheck();
        meterCheck.setMeterIndication(this.meterCheckInputDto.getMeterIndication());
        meterCheck.setMeterCheckDate(new Date());
        Customer customer = this.customerDao.getCustomerByCustomerCode(this.meterCheckInputDto.getCustomerCode());
        meterCheck.setCustomer(customer);
        meterCheck.setCustomerId(customer.getCustomerId());
        this.gasRateBao.enterMeterCheck(meterCheck);
        
        this.billReportDto.setBillCode(meterCheck.getBillCode());
        this.billReportDto.setBillAmount(meterCheck.getBillAmount());
        this.billReportDto.setCustomerName(meterCheck.getCustomer().getCustomerName());
        this.billReportDto.setRecipientMailAddress(meterCheck.getCustomer().getMailAddress());
        return "billReport";
    }

    public void setMeterCheckInputDto(MeterCheckInputDto meterCheckInputDto) {
        this.meterCheckInputDto = meterCheckInputDto;
    }
    public MeterCheckInputDto getMeterCheckInputDto() {
        return meterCheckInputDto;
    }
    public void setBillReportDto(BillReportDto billReportDto) {
        this.billReportDto = billReportDto;
    }
    public BillReportDto getBillReportDto() {
        return billReportDto;
    }
    
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public void setGasRateBao(GasRateBao gasRateBao) {
        this.gasRateBao = gasRateBao;
    }
}
