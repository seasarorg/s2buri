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
package example.org.escafe.buri.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.seasar.framework.util.tiger.CollectionsUtil;

@Entity
@Table(name = "ORDER_TITLE")
public class OrderTitle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_TITLE_GEN")
	@SequenceGenerator(name = "ORDER_TITLE_GEN", sequenceName = "ORDER_TITLE_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long orderTitleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date orderDate;

	@Column(precision = 19, nullable = true, unique = false)
	public Long customerId;

	@Column(precision = 10, nullable = true, unique = false)
	public Integer status;

	@OneToMany(mappedBy = "orderTitle")
	public List<OrderDetail> orderDetailList = CollectionsUtil.newArrayList();
}
