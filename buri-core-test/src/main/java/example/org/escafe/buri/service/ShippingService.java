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

import static example.org.escafe.buri.names.ShippingNames.orderTitle;
import static example.org.escafe.buri.names.ShippingNames.shippingId;
import static example.org.escafe.buri.names.ShippingNames.shippingItemList;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.dto.ShippingFindDto;
import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.entity.Shipping;
import example.org.escafe.buri.entity.ShippingItem;

/**
 * {@link Shipping}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ShippingService extends AbstractService<Shipping> {
	/**
	 * {@link ShippingItemService}です。
	 */
	public ShippingItemService shippingItemService;

	/**
	 * IDから{@link Shipping}エンティティを検索し返します。
	 * 
	 * @param shippingId
	 *            {@link Shipping}のID
	 * @return {@link Shipping}
	 */
	public Shipping getShipping(Long shippingId) {
		return select().id(shippingId).getSingleResult();
	}

	/**
	 * 複数のIDIDから{@link Shipping}エンティティを検索し返します。
	 * 
	 * @param shippingIds
	 *            {@link Shipping}のID
	 * @return {@link Shipping}
	 */
	public List<Shipping> getShippingByIds(List<Long> shippingIds) {
		return select().where(in(shippingId(), shippingIds)).getResultList();
	}

	/**
	 * IDから{@link Shipping}エンティティに変換します。
	 * 
	 * @param shippingId
	 *            {@link Shipping}のID
	 * @return {@link Shipping}
	 */
	public Shipping convertOrNew(Long shippingId) {
		if (shippingId == null) {
			throw new IllegalArgumentException("shippingId is null.");
		}
		Shipping result = new Shipping();
		Shipping entity =
		    select()
		        .leftOuterJoin(shippingItemList())
		        .id(shippingId)
		        .getSingleResult();
		return entity == null ? result : entity;
	}

	/**
	 * {@link OrderTitle}から{@link Shipping}エンティティに変換します。
	 * 
	 * @param orderTitle
	 *            {@link Shipping}
	 * @return {@link Shipping}
	 */
	public Shipping convertOrNew(OrderTitle orderTitle) {
		if (orderTitle == null) {
			throw new IllegalArgumentException("orderTitle is null.");
		}
		if (orderTitle.orderTitleId == null) {
			throw new IllegalArgumentException(
			    "orderTitle.orderTitleId is null.");
		}
		Shipping result = new Shipping();
		Shipping entity =
		    select()
		        .leftOuterJoin(orderTitle())
		        .where(eq(orderTitle().orderTitleId(), orderTitle.orderTitleId))
		        .getSingleResult();
		if (entity == null) {
			result.customerId = orderTitle.customerId;
			result.orderTitleId = orderTitle.orderTitleId;
			for (OrderDetail od : orderTitle.orderDetailList) {
				ShippingItem si = new ShippingItem();
				si.orderDetailId = od.orderDetailId;
				result.shippingItemList.add(si);
			}
			return result;
		}
		result = convertOrNew(entity.shippingId);
		return result;
	}

	/**
	 * ワークフロー上の{@link Shipping}を検索します。
	 * 
	 * @param dto
	 *            検索条件用DTO
	 * @param paths
	 *            ぶりパスのリスト
	 * @return {@link Shipping}のリスト
	 */
	public List<Shipping> find(ShippingFindDto dto, List<String> paths) {
		class Param {
			@SuppressWarnings("unused")
			public ShippingFindDto dto;

			@SuppressWarnings("unused")
			public List<String> paths;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		return selectBySqlFile(Shipping.class, "find.sql", param)
		    .getResultList();
	}

	/**
	 * ワークフロー上の{@link Shipping}を検索します。
	 * 
	 * @param dto
	 *            検索条件用DTO
	 * @param paths
	 *            ぶりパスのリスト
	 * @param userIds
	 *            ユーザIDのリスト
	 * @return {@link Shipping}のリスト
	 */
	public List<Shipping> findAndUser(ShippingFindDto dto, List<String> paths,
	        List<Long> userIds) {
		class Param {
			@SuppressWarnings("unused")
			public ShippingFindDto dto;

			@SuppressWarnings("unused")
			public List<String> paths;

			@SuppressWarnings("unused")
			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		param.userIds = userIds;
		return selectBySqlFile(Shipping.class, "findAndUser.sql", param)
		    .getResultList();
	}

	/**
	 * {@link Shipping}エンティティをINSERTします。
	 * <p>
	 * {@link ShippingItem}エンティティもINSERTします。
	 * </p>
	 * 
	 * @see org.seasar.extension.jdbc.service.S2AbstractService#insert(java.lang.
	 *      Object)
	 */
	@Override
	public int insert(Shipping entity) {
		int result = super.insert(entity);
		if (result == 1) {
			for (ShippingItem shippingItem : entity.shippingItemList) {
				shippingItem.shippingId = entity.shippingId;
				shippingItemService.insert(shippingItem);
			}
		}
		return result;
	}

	/**
	 * {@link Shipping}エンティティをUPDATEします。
	 * <p>
	 * {@link ShippingItem}エンティティもUPDATEします。
	 * </p>
	 * 
	 * @see org.seasar.extension.jdbc.service.S2AbstractService#update(java.lang.
	 *      Object)
	 */
	@Override
	public int update(Shipping entity) {
		int result = super.update(entity);
		if (result == 1) {
			for (ShippingItem shippingItem : entity.shippingItemList) {
				if (shippingItem.shippingItemId != null) {
					shippingItemService.update(shippingItem);
				} else {
					shippingItemService.insert(shippingItem);
				}
			}
		}
		return result;
	}
}
