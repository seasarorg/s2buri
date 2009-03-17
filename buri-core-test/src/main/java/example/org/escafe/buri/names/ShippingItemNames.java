package example.org.escafe.buri.names;

import example.org.escafe.buri.entity.ShippingItem;
import example.org.escafe.buri.names.ShippingNames._ShippingNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link ShippingItem}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class ShippingItemNames {

	private ShippingItemNames() {
		throw new AssertionError();
	}

    /**
     * shippingItemIdのプロパティ名を返します。
     * 
     * @return shippingItemIdのプロパティ名
     */
    public static PropertyName<Long> shippingItemId() {
        return new PropertyName<Long>("shippingItemId");
    }

    /**
     * orderDetailIdのプロパティ名を返します。
     * 
     * @return orderDetailIdのプロパティ名
     */
    public static PropertyName<Long> orderDetailId() {
        return new PropertyName<Long>("orderDetailId");
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
     * shippingのプロパティ名を返します。
     * 
     * @return shippingのプロパティ名
     */
    public static _ShippingNames shipping() {
        return new _ShippingNames("shipping");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _ShippingItemNames extends PropertyName<ShippingItem> {

        /**
         * インスタンスを構築します。
         */
        public _ShippingItemNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ShippingItemNames(final String name) {
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
        public _ShippingItemNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * shippingItemIdのプロパティ名を返します。
         *
         * @return shippingItemIdのプロパティ名
         */
        public PropertyName<Long> shippingItemId() {
            return new PropertyName<Long>(this, "shippingItemId");
        }

        /**
         * orderDetailIdのプロパティ名を返します。
         *
         * @return orderDetailIdのプロパティ名
         */
        public PropertyName<Long> orderDetailId() {
            return new PropertyName<Long>(this, "orderDetailId");
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
         * shippingのプロパティ名を返します。
         * 
         * @return shippingのプロパティ名
         */
        public _ShippingNames shipping() {
            return new _ShippingNames(this, "shipping");
        }
    }
}