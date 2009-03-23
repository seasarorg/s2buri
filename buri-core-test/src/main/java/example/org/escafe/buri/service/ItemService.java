package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ItemNames.itemId;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import org.apache.commons.beanutils.BeanMap;

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
	 * 識別子でエンティティを検索します。
	 * 
	 * @param itemId
	 *            識別子
	 * @return エンティティ
	 */
	public Item findById(Long itemId) {
		return select().id(itemId).getSingleResult();
	}

	public Item getItem(Long itemId) {
		return findById(itemId);
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<Item> findAllOrderById() {
		return select().orderBy(asc(itemId())).getResultList();
	}

	public List<Item> findAndUser(ItemFindDto findDto, List<String> pathList,
	        List<Long> userList) {
		BeanMap bm = new BeanMap();
		bm.put("dto", findDto);
		bm.put("paths", pathList);
		bm.put("userIds", userList);
		return selectBySqlFile(Item.class, "findAndUser", bm).getResultList();
	}
}
