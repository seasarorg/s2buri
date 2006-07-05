/*
 * ì¬“ú: 2006/07/04
 *
 */
package org.seasar.buri.compiler.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.compiler.util.BuriDataFieldCompilePreprocessor;
import org.seasar.buri.compiler.util.BuriDataFieldProcRule;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.seasar.buri.oouo.internal.structure.util.ExtentedAttributeUtil;
import org.seasar.framework.log.Logger;

public class BuriDataFieldCompilePreprocessorImpl implements BuriDataFieldCompilePreprocessor {
    
    private static Logger logger = Logger.getLogger(BuriDataFieldCompilePreprocessorImpl.class);
    
    private List preprocessRules = new ArrayList();
    private List dataAccessRules = new ArrayList();
    
     
    public BuriDataFieldType preprocess(BuriDataFieldType src) {
        BuriDataFieldType dst = copyBuriDataFieldType(src);
        if(hasPreprocess(dst)) {
            checkDataAccess(dst,preprocessRules);
        }
        if(hasDataAccess(dst)) {
            checkDataAccess(dst,dataAccessRules);
        }
        
        dst.getCache().clear();
        return dst;
    }
    
    protected void checkDataAccess(BuriDataFieldType src,List srcRuleList) {
        List ruleList = fstCheckRule(src,srcRuleList);
        boolean processResult;
        do {
            processResult = processRules(src,ruleList);
            processResult = checkExitRule(src,ruleList,processResult);
        }while(processResult == true);
        finishProcess(src,ruleList);
    }
    
    protected void finishProcess(BuriDataFieldType src,List ruleList) {
        Iterator ite = ruleList.iterator();
        while(ite.hasNext()) {
            BuriDataFieldProcRule rule = (BuriDataFieldProcRule)ite.next();
            rule.finishCheck(src);
        }
    }
    
    protected boolean checkExitRule(BuriDataFieldType src,List ruleList,boolean processResult) {
        Iterator ite = ruleList.iterator();
        boolean checkRule = false;
        while(ite.hasNext()) {
            BuriDataFieldProcRule rule = (BuriDataFieldProcRule)ite.next();
            checkRule = checkRule | rule.afterCheck(src,processResult);
        }
        return checkRule;
    }
    
    protected boolean processRules(BuriDataFieldType src,List ruleList) {
        Iterator ite = ruleList.iterator();
        boolean checkRule = false;
        while(ite.hasNext()) {
            BuriDataFieldProcRule rule = (BuriDataFieldProcRule)ite.next();
            checkRule = checkRule | rule.process(src);
        }
        return checkRule;
    }
    
    protected List fstCheckRule(BuriDataFieldType src,List rules) {
        Iterator ite = rules.iterator();
        List ruleList = new ArrayList();
        while(ite.hasNext()) {
            BuriDataFieldProcRule rule = (BuriDataFieldProcRule)ite.next();
            boolean fst = rule.fstCheckProcess(src);
            if(fst==true) {
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
        Iterator ite = dataAccessRules.iterator();
        boolean result = false;
        while(ite.hasNext()) {
            BuriDataFieldProcRule dap = (BuriDataFieldProcRule)ite.next();
            if(dap.getKeyName() != null) {
                result = hasName(src,dap.getKeyName());
                if(result == true) {
                    return true;
                }
            }
        }
        return result;
    }
    
    protected boolean hasName(BuriDataFieldType src,String name) {
        BuriExtendedAttributeType attri = getExAttri(src,name);
        if(attri==null) {
            return false;
        }
        return true;
    }
    
    protected BuriExtendedAttributeType getExAttri(BuriDataFieldType src,String name) {
        if(src.getCache().containsKey(name)) {
            return (BuriExtendedAttributeType)src.getCache().get(name);
        }
        BuriExtendedAttributeType attri = ExtentedAttributeUtil.getExtendedAttribute(src.getExtentedAttribute(),name);
        src.getCache().put(name,attri);
        return attri;
    }
    
    protected boolean hasPreprocess(BuriDataFieldType src) {
        boolean result = hasName(src,"preprocess");
        return result;
    }
    
}
