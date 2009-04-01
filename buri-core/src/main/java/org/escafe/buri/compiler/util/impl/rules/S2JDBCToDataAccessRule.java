/*
 * 作成日: 2006/07/06
 * 
 */
package org.escafe.buri.compiler.util.impl.rules;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import org.escafe.buri.common.util.BuriConfiguration;
import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

/**
 * S2JDBC用Dao自動設定用クラス
 * 
 * @author j5ik2o
 */
public class S2JDBCToDataAccessRule extends AbstractBuriDataFieldProcRule {
	private static final Logger LOG =
	    Logger.getLogger(S2JDBCToDataAccessRule.class);

	protected String serviceKeyName = "service";

	public final String serviceKEY = "service";

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
		if (src.getCache().containsKey(serviceKeyName)) {
		} else if (hasName(src, serviceKEY)) {
			src.getCache().put(serviceKeyName, getNameVal(src, serviceKEY));
		}
		dataAccessRuleEventCaller.determinedRule(this, src);
		return true;
	}

	@Override
	public boolean process(BuriDataFieldType src) {
		// if( ! src.getCache().containsKey(serviceKeyName) ) {
		// return false;
		// }
		if (src.getCache().containsKey(serviceKeyName + "_end")) {
			return false;
		}
		negotiateService(src);
		dataAccessRuleEventCaller.endNegotiateDao(this, src);
		src.getCache().put(serviceKeyName + "_end", Boolean.TRUE);
		return false;
	}

	protected void negotiateService(BuriDataFieldType src) {
		String dtoClassName = src.getId();
		String serviceName = getServiceName(src, dtoClassName);
		if (serviceName != null) {
			src.getCache().put(serviceKeyName, serviceName);
			Class serviceClass =
			    container
			        .getRoot()
			        .getComponentDef(serviceName)
			        .getComponentClass();
			pkeySetup(src);
			if (src.getKeys().size() == 1) {
				String keyName = src.getKeys().keySet().toArray()[0].toString();
				BeanDesc beanDesc =
				    BeanDescFactory
				        .getBeanDesc(ClassUtil.forName(dtoClassName));
				Class<?> tgtClass =
				    beanDesc.getPropertyDesc(keyName).getPropertyType();
				src.getCache().put(serviceKeyName + "_KeyType", tgtClass);
				src.getCache().put(serviceKeyName + "_KeyName", keyName);
				findAndSetupAllMethod(src, beanDesc, serviceClass);
			}
		}
	}

	/**
	 * 自動設定を行います。
	 * 
	 * @param src
	 * @param beanDesc
	 * @param serviceClass
	 */
	protected void findAndSetupAllMethod(BuriDataFieldType src,
	        BeanDesc beanDesc, Class<?> serviceClass) {
		Method methods[] = serviceClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			selectSetup(src, method, methodName, beanDesc);
			updateSetup(src, method, methodName, beanDesc);
			deleteSetup(src, method, methodName, beanDesc);
			selectManySetup(src, method, methodName, beanDesc);
			insertSetup(src, method, methodName, beanDesc);
			tableNameSetup(src, method, methodName, beanDesc);
			selectNextValSetup(src, method, methodName, beanDesc);
		}
	}

	/**
	 * Sequence取得用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void selectNextValSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (isSelectNextValMethod(src, method, methodName)) {
			String serviceName = src.getCache().get(serviceKeyName).toString();
			String pkey =
			    (String) src.getCache().get(serviceKeyName + "_KeyName");
			String selectNextVal =
			    "#data." + pkey + " = " + serviceName + "." + methodName + "()";
			if (!StringUtil.isEmpty(src.getInsert())) {
				String insertStr = src.getInsert();
				StringBuffer strBuff = new StringBuffer();
				strBuff.append(selectNextVal);
				strBuff.append("\n");
				strBuff.append(insertStr);
				src.setInsert(strBuff.toString());
			} else {
				src.setInsert(selectNextVal);
			}
		}
	}

	/**
	 * 引数のメソッドがSequence取得用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isSelectNextValMethod(BuriDataFieldType src,
	        Method method, String methodName) {
		if (methodName.startsWith("selectNextVal")) {
			if (method.getParameterTypes().length == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * テーブル名をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void tableNameSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getTableName())) {
			return;
		}
		Class<?> tgt = ClassUtil.forName(src.getId());
		String tableName = classDefUtil.getClazz(tgt).getSimpleName();
		Table table = tgt.getAnnotation(Table.class);
		if (table != null) {
			tableName = table.name();
		}
		if (!StringUtil.isEmpty(tableName)) {
			src.setTableName(tableName);
		}
	}

	/**
	 * 複数データ取得用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void selectManySetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getSelectMany())) {
			return;
		}
		if (isSelectManyMethod(src, method, methodName)) {
			String serviceName = src.getCache().get(serviceKeyName).toString();
			src.setSelectMany(serviceName + "." + methodName + "(#data)");
		}
	}

	/**
	 * 引数のメソッドが複数データ取得用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isSelectManyMethod(BuriDataFieldType src, Method method,
	        String methodName) {
		if (methodName.startsWith("get")
		    || methodName.startsWith("select")
		    || methodName.startsWith("find")) {
			if (method.getParameterTypes().length == 1) {
				if (method.getParameterTypes()[0].isAssignableFrom(List.class)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * データ削除用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void deleteSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getDelete())) {
			return;
		}
		if (isDeleteMethod(src, method, methodName)) {
			String serviceName = src.getCache().get(serviceKeyName).toString();
			src.setDelete(serviceName + "." + methodName + "(#data)");
		}
	}

	/**
	 * 引数のメソッドがデータ削除用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isDeleteMethod(BuriDataFieldType src, Method method,
	        String methodName) {
		if (methodName.startsWith("del") && !methodName.endsWith("Batch")) {
			if (method.getParameterTypes().length == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * データ更新用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void updateSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getUpdate())) {
			return;
		}
		if (isUpdateMethod(src, method, methodName)) {
			String serviceName = src.getCache().get(serviceKeyName).toString();
			src.setUpdate(serviceName + "." + methodName + "(#data)");
		}
	}

	/**
	 * 引数のメソッドがデータ更新用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isUpdateMethod(BuriDataFieldType src, Method method,
	        String methodName) {
		if (methodName.startsWith("update") && !methodName.endsWith("Batch")) {
			if (method.getParameterTypes().length == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * データ作成用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void insertSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getInsert())) {
			return;
		}
		if (isInsertMethod(src, method, methodName)) {
			String daoName = src.getCache().get(serviceKeyName).toString();
			src.setInsert(daoName + "." + methodName + "(#data)");
		}
	}

	/**
	 * 引数のメソッドがデータ作成用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isInsertMethod(BuriDataFieldType src, Method method,
	        String methodName) {
		if (methodName.startsWith("insert") && !methodName.endsWith("Batch")) {
			if (method.getParameterTypes().length == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 単一データ取得用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void selectSetup(BuriDataFieldType src, Method method,
	        String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getSelect())) {
			return;
		}
		if (isSelectMethod(src, method, methodName)) {
			String keyName =
			    src.getCache().get(serviceKeyName + "_KeyName").toString();
			String serviceName = src.getCache().get(serviceKeyName).toString();
			src.setSelect(serviceName
			    + "."
			    + methodName
			    + "(#data."
			    + keyName
			    + ")");
		}
	}

	/**
	 * 引数のメソッドが単一データ取得用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
	protected boolean isSelectMethod(BuriDataFieldType src, Method method,
	        String methodName) {
		if (methodName.startsWith("get")
		    || methodName.startsWith("select")
		    || methodName.startsWith("find")) {
			if (method.getParameterTypes().length == 1) {
				// Class<?> tgtClass =
				// (Class<?>) src.getCache().get(serviceKeyName + "_KeyType");
				// if (method.getParameterTypes()[0].equals(tgtClass)) {
				return true;
				// }
			}
		}
		return false;
	}

	/**
	 * ID存在判定用OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void pkeySetup(BuriDataFieldType src) {
		if ((src.getKeys().size() > 0) || hasName(src, "pkey")) {
			return;
		}
		Class<?> entityClass = ClassUtil.forName(src.getId());
		BeanDesc bd = BeanDescFactory.getBeanDesc(entityClass);
		for (int i = 0; i < bd.getPropertyDescSize(); i++) {
			PropertyDesc pd = bd.getPropertyDesc(i);
			Field f = pd.getField();
			if (f == null) {
				continue;
			}
			Id id = f.getAnnotation(Id.class);
			if (id != null) {
				String condition = createPkeyCondition(pd);
				src.getKeys().put(pd.getPropertyName(), condition);
			}
		}
	}

	/**
	 * IDアノテーションのフィールド型を判定し、ID存在判定OGNL文を返却します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected String createPkeyCondition(PropertyDesc pd) {
		String condition = null;
		String pkeyName = pd.getPropertyName();
		Class<?> propType = pd.getPropertyType();
		if (propType.equals(Long.TYPE)) {
			condition = pkeyName + " != 0";
		} else if (propType.equals(Long.class)) {
			condition = pkeyName + " != null" + " && " + pkeyName + " != 0";
		} else if (propType.isAssignableFrom(Number.class)) {
			condition = pkeyName + " != 0";
		} else {
			condition = pkeyName + " != null";
		}
		return condition;
	}

	protected String getServiceName(BuriDataFieldType src, String dtoClassName) {
		String service = null;
		String shtName2 = createServiceName(src, dtoClassName);
		String shtName =
		    shtName2.substring(0, 1).toLowerCase() + shtName2.substring(1);
		if (container.getRoot().hasComponentDef(shtName)) {
			service = shtName;
		} else if (container.getRoot().hasComponentDef(shtName2)) {
			service = shtName2;
		} else {
			service = findServiceClass(shtName, dtoClassName);
		}
		if (service == null) {
			service = findServiceClassFromNamespace(shtName, dtoClassName);
		}
		return service;
	}

	protected String findServiceClassFromNamespace(String shtName,
	        String dtoClassName) {
		String service = null;
		List serviceNamespace = configuration.getValList("Namespace");
		Iterator ite = serviceNamespace.iterator();
		while (ite.hasNext()) {
			String nameSpace = ite.next().toString();
			String componentName = nameSpace + "." + shtName;
			if (container.getRoot().hasComponentDef(componentName)) {
				return componentName;
			}
		}
		return service;
	}

	protected String findServiceClass(String shtName, String dtoClassName) {
		String service = null;
		List servicePackageName =
		    configuration.getValList("ServicePackageName");
		Iterator ite = servicePackageName.iterator();
		while (ite.hasNext()) {
			String pacName = ite.next().toString();
			String fullName = pacName + "." + shtName;
			service = classNameToService(fullName);
			if (service != null) {
				break;
			}
		}
		/*
		 * if(service == null) { service =
		 * genarateserviceClass(shtName,dtoClassName); }
		 */
		return service;
	}

	/*
	 * protected String genarateserviceClass(String shtName,String dtoClassName)
	 * { Class tgtClass = ClassUtil.forName(dtoClassName); String packageName =
	 * tgtClass.getPackage().getName(); packageName =
	 * packageName.replaceAll("dto","service") + "." + shtName; packageName =
	 * classNameToservice(packageName); return packageName; }
	 */
	protected String classNameToService(String fullName) {
		String service = null;
		if (ClassDefUtilImpl.isClassName(fullName)) {
			Class serviceClass = ClassUtil.forName(fullName);
			if (container.getRoot().hasComponentDef(serviceClass)) {
				service =
				    container
				        .getRoot()
				        .getComponentDef(serviceClass)
				        .getComponentName();
			}
		}
		return service;
	}

	protected String createServiceName(BuriDataFieldType src,
	        String dtoClassName) {
		if (hasName(src, serviceKEY)) {
			return getNameVal(src, serviceKEY);
		}
		if (dtoClassName.indexOf("$$") > -1) {
			int enhansPos = dtoClassName.indexOf("$$");
			dtoClassName = dtoClassName.substring(0, enhansPos);
		}
		Class tgtClass = ClassUtil.forName(dtoClassName);
		String shtName = ClassUtil.getShortClassName(tgtClass);
		if (shtName.length() > 3) {
			if (shtName.substring(shtName.length() - 3).equalsIgnoreCase("Dto")) {
				shtName = shtName.substring(0, shtName.length() - 3);
			}
		}
		if (shtName.length() > 6) {
			if (shtName.substring(shtName.length() - 6).equalsIgnoreCase(
			    "Entity")) {
				shtName = shtName.substring(0, shtName.length() - 6);
			}
		}
		shtName = shtName + "Service";
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
