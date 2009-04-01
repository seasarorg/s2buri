package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriWaitingUserEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriWaitingUserEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriWaitingUserEntityNames {

	private BuriWaitingUserEntityNames() {
		throw new AssertionError();
	}

    /**
     * waitingUserIdのプロパティ名を返します。
     * 
     * @return waitingUserIdのプロパティ名
     */
    public static PropertyName<Long> waitingUserId() {
        return new PropertyName<Long>("waitingUserId");
    }

    /**
     * waitingIdのプロパティ名を返します。
     * 
     * @return waitingIdのプロパティ名
     */
    public static PropertyName<Long> waitingId() {
        return new PropertyName<Long>("waitingId");
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
    public static class _BuriWaitingUserEntityNames extends PropertyName<BuriWaitingUserEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriWaitingUserEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriWaitingUserEntityNames(final String name) {
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
        public _BuriWaitingUserEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * waitingUserIdのプロパティ名を返します。
         *
         * @return waitingUserIdのプロパティ名
         */
        public PropertyName<Long> waitingUserId() {
            return new PropertyName<Long>(this, "waitingUserId");
        }

        /**
         * waitingIdのプロパティ名を返します。
         *
         * @return waitingIdのプロパティ名
         */
        public PropertyName<Long> waitingId() {
            return new PropertyName<Long>(this, "waitingId");
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