package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriBranchEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriBranchEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriBranchEntityNames {

	private BuriBranchEntityNames() {
		throw new AssertionError();
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
     * parentBranchIdのプロパティ名を返します。
     * 
     * @return parentBranchIdのプロパティ名
     */
    public static PropertyName<Long> parentBranchId() {
        return new PropertyName<Long>("parentBranchId");
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
     * btIdのプロパティ名を返します。
     * 
     * @return btIdのプロパティ名
     */
    public static PropertyName<Long> btId() {
        return new PropertyName<Long>("btId");
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
    public static class _BuriBranchEntityNames extends PropertyName<BuriBranchEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriBranchEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriBranchEntityNames(final String name) {
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
        public _BuriBranchEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * parentBranchIdのプロパティ名を返します。
         *
         * @return parentBranchIdのプロパティ名
         */
        public PropertyName<Long> parentBranchId() {
            return new PropertyName<Long>(this, "parentBranchId");
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
         * btIdのプロパティ名を返します。
         *
         * @return btIdのプロパティ名
         */
        public PropertyName<Long> btId() {
            return new PropertyName<Long>(this, "btId");
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
         * versionNoのプロパティ名を返します。
         *
         * @return versionNoのプロパティ名
         */
        public PropertyName<Long> versionNo() {
            return new PropertyName<Long>(this, "versionNo");
        }
    }
}