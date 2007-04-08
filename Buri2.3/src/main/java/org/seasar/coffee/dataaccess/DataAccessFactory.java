/*
 * 作成日: 2006/03/24
 *
 */
package org.seasar.coffee.dataaccess;


public interface DataAccessFactory {
    DataAccessUtil getDataAccessUtil(Class tgtClass);
    FilterAccessUtil getFilterAccessUtil(Class tgtClass);
    PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass);
    void addChildFactory(String key,DataAccessFactory factory);

}
