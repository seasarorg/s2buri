package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.BillDto;
import example.org.escafe.buri.dto.BillFindDto;

public interface BillDao {
    public Class BEAN = BillDto.class;

    public List getAllBill();

    public String getBill_QUERY = "billID = ?";
    public BillDto getBill(long billID);

    public String getBillByIds_ARGS = "billIDs";
    public String getBillByIds_QUERY = "billID in /*billIDs*/(1)";
    public List getBillByIds(List billIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(BillFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(BillFindDto dto,List paths,List userIDs);

    //public String soleMatch_ARGS = "dto";
    //public BillDto soleMatch(BillFindDto dto);
    
    public void insert(BillDto dto);
    
    public void update(BillDto dto);
    
    public void delete(BillDto dto);
}

