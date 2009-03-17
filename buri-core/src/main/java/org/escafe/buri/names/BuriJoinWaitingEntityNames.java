package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriJoinWaitingEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriJoinWaitingEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriJoinWaitingEntityNames {

	private BuriJoinWaitingEntityNames() {
		throw new AssertionError();
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
     * pathIdのプロパティ名を返します。
     * 
     * @return pathIdのプロパティ名
     */
    public static PropertyName<Long> pathId() {
        return new PropertyName<Long>("pathId");
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
     * branchIdのプロパティ名を返します。
     * 
     * @return branchIdのプロパティ名
     */
    public static PropertyName<Long> branchId() {
        return new PropertyName<Long>("branchId");
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
     * processDateのプロパティ名を返します。
     * 
     * @return processDateのプロパティ名
     */
    public static PropertyName<Date> processDate() {
        return new PropertyName<Date>("processDate");
    }

    /**
     * abortDateのプロパティ名を返します。
     * 
     * @return abortDateのプロパティ名
     */
    public static PropertyName<Date> abortDate() {
        return new PropertyName<Date>("abortDate");
    }

    /**
     * versionNoのプロパティ名を返します。
     * 
     * @return versionNoのプロパティ名
     */
    public static PropertyName<Integer> versionNo() {
        return new PropertyName<Integer>("versionNo");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriJoinWaitingEntityNames extends PropertyName<BuriJoinWaitingEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriJoinWaitingEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriJoinWaitingEntityNames(final String name) {
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
        public _BuriJoinWaitingEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * pathIdのプロパティ名を返します。
         *
         * @return pathIdのプロパティ名
         */
        public PropertyName<Long> pathId() {
            return new PropertyName<Long>(this, "pathId");
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
         * branchIdのプロパティ名を返します。
         *
         * @return branchIdのプロパティ名
         */
        public PropertyName<Long> branchId() {
            return new PropertyName<Long>(this, "branchId");
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
         * processDateのプロパティ名を返します。
         *
         * @return processDateのプロパティ名
         */
        public PropertyName<Date> processDate() {
            return new PropertyName<Date>(this, "processDate");
        }

        /**
         * abortDateのプロパティ名を返します。
         *
         * @return abortDateのプロパティ名
         */
        public PropertyName<Date> abortDate() {
            return new PropertyName<Date>(this, "abortDate");
        }

        /**
         * versionNoのプロパティ名を返します。
         *
         * @return versionNoのプロパティ名
         */
        public PropertyName<Integer> versionNo() {
            return new PropertyName<Integer>(this, "versionNo");
        }
    }
}