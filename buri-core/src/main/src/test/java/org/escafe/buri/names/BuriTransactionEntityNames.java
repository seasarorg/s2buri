package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriTransactionEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriTransactionEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriTransactionEntityNames {

	private BuriTransactionEntityNames() {
		throw new AssertionError();
	}

    /**
     * btIdのプロパティ名を返します。
     * 
     * @return btIdのプロパティ名
     */
    public static PropertyName<Long> btId() {
        return new PropertyName<Long>("btId");
    }

    /**
     * insertDateのプロパティ名を返します。
     * 
     * @return insertDateのプロパティ名
     */
    public static PropertyName<Date> insertDate() {
        return new PropertyName<Date>("insertDate");
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
    public static class _BuriTransactionEntityNames extends PropertyName<BuriTransactionEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriTransactionEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriTransactionEntityNames(final String name) {
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
        public _BuriTransactionEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * btIdのプロパティ名を返します。
         *
         * @return btIdのプロパティ名
         */
        public PropertyName<Long> btId() {
            return new PropertyName<Long>(this, "btId");
        }

        /**
         * insertDateのプロパティ名を返します。
         *
         * @return insertDateのプロパティ名
         */
        public PropertyName<Date> insertDate() {
            return new PropertyName<Date>(this, "insertDate");
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