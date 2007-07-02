package example.org.escafe.buri.dxo;

import java.util.List;

import example.org.escafe.buri.web.item.ItemPageListDto;

public interface ItemSelectPageDxo {
	ItemPageListDto[] convert(List result);
}
