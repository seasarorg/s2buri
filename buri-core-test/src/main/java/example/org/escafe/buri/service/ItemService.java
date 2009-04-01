package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ItemNames.itemId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.dto.ItemFindDto;
import example.org.escafe.buri.entity.Item;

/**
 * {@link Item}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ItemService extends AbstractService<Item> {
	public Item getItem(Long itemId) {
		return select().id(itemId).getSingleResult();
	}

	public List<Item> getItemByIds(List<Long> itemIds) {
		return select().where(in(itemId(), itemIds)).getResultList();
	}

	public List<Item> findAndUser(ItemFindDto findDto, List<String> pathList,
	        List<Long> userList) {
		class Param {
			public ItemFindDto dto;

			public List<String> paths;

			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = findDto;
		param.paths = pathList;
		param.userIds = userList;
		return selectBySqlFile(Item.class, "findAndUser", param)
		    .getResultList();
	}
}
