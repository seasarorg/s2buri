package org.escafe.buri.names;

import org.escafe.buri.entity.BuriTestINT;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriTestINT}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriTestINTNames {

	private BuriTestINTNames() {
		throw new AssertionError();
	}

    /**
     * testIdのプロパティ名を返します。
     * 
     * @return testIdのプロパティ名
     */
    public static PropertyName<Long> testId() {
        return new PropertyName<Long>("testId");
    }

    /**
     * valueのプロパティ名を返します。
     * 
     * @return valueのプロパティ名
     */
    public static PropertyName<String> value() {
        return new PropertyName<String>("value");
    }

    /**
     * versionNoのプロパティ名を返します。
     * 
     * @return versionNoのプロパティ名
     */
    public static PropertyName<Long> versionNo() {
        return new PropertyName<Long>("versionNo");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriTestINTNames extends PropertyName<BuriTestINT> {

        /**
         * インスタンスを構築します。
         */
        public _BuriTestINTNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriTestINTNames(final String name) {
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
        public _BuriTestINTNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * testIdのプロパティ名を返します。
         *
         * @return testIdのプロパティ名
         */
        public PropertyName<Long> testId() {
            return new PropertyName<Long>(this, "testId");
        }

        /**
         * valueのプロパティ名を返します。
         *
         * @return valueのプロパティ名
         */
        public PropertyName<String> value() {
            return new PropertyName<String>(this, "value");
        }

        /**
         * versionNoのプロパティ名を返します。
         *
         * @return versionNoのプロパティ名
         */
        public PropertyName<Long> versionNo() {
            return new PropertyName<Long>(this, "versionNo");
        }
    }
}