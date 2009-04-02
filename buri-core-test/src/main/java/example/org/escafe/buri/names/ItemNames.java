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

import example.org.escafe.buri.entity.Item;

/**
 * {@link Item}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class ItemNames {
	private ItemNames() {
		throw new AssertionError();
	}

	/**
	 * itemIdのプロパティ名を返します。
	 * 
	 * @return itemIdのプロパティ名
	 */
	public static PropertyName<Long> itemId() {
		return new PropertyName<Long>("itemId");
	}

	/**
	 * itemNameのプロパティ名を返します。
	 * 
	 * @return itemNameのプロパティ名
	 */
	public static PropertyName<String> itemName() {
		return new PropertyName<String>("itemName");
	}

	/**
	 * priceのプロパティ名を返します。
	 * 
	 * @return priceのプロパティ名
	 */
	public static PropertyName<Long> price() {
		return new PropertyName<Long>("price");
	}

	/**
	 * @author S2JDBC-Gen
	 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
	 */
	public static class _ItemNames extends PropertyName<Item> {
		/**
		 * インスタンスを構築します。
		 */
		public _ItemNames() {
		}

		/**
		 * インスタンスを構築します。
		 * 
		 * @param name
		 *            名前
		 */
		public _ItemNames(final String name) {
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
		public _ItemNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * itemIdのプロパティ名を返します。
		 * 
		 * @return itemIdのプロパティ名
		 */
		public PropertyName<Long> itemId() {
			return new PropertyName<Long>(this, "itemId");
		}

		/**
		 * itemNameのプロパティ名を返します。
		 * 
		 * @return itemNameのプロパティ名
		 */
		public PropertyName<String> itemName() {
			return new PropertyName<String>(this, "itemName");
		}

		/**
		 * priceのプロパティ名を返します。
		 * 
		 * @return priceのプロパティ名
		 */
		public PropertyName<Long> price() {
			return new PropertyName<Long>(this, "price");
		}
	}
}
