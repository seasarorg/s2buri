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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.seasar.framework.util.tiger.CollectionsUtil;

@Entity
@Table(name = "SHIPPING")
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPPING_GEN")
	@SequenceGenerator(name = "SHIPPING_GEN", sequenceName = "SHIPPING_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long shippingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date shippingDate = new Date(8070, 11, 31, 23, 59, 59);

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderTitleId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long customerId;

	@OneToMany(mappedBy = "shipping")
	public List<ShippingItem> shippingItemList = CollectionsUtil.newArrayList();

	@ManyToOne
	@JoinColumn(name = "ORDER_TITLE_ID", referencedColumnName = "ORDER_TITLE_ID")
	public OrderTitle orderTitle;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
