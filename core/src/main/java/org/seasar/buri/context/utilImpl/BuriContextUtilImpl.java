/*
 * çÏê¨ì˙: 2005/10/09
 *
 */
package org.seasar.buri.context.utilImpl;

import org.seasar.buri.context.BuriInnerContext;
import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.framework.container.S2Container;

public class BuriContextUtilImpl implements ContextUtil {
    private S2Container container;

    public BuriInnerContext getContext() {
        BuriInnerContext localContext = (BuriInnerContext)container.getComponent(BuriInnerContext.class);
        return localContext;
    }

    public BuriLocalContext getLocalContext() {
        BuriLocalContext localContext = (BuriLocalContext)container.getComponent(BuriLocalContext.class);
        return localContext;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }


}
