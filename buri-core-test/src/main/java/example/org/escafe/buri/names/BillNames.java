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

import example.org.escafe.buri.entity.Bill;

/**
 * {@link Bill}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BillNames {
	private BillNames() {
		throw new AssertionError();
	}

	/**
	 * billIdのプロパティ名を返します。
	 * 
	 * @return billIdのプロパティ名
	 */
	public static PropertyName<Long> billId() {
		return new PropertyName<Long>("billId");
	}

	/**
	 * billDateのプロパティ名を返します。
	 * 
	 * @return billDateのプロパティ名
	 */
	public static PropertyName<Date> billDate() {
		return new PropertyName<Date>("billDate");
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
	 * @author S2JDBC-Gen
	 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
	 */
	public static class _BillNames extends PropertyName<Bill> {
		/**
		 * インスタンスを構築します。
		 */
		public _BillNames() {
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param name
		 *            名前
		 */
		public _BillNames(final String name) {
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
		public _BillNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * billIdのプロパティ名を返します。
		 * 
		 * @return billIdのプロパティ名
		 */
		public PropertyName<Long> billId() {
			return new PropertyName<Long>(this, "billId");
		}

		/**
		 * billDateのプロパティ名を返します。
		 * 
		 * @return billDateのプロパティ名
		 */
		public PropertyName<Date> billDate() {
			return new PropertyName<Date>(this, "billDate");
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
	}
}
