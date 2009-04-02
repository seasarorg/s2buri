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

import static example.org.escafe.buri.names.BillNames.billId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.Bill;

/**
 * {@link Bill}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class BillService extends AbstractService<Bill> {
	/**
	 * IDから{@link Bill}エンティティを検索し返します。
	 * 
	 * @param billId
	 *            {@link Bill}のID
	 * @return {@link Bill}
	 */
	public Bill getBill(Long billId) {
		return select().id(billId).getSingleResult();
	}

	/**
	 * 複数のIDから{@link Bill}エンティティを検索し返します。
	 * 
	 * @param billIds
	 *            {@link Bill}のIDのリスト
	 * @return {@link Bill}のリスト
	 */
	public List<Bill> getBillByIds(List<Long> billIds) {
		return select().where(in(billId(), billIds)).getResultList();
	}
}
