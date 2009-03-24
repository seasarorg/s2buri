package org.escafe.buri.service;

import static org.escafe.buri.names.FurnitureItemNames.furnitureId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.escafe.buri.dto.FurnitureItemFindDto;
import org.escafe.buri.entity.FurnitureItem;

public class FurnitureItemService extends AbstractService<FurnitureItem> {
	public List<FurnitureItem> getAllFurnitureItem() {
		return findAll();
	}

	public FurnitureItem getFurnitureItem(Long furnitureId) {
		return select().id(furnitureId).getSingleResult();
	}

	public List<FurnitureItem> getFurnitureItemByIds(List<Long> furnitureIds) {
		return select().where(in(furnitureId(), furnitureIds)).getResultList();
	}

	public List<FurnitureItem> findAndUser(FurnitureItemFindDto dto,
	        List<String> paths, List<Long> userIds) {
		class Param {
			public FurnitureItemFindDto dto;

			public List<String> paths;

			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		param.userIds = userIds;
		return selectBySqlFile(FurnitureItem.class, "findAndUser.sql", param)
		    .getResultList();
	}

	public List<FurnitureItem> find(FurnitureItemFindDto findDto,
	        List<String> pathNames) {
		class Param {
			public FurnitureItemFindDto dto;

			public List<String> paths;
		}
		Param param = new Param();
		param.dto = findDto;
		param.paths = pathNames;
		return selectBySqlFile(FurnitureItem.class, "find.sql", param)
		    .getResultList();
	}
}
