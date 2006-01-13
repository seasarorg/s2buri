package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.BillDto;

public interface BillDao {
    public Class BEAN = BillDto.class;

    public List getAllBill();

    public String getBill_ARGS = "billID";
    public BillDto getBill(long billID);

    public String getBillByIds_ARGS = "billIDs";
    public String getBillByIds_QUERY = "billID in /*billIDs*/(1)";
    public List getBillByIds(List billIDs);
    
    public void insert(BillDto dto);
    
    public void update(BillDto dto);
    
    public void delete(BillDto dto);
}

