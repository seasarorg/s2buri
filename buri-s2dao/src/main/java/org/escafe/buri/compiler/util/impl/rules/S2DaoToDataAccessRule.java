/*
 * 作成日: 2006/07/06
 *
 */
package org.escafe.buri.compiler.util.impl.rules;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.BuriConfiguration;
import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class S2DaoToDataAccessRule extends AbstractBuriDataFieldProcRule {

	private static Logger logger = Logger.getLogger(S2DaoToDataAccessRule.class);

	protected String daoKeyName = "dao";
	public final String DAOKEY = "dao";

	private BuriConfiguration configuration;
	private S2Container container;
	private ClassDefUtil classDefUtil;
	private DataAccessRuleEventCaller dataAccessRuleEventCaller;
    
	@Override
	public boolean getRequiredRule(BuriDataFieldType src) {
		if (hasName(src, "preprocess")) {
			return false;
		}
		return true;
	}

	public boolean isRequiredNegotiate(BuriDataFieldType src) {
		if (src.getKeys().size() == 0) {
			return true;
		}
		if (!hasName(src, "select")) {
			return true;
		}
		if (!hasName(src, "insert")) {
			return true;
		}
		if (!hasName(src, "update")) {
			return true;
		}
		if (!hasName(src, "delete")) {
			return true;
		}
		if (!hasName(src, "tableName")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean fstCheckProcess(BuriDataFieldType src) {
		if (!isRequiredNegotiate(src)) {
			return false;
		}
		if (src.getCache().containsKey(daoKeyName)) {

		} else if (hasName(src, DAOKEY)) {
			src.getCache().put(daoKeyName, getNameVal(src, DAOKEY));
		}
		dataAccessRuleEventCaller.determinedRule(this, src);
		return true;
	}

	@Override
	public boolean process(BuriDataFieldType src) {
		// if( ! src.getCache().containsKey(daoKeyName) ) {
		// return false;
		// }
		if (src.getCache().containsKey(daoKeyName + "_end")) {
			return false;
		}
		negotiateDao(src);

		dataAccessRuleEventCaller.endNegotiateDao(this, src);

		src.getCache().put(daoKeyName + "_end", Boolean.TRUE);
		return false;
	}

	protected void negotiateDao(BuriDataFieldType src) {
		String dtoClassName = src.getId();
		String daoName = getDaoName(src, dtoClassName);
		if (daoName != null) {
			src.getCache().put(daoKeyName, daoName);
			Class daoClass = container.getRoot().getComponentDef(daoName).getComponentClass();
			pkeySetup(src);

			if (src.getKeys().size() == 1) {
				String keyName = src.getKeys().keySet().toArray()[0].toString();
				BeanDesc beanDesc = BeanDescFactory.getBeanDesc(ClassUtil.forName(dtoClassName));
				Class tgtClass = beanDesc.getPropertyDesc(keyName).getPropertyType();
				src.getCache().put(daoKeyName + "_KeyType", tgtClass);
				src.getCache().put(daoKeyName + "_KeyName", keyName);
				findAndSetupAllMethod(src, beanDesc, daoClass);
			}
		}

	}

	protected void findAndSetupAllMethod(BuriDataFieldType src, BeanDesc beanDesc, Class daoClass) {
		Method methods[] = daoClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			selectSetup(src, method, methodName, beanDesc);
			updateSetup(src, method, methodName, beanDesc);
			deleteSetup(src, method, methodName, beanDesc);
			selectManySetup(src, method, methodName, beanDesc);
			insertSetup(src, method, methodName, beanDesc);
			tableNameSetup(src, method, methodName, beanDesc);
		}
	}

	protected void tableNameSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getTableName())) {
			return;
		}
		Class tgt = ClassUtil.forName(src.getId());
		Bean bean = (Bean) tgt.getAnnotation(Bean.class);
		if (bean != null) {
			String tableName = bean.table();
			if (!StringUtil.isEmpty(tableName)) {
				src.setTableName(tableName);
				return;
			}
		}
		Object sig = classDefUtil.getMethodSignatureValue(tgt, "TABLE", "");
		if (sig != null) {
			src.setTableName(sig.toString());
		}
	}

	protected void selectManySetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getSelectMany())) {
			return;
		}

		if (isSelectManyMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setSelectMany(daoName + "." + methodName + "(#data)");
		}
	}

	protected boolean isSelectManyMethod(BuriDataFieldType src, Method method, String methodName) {
		if (methodName.startsWith("get") || methodName.startsWith("select")) {
			if (method.getParameterTypes().length == 1) {
				if (method.getParameterTypes()[0].isAssignableFrom(List.class)) {
					return true;
				}
			}
		}
		return false;
	}

	protected void deleteSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getDelete())) {
			return;
		}

		if (isDeleteMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setDelete(daoName + "." + methodName + "(#data)");
		}
	}

	protected boolean isDeleteMethod(BuriDataFieldType src, Method method, String methodName) {
		if (methodName.startsWith("del")) {
			if (method.getParameterTypes().length == 1) {
				String clazzName = classDefUtil.getClassName(method.getParameterTypes()[0]);
				if (clazzName.equals(src.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	protected void updateSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getUpdate())) {
			return;
		}

		if (isUpdateMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setUpdate(daoName + "." + methodName + "(#data)");
		}
	}

	protected boolean isUpdateMethod(BuriDataFieldType src, Method method, String methodName) {
		if (methodName.startsWith("update")) {
			if (method.getParameterTypes().length == 1) {
				String clazzName = classDefUtil.getClassName(method.getParameterTypes()[0]);
				if (clazzName.equals(src.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	protected void insertSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getInsert())) {
			return;
		}

		if (isInsertMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setInsert(daoName + "." + methodName + "(#data)");
		}
	}

	protected boolean isInsertMethod(BuriDataFieldType src, Method method, String methodName) {
		if (methodName.startsWith("insert")) {
			if (method.getParameterTypes().length == 1) {
				String clazzName = classDefUtil.getClassName(method.getParameterTypes()[0]);
				if (clazzName.equals(src.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	protected void selectSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getSelect())) {
			return;
		}

		if (isSelectMethod(src, method, methodName)) {
			String keyName = src.getCache().get(daoKeyName + "_KeyName").toString();
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setSelect(daoName + "." + methodName + "(#data." + keyName + ")");
		}
	}

	protected boolean isSelectMethod(BuriDataFieldType src, Method method, String methodName) {
		if (methodName.startsWith("get") || methodName.startsWith("select")) {
			if (method.getParameterTypes().length == 1) {
				Class tgtClass = (Class) src.getCache().get(daoKeyName + "_KeyType");
				if (method.getParameterTypes()[0].equals(tgtClass)) {
					return true;
				}
			}
		}
		return false;
	}

	protected void pkeySetup(BuriDataFieldType src) {
		if ((src.getKeys().size() > 0) || hasName(src, "pkey")) {
			return;
		}
		Class tgt = ClassUtil.forName(src.getId());
		BeanDesc bd = BeanDescFactory.getBeanDesc(tgt);
		int propLen = bd.getPropertyDescSize();
		// s2Dao-Tiger、IDアノテーションチェック
		for (int i = 0; i < propLen; i++) {
			PropertyDesc pd = bd.getPropertyDesc(i);
			Method writeMethod = null;
			if ((writeMethod = pd.getWriteMethod()) != null) {
				Id id = writeMethod.getAnnotation(Id.class);
				if (id != null) {
					String condition = createPkeyCondition(pd);
					src.getKeys().put(pd.getPropertyName(), condition);
					return;
				}
			}
		}
		// IDアノテーションがない場合、定数アノテーションチェック
		for (int i = 0; i < propLen; i++) {
			PropertyDesc pd = bd.getPropertyDesc(i);
			Object sig = classDefUtil.getMethodSignatureValue(tgt, "_ID", pd.getPropertyName());
			if (sig != null) {
				String condition = createPkeyCondition(pd);
				src.getKeys().put(pd.getPropertyName(), condition);
			}
		}
	}

	protected String createPkeyCondition(PropertyDesc pd) {
		String condition = null;
		String pkeyName = pd.getPropertyName();
		Class propType = pd.getPropertyType();
		if (propType.equals(Long.TYPE)) {
			condition = pkeyName + " != 0";
		} else if (propType.isAssignableFrom(Number.class)) {
			condition = pkeyName + " != 0";
		} else {
			condition = pkeyName + " != null";
		}
		return condition;
	}

	protected String getDaoName(BuriDataFieldType src, String dtoClassName) {
		String dao = null;
		String shtName = createDaoName(src, dtoClassName);
		String shtName2 = shtName.substring(0, 1).toLowerCase() + shtName.substring(1);
		if (container.getRoot().hasComponentDef(shtName)) {
			dao = shtName;
		} else if (container.getRoot().hasComponentDef(shtName2)) {
			dao = shtName2;
		} else {
			dao = findDaoClass(shtName, dtoClassName);
		}
		if(dao == null) {
			dao = findDaoClassFromNamespace(shtName, dtoClassName);
		}
		return dao;
	}
	
	protected String findDaoClassFromNamespace(String shtName, String dtoClassName) {
		String dao = null;
		List daoNamespace = configuration.getValList("Namespace");
		Iterator ite = daoNamespace.iterator();
		while (ite.hasNext()) {
			String nameSpace = ite.next().toString();
			String componentName = nameSpace + "." + shtName;
			if (container.getRoot().hasComponentDef(componentName)) {
				return componentName;
			}
		}
		return dao;
	}

	protected String findDaoClass(String shtName, String dtoClassName) {
		String dao = null;
		List daoPackageName = configuration.getValList("DaoPackageName");
		Iterator ite = daoPackageName.iterator();
		while (ite.hasNext()) {
			String pacName = ite.next().toString();
			String fullName = pacName + "." + shtName;
			dao = classNameToDao(fullName);
			if (dao != null) {
				break;
			}
		}
		/*
		 * if(dao == null) { dao = genarateDaoClass(shtName,dtoClassName); }
		 */
		return dao;
	}

	/*
	 * protected String genarateDaoClass(String shtName,String dtoClassName) {
	 * Class tgtClass = ClassUtil.forName(dtoClassName); String packageName =
	 * tgtClass.getPackage().getName(); packageName =
	 * packageName.replaceAll("dto","dao") + "." + shtName; packageName =
	 * classNameToDao(packageName); return packageName; }
	 */

	protected String classNameToDao(String fullName) {
		String dao = null;
		if (ClassDefUtilImpl.isClassName(fullName)) {
			Class daoClass = ClassUtil.forName(fullName);
			if (container.getRoot().hasComponentDef(daoClass)) {
				dao = container.getRoot().getComponentDef(daoClass).getComponentName();
			}
		}
		return dao;
	}

	protected String createDaoName(BuriDataFieldType src, String dtoClassName) {
		if (hasName(src, DAOKEY)) {
			return getNameVal(src, DAOKEY);
		}
		if (dtoClassName.indexOf("$$") > -1) {
			int enhansPos = dtoClassName.indexOf("$$");
			dtoClassName = dtoClassName.substring(0, enhansPos);
		}
		Class tgtClass = ClassUtil.forName(dtoClassName);
		String shtName = ClassUtil.getShortClassName(tgtClass);
		if(shtName.length() > 3) {
			if(shtName.substring(shtName.length() - 3).equalsIgnoreCase("Dto")) {
				shtName = shtName.substring(0,shtName.length() - 3);
			}
		}
		if(shtName.length() > 6) {
			if(shtName.substring(shtName.length() - 6).equalsIgnoreCase("Entity")) {
				shtName = shtName.substring(0,shtName.length() - 6);
			}
		}
		shtName = shtName + "Dao";
		return shtName;
	}

	public S2Container getContainer() {
		return container;
	}

	public void setContainer(S2Container container) {
		this.container = container;
	}

	public BuriConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(BuriConfiguration configuration) {
		this.configuration = configuration;
	}

	public ClassDefUtil getClassDefUtil() {
		return classDefUtil;
	}

	public void setClassDefUtil(ClassDefUtil classDefUtil) {
		this.classDefUtil = classDefUtil;
	}

	public DataAccessRuleEventCaller getDataAccessRuleEventCaller() {
		return dataAccessRuleEventCaller;
	}

	public void setDataAccessRuleEventCaller(
			DataAccessRuleEventCaller dataAccessRuleEventCaller) {
		this.dataAccessRuleEventCaller = dataAccessRuleEventCaller;
	}

}
