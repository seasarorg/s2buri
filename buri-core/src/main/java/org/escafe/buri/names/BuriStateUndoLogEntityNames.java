package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriStateUndoLogEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriStateUndoLogEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriStateUndoLogEntityNames {

	private BuriStateUndoLogEntityNames() {
		throw new AssertionError();
	}

    /**
     * stateUndoLogIdのプロパティ名を返します。
     * 
     * @return stateUndoLogIdのプロパティ名
     */
    public static PropertyName<Long> stateUndoLogId() {
        return new PropertyName<Long>("stateUndoLogId");
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
     * btIdのプロパティ名を返します。
     * 
     * @return btIdのプロパティ名
     */
    public static PropertyName<Long> btId() {
        return new PropertyName<Long>("btId");
    }

    /**
     * createBtIdのプロパティ名を返します。
     * 
     * @return createBtIdのプロパティ名
     */
    public static PropertyName<Long> createBtId() {
        return new PropertyName<Long>("createBtId");
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
     * autoRunTimeのプロパティ名を返します。
     * 
     * @return autoRunTimeのプロパティ名
     */
    public static PropertyName<Date> autoRunTime() {
        return new PropertyName<Date>("autoRunTime");
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
    public static class _BuriStateUndoLogEntityNames extends PropertyName<BuriStateUndoLogEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriStateUndoLogEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriStateUndoLogEntityNames(final String name) {
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
        public _BuriStateUndoLogEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * stateUndoLogIdのプロパティ名を返します。
         *
         * @return stateUndoLogIdのプロパティ名
         */
        public PropertyName<Long> stateUndoLogId() {
            return new PropertyName<Long>(this, "stateUndoLogId");
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

        /**
         * btIdのプロパティ名を返します。
         *
         * @return btIdのプロパティ名
         */
        public PropertyName<Long> btId() {
            return new PropertyName<Long>(this, "btId");
        }

        /**
         * createBtIdのプロパティ名を返します。
         *
         * @return createBtIdのプロパティ名
         */
        public PropertyName<Long> createBtId() {
            return new PropertyName<Long>(this, "createBtId");
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
         * autoRunTimeのプロパティ名を返します。
         *
         * @return autoRunTimeのプロパティ名
         */
        public PropertyName<Date> autoRunTime() {
            return new PropertyName<Date>(this, "autoRunTime");
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