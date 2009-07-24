package org.escafe.buri.example.dto;

import java.io.Serializable;
import java.util.List;

import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.util.tiger.CollectionsUtil;

@SuppressWarnings("serial")
@Component(instance = InstanceType.SESSION)
public class PurchaseApplicationViewDto implements Serializable {

    public List<BeanMap> purchaseApplicationItems = CollectionsUtil
        .newArrayList();

    public void initialize() {
        purchaseApplicationItems = CollectionsUtil.newArrayList();
    }

}
