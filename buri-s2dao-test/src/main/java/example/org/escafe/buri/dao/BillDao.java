package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.BillDto;
import example.org.escafe.buri.dto.BillFindDto;

public interface BillDao {
	public Class<?> BEAN = BillDto.class;

	public List<BillDto> getAllBill();

	public String getBill_QUERY = "BILL_ID = ?";

	public BillDto getBill(Long billId);

	public String getBillByIds_ARGS = "billIds";

	public String getBillByIds_QUERY = "BILL_ID in /*billIds*/(1)";

	public List<BillDto> getBillByIds(List<Long> billIds);

	public String find_ARGS = "dto,paths";

	public List<BillDto> find(BillFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<BillDto> findAndUser(BillFindDto dto, List<String> paths,
	        List<Long> userIds);

	public void insert(BillDto entity);

	public void update(BillDto entity);

	public void delete(BillDto entity);
}
