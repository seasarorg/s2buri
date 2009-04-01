/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.util.packages.abst;

import org.escafe.buri.common.util.StringUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

public abstract class AbstDataAccessUtilManyKey extends AbstDataAccessUtil implements DataAccessUtilManyKey {

    protected String getKey(Object key, String params[]) {
        StringBuffer buff = new StringBuffer();
        String oneKey;
        for (String param : params) {
            oneKey = pkeyExpressionScript.eval(key, param, null).toString();
            buff.append(param).append("=").append(oneKey).append("\n");
        }
        return buff.toString();
    }

    protected void setStringKeyToObj(Object dto, String keyObj) {
        String vals[] = keyObj.split("\n");
        for (String val : vals) {
            if (val.length() > 0) {
                String keyVal[] = StringUtil.splitFastString(val, "=");
                assert keyVal.length == 2;
                setValueToObject(dto, keyVal[1], keyVal[0]);
            }
        }
    }

    protected void setValueToObject(Object target, Object val, String prop) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc(prop);
        propertyDesc.setValue(target, val);
    }

    protected boolean hasPkey(Object data, String conditions[]) {
        boolean result = true;
        for (String condition : conditions) {
            Object evalResult = pkeyExpressionScript.eval(data, condition, null);
            assert evalResult instanceof Boolean;
            if (!((Boolean) evalResult).booleanValue()) {
                result = false;
            }
        }
        if (result) {
            Object check = getDataFromDto(data);
            if (check == null) {
                result = false;
            }
        }
        return result;
    }

    protected abstract String[] getConditions();

    public boolean hasAvailableKey(Object keyVal) {
        return hasPkey(keyVal, getConditions());
    }

}
