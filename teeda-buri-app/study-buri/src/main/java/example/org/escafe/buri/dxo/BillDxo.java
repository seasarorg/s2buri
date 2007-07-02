package example.org.escafe.buri.dxo;

import java.util.List;

import example.org.escafe.buri.dto.BillDto;

public interface BillDxo {
	BillDto[] convert(List result);
}
