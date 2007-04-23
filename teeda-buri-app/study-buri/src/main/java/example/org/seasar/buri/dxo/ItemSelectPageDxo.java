package example.org.seasar.buri.dxo;

import java.util.List;

import example.org.seasar.buri.web.item.ItemPageListDto;

public interface ItemSelectPageDxo {
	ItemPageListDto[] convert(List result);
}
