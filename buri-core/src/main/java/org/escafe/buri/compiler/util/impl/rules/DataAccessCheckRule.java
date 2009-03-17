/*
 * 作成日: 2006/07/05
 *
 */
package org.escafe.buri.compiler.util.impl.rules;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.persistence.Id;

import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.escafe.buri.exception.BuriDataFieldErrorException;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.util.ClassUtil;

public class DataAccessCheckRule extends AbstractBuriDataFieldProcRule {
	@Override
	public void finishCheck(BuriDataFieldType src) {
		String id = src.getId();
		if (!ClassDefUtilImpl.isClassName(id)) {
			throw new BuriDataFieldErrorException(id);
		}
		if (src.getKeys().size() > 0) {
			checkKeyName(src);
		}
	}

	protected void checkKeyName(BuriDataFieldType src) {
		String id = src.getId();
		Class<?> tgtClass = ClassUtil.forName(id);
		Field fields[] = ClassUtil.getDeclaredFields(tgtClass);
		for (Field field : fields) {
			if (field.getAnnotation(Id.class) != null) {
				return;
			}
		}
		Iterator<?> ite = src.getKeys().keySet().iterator();
		while (ite.hasNext()) {
			String keyVal = ite.next().toString();
			if (!ClassDefUtilImpl.hasPropertyName(tgtClass, keyVal)) {
				throw new BuriDataFieldErrorException(tgtClass, keyVal);
			}
		}
	}
}
