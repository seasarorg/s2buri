package org.escafe.buri.event.util;

import org.escafe.buri.compiler.util.BuriDataFieldProcRule;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;

public class DataAccessRuleEvent {
	private BuriDataFieldType buriDataFieldType;
	private BuriDataFieldProcRule rule;

	public BuriDataFieldType getBuriDataFieldType() {
		return buriDataFieldType;
	}

	public void setBuriDataFieldType(BuriDataFieldType buriDataFieldType) {
		this.buriDataFieldType = buriDataFieldType;
	}

	public BuriDataFieldProcRule getRule() {
		return rule;
	}

	public void setRule(BuriDataFieldProcRule rule) {
		this.rule = rule;
	}
	
}
