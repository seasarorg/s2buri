package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.BuriPathDataUserEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriPathDataUserEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriPathDataUserEntityNames {

	private BuriPathDataUserEntityNames() {
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
     * realPathNameのプロパティ名を返します。
     * 
     * @return realPathNameのプロパティ名
     */
    public static PropertyName<String> realPathName() {
        return new PropertyName<String>("realPathName");
    }

    /**
     * pathTypeのプロパティ名を返します。
     * 
     * @return pathTypeのプロパティ名
     */
    public static PropertyName<Long> pathType() {
        return new PropertyName<Long>("pathType");
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
     * pkeyValのプロパティ名を返します。
     * 
     * @return pkeyValのプロパティ名
     */
    public static PropertyName<String> pkeyVal() {
        return new PropertyName<String>("pkeyVal");
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
     * autoRunTimeのプロパティ名を返します。
     * 
     * @return autoRunTimeのプロパティ名
     */
    public static PropertyName<Date> autoRunTime() {
        return new PropertyName<Date>("autoRunTime");
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
     * stateIdのプロパティ名を返します。
     * 
     * @return stateIdのプロパティ名
     */
    public static PropertyName<Long> stateId() {
        return new PropertyName<Long>("stateId");
    }

    /**
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriPathDataUserEntityNames extends PropertyName<BuriPathDataUserEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriPathDataUserEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriPathDataUserEntityNames(final String name) {
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
        public _BuriPathDataUserEntityNames(final PropertyName<?> parent, final String name) {
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
         * realPathNameのプロパティ名を返します。
         *
         * @return realPathNameのプロパティ名
         */
        public PropertyName<String> realPathName() {
            return new PropertyName<String>(this, "realPathName");
        }

        /**
         * pathTypeのプロパティ名を返します。
         *
         * @return pathTypeのプロパティ名
         */
        public PropertyName<Long> pathType() {
            return new PropertyName<Long>(this, "pathType");
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
         * pkeyValのプロパティ名を返します。
         *
         * @return pkeyValのプロパティ名
         */
        public PropertyName<String> pkeyVal() {
            return new PropertyName<String>(this, "pkeyVal");
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
         * autoRunTimeのプロパティ名を返します。
         *
         * @return autoRunTimeのプロパティ名
         */
        public PropertyName<Date> autoRunTime() {
            return new PropertyName<Date>(this, "autoRunTime");
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
         * stateIdのプロパティ名を返します。
         *
         * @return stateIdのプロパティ名
         */
        public PropertyName<Long> stateId() {
            return new PropertyName<Long>(this, "stateId");
        }
    }
}