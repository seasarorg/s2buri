/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.dataaccess;

import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public interface BuriDataAccessFactory extends DataAccessFactory {
	void setDataAccessUtil(Class<?> tgtClass, DataAccessUtilLongKey utilLongKey);

	void setDataAccessUtil(Class<?> tgtClass, DataAccessUtilManyKey utilManyKey);

	void setFilterAccessUtil(Class<?> tgtClass, FilterAccessUtil accessUtil);

	void setPreprocessAccessUtil(Class<?> tgtClass,
	        PreprocessAccessUtil accessUtil);

	void addChildFactory(String key, DataAccessFactory factory);

	void destroy();
}
