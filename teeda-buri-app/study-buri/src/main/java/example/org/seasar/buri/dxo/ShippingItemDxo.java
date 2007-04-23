package example.org.seasar.buri.dxo;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemDxo {
	ShippingItemDto[] convert(List result);
}
