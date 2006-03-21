package org.seasar.buri.examples.gas.dao;

import java.util.List;

import org.seasar.buri.examples.gas.entity.MeterCheck;


public interface MeterCheckDao {
    public Class BEAN = MeterCheck.class;
    public List getAllMeterCheck();

    // selectManyが定義されていない場合に呼ばれる
    public String getMeterCheckById_ARGS = "meterCheckId";
    public MeterCheck getMeterCheckById(long meterCheckId);

    // selectManyとして定義されている場合に、getDataListFromPathで優先的に呼ばれる
    public String getMeterCheckListByIds_ARGS = "meterCheckIds";
    public String getMeterCheckListByIds_QUERY = "meterCheckId in /*meterCheck*/(1)";
    public List getMeterCheckListByIds(List meterCheckIds);

    // filterManyとして定義されている場合に呼ばれる
    public String getByIdAndDto_ARGS = "testIds,dto";
    public String getByIdAndDto_QUERY = "meterCheckId in /*testIds*/(1)" +
            " /*IF dto.meterIndication != 0*/and meterIndication = /*dto.meterIndication*/'hoge' /*END*/" +
            " /*IF dto.billAmount != 0*/and billAmount = /*dto.billAmount*/'hoge' /*END*/" +
            " /*IF dto.paymentAmount != 0*/and paymentAmount = /*dto.paymentAmount*/'hoge' /*END*/" +
            " /*IF dto.meterCheckDate != null*/and meterCheckDate = /*dto.meterCheckDate*/'hoge' /*END*/" +
            " /*IF dto.billCode != null*/and billCode = /*dto.billCode*/'hoge' /*END*/" +
            "";
    public List getByIdAndDto(List testIds, MeterCheck dto);
    
    public void insert(MeterCheck meterCheck);
    public void update(MeterCheck meterCheck);
    public void delete(MeterCheck meterCheck);
}
