package org.escafe.buri.compiler.util;

import java.util.ArrayList;
import java.util.List;

public class BuriDataFieldProcRuleSet {
    private List<BuriDataFieldProcRule> dataAccessRules = new ArrayList<BuriDataFieldProcRule>();
    
    public void addDataAccessRules(BuriDataFieldProcRule rule) {
        dataAccessRules.add(rule);
    }

	public List<BuriDataFieldProcRule> getDataAccessRules() {
		return dataAccessRules;
	}

	public void setDataAccessRules(List<BuriDataFieldProcRule> dataAccessRules) {
		this.dataAccessRules = dataAccessRules;
	}

}
