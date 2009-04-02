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
	/**
	 * IDから{@link Item}エンティティを検索し返します。
	 * 
	 * @param itemId
	 *            {@link Item}のID
	 * @return {@link Item}
	 */
	public Item getItem(Long itemId) {
		return select().id(itemId).getSingleResult();
	}

	/**
	 * 複数のIDから{@link Item}エンティティを検索し返します。
	 * 
	 * @param itemIds
	 *            {@link Item}のIDのリスト
	 * @return {@link Item}のリスト
	 */
	public List<Item> getItemByIds(List<Long> itemIds) {
		return select().where(in(itemId(), itemIds)).getResultList();
	}

	/**
	 * ワークフロー上の{@link Item}を検索します。
	 * 
	 * @param findDto
	 *            検索条件用DTO
	 * @param pathList
	 *            ぶりパスのリスト
	 * @param userList
	 *            ユーザIDのリスト
	 * @return {@link Item}のリスト
	 */
	public List<Item> findAndUser(ItemFindDto findDto, List<String> pathList,
	        List<Long> userList) {
		class Param {
			@SuppressWarnings("unused")
			public ItemFindDto dto;

			@SuppressWarnings("unused")
			public List<String> paths;

			@SuppressWarnings("unused")
			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = findDto;
		param.paths = pathList;
		param.userIds = userList;
		return selectBySqlFile(Item.class, "findAndUser.sql", param)
		    .getResultList();
	}
}
