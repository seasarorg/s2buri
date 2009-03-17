package org.escafe.buri.service;

import java.util.List;

import org.escafe.buri.entity.FurnitureItem;

public class FurnitureItemService extends AbstractService<FurnitureItem> {
	public List<FurnitureItem> getAllFurnitureItem() {
		return findAll();
	}

	public FurnitureItem getFurnitureItem(Long furnitureId) {
		return select().id(furnitureId).getSingleResult();
	}

	public List<FurnitureItem> getFurnitureItemByIds(List<Long> furnitureIds) {
		return select()
		    .where("furnitureId in (?)", furnitureIds)
		    .getResultList();
	}
	/*
	 * public List<FurnitureItem> find(FurnitureItemFindDto dto, List<String>
	 * paths) { BeanMap bm = new BeanMap(); bm.put("dto", dto); bm.put("paths",
	 * paths); return selectBySqlFile(FurnitureItem.class,
	 * "find.sql").getResultList(); }
	 */
	/*
	 * public List<FurnitureItem> findAndUser(FurnitureItemFindDto dto,
	 * List<String> paths, List<Long> userIds) { BeanMap bm = new BeanMap();
	 * bm.put("dto", dto); bm.put("paths", paths); class Param { public
	 * FurnitureItemFindDto dto; public List<String> paths; public List<Long>
	 * userIds; } Param param = new Param(); param.dto = dto; param.paths =
	 * paths; param.userIds = userIds; return
	 * selectBySqlFile(FurnitureItem.class, "find.sql").getResultList(); }
	 */
}
