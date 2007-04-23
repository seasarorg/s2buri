package example.org.seasar.buri.dxo;

import java.util.List;

import example.org.seasar.buri.dto.BillDto;

public interface BillDxo {
	BillDto[] convert(List result);
}
