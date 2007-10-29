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
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

/**
 * DBFlute用Dao自動設定用クラス
 * 
 * @author a-conv
 * 
 */
public class DBFluteToDataAccessRule extends AbstractBuriDataFieldProcRule {

	private static Logger logger = Logger.getLogger(DBFluteToDataAccessRule.class);

	protected String daoKeyName = "dao";

	public final String DAOKEY = "dao";

	public final String dbFluteComponentName = "dbflute.interceptor";

	private BuriConfiguration configuration;

	private S2Container container;

	private ClassDefUtil classDefUtil;

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
		if (!isUseDBFlute()) {
			return false;
		}
		if (!isRequiredNegotiate(src)) {
			return false;
		}
		if (src.getCache().containsKey(daoKeyName)) {

		} else if (hasName(src, DAOKEY)) {
			src.getCache().put(daoKeyName, getNameVal(src, DAOKEY));
		}
		logger.debug("★-------------------------------------------------------------");
		logger.debug("UseDBFluteToDataAccessRule");
		logger.debug("★-------------------------------------------------------------");
		return true;
	}

	/**
	 * DBFluteを使用しているかチェック(基本のコンポーネント名でチェックしているので、異なる場合は判定不可)
	 * 
	 * @return DBFltueを利用しているかどうか
	 */
	private boolean isUseDBFlute() {
		if (checkDBFluteComponent(dbFluteComponentName)) {
			return true;
		}
		String customDBFluteComponentName = setupUseDBFluteCustomComponentName();
		if (customDBFluteComponentName == null) {
			return false;
		}
		if (checkDBFluteComponent(customDBFluteComponentName)) {
			return true;
		}
		return false;
	}

	/**
	 * BuriConfigurationからDBFluteのコンポーネント名を受け取る
	 * 
	 * @return
	 */
	private String setupUseDBFluteCustomComponentName() {
		String dbFluteInterceptorName = null;
		List dbFluteInterceptorname = configuration.getValList("DBFluteComponentName");
		Iterator ite = dbFluteInterceptorname.iterator();
		while (ite.hasNext()) {
			dbFluteInterceptorName = ite.next().toString();
			break;
		}
		return dbFluteInterceptorName;
	}

	/**
	 * DBFluteのコンポーネント検索
	 * 
	 * @param componentName
	 * @return
	 */
	private boolean checkDBFluteComponent(String componentName) {
		S2Container rootContainer = container.getRoot();
		try {
			rootContainer.getComponent(componentName);
		} catch (ComponentNotFoundRuntimeException e) {
			return false;
		}
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

		logger.info("★===========================================================");
		logger.info("ぶりで自動設定された結果");
		logger.info("Keys:=" + src.getKeys());
		logger.info("ID:=" + src.getId());
		logger.info("Insert:=" + src.getInsert());
		logger.info("Update:=" + src.getUpdate());
		logger.info("Delete:=" + src.getDelete());
		logger.info("Select:=" + src.getSelect());
		logger.info("SelectMany:=" + src.getSelectMany());
		logger.info("★===========================================================");

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

	/**
	 * 自動設定を行います。
	 * 
	 * @param src
	 * @param beanDesc
	 * @param daoClass
	 */
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
	protected void selectNextValSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (isSelectNextValMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			String pkey = (String) src.getCache().get(daoKeyName + "_KeyName");
			String selectNextVal = "#data." + pkey + " = " + daoName + "." + methodName + "()";
			if (!StringUtil.isEmpty(src.getInsert())) {
				String insertStr = src.getInsert();
				StringBuffer strBuff = new StringBuffer();
				strBuff.append(selectNextVal.toString());
				strBuff.append("\n");
				strBuff.append(insertStr);
				src.setInsert(strBuff.toString());
			} else {
				src.setInsert(selectNextVal.toString());
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
	protected boolean isSelectNextValMethod(BuriDataFieldType src, Method method, String methodName) {
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

	/**
	 * 複数データ取得用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void selectManySetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getSelectMany())) {
			return;
		}

		if (isSelectManyMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setSelectMany(daoName + "." + methodName + "(#data)");
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

	/**
	 * データ削除用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void deleteSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getDelete())) {
			return;
		}

		if (isDeleteMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setDelete(daoName + "." + methodName + "(#data)");
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

	/**
	 * データ更新用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void updateSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (!StringUtil.isEmpty(src.getUpdate())) {
			return;
		}

		if (isUpdateMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			src.setUpdate(daoName + "." + methodName + "(#data)");
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

	/**
	 * データ作成用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
	protected void insertSetup(BuriDataFieldType src, Method method, String methodName, BeanDesc beanDesc) {
		if (isInsertMethod(src, method, methodName)) {
			String daoName = src.getCache().get(daoKeyName).toString();
			String insertOgnl = daoName + "." + methodName + "(#data)";
			if (!StringUtil.isEmpty(src.getInsert())) {
				String insertStr = src.getInsert();
				StringBuffer strBuff = new StringBuffer();
				strBuff.append(insertStr);
				strBuff.append("\n");
				strBuff.append(insertOgnl);
				src.setInsert(strBuff.toString());
			} else {
				src.setInsert(insertOgnl);
			}
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

	/**
	 * 単一データ取得用メソッド実行OGNL文をセットします。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @param beanDesc
	 */
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

	/**
	 * 引数のメソッドが単一データ取得用か判定します。
	 * 
	 * @param src
	 * @param method
	 * @param methodName
	 * @return
	 */
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
		Class propType = pd.getPropertyType();
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
		if (dao == null) {
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
		if (shtName.length() > 3) {
			if (shtName.substring(shtName.length() - 3).equalsIgnoreCase("Dto")) {
				shtName = shtName.substring(0, shtName.length() - 3);
			}
		}
		if (shtName.length() > 6) {
			if (shtName.substring(shtName.length() - 6).equalsIgnoreCase("Entity")) {
				shtName = shtName.substring(0, shtName.length() - 6);
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

}
