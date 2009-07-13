package org.escafe.buri.example.names;

import java.util.Date;
import javax.annotation.Generated;
import org.escafe.buri.example.entity.PurchaseApplication;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link PurchaseApplication}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public final class PurchaseApplicationNames {

	private PurchaseApplicationNames() {
		throw new AssertionError();
	}

    /**
     * purchaseApplicationIdのプロパティ名を返します。
     * 
     * @return purchaseApplicationIdのプロパティ名
     */
    public static PropertyName<Long> purchaseApplicationId() {
        return new PropertyName<Long>("purchaseApplicationId");
    }

    /**
     * applicantNameのプロパティ名を返します。
     * 
     * @return applicantNameのプロパティ名
     */
    public static PropertyName<String> applicantName() {
        return new PropertyName<String>("applicantName");
    }

    /**
     * applicationDateのプロパティ名を返します。
     * 
     * @return applicationDateのプロパティ名
     */
    public static PropertyName<Date> applicationDate() {
        return new PropertyName<Date>("applicationDate");
    }

    /**
     * productNameのプロパティ名を返します。
     * 
     * @return productNameのプロパティ名
     */
    public static PropertyName<String> productName() {
        return new PropertyName<String>("productName");
    }

    /**
     * amountのプロパティ名を返します。
     * 
     * @return amountのプロパティ名
     */
    public static PropertyName<Integer> amount() {
        return new PropertyName<Integer>("amount");
    }

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Date> createDate() {
        return new PropertyName<Date>("createDate");
    }

    /**
     * updateDateのプロパティ名を返します。
     * 
     * @return updateDateのプロパティ名
     */
    public static PropertyName<Date> updateDate() {
        return new PropertyName<Date>("updateDate");
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
    public static class _PurchaseApplicationNames extends PropertyName<PurchaseApplication> {

        /**
         * インスタンスを構築します。
         */
        public _PurchaseApplicationNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _PurchaseApplicationNames(final String name) {
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
        public _PurchaseApplicationNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * purchaseApplicationIdのプロパティ名を返します。
         *
         * @return purchaseApplicationIdのプロパティ名
         */
        public PropertyName<Long> purchaseApplicationId() {
            return new PropertyName<Long>(this, "purchaseApplicationId");
        }

        /**
         * applicantNameのプロパティ名を返します。
         *
         * @return applicantNameのプロパティ名
         */
        public PropertyName<String> applicantName() {
            return new PropertyName<String>(this, "applicantName");
        }

        /**
         * applicationDateのプロパティ名を返します。
         *
         * @return applicationDateのプロパティ名
         */
        public PropertyName<Date> applicationDate() {
            return new PropertyName<Date>(this, "applicationDate");
        }

        /**
         * productNameのプロパティ名を返します。
         *
         * @return productNameのプロパティ名
         */
        public PropertyName<String> productName() {
            return new PropertyName<String>(this, "productName");
        }

        /**
         * amountのプロパティ名を返します。
         *
         * @return amountのプロパティ名
         */
        public PropertyName<Integer> amount() {
            return new PropertyName<Integer>(this, "amount");
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Date> createDate() {
            return new PropertyName<Date>(this, "createDate");
        }

        /**
         * updateDateのプロパティ名を返します。
         *
         * @return updateDateのプロパティ名
         */
        public PropertyName<Date> updateDate() {
            return new PropertyName<Date>(this, "updateDate");
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