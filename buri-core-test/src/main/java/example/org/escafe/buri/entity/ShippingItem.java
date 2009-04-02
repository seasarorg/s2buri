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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPING_ITEM")
public class ShippingItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPPING_ITEM_GEN")
	@SequenceGenerator(name = "SHIPPING_ITEM_GEN", sequenceName = "SHIPPING_ITEM_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long shippingItemId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderDetailId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long shippingId;

	@ManyToOne
	@JoinColumn(name = "SHIPPING_ID", referencedColumnName = "SHIPPING_ID")
	public Shipping shipping;
}
