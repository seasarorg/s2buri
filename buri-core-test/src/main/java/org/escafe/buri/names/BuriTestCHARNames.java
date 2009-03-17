package org.escafe.buri.names;

import org.escafe.buri.entity.BuriTestCHAR;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriTestCHAR}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriTestCHARNames {

	private BuriTestCHARNames() {
		throw new AssertionError();
	}

    /**
     * testIdのプロパティ名を返します。
     * 
     * @return testIdのプロパティ名
     */
    public static PropertyName<String> testId() {
        return new PropertyName<String>("testId");
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
    public static class _BuriTestCHARNames extends PropertyName<BuriTestCHAR> {

        /**
         * インスタンスを構築します。
         */
        public _BuriTestCHARNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriTestCHARNames(final String name) {
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
        public _BuriTestCHARNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * testIdのプロパティ名を返します。
         *
         * @return testIdのプロパティ名
         */
        public PropertyName<String> testId() {
            return new PropertyName<String>(this, "testId");
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