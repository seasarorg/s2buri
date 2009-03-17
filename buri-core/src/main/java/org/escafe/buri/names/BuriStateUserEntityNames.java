package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriStateUserEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriStateUserEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriStateUserEntityNames {

	private BuriStateUserEntityNames() {
		throw new AssertionError();
	}

    /**
     * stateUserIdのプロパティ名を返します。
     * 
     * @return stateUserIdのプロパティ名
     */
    public static PropertyName<Long> stateUserId() {
        return new PropertyName<Long>("stateUserId");
    }

    /**
     * stateIdのプロパティ名を返します。
     * 
     * @return stateIdのプロパティ名
     */
    public static PropertyName<Long> stateId() {
        return new PropertyName<Long>("stateId");
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
     * insertDateのプロパティ名を返します。
     * 
     * @return insertDateのプロパティ名
     */
    public static PropertyName<Date> insertDate() {
        return new PropertyName<Date>("insertDate");
    }

    /**
     * deleteDateのプロパティ名を返します。
     * 
     * @return deleteDateのプロパティ名
     */
    public static PropertyName<Date> deleteDate() {
        return new PropertyName<Date>("deleteDate");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriStateUserEntityNames extends PropertyName<BuriStateUserEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriStateUserEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriStateUserEntityNames(final String name) {
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
        public _BuriStateUserEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * stateUserIdのプロパティ名を返します。
         *
         * @return stateUserIdのプロパティ名
         */
        public PropertyName<Long> stateUserId() {
            return new PropertyName<Long>(this, "stateUserId");
        }

        /**
         * stateIdのプロパティ名を返します。
         *
         * @return stateIdのプロパティ名
         */
        public PropertyName<Long> stateId() {
            return new PropertyName<Long>(this, "stateId");
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
         * insertDateのプロパティ名を返します。
         *
         * @return insertDateのプロパティ名
         */
        public PropertyName<Date> insertDate() {
            return new PropertyName<Date>(this, "insertDate");
        }

        /**
         * deleteDateのプロパティ名を返します。
         *
         * @return deleteDateのプロパティ名
         */
        public PropertyName<Date> deleteDate() {
            return new PropertyName<Date>(this, "deleteDate");
        }
    }
}