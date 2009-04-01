package example.org.escafe.buri.names;

import example.org.escafe.buri.entity.Item;
import org.seasar.extension.jdbc.name.PropertyName;

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