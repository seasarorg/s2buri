package org.seasar.buri.dataaccess.impl;

import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public class BuriDataAccessDummyFactoryImpl implements BuriDataAccessFactory {

	public void addChildFactory(String key, DataAccessFactory factory) {
	}

	public void setDataAccessUtil(Class tgtClass,DataAccessUtilLongKey utilLongKey) {
	}

	public void setDataAccessUtil(Class tgtClass,DataAccessUtilManyKey utilManyKey) {
	}

	public void setFilterAccessUtil(Class tgtClass, FilterAccessUtil accessUtil) {
	}

	public void setPreprocessAccessUtil(Class tgtClass,PreprocessAccessUtil accessUtil) {
	}

	public DataAccessUtil getDataAccessUtil(Class tgtClass) {
		return null;
	}

	public FilterAccessUtil getFilterAccessUtil(Class tgtClass) {
		return null;
	}

	public PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass) {
		return null;
	}

}
