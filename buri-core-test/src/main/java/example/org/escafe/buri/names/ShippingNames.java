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
package example.org.escafe.buri.names;

import java.util.Date;

import org.seasar.extension.jdbc.name.PropertyName;

import example.org.escafe.buri.entity.Shipping;
import example.org.escafe.buri.names.OrderTitleNames._OrderTitleNames;
import example.org.escafe.buri.names.ShippingItemNames._ShippingItemNames;

/**
 * {@link Shipping}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class ShippingNames {
	private ShippingNames() {
		throw new AssertionError();
	}

	/**
	 * shippingIdのプロパティ名を返します。
	 * 
	 * @return shippingIdのプロパティ名
	 */
	public static PropertyName<Long> shippingId() {
		return new PropertyName<Long>("shippingId");
	}

	/**
	 * shippingDateのプロパティ名を返します。
	 * 
	 * @return shippingDateのプロパティ名
	 */
	public static PropertyName<Date> shippingDate() {
		return new PropertyName<Date>("shippingDate");
	}

	/**
	 * orderTitleIdのプロパティ名を返します。
	 * 
	 * @return orderTitleIdのプロパティ名
	 */
	public static PropertyName<Long> orderTitleId() {
		return new PropertyName<Long>("orderTitleId");
	}

	/**
	 * customerIdのプロパティ名を返します。
	 * 
	 * @return customerIdのプロパティ名
	 */
	public static PropertyName<Long> customerId() {
		return new PropertyName<Long>("customerId");
	}

	/**
	 * shippingItemListのプロパティ名を返します。
	 * 
	 * @return shippingItemListのプロパティ名
	 */
	public static _ShippingItemNames shippingItemList() {
		return new _ShippingItemNames("shippingItemList");
	}

	/**
	 * orderTitleのプロパティ名を返します。
	 * 
	 * @return orderTitleのプロパティ名
	 */
	public static _OrderTitleNames orderTitle() {
		return new _OrderTitleNames("orderTitle");
	}

	/**
	 * @author S2JDBC-Gen
	 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
	 */
	public static class _ShippingNames extends PropertyName<Shipping> {
		/**
		 * インスタンスを構築します。
		 */
		public _ShippingNames() {
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param name
		 *            名前
		 */
		public _ShippingNames(final String name) {
			super(name);
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param parent
		 *            親
		 * @param name
		 *            名前
		 */
		public _ShippingNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * shippingIdのプロパティ名を返します。
		 * 
		 * @return shippingIdのプロパティ名
		 */
		public PropertyName<Long> shippingId() {
			return new PropertyName<Long>(this, "shippingId");
		}

		/**
		 * shippingDateのプロパティ名を返します。
		 * 
		 * @return shippingDateのプロパティ名
		 */
		public PropertyName<Date> shippingDate() {
			return new PropertyName<Date>(this, "shippingDate");
		}

		/**
		 * orderTitleIdのプロパティ名を返します。
		 * 
		 * @return orderTitleIdのプロパティ名
		 */
		public PropertyName<Long> orderTitleId() {
			return new PropertyName<Long>(this, "orderTitleId");
		}

		/**
		 * customerIdのプロパティ名を返します。
		 * 
		 * @return customerIdのプロパティ名
		 */
		public PropertyName<Long> customerId() {
			return new PropertyName<Long>(this, "customerId");
		}

		/**
		 * shippingItemListのプロパティ名を返します。
		 * 
		 * @return shippingItemListのプロパティ名
		 */
		public _ShippingItemNames shippingItemList() {
			return new _ShippingItemNames(this, "shippingItemList");
		}

		/**
		 * orderTitleのプロパティ名を返します。
		 * 
		 * @return orderTitleのプロパティ名
		 */
		public _OrderTitleNames orderTitle() {
			return new _OrderTitleNames(this, "orderTitle");
		}
	}
}
