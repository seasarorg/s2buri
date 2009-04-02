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

import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.names.OrderDetailNames._OrderDetailNames;

/**
 * {@link OrderTitle}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class OrderTitleNames {
	private OrderTitleNames() {
		throw new AssertionError();
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
	 * orderDateのプロパティ名を返します。
	 * 
	 * @return orderDateのプロパティ名
	 */
	public static PropertyName<Date> orderDate() {
		return new PropertyName<Date>("orderDate");
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
	 * statusのプロパティ名を返します。
	 * 
	 * @return statusのプロパティ名
	 */
	public static PropertyName<Integer> status() {
		return new PropertyName<Integer>("status");
	}

	/**
	 * orderDetailListのプロパティ名を返します。
	 * 
	 * @return orderDetailListのプロパティ名
	 */
	public static _OrderDetailNames orderDetailList() {
		return new _OrderDetailNames("orderDetailList");
	}

	/**
	 * @author S2JDBC-Gen
	 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
	 */
	public static class _OrderTitleNames extends PropertyName<OrderTitle> {
		/**
		 * インスタンスを構築します。
		 */
		public _OrderTitleNames() {
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param name
		 *            名前
		 */
		public _OrderTitleNames(final String name) {
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
		public _OrderTitleNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
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
		 * orderDateのプロパティ名を返します。
		 * 
		 * @return orderDateのプロパティ名
		 */
		public PropertyName<Date> orderDate() {
			return new PropertyName<Date>(this, "orderDate");
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
		 * statusのプロパティ名を返します。
		 * 
		 * @return statusのプロパティ名
		 */
		public PropertyName<Integer> status() {
			return new PropertyName<Integer>(this, "status");
		}

		/**
		 * orderDetailListのプロパティ名を返します。
		 * 
		 * @return orderDetailListのプロパティ名
		 */
		public _OrderDetailNames orderDetailList() {
			return new _OrderDetailNames(this, "orderDetailList");
		}
	}
}
