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

import static example.org.escafe.buri.names.CustomerNames.customerId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.Customer;

/**
 * {@link Customer}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class CustomerService extends AbstractService<Customer> {
	/**
	 * IDから{@link Customer}エンティティを検索し返します。
	 * 
	 * @param customerId
	 *            {@link Customer}のID
	 * @return {@link Customer}
	 */
	public Customer getCustomer(Long customerId) {
		return select().id(customerId).getSingleResult();
	}

	/**
	 * 複数のIDから{@link Customer}エンティティを検索し返します。
	 * 
	 * @param customerIds
	 *            {@link Customer}のIDのリスト
	 * @return {@link Customer}のリスト
	 */
	public List<Customer> getCustomerByIds(List<Long> customerIds) {
		return select().where(in(customerId(), customerIds)).getResultList();
	}
}
