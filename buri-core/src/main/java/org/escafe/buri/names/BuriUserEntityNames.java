package org.escafe.buri.names;

import org.escafe.buri.entity.BuriUserEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriUserEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriUserEntityNames {

	private BuriUserEntityNames() {
		throw new AssertionError();
	}

    /**
     * buriUserIdのプロパティ名を返します。
     * 
     * @return buriUserIdのプロパティ名
     */
    public static PropertyName<Long> buriUserId() {
        return new PropertyName<Long>("buriUserId");
    }

    /**
     * userIdValのプロパティ名を返します。
     * 
     * @return userIdValのプロパティ名
     */
    public static PropertyName<String> userIdVal() {
        return new PropertyName<String>("userIdVal");
    }

    /**
     * userIdNumのプロパティ名を返します。
     * 
     * @return userIdNumのプロパティ名
     */
    public static PropertyName<Long> userIdNum() {
        return new PropertyName<Long>("userIdNum");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriUserEntityNames extends PropertyName<BuriUserEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriUserEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriUserEntityNames(final String name) {
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
        public _BuriUserEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * buriUserIdのプロパティ名を返します。
         *
         * @return buriUserIdのプロパティ名
         */
        public PropertyName<Long> buriUserId() {
            return new PropertyName<Long>(this, "buriUserId");
        }

        /**
         * userIdValのプロパティ名を返します。
         *
         * @return userIdValのプロパティ名
         */
        public PropertyName<String> userIdVal() {
            return new PropertyName<String>(this, "userIdVal");
        }

        /**
         * userIdNumのプロパティ名を返します。
         *
         * @return userIdNumのプロパティ名
         */
        public PropertyName<Long> userIdNum() {
            return new PropertyName<Long>(this, "userIdNum");
        }
    }
}