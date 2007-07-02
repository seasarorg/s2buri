package example.org.escafe.buri.dxo;

import java.util.List;

import example.org.escafe.buri.dto.CustomerDto;

public interface CustomerDxo {
	CustomerDto[] convert(List result);
}
