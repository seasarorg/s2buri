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

import static example.org.escafe.buri.names.OrderTitleNames.orderDetailList;
import static example.org.escafe.buri.names.OrderTitleNames.orderTitleId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.entity.OrderTitle;

/**
 * {@link OrderTitle}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class OrderTitleService extends AbstractService<OrderTitle> {
	/**
	 * {@link OrderDetailService}です。
	 */
	public OrderDetailService orderDetailService;

	/**
	 * IDから{@link OrderTitle}エンティティを検索し返します。
	 * 
	 * @param orderTitleId
	 *            {@link OrderTitle}のID
	 * @return {@link OrderTitle}
	 */
	public OrderTitle getOrderTitle(Long orderTitleId) {
		return select().id(orderTitleId).getSingleResult();
	}

	/**
	 * IDから{@link OrderTitle}エンティティと関連する{@link OrderDetail}のリストを検索し返します。
	 * 
	 * @param orderTitleId
	 *            {@link OrderTitle}のID
	 * @return {@link OrderTitle}
	 */
	public OrderTitle findByIdWithOrderDetail(Long orderTitleId) {
		return select()
		    .leftOuterJoin(orderDetailList())
		    .id(orderTitleId)
		    .getSingleResult();
	}

	/**
	 * 複数のIDから{@link OrderTitle}エンティティを検索し返します。
	 * 
	 * @param orderTitleIds
	 *            {@link OrderTitle}のIDのリスト
	 * @return {@link OrderTitle}のリスト
	 */
	public List<OrderTitle> getOrderTitleByIds(List<Long> orderTitleIds) {
		return select()
		    .where(in(orderTitleId(), orderTitleIds))
		    .getResultList();
	}

	/**
	 * {@link OrderTitle}エンティティをINSERTします。
	 * <p>
	 * {@link OrderDetail}エンティティもINSERTします。
	 * </p>
	 * 
	 * @see org.seasar.extension.jdbc.service.S2AbstractService#insert(java.lang.
	 *      Object)
	 */
	@Override
	public int insert(OrderTitle entity) {
		int result = super.insert(entity);
		if (result == 1) {
			for (OrderDetail od : entity.orderDetailList) {
				od.orderTitleId = entity.orderTitleId;
				orderDetailService.insert(od);
			}
		}
		return result;
	}

	/**
	 * {@link OrderTitle}エンティティをUPDATEします。
	 * <p>
	 * {@link OrderDetail}エンティティもUPDATEします。
	 * </p>
	 * 
	 * @see org.seasar.extension.jdbc.service.S2AbstractService#update(java.lang.
	 *      Object)
	 */
	@Override
	public int update(OrderTitle entity) {
		int result = super.update(entity);
		if (result == 1) {
			for (OrderDetail od : entity.orderDetailList) {
				od.orderTitleId = entity.orderTitleId;
				if (od.orderTitleId != null) {
					orderDetailService.update(od);
				} else {
					orderDetailService.insert(od);
				}
			}
		}
		return result;
	}
}
