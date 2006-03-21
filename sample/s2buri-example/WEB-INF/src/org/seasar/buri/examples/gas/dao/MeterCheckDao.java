package org.seasar.buri.examples.gas.dao;

import java.util.List;

import org.seasar.buri.examples.gas.entity.MeterCheck;


public interface MeterCheckDao {
    public Class BEAN = MeterCheck.class;
    public List getAllMeterCheck();

    // selectMany����`����Ă��Ȃ��ꍇ�ɌĂ΂��
    public String getMeterCheckById_ARGS = "meterCheckId";
    public MeterCheck getMeterCheckById(long meterCheckId);

    // selectMany�Ƃ��Ē�`����Ă���ꍇ�ɁAgetDataListFromPath�ŗD��I�ɌĂ΂��
    public String getMeterCheckListByIds_ARGS = "meterCheckIds";
    public String getMeterCheckListByIds_QUERY = "meterCheckId in /*meterCheck*/(1)";
    public List getMeterCheckListByIds(List meterCheckIds);

    // filterMany�Ƃ��Ē�`����Ă���ꍇ�ɌĂ΂��
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
