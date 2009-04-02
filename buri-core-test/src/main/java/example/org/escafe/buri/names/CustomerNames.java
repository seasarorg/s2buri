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

import org.seasar.extension.jdbc.name.PropertyName;

import example.org.escafe.buri.entity.Customer;

/**
 * {@link Customer}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class CustomerNames {
	private CustomerNames() {
		throw new AssertionError();
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
	 * customerNameのプロパティ名を返します。
	 * 
	 * @return customerNameのプロパティ名
	 */
	public static PropertyName<String> customerName() {
		return new PropertyName<String>("customerName");
	}

	/**
	 * @author S2JDBC-Gen
	 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
	 */
	public static class _CustomerNames extends PropertyName<Customer> {
		/**
		 * インスタンスを構築します。
		 */
		public _CustomerNames() {
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param name
		 *            名前
		 */
		public _CustomerNames(final String name) {
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
		public _CustomerNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
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
		 * customerNameのプロパティ名を返します。
		 * 
		 * @return customerNameのプロパティ名
		 */
		public PropertyName<String> customerName() {
			return new PropertyName<String>(this, "customerName");
		}
	}
}
