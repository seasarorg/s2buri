/*
 * 作成日: 2006/07/04
 *
 */
package org.escafe.buri.compiler.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.escafe.buri.compiler.util.BuriDataFieldProcRule;
import org.escafe.buri.compiler.util.BuriDataFieldProcRuleSet;
import org.escafe.buri.event.util.caller.DataAccessRuleEventCaller;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.seasar.framework.container.S2Container;

public class BuriDataFieldCompilePreprocessorImpl implements BuriDataFieldCompilePreprocessor {

    private List<BuriDataFieldProcRule> preprocessRules = new ArrayList<BuriDataFieldProcRule>();
    private List<BuriDataFieldProcRule> dataAccessRules = new ArrayList<BuriDataFieldProcRule>();
    
    private DataAccessRuleEventCaller dataAccessRuleEventCaller;
    private S2Container container;


    public BuriDataFieldType preprocess(BuriDataFieldType src) {
    	dataAccessRuleEventCaller.entryProcessor(src);
        BuriDataFieldType dst = copyBuriDataFieldType(src);
        if (hasPreprocess(dst)) {
            checkDataAccess(dst, preprocessRules);
        }
        BuriDataFieldProcRuleSet[] allComponents = (BuriDataFieldProcRuleSet[])container.findAllComponents("userDataFieldRuleSet");
        if(allComponents != null && allComponents.length != 0) {
        	for (int i = 0; i < allComponents.length; i++) {
        		dataAccessRules.addAll(allComponents[i].getDataAccessRules());
			}
        }
        if (hasDataAccess(dst)) {
            checkDataAccess(dst, dataAccessRules);
        }

        dst.getCache().clear();
    	dataAccessRuleEventCaller.returnProcessor(dst);
        return dst;
    }

    protected void checkDataAccess(BuriDataFieldType src, List<BuriDataFieldProcRule> srcRuleList) {
        List<BuriDataFieldProcRule> ruleList = fstCheckRule(src, srcRuleList);
        boolean processResult;
        do {
            processResult = processRules(src, ruleList);
            processResult = checkExitRule(src, ruleList, processResult);
        } while (processResult == true);
        finishProcess(src, ruleList);
    }

    protected void finishProcess(BuriDataFieldType src, List<BuriDataFieldProcRule> ruleList) {
        Iterator<BuriDataFieldProcRule> ite = ruleList.iterator();
        while (ite.hasNext()) {
            BuriDataFieldProcRule rule = ite.next();
            rule.finishCheck(src);
        }
    }

    protected boolean checkExitRule(BuriDataFieldType src, List<BuriDataFieldProcRule> ruleList, boolean processResult) {
        Iterator<BuriDataFieldProcRule> ite = ruleList.iterator();
        boolean checkRule = false;
        while (ite.hasNext()) {
            BuriDataFieldProcRule rule = ite.next();
            checkRule = checkRule | rule.afterCheck(src, processResult);
        }
        return checkRule;
    }

    protected boolean processRules(BuriDataFieldType src, List<BuriDataFieldProcRule> ruleList) {
        Iterator<BuriDataFieldProcRule> ite = ruleList.iterator();
        boolean checkRule = false;
        while (ite.hasNext()) {
            BuriDataFieldProcRule rule = ite.next();
            checkRule = checkRule | rule.process(src);
        }
        return checkRule;
    }

    protected List<BuriDataFieldProcRule> fstCheckRule(BuriDataFieldType src, List<BuriDataFieldProcRule> rules) {
        Iterator<BuriDataFieldProcRule> ite = rules.iterator();
        List<BuriDataFieldProcRule> ruleList = new ArrayList<BuriDataFieldProcRule>();
        while (ite.hasNext()) {
            BuriDataFieldProcRule rule = ite.next();
            boolean fst = rule.fstCheckProcess(src);
            if (fst == true) {
                ruleList.add(rule);
            }
        }
        return ruleList;
    }

    public void addDataAccessRules(BuriDataFieldProcRule rule) {
        dataAccessRules.add(rule);
    }

    public void addPreprocessRules(BuriDataFieldProcRule rule) {
        preprocessRules.add(rule);
    }

    /*
     
     protected void updateDataField(BuriDataFieldType src) {
     Iterator ite = src.getExtendedAttribute().iterator();
     while(ite.hasNext()) {
     BuriExtendedAttributeType attri = (BuriExtendedAttributeType)ite.next();
     String name = attri.getName();
     String value = attri.getValue();
     dispach(src,name,value);
     }
     checkError(src);
     }
     
     
     public void checkError(BuriDataFieldType src) {
     if(isErrorString(src,src.getInsert())) {
     throw new BuriDataFieldErrorException(src.getId(),"insert");
     }
     if(isErrorString(src,src.getUpdate())) {
     throw new BuriDataFieldErrorException(src.getId(),"update");
     }
     if(isErrorString(src,src.getSelect())) {
     throw new BuriDataFieldErrorException(src.getId(),"select");
     }
     if(src.getKeys().size()==0 && hasDataAccess(src)) {
     throw new BuriDataFieldErrorException(src.getId(),"pkey");
     }
     if(src.getInsert() == null && (src.getUpdate() != null || src.getSelect() != null)) {
     throw new BuriDataFieldErrorException(src.getId(),"insert");
     }
     if(src.getUpdate() == null && (src.getInsert() != null || src.getSelect() != null)) {
     throw new BuriDataFieldErrorException(src.getId(),"update");
     }
     if(src.getSelect() == null && (src.getInsert() != null || src.getUpdate() != null)) {
     throw new BuriDataFieldErrorException(src.getId(),"select");
     }
     if(src.getSelectMany() != null && src.getKeys().size() == 0) {
     throw new BuriDataFieldErrorException(src.getId(),"pkey,insert,select,update");
     }
     if( ! ClassDefUtilImpl.isClassName(src.getId())) {
     throw new BuriDataFieldErrorException(src.getId());
     }
     if(src.getKeys().size() > 0) {
     Class tgtClass = ClassUtil.forName(src.getId());
     Iterator ite = src.getKeys().keySet().iterator();
     while(ite.hasNext()) {
     String keyVal = ite.next().toString();
     
     if( ! ClassDefUtilImpl.hasPropertyName(tgtClass,keyVal)) {
     throw new BuriDataFieldErrorException(tgtClass,keyVal);
     }
     }
     }
     }
     
     private boolean isErrorString(BuriDataFieldType src,String str) {
     if(isNoDefaultUse(src)) {
     return false;
     }
     if(org.seasar.framework.util.StringUtil.isEmpty(str)) {
     return true;
     }
     return false;
     }
     
     private boolean isNoDefaultUse(BuriDataFieldType src) {
     if(hasPreprocess(src)) {
     return true;
     }
     return false;
     }
     
     private void dispach(BuriDataFieldType src,String name,String value) {
     if(name.equals("pkey")) {
     setupPkey(src,value);
     } else if(name.equals("insert")) {
     src.setInsert(value);
     } else if(name.equals("update")) {
     src.setUpdate(value);
     } else if(name.equals("select")) {
     src.setSelect(value);
     } else if(name.equals("delete")) {
     src.setDelete(value);
     } else if(name.equals("selectMany")) {
     src.setSelectMany(value);
     } else if(name.equals("preprocess")) {
     src.setPreprocess(value);
     }

     }
     
     
     private void setupPkey(BuriDataFieldType src,String pkey) {
     if(org.seasar.framework.util.StringUtil.isEmpty(pkey)) {
     throw new BuriNoPkeyDefine(src.getId());
     }
     String pkeys[] = pkey.split("\n");
     if(pkeys.length==0) {
     throw new BuriNoPkeyDefine(src.getId());
     }
     for(int i=0 ; i< pkeys.length ; i++ ) {
     String onePkey = pkeys[i];
     setOnePkey(src,onePkey,src.getKeys());
     }
     }
     protected void setOnePkey(BuriDataFieldType src,String onePkey,Map pkeyMap) {
     String splitStr[] = StringUtil.SplitFastString(onePkey,",");
     String keyName = splitStr[0];
     String keyCheck = keyName + "!=0";
     if(splitStr.length==0) {
     throw new BuriNoPkeyDefine(src.getKeys());
     } else if(splitStr.length==1) {
     String msg = MessageFormatter.getMessage("IBRI0001",new Object[]{keyName,keyCheck});
     logger.info(msg);
     } else {
     keyCheck = splitStr[1];
     }
     pkeyMap.put(keyName,keyCheck);
     }
     */

    protected BuriDataFieldType copyBuriDataFieldType(BuriDataFieldType src) {
        BuriDataFieldType dst = new BuriDataFieldType();
        dst.setId(src.getId());
        dst.setExtentedAttribute(src.getExtentedAttribute());
        return dst;
    }

    protected boolean hasDataAccess(BuriDataFieldType src) {
        Iterator<BuriDataFieldProcRule> ite = dataAccessRules.iterator();
        boolean result = false;
        while (ite.hasNext()) {
            BuriDataFieldProcRule dap = ite.next();
            if (dap.getKeyName() != null) {
                result = hasName(src, dap.getKeyName());
                if (result == true) {
                    return true;
                }
            }
            if (dap.getRequiredRule(src)) {
                return true;
            }
        }
        return result;
    }

    protected boolean hasName(BuriDataFieldType src, String name) {
        BuriExtendedAttributeType attri = getExAttri(src, name);
        if (attri == null) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
	protected BuriExtendedAttributeType getExAttri(BuriDataFieldType src, String name) {
        if (src.getCache().containsKey(name)) {
            return (BuriExtendedAttributeType) src.getCache().get(name);
        }
        BuriExtendedAttributeType attri = ExtentedAttributeUtil.getExtendedAttribute(src.getExtentedAttribute(), name);
        src.getCache().put(name, attri);
        return attri;
    }

    protected boolean hasPreprocess(BuriDataFieldType src) {
        boolean result = hasName(src, "preprocess");
        return result;
    }

	public DataAccessRuleEventCaller getDataAccessRuleEventCaller() {
		return dataAccessRuleEventCaller;
	}

	public void setDataAccessRuleEventCaller(
			DataAccessRuleEventCaller dataAccessRuleEventCaller) {
		this.dataAccessRuleEventCaller = dataAccessRuleEventCaller;
	}

	public S2Container getContainer() {
		return container;
	}

	public void setContainer(S2Container container) {
		this.container = container;
	}

}
