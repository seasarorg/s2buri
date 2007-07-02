package example.org.escafe.buri.dxo;

import java.util.List;

import example.org.escafe.buri.dto.ShippingItemDto;

public interface ShippingItemDxo {
	ShippingItemDto[] convert(List result);
}
