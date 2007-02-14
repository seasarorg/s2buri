package org.seasar.buri.creator;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

public class PackCreator extends ComponentCreatorImpl {

	public PackCreator(NamingConvention namingConvention) {
		super(namingConvention);
		setNameSuffix("Pack");
		setInstanceDef(InstanceDefFactory.PROTOTYPE);
		setEnableInterface(true);
		setEnableAbstract(true);
	}

	public ComponentCustomizer getPackCustomizer() {
		return getCustomizer();
	}

	public void setPackCustomizer(ComponentCustomizer customizer) {
		setCustomizer(customizer);
	}

}
