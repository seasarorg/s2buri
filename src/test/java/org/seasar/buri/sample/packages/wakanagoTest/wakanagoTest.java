/*
 * çÏê¨ì˙: 2006/03/28
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.abst.AbstBuriExePackages;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;

public class wakanagoTest extends AbstBuriExePackages{
    /*
    private wakanagoTest_wp1 wp1;
    private S2Container container;
    
    public void setup(BuriPackageType packageType) {
        wp1 = (wakanagoTest_wp1)container.getComponent(wakanagoTest_wp1.class);
        BuriWorkflowProcessType process = (BuriWorkflowProcessType)packageType.getProcessById().get("wakanagoTest_wp1");
        assert process != null;
        wp1.setup(process);
    }
    
    public Object getExecProcess(BuriSystemContext sysContext) {
        String processName = sysContext.getCallPath().getWorkflowProcess();
        assert ! StringUtil.isEmpty(processName);
        return wp1;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public wakanagoTest_wp1 getWp1() {
        return wp1;
    }

    public void setWp1(wakanagoTest_wp1 wp1) {
        this.wp1 = wp1;
    }
    */
}
