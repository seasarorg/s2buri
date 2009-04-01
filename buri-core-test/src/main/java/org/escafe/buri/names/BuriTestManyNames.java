package org.escafe.buri.names;

import org.escafe.buri.entity.BuriTestMany;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriTestMany}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriTestManyNames {

	private BuriTestManyNames() {
		throw new AssertionError();
	}

    /**
     * testId01のプロパティ名を返します。
     * 
     * @return testId01のプロパティ名
     */
    public static PropertyName<Long> testId01() {
        return new PropertyName<Long>("testId01");
    }

    /**
     * testId02のプロパティ名を返します。
     * 
     * @return testId02のプロパティ名
     */
    public static PropertyName<Long> testId02() {
        return new PropertyName<Long>("testId02");
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
    public static class _BuriTestManyNames extends PropertyName<BuriTestMany> {

        /**
         * インスタンスを構築します。
         */
        public _BuriTestManyNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriTestManyNames(final String name) {
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
        public _BuriTestManyNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * testId01のプロパティ名を返します。
         *
         * @return testId01のプロパティ名
         */
        public PropertyName<Long> testId01() {
            return new PropertyName<Long>(this, "testId01");
        }

        /**
         * testId02のプロパティ名を返します。
         *
         * @return testId02のプロパティ名
         */
        public PropertyName<Long> testId02() {
            return new PropertyName<Long>(this, "testId02");
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