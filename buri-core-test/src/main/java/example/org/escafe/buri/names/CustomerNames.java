package example.org.escafe.buri.names;

import example.org.escafe.buri.entity.Customer;
import org.seasar.extension.jdbc.name.PropertyName;

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