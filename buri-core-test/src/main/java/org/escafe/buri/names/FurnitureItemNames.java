package org.escafe.buri.names;

import java.util.Date;
import org.escafe.buri.entity.AcquisitionType;
import org.escafe.buri.entity.FurnitureItem;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link FurnitureItem}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class FurnitureItemNames {

	private FurnitureItemNames() {
		throw new AssertionError();
	}

    /**
     * furnitureIdのプロパティ名を返します。
     * 
     * @return furnitureIdのプロパティ名
     */
    public static PropertyName<Long> furnitureId() {
        return new PropertyName<Long>("furnitureId");
    }

    /**
     * typeのプロパティ名を返します。
     * 
     * @return typeのプロパティ名
     */
    public static PropertyName<String> type() {
        return new PropertyName<String>("type");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
    }

    /**
     * acquisitionのプロパティ名を返します。
     * 
     * @return acquisitionのプロパティ名
     */
    public static PropertyName<Date> acquisition() {
        return new PropertyName<Date>("acquisition");
    }

    /**
     * acquisitionTypeのプロパティ名を返します。
     * 
     * @return acquisitionTypeのプロパティ名
     */
    public static PropertyName<AcquisitionType> acquisitionType() {
        return new PropertyName<AcquisitionType>("acquisitionType");
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
    public static class _FurnitureItemNames extends PropertyName<FurnitureItem> {

        /**
         * インスタンスを構築します。
         */
        public _FurnitureItemNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _FurnitureItemNames(final String name) {
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
        public _FurnitureItemNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * furnitureIdのプロパティ名を返します。
         *
         * @return furnitureIdのプロパティ名
         */
        public PropertyName<Long> furnitureId() {
            return new PropertyName<Long>(this, "furnitureId");
        }

        /**
         * typeのプロパティ名を返します。
         *
         * @return typeのプロパティ名
         */
        public PropertyName<String> type() {
            return new PropertyName<String>(this, "type");
        }

        /**
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
        }

        /**
         * acquisitionのプロパティ名を返します。
         *
         * @return acquisitionのプロパティ名
         */
        public PropertyName<Date> acquisition() {
            return new PropertyName<Date>(this, "acquisition");
        }

        /**
         * acquisitionTypeのプロパティ名を返します。
         *
         * @return acquisitionTypeのプロパティ名
         */
        public PropertyName<AcquisitionType> acquisitionType() {
            return new PropertyName<AcquisitionType>(this, "acquisitionType");
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