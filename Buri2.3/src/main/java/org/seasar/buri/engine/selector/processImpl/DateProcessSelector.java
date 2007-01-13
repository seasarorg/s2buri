/*
 * 作成日: 2006/07/15
 *
 */
package org.seasar.buri.engine.selector.processImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.selector.abst.AbstractBuriProcessSelector;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.framework.util.StringUtil;

public class DateProcessSelector extends AbstractBuriProcessSelector {

    protected boolean checkCanProcessSelect(List procs,BuriSystemContext systemContext, BuriExePackages exePackages) {
        String realProcsName = systemContext.getCallPath().getWorkflowProcess();
        if(StringUtil.isEmpty(realProcsName)) {
            return false;
        }
        List tgts = exePackages.getBuriPackageType().getProcessByName(realProcsName);
        if(tgts.size() > 1) {
            return true;
        }
        return false;
    }

    protected List getProcessList(List procs, BuriSystemContext systemContext,BuriExePackages exePackages) {
        String realProcsName = systemContext.getCallPath().getWorkflowProcess();
        List tgts = exePackages.getBuriPackageType().getProcessByName(realProcsName);
        List result = new ArrayList();
        procs.clear();
        Iterator ite = tgts.iterator();
        while(ite.hasNext()) {
            BuriWorkflowProcessType process = (BuriWorkflowProcessType)ite.next();
            if(isHitDate(process)) { 
                result.add(process);
            }
        }
        return result;
    }
    
    private boolean isHitDate(BuriWorkflowProcessType process) {
        Date validFrom = process.getValidFrom();
        Date validTo = process.getValidTo();
        if(validFrom != null && validTo != null) {
            Date now = new Date();
            if(DateUtil.compare(validFrom,now) <= 0 && DateUtil.compare(now,validTo) < 0) {
                return true;
            }
        }
        return false;
    }

}
