package example.org.seasar.buri.dxo;

import java.util.List;

import example.org.seasar.buri.dto.CustomerDto;

public interface CustomerDxo {
	CustomerDto[] convert(List result);
}
