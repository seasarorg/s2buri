package org.escafe.buri.creator;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

/**
 * Bao用Creator
 * 
 * @author a-conv
 * 
 */
public class BaoCreator extends ComponentCreatorImpl {

    public BaoCreator(NamingConvention namingConvention) {
        super(namingConvention);
        setNameSuffix("Bao");
        setInstanceDef(InstanceDefFactory.PROTOTYPE);
        setEnableInterface(true);
        setEnableAbstract(true);
    }

    public ComponentCustomizer getBaoCustomizer() {
        return getCustomizer();
    }

    public void setBaoCustomizer(ComponentCustomizer customizer) {
        setCustomizer(customizer);
    }

}
