package org.escafe.buri.names;

import org.escafe.buri.entity.BuriTestUser;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriTestUser}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriTestUserNames {

	private BuriTestUserNames() {
		throw new AssertionError();
	}

    /**
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<Long> userId() {
        return new PropertyName<Long>("userId");
    }

    /**
     * userNameのプロパティ名を返します。
     * 
     * @return userNameのプロパティ名
     */
    public static PropertyName<String> userName() {
        return new PropertyName<String>("userName");
    }

    /**
     * roleNameのプロパティ名を返します。
     * 
     * @return roleNameのプロパティ名
     */
    public static PropertyName<String> roleName() {
        return new PropertyName<String>("roleName");
    }

    /**
     * parentUserIdのプロパティ名を返します。
     * 
     * @return parentUserIdのプロパティ名
     */
    public static PropertyName<Long> parentUserId() {
        return new PropertyName<Long>("parentUserId");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriTestUserNames extends PropertyName<BuriTestUser> {

        /**
         * インスタンスを構築します。
         */
        public _BuriTestUserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriTestUserNames(final String name) {
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
        public _BuriTestUserNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * userIdのプロパティ名を返します。
         *
         * @return userIdのプロパティ名
         */
        public PropertyName<Long> userId() {
            return new PropertyName<Long>(this, "userId");
        }

        /**
         * userNameのプロパティ名を返します。
         *
         * @return userNameのプロパティ名
         */
        public PropertyName<String> userName() {
            return new PropertyName<String>(this, "userName");
        }

        /**
         * roleNameのプロパティ名を返します。
         *
         * @return roleNameのプロパティ名
         */
        public PropertyName<String> roleName() {
            return new PropertyName<String>(this, "roleName");
        }

        /**
         * parentUserIdのプロパティ名を返します。
         *
         * @return parentUserIdのプロパティ名
         */
        public PropertyName<Long> parentUserId() {
            return new PropertyName<Long>(this, "parentUserId");
        }
    }
}