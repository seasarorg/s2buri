/*
 * çÏê¨ì˙: 2005/12/28
 *
 */
package org.seasar.buri.engine.invoker.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.dao.datautil.DataAccessUtil;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.framework.container.S2Container;

public class StateBuriInvoker extends AbstractBuriInvoker {
    private S2Container container;
    private DataAccessUtil accessUtil;
    private BuriPathDaoUtil pathDaoUtil;
    
    public void invoke(BuriStateEntityDto stateDto) {
        Map addContext = new HashMap();
        addContext.put("autoAction",Boolean.TRUE);
        BuriPath path = pathDaoUtil.getBuriPath(stateDto.getPathID().longValue());
        Object data = accessUtil.getObjectFromDataID(path,stateDto.getDataID().longValue());
        innerInvoke(path, container.getRoot(), data, null,null, null, false,addContext,null);
    }

    public DataAccessUtil getAccessUtil() {
        return accessUtil;
    }

    public void setAccessUtil(DataAccessUtil accessUtil) {
        this.accessUtil = accessUtil;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriPathDaoUtil getPathDaoUtil() {
        return pathDaoUtil;
    }

    public void setPathDaoUtil(BuriPathDaoUtil pathDaoUtil) {
        this.pathDaoUtil = pathDaoUtil;
    }
}
