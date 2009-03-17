package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriDataPathHistoryEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriDataPathHistoryEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriDataPathHistoryEntityNames {

	private BuriDataPathHistoryEntityNames() {
		throw new AssertionError();
	}

    /**
     * historyIdのプロパティ名を返します。
     * 
     * @return historyIdのプロパティ名
     */
    public static PropertyName<Long> historyId() {
        return new PropertyName<Long>("historyId");
    }

    /**
     * pathIdのプロパティ名を返します。
     * 
     * @return pathIdのプロパティ名
     */
    public static PropertyName<Long> pathId() {
        return new PropertyName<Long>("pathId");
    }

    /**
     * pathNameのプロパティ名を返します。
     * 
     * @return pathNameのプロパティ名
     */
    public static PropertyName<String> pathName() {
        return new PropertyName<String>("pathName");
    }

    /**
     * dataIdのプロパティ名を返します。
     * 
     * @return dataIdのプロパティ名
     */
    public static PropertyName<Long> dataId() {
        return new PropertyName<Long>("dataId");
    }

    /**
     * actionのプロパティ名を返します。
     * 
     * @return actionのプロパティ名
     */
    public static PropertyName<String> action() {
        return new PropertyName<String>("action");
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
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriDataPathHistoryEntityNames extends PropertyName<BuriDataPathHistoryEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriDataPathHistoryEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriDataPathHistoryEntityNames(final String name) {
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
        public _BuriDataPathHistoryEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * historyIdのプロパティ名を返します。
         *
         * @return historyIdのプロパティ名
         */
        public PropertyName<Long> historyId() {
            return new PropertyName<Long>(this, "historyId");
        }

        /**
         * pathIdのプロパティ名を返します。
         *
         * @return pathIdのプロパティ名
         */
        public PropertyName<Long> pathId() {
            return new PropertyName<Long>(this, "pathId");
        }

        /**
         * pathNameのプロパティ名を返します。
         *
         * @return pathNameのプロパティ名
         */
        public PropertyName<String> pathName() {
            return new PropertyName<String>(this, "pathName");
        }

        /**
         * dataIdのプロパティ名を返します。
         *
         * @return dataIdのプロパティ名
         */
        public PropertyName<Long> dataId() {
            return new PropertyName<Long>(this, "dataId");
        }

        /**
         * actionのプロパティ名を返します。
         *
         * @return actionのプロパティ名
         */
        public PropertyName<String> action() {
            return new PropertyName<String>(this, "action");
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
    }
}