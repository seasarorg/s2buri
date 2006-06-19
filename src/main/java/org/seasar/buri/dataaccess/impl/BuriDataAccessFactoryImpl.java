/*
 * çÏê¨ì˙: 2006/05/02
 *
 */
package org.seasar.buri.dataaccess.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public class BuriDataAccessFactoryImpl implements BuriDataAccessFactory{
    private Map dataAccess = new HashMap();
    private Map filterAccess = new HashMap();
    private Map preprocess = new HashMap();
    private Map childFactory = new HashMap();

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilLongKey utilLongKey) {
        dataAccess.put(tgtClass, utilLongKey);
    }

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilManyKey utilManyKey) {
        dataAccess.put(tgtClass, utilManyKey);
    }

    public void setFilterAccessUtil(Class tgtClass, FilterAccessUtil accessUtil) {
        filterAccess.put(tgtClass, accessUtil);
    }

    public void setPreprocessAccessUtil(Class tgtClass, PreprocessAccessUtil accessUtil) {
        preprocess.put(tgtClass, accessUtil);
    }

    public void addChildFactory(String key, DataAccessFactory factory) {
        childFactory.put(key, factory);
    }

    public DataAccessUtil getDataAccessUtil(Class tgtClass) {
        DataAccessUtil result = null;
        if( dataAccess.containsKey(tgtClass) ) {
            result = (DataAccessUtil)dataAccess.get(tgtClass);
        } else {
            result = getChildDataAccessUtil(tgtClass);
        }
        return result;
    }
    
    protected DataAccessUtil getChildDataAccessUtil(Class tgtClass) {
        DataAccessUtil result = null;
        Iterator ite = childFactory.keySet().iterator();
        while(ite.hasNext()) {
            String key = (String)ite.next();
            DataAccessFactory factory = (DataAccessFactory)childFactory.get(key);
            result = factory.getDataAccessUtil(tgtClass);
            if(result != null) {
                break;
            }
        }
        return result;
    }

    public FilterAccessUtil getFilterAccessUtil(Class tgtClass) {
        FilterAccessUtil result = null;
        if( filterAccess.containsKey(tgtClass) ) {
            result = (FilterAccessUtil)filterAccess.get(tgtClass);
        } else {
            result = getChildFilterAccessUtil(tgtClass);
        }
        return result;
    }
    
    protected FilterAccessUtil getChildFilterAccessUtil(Class tgtClass) {
        FilterAccessUtil result = null;
        Iterator ite = childFactory.keySet().iterator();
        while(ite.hasNext()) {
            String key = (String)ite.next();
            DataAccessFactory factory = (DataAccessFactory)childFactory.get(key);
            result = factory.getFilterAccessUtil(tgtClass);
            if(result != null) {
                break;
            }
        }
        return result;
    }

    public PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass) {
        PreprocessAccessUtil result = null;
        if( preprocess.containsKey(tgtClass) ) {
            result = (PreprocessAccessUtil)preprocess.get(tgtClass);
        } else {
            result = getChildPreprocessAccessUtil(tgtClass);
        }
        return result;
    }
    
    protected PreprocessAccessUtil getChildPreprocessAccessUtil(Class tgtClass) {
        PreprocessAccessUtil result = null;
        Iterator ite = childFactory.keySet().iterator();
        while(ite.hasNext()) {
            String key = (String)ite.next();
            DataAccessFactory factory = (DataAccessFactory)childFactory.get(key);
            result = factory.getPreprocessAccessUtil(tgtClass);
            if(result != null) {
                break;
            }
        }
        return result;
    }

}
