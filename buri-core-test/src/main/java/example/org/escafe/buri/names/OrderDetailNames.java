package example.org.escafe.buri.names;

import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.names.OrderTitleNames._OrderTitleNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link OrderDetail}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class OrderDetailNames {

	private OrderDetailNames() {
		throw new AssertionError();
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
     * orderCountのプロパティ名を返します。
     * 
     * @return orderCountのプロパティ名
     */
    public static PropertyName<Integer> orderCount() {
        return new PropertyName<Integer>("orderCount");
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
     * orderTitleIdのプロパティ名を返します。
     * 
     * @return orderTitleIdのプロパティ名
     */
    public static PropertyName<Long> orderTitleId() {
        return new PropertyName<Long>("orderTitleId");
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
    public static class _OrderDetailNames extends PropertyName<OrderDetail> {

        /**
         * インスタンスを構築します。
         */
        public _OrderDetailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _OrderDetailNames(final String name) {
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
        public _OrderDetailNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * orderCountのプロパティ名を返します。
         *
         * @return orderCountのプロパティ名
         */
        public PropertyName<Integer> orderCount() {
            return new PropertyName<Integer>(this, "orderCount");
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
         * orderTitleIdのプロパティ名を返します。
         *
         * @return orderTitleIdのプロパティ名
         */
        public PropertyName<Long> orderTitleId() {
            return new PropertyName<Long>(this, "orderTitleId");
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