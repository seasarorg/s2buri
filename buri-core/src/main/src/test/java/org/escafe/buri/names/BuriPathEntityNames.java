package org.escafe.buri.names;

import org.escafe.buri.entity.BuriPathEntity;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BuriPathEntity}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class BuriPathEntityNames {

	private BuriPathEntityNames() {
		throw new AssertionError();
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
     * @author S2JDBC-Gen
     * @suppresshack com.google.code.hack.ej2.ToStringRewriter
     */
    public static class _BuriPathEntityNames extends PropertyName<BuriPathEntity> {

        /**
         * インスタンスを構築します。
         */
        public _BuriPathEntityNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BuriPathEntityNames(final String name) {
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
        public _BuriPathEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
    }
}