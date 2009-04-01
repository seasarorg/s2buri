package org.escafe.buri.names;

import org.escafe.buri.entity.BuriDataEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriDataEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriDataEntityNames {

	private BuriDataEntityNames() {
		throw new AssertionError();
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
     * pkeyValのプロパティ名を返します。
     * 
     * @return pkeyValのプロパティ名
     */
    public static PropertyName<String> pkeyVal() {
        return new PropertyName<String>("pkeyVal");
    }

    /**
     * pkeyNumのプロパティ名を返します。
     * 
     * @return pkeyNumのプロパティ名
     */
    public static PropertyName<Long> pkeyNum() {
        return new PropertyName<Long>("pkeyNum");
    }

    /**
     * dataTypeのプロパティ名を返します。
     * 
     * @return dataTypeのプロパティ名
     */
    public static PropertyName<String> dataType() {
        return new PropertyName<String>("dataType");
    }

    /**
     * tableNameのプロパティ名を返します。
     * 
     * @return tableNameのプロパティ名
     */
    public static PropertyName<String> tableName() {
        return new PropertyName<String>("tableName");
    }

    /**
     * insertUserIdのプロパティ名を返します。
     * 
     * @return insertUserIdのプロパティ名
     */
    public static PropertyName<Long> insertUserId() {
        return new PropertyName<Long>("insertUserId");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriDataEntityNames extends PropertyName<BuriDataEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriDataEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriDataEntityNames(final String name) {
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
        public _BuriDataEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * pkeyValのプロパティ名を返します。
         *
         * @return pkeyValのプロパティ名
         */
        public PropertyName<String> pkeyVal() {
            return new PropertyName<String>(this, "pkeyVal");
        }

        /**
         * pkeyNumのプロパティ名を返します。
         *
         * @return pkeyNumのプロパティ名
         */
        public PropertyName<Long> pkeyNum() {
            return new PropertyName<Long>(this, "pkeyNum");
        }

        /**
         * dataTypeのプロパティ名を返します。
         *
         * @return dataTypeのプロパティ名
         */
        public PropertyName<String> dataType() {
            return new PropertyName<String>(this, "dataType");
        }

        /**
         * tableNameのプロパティ名を返します。
         *
         * @return tableNameのプロパティ名
         */
        public PropertyName<String> tableName() {
            return new PropertyName<String>(this, "tableName");
        }

        /**
         * insertUserIdのプロパティ名を返します。
         *
         * @return insertUserIdのプロパティ名
         */
        public PropertyName<Long> insertUserId() {
            return new PropertyName<Long>(this, "insertUserId");
        }
    }
}