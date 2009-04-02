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

import static example.org.escafe.buri.names.ShippingItemNames.shippingId;
import static example.org.escafe.buri.names.ShippingItemNames.shippingItemId;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.ShippingItem;

/**
 * {@link ShippingItem}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ShippingItemService extends AbstractService<ShippingItem> {
	/**
	 * IDから{@link ShippingItem}エンティティを検索し返します。
	 * 
	 * @param shippingItemId
	 *            {@link ShippingItem}のID
	 * @return {@link ShippingItem}
	 */
	public ShippingItem getShippingItem(Long shippingItemId) {
		ShippingItem result = select().id(shippingItemId).getSingleResult();
		return result;
	}

	/**
	 * 複数のIDから{@link ShippingItem}エンティティを検索し返します。
	 * 
	 * @param shippingItemIds
	 *            {@link ShippingItem}のIDのリスト
	 * @return {@link ShippingItem}のリスト
	 */
	public List<ShippingItem> getShippingItemByIds(List<Long> shippingItemIds) {
		List<ShippingItem> result =
		    select()
		        .where(in(shippingItemId(), shippingItemIds))
		        .getResultList();
		return result;
	}

	public List<ShippingItem> getShippingItemByShippingId(Long shippingId) {
		List<ShippingItem> result =
		    select().where(eq(shippingId(), shippingId)).getResultList();
		return result;
	}
}
