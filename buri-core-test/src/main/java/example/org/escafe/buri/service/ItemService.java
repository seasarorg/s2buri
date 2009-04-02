/*
 * Copyright 2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ItemNames.itemId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.dto.ItemFindDto;
import example.org.escafe.buri.entity.Item;

/**
 * {@link Item}のサービスクラスです。
 * 
 * @author j5ik2o
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
